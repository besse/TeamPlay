package com.teamplay.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamplay.navigation.Direction;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-09
 * Time: 7:35 PM
 */
public class DoorEntity extends DrawableEntity {

    private boolean closed = true;
    private float timeToOpen = 5.0f;
    private float elapsedOpeningTime = 0.0f;

    private static final String TEXTURE_FILE_NAME = "door.png";

    public DoorEntity(String name, int x, int y, Direction direction) {
        super(name, x, y, direction, TEXTURE_FILE_NAME);

    }

    @Override
    public void update(float dt) {
        elapsedOpeningTime += dt;
    }

    @Override
    public void trigger() {

    }

    @Override
    public Texture getCurrentFrame() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void changeState() {
        closed = !closed;
    }

    @Override
    public Sprite getSprite() {
        if (closed){
            return super.getSprite();
        }
        Texture texture = new Texture(Gdx.files.internal("openDoor.png"));

        Sprite sprite = new Sprite(texture);
        sprite.setPosition(xPos, yPos);
        sprite.setRotation(direction.rotationRelativeWorldCoordinates);
        return sprite;
    }

    public boolean isClosed() {
        return closed;
    }
}



