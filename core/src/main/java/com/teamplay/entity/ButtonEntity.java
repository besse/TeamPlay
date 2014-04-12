package com.teamplay.entity;

import com.badlogic.gdx.graphics.Texture;
import com.teamplay.navigation.Direction;

import java.util.HashSet;
import java.util.Set;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-10
 * Time: 10:21 AM
 */
public class ButtonEntity extends DrawableEntity {

    private static final String TEXTURE_FILE_NAME = "button.png";

    private final Set<Entity> connectedEntities = new HashSet<Entity>();

    public ButtonEntity(int x, int y, Direction direction) {
        super(x, y, direction, TEXTURE_FILE_NAME);
    }

    @Override
    public void update(float dt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void trigger() {
        for (Entity entity : connectedEntities){
            entity.changeState();
        }
    }

    @Override
    public Texture getCurrentFrame() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void changeState() {
        //Do nothing;
    }

    public void addConnectedEntity(Entity connectedEntity){
        connectedEntities.add(connectedEntity);
    }
}
