package com.teamplay.data;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-04
 * Time: 3:08 PM
 */
public class Level {

    private OrthogonalTiledMapRenderer tileMapRenderer;

    private TiledMap tileMap;
    private int levelHeight;
    private int levelWidth;

    public Level(String name){
        load(name);
    }

    public void load(String name){
        tileMap = new TmxMapLoader().load(name + ".tmx");
        tileMapRenderer = new OrthogonalTiledMapRenderer(tileMap);
        levelHeight = (Integer) tileMap.getProperties().get("height");
        levelWidth = (Integer) tileMap.getProperties().get("height");
        tileMap.getProperties().get("width");
        tileMap.getLayers().get("floor");
    }

    public OrthogonalTiledMapRenderer getTileMapRenderer(){
        return tileMapRenderer;
    }


    public int getLevelHeight() {
        return levelHeight;
    }

    public void setLevelHeight(int levelHeight) {
        this.levelHeight = levelHeight;
    }

    public int getLevelWidth() {
        return levelWidth;
    }

    public void setLevelWidth(int levelWidth) {
        this.levelWidth = levelWidth;
    }
}
