package com.teamplay.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.teamplay.navigation.Direction;

public abstract class DrawableEntity implements Entity{


    protected final float xPos;
    protected final float yPos;
    protected final Direction direction;

    private final String name;

    private Texture texture;
    private final Sprite sprite;

    public DrawableEntity(String name, int x, int y, Direction direction, String textureFileName) {
        this.name = name;
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

    @Override
    public Rectangle getBoundingBox() {
        //Todo: Replace 32 with tile width and height
        return new Rectangle(xPos, yPos, 32, 32);
    }

    protected void setTextureFileName(String textureFileName){
        texture = new Texture(Gdx.files.internal(textureFileName));
        sprite.setTexture(texture);
    }

    @Override
    public String getName() {
        return name;
    }
}

