package com.teamplay.managers;

import com.badlogic.gdx.InputAdapter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class InputProcessor extends InputAdapter {

    private static final Set<GameKey> downKeys = new HashSet<GameKey>();
    private static final Set<GameKey> pressedKeys= new HashSet<GameKey>();
    private static final Set<GameKey> releasedKeys = new HashSet<GameKey>();


    public void keyDown(GameKey key){
        downKeys.add(key);
        pressedKeys.add(key);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (isValidKey(keycode)){
            GameKey key = GameKey.fromKeycode(keycode);
            keyDown(key);
        }
        return true;
    }

    private boolean isValidKey(int keycode) {
        return GameKey.isValid(keycode);
    }

    public void keyUp(GameKey key){
        downKeys.remove(key);
        releasedKeys.add(key);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (isValidKey(keycode)){
            GameKey key = GameKey.fromKeycode(keycode);
            keyUp(key);
        }
        return true;
    }

    public static boolean isDown(GameKey key) {
        return downKeys.contains(key);
    }
    public static boolean isReleased(GameKey key) {
        return releasedKeys.contains(key);
    }
    public static boolean isPressed(GameKey key) {
        return pressedKeys.contains(key);
    }


    public static void refresh(){
        pressedKeys.clear();
        releasedKeys.clear();
    }
}
