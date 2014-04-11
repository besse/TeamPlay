package com.teamplay.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamplay.navigation.Direction;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-10
 * Time: 10:21 AM
 */
public class ButtonEntity extends DrawableEntity {

    private static final String TEXTURE_FILE_NAME = "button.png";

    public ButtonEntity(int x, int y, Direction direction) {
        super(x, y, direction, TEXTURE_FILE_NAME);
    }

    @Override
    public void update(float dt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void trigger() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Texture getCurrentFrame() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
