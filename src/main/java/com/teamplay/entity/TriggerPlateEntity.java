package com.teamplay.entity;

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
    public void update() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void trigger() {
        active = !active;
        if(isActive()){
           updateConnectedObjects();
        }
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
