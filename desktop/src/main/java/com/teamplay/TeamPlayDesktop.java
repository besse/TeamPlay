package com.teamplay;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeamPlayDesktop {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamPlayDesktop.class);

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Drop";
        cfg.width = 640;
        cfg.height = 480;
        LOGGER.debug("Starting app: "+cfg.title+" width:"+cfg.width+" height:"+cfg.height);
        new LwjglApplication(new Game(), cfg);
    }
}

