package com.teamplay.entity;

import com.badlogic.gdx.graphics.Texture;
import com.teamplay.navigation.Direction;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-09
 * Time: 7:35 PM
 */
public class DoorEntity extends DrawableEntity {

    private boolean open;
    private float timeToOpen = 5.0f;
    private float elapsedOpeningTime = 0.0f;

    private static final String TEXTURE_FILE_NAME = "door.png";

    public DoorEntity(int x, int y, Direction direction) {
        super(x, y, direction, TEXTURE_FILE_NAME);

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
}



