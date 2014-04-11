package com.teamplay.data;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.teamplay.entity.ButtonEntity;
import com.teamplay.entity.DoorEntity;
import com.teamplay.entity.Entity;
import com.teamplay.entity.StartingPosition;
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

    private List<Entity> levelObjects;

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
        levelHeight = (Integer) tileMap.getProperties().get("height");
        levelWidth = (Integer) tileMap.getProperties().get("width");
        tileWidth = (Integer) tileMap.getProperties().get("tilewidth");
        tileHeight = (Integer) tileMap.getProperties().get("tileheight");

        tileMap.getProperties().get("width");
        tileMap.getLayers().get("floor");

        loadLevelObjects();
    }

    private void loadLevelObjects() {
        levelObjects = new ArrayList<Entity>();
        MapLayer objectLayer = tileMap.getLayers().get("objects");

        /*

         3 typer av objekt.

         button, door, start
          */

        for (MapObject mapObject : objectLayer.getObjects()) {
            int x = (Integer) mapObject.getProperties().get("x");
            int y = (Integer) mapObject.getProperties().get("y");

            Entity entity;
            switch (typeOf(mapObject)){
                case DOOR:
                    entity = new DoorEntity(x, y, directionOf(mapObject));
                    break;
                case BUTTON:
                    entity = new ButtonEntity(x,y, directionOf(mapObject));
                    break;
                case START:
                    StartingPosition startingPosition = new StartingPosition(x,y);
                    startingPositions.add(startingPosition);
                    entity = startingPosition;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown entity type");
            }
            levelObjects.add(entity);
        }
    }

    private EntityType typeOf(MapObject mapObject) {
        return EntityType.fromString((String)mapObject.getProperties().get("type"));
    }

    public Direction directionOf(MapObject mapObject){
        return Direction.fromString((String) mapObject.getProperties().get("direction"));
    }

    public OrthogonalTiledMapRenderer getTileMapRenderer() {
        return tileMapRenderer;
    }

    public List<Entity> getLevelObjects() {
        return levelObjects;
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
        return new Rectangle(x * 32, y * 32, 32, 32);//To change body of created methods use File | Settings | File Templates.
    }

    public List<Rectangle> getCollidableRectangles(float startX, float startY, float endX, float endY) {
        List<Rectangle> rectangles = new ArrayList<Rectangle>();
        int xMin = (int) Math.floor(startX / 32);
        int yMin = (int) Math.floor(startY / 32);
        int xMax = (int) Math.ceil(endX / 32);
        int yMax = (int) Math.ceil(endY / 32);

        for (int j = yMin; j < yMax; j++) {
            for (int i = xMin; i < xMax; i++) {
                if (!isTileWalkable(i, j)) {
                    rectangles.add(getBoundsFrom(i, j));
                }
            }
        }

        return rectangles;
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
