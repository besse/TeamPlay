package com.teamplay.data;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created for TeamPlay
 * <p/>
 * Level data is created in .tmx format.
 * <p/>
 * User: jonasbirgersson
 * Date: 2014-04-04
 * Time: 3:08 PM
 */
public class Level {

    private OrthogonalTiledMapRenderer tileMapRenderer;

    private TiledMap tileMap;

    public Level(String name) {
        load(name);
    }

    public void load(String name) {
        tileMap = new TmxMapLoader().load(name + ".tmx");
        tileMapRenderer = new OrthogonalTiledMapRenderer(tileMap);

        TiledMapTileLayer tmtl = (TiledMapTileLayer) tileMap.getLayers().get("floor");
    }

    public OrthogonalTiledMapRenderer getTileMapRenderer() {
        return tileMapRenderer;
    }


}
