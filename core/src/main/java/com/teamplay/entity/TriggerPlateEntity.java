package com.teamplay.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 8:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class TriggerPlateEntity implements Entity {

    private boolean active;
    private List<Entity> connectedObjects;

    public TriggerPlateEntity(){
        connectedObjects = new ArrayList<Entity>();
    }

    @Override
    public void update(float dt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void trigger() {
        active = !active;
        if(isActive()){
           updateConnectedObjects();
        }
    }

    @Override
    public Sprite getSprite() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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

    @Override
    public void changeState() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Rectangle getBoundingBox() {
        //Todo: Replace 32 with tile width and height
        return new Rectangle(0, 0, 32, 32);
    }

    public boolean isActive() {
        return active;
    }

    public void updateConnectedObjects(){
        for (Entity connectedObject : connectedObjects) {
            connectedObject.trigger();
        }
    }
}
