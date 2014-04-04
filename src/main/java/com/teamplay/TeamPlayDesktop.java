package com.teamplay;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class TeamPlayDesktop {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Drop";
        cfg.width = 640;
        cfg.height = 480;
        new LwjglApplication(new Game(), cfg);
    }
}

