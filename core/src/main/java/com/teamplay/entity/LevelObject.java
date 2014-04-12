package com.teamplay.entity;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-10
 * Time: 11:17 AM
 */
public abstract class LevelObject {

    protected float xPos;
    protected float yPos;

    protected Rectangle boundingBox;
    protected boolean collidable;

    public LevelObject(float x, float y) {
        xPos = x;
        yPos = y;
        boundingBox = new Rectangle();
    }

    protected void update(float dt) {

    }

    public void setBoundingBox(float x, float y, float w, float h) {
        boundingBox.set(x, y, w, h);
    }

    public void setX(float x) {
        this.xPos = x;
    }

    public void setY(float y) {
        this.yPos = y;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }
}
