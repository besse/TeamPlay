package com.teamplay.data;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.teamplay.entity.*;
import com.teamplay.navigation.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-04
 * Time: 3:08 PM
 */
public class Level {

    private static final Logger LOGGER = LoggerFactory.getLogger(Level.class);

    private static final String LAYER_WALL = "walls";
    private static final int LAYER_FLOOR2 = 1;
    private static final int LAYER_FLOOR = 0;

    private List<DrawableEntity> drawableLevelObjects;
    List<DoorEntity> doors = new ArrayList<DoorEntity>();

    private List<StartingPosition> startingPositions = new ArrayList<StartingPosition>();

    private OrthogonalTiledMapRenderer tileMapRenderer;

    private TiledMap tileMap;
    private int levelHeight;
    private int levelWidth;
    private int tileWidth;
    private int tileHeight;

    public Level(String name) {
        load(name);
    }

    public void load(String name) {
        tileMap = new TmxMapLoader().load(name + ".tmx");
        tileMapRenderer = new OrthogonalTiledMapRenderer(tileMap);
        levelHeight = tileMap.getProperties().get("height", Integer.class);
        levelWidth = tileMap.getProperties().get("width", Integer.class);
        tileWidth = tileMap.getProperties().get("tilewidth", Integer.class);
        tileHeight = tileMap.getProperties().get("tileheight", Integer.class);

        loadLevelObjects();
    }

    private void loadLevelObjects() {
        drawableLevelObjects = new ArrayList<DrawableEntity>();
        MapLayer objectLayer = tileMap.getLayers().get("objects");

        for (MapObject mapObject : objectLayer.getObjects()) {
            int x = mapObject.getProperties().get("x", Integer.class);
            int y = mapObject.getProperties().get("y", Integer.class);

            switch (typeOf(mapObject)){
                case DOOR:
                    DoorEntity door = new DoorEntity(x, y, directionOf(mapObject));
                    drawableLevelObjects.add(door);
                    doors.add(door);
                    break;
                case BUTTON:
                    ButtonEntity button = new ButtonEntity(x, y, directionOf(mapObject));
                    for (DoorEntity doorEntity : doors){
                        button.addConnectedEntity(doorEntity);
                    }
                    drawableLevelObjects.add(button);
                    break;
                case START:
                    StartingPosition startingPosition = new StartingPosition(x,y);
                    startingPositions.add(startingPosition);
                    break;
                default:
                    String errorString = "Unknown entity type "+typeOf(mapObject);
                    LOGGER.error(errorString);
                    throw new IllegalArgumentException(errorString);
            }
        }
    }

    private EntityType typeOf(MapObject mapObject) {
        return EntityType.fromString(mapObject.getProperties().get("type", String.class));
    }

    public Direction directionOf(MapObject mapObject){
        return Direction.fromString(mapObject.getProperties().get("direction", String.class));
    }

    public OrthogonalTiledMapRenderer getTileMapRenderer() {
        return tileMapRenderer;
    }

    public List<DrawableEntity> getDrawableLevelObjects() {
        return drawableLevelObjects;
    }

    public int getLevelHeight() {
        return levelHeight;
    }

    public int getLevelWidth() {
        return levelWidth;
    }

    public float getPixelWidth() {
        return levelWidth * tileWidth;
    }

    public int getPixelHeight() {
        return levelHeight * tileHeight;
    }

    public boolean isTileWalkable(int x, int y) {
        TiledMapTileLayer walls = (TiledMapTileLayer) tileMap.getLayers().get(LAYER_WALL);
        if (walls != null && walls.getCell(x, y) != null) {
            TiledMapTile t = walls.getCell(x, y).getTile();
            if (t != null) {
                String walkable = t.getProperties().get("walkable", String.class);
                return walkable == null || walkable.equalsIgnoreCase("true");
            }
        }
        return true;
    }

    public Rectangle getBoundsFrom(int x, int y) {
        return new Rectangle(x * 32, y * 32, 32, 32);
    }

    public List<Rectangle> getCollidableRectangles(float startX, float startY, float endX, float endY) {
        List<Rectangle> rectangles = new ArrayList<Rectangle>();
        int xMin = (int) Math.floor(startX / 32);
        int yMin = (int) Math.floor(startY / 32);
        int xMax = (int) Math.ceil(endX / 32);
        int yMax = (int) Math.ceil(endY / 32);

        for (int j = yMin; j < yMax; j++) {
            for (int i = xMin; i < xMax; i++) {
                if (!isTileWalkable(i, j) || hasClosedDoor(i,j)) {
                    rectangles.add(getBoundsFrom(i, j));
                }
            }
        }

        return rectangles;
    }

    private boolean hasClosedDoor(int i, int j) {
        for (DoorEntity door : doors){
            LOGGER.trace("Looking for closed door @ " + i + "," + j);
            if (toXTile(door.getX()) == i && toYTile(door.getY()) == j){
                if (door.isClosed()){
                    LOGGER.trace("Found closed door @ "+i+","+j);
                    return true;
                } else {
                    LOGGER.trace("Found open door @ "+i+","+j);
                    return false;
                }
            } else {
                LOGGER.trace("Found no door @ "+i+","+j);
            }
        }
        return false;
    }

    private int toYTile(float y) {
        return (int)Math.floor(y/tileHeight);
    }

    private int toXTile(float x) {
        return (int)Math.floor(x/tileWidth);
    }

    public void trigger(float xPos, float yPos) {
        for (Entity entity : allEntities()){
            if (toXTile(entity.getX()) == toXTile(xPos) && toYTile(entity.getY()) == toYTile(yPos)){
                entity.trigger();
            }
        }
    }

    private List<Entity> allEntities() {
        List<Entity> entites = new ArrayList<Entity>();
        entites.addAll(drawableLevelObjects);
        entites.addAll(startingPositions);
        return entites;
    }

    private enum EntityType{
        START,
        DOOR,
        BUTTON;

        public static EntityType fromString(String typeAsString) {
            for (EntityType type : values()){
                if (type.name().compareToIgnoreCase(typeAsString) == 0){
                    return type;
                }
            }
            throw new IllegalArgumentException(typeAsString + " is not a valid entity type");
        }
    }

    public StartingPosition getStartingPosition(){
        for (StartingPosition startingPosition : startingPositions){
            return startingPosition;
        }
        throw new IllegalStateException("No starting position defined in map!");
    }
}
