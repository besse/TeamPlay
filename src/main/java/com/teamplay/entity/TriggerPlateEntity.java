package com.teamplay.entity;

import java.util.ArrayList;
import java.util.List;

public class TriggerPlateEntity implements Entity {

    private boolean active;
    private List<Entity> connectedObjects;

    public TriggerPlateEntity(){
        connectedObjects = new ArrayList<Entity>();
    }

    @Override
    public void update() {
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
