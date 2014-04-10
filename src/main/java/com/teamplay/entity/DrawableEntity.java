package com.teamplay.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamplay.navigation.Direction;

public abstract class DrawableEntity implements Entity{


    protected final float xPos;
    protected final float yPos;
    protected final Direction direction;

    protected final Texture texture;
    protected final Sprite sprite;

    public DrawableEntity(int x, int y, Direction direction, String textureFileName) {
        this.xPos = x;
        this.yPos = y;
        this.direction = direction;
        texture = new Texture(Gdx.files.internal(textureFileName));

        sprite = new Sprite(texture);
        sprite.setPosition(xPos, yPos);
        sprite.setRotation(direction.rotationRelativeWorldCoordinates);
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

