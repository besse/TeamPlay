package com.teamplay.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * ResourceManager handles all resources such as music, sounds and graphics
 * <p/>
 * <p/>
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-10
 * Time: 10:41 AM
 */
public class ResourceManager {

    private Map<String, Texture> textureMap;


    public ResourceManager() {
        textureMap = new HashMap<String, Texture>();

        loadResources();
    }

    private void loadResources() {
        textureMap.put("button", new Texture(Gdx.files.internal("button.png")));
        textureMap.put("door", new Texture(Gdx.files.internal("door.png")));
        textureMap.put("manwalking", new Texture(Gdx.files.internal("manwalking.png")));
    }

    public Texture getTexture(String key) {
        return textureMap.get(key);
    }

}
