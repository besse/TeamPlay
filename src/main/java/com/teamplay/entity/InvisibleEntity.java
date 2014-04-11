package com.teamplay.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class InvisibleEntity implements Entity {

    float x;
    float y;

    InvisibleEntity(float x, float y){
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
}
