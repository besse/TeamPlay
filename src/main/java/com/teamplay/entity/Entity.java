package com.teamplay.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created with IntelliJ IDEA.
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 8:32 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Entity {


    public void update(float dt);

    public void trigger();


    public Sprite getSprite();

    public Texture getCurrentFrame();

    float getY();

    float getX();
}
