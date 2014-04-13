package com.teamplay.player;

/**
 * TODO: An idea - extract some features into different creatures for an interface later on maybe?
 *
 */
public interface Creature {

    public void decreaseHealth(float amount);

    public void increaseHealth(float amount);

    public float getHealth();

    public boolean isAlive();
   

}
