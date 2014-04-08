package com.teamplay.data;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-04
 * Time: 3:08 PM
 */
public class Level {

    private static final int LAYER_WALL = 2;
    private static final int LAYER_FLOOR2 = 1;
    private static final int LAYER_FLOOR = 0;

    private OrthogonalTiledMapRenderer tileMapRenderer;

    private TiledMap tileMap;
    private int levelHeight;
    private int levelWidth;
    private int tileWidth;
    private int tileHeight;

    public Level(String name){
        load(name);
    }

    public void load(String name){
        tileMap = new TmxMapLoader().load(name + ".tmx");
        tileMapRenderer = new OrthogonalTiledMapRenderer(tileMap);
        levelHeight = (Integer) tileMap.getProperties().get("height");
        levelWidth = (Integer) tileMap.getProperties().get("height");
        tileWidth = (Integer) tileMap.getProperties().get("tilewidth");
        tileHeight = (Integer) tileMap.getProperties().get("tileheight");

        tileMap.getProperties().get("width");
        tileMap.getLayers().get("floor");
    }

    public OrthogonalTiledMapRenderer getTileMapRenderer(){
        return tileMapRenderer;
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

    public int getPixelHeight(){
        return levelHeight * tileHeight;
    }

    public boolean isTileWalkable(int x, int y){
        TiledMapTileLayer walls = (TiledMapTileLayer) tileMap.getLayers().get(LAYER_WALL);
        if(walls != null && walls.getCell(x,y) != null){
            TiledMapTile t = walls.getCell(x,y).getTile();
            if(t != null){
                String walkable = t.getProperties().get("walkable", String.class);
                return walkable == null || ((String) walkable).equalsIgnoreCase("true");
            }
        }
        return true;
    }
}
