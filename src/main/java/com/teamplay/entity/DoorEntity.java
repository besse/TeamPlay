package com.teamplay.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-09
 * Time: 7:35 PM
 */
public class DoorEntity implements Entity {

    private final float xPos;
    private final float yPos;
    private boolean open;
    private float timeToOpen = 5.0f;
    private float elapsedOpeningTime = 0.0f;
    private String direction;

    Texture texture;
    Sprite sprite;


    public DoorEntity(int x, int y, String direction) {
        this.xPos = x;
        this.yPos = y;
        this.direction = direction;

        texture = new Texture(Gdx.files.internal("door.png"));
        sprite = new Sprite(texture);
        sprite.setPosition(xPos, yPos);
        if (direction.equals("west")) {

            sprite.setRotation(90.0f);
        }

    }

    @Override
    public void update(float dt) {
        elapsedOpeningTime += dt;
    }

    @Override
    public void trigger() {
        open = !open;
    }

    @Override
    public Texture getCurrentFrame() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Sprite getSprite() {
        return sprite;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float getX() {
        return xPos;
    }

    @Override
    public float getY() {
        return yPos;
    }
}
