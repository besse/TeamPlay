package com.teamplay.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-10
 * Time: 10:21 AM
 */
public class ButtonEntity implements Entity {

    private float xPos;
    private float yPos;

    Texture texture;
    Sprite sprite;

    public ButtonEntity(float x, float y) {
        this.xPos = x;
        this.yPos = y;
        texture = new Texture("button.png");

        sprite = new Sprite(texture);
        sprite.setPosition(xPos, yPos);
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
    public Sprite getSprite() {
        return sprite;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Texture getCurrentFrame() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float getY() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float getX() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
