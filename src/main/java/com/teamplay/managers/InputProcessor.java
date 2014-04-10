package com.teamplay.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class InputProcessor extends InputAdapter {

    @Override
    public boolean keyDown(int k) {
        if(k == Input.Keys.UP){
            GameKeys.setKey(GameKeys.UP, true);
        }
        if(k == Input.Keys.LEFT){
            GameKeys.setKey(GameKeys.LEFT, true);
        }
        if(k == Input.Keys.DOWN){
            GameKeys.setKey(GameKeys.DOWN, true);
        }
        if(k == Input.Keys.RIGHT){
            GameKeys.setKey(GameKeys.RIGHT, true);
        }
        if(k == Input.Keys.SHIFT_LEFT || k == Input.Keys.SHIFT_RIGHT){
            GameKeys.setKey(GameKeys.SHIFT, true);
        }
        if(k == Input.Keys.ESCAPE){
            GameKeys.setKey(GameKeys.ESCAPE, true);
        }
        if(k == Input.Keys.ENTER){
            GameKeys.setKey(GameKeys.ENTER, true);
        }
        if(k == Input.Keys.SPACE){
            GameKeys.setKey(GameKeys.SPACE, true);
        }


        return true;
    }

    @Override
    public boolean keyUp(int k) {
        if(k == Input.Keys.UP){
            GameKeys.setKey(GameKeys.UP, false);
        }
        if(k == Input.Keys.LEFT){
            GameKeys.setKey(GameKeys.LEFT, false);
        }
        if(k == Input.Keys.DOWN){
            GameKeys.setKey(GameKeys.DOWN, false);
        }
        if(k == Input.Keys.RIGHT){
            GameKeys.setKey(GameKeys.RIGHT, false);
        }
        if(k == Input.Keys.SHIFT_LEFT || k == Input.Keys.SHIFT_RIGHT){
            GameKeys.setKey(GameKeys.SHIFT, false);
        }
        if(k == Input.Keys.ESCAPE){
            GameKeys.setKey(GameKeys.ESCAPE, false);
        }
        if(k == Input.Keys.ENTER){
            GameKeys.setKey(GameKeys.ENTER, false);
        }
        if(k == Input.Keys.SPACE){
            GameKeys.setKey(GameKeys.SPACE, false);
        }

        return true;
    }


}
