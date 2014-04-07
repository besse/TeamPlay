package com.teamplay;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class TeamPlayDesktop {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Drop";
        cfg.width = 640;
        cfg.height = 480;
        new LwjglApplication(new Game(), cfg);
    }
}

