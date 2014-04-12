package com.teamplay.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class InvisibleEntity implements Entity {

    private final String name;

    private float x;
    private float y;

    InvisibleEntity(String name, float x, float y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    @Override
    public final Sprite getSprite() {
        return null;
    }

    @Override
    public final Texture getCurrentFrame() {
        return null;
    }

    @Override
    public float getY() {
         return y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public Rectangle getBoundingBox() {
        //Todo: Replace 32 with tile width and height
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public String getName(){
        return name;
    }

}
