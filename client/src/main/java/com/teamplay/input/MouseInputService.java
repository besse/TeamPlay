package com.teamplay.input;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MouseInputService extends PositionBasedInputService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MouseInputService.class);

    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT = 32;

    private final Camera camera;

    public MouseInputService(Camera camera) {
        this.camera = camera;
    }

    int oldX,oldY;

    @Override
    int toMapCoordinateX(int screenX, int screenY, int screenZ) {
        Vector3 coord = new Vector3(screenX, screenY, screenZ);
        camera.unproject(coord);
        int tileX = (int) Math.floor((coord.x-(TILE_HEIGHT/2))/TILE_HEIGHT);

        if (LOGGER.isDebugEnabled() && (oldX != screenX || oldY != screenY)){
            int tileY = (int) Math.floor((coord.y-(TILE_HEIGHT/2))/TILE_HEIGHT);
            LOGGER.debug("Screen: " + screenX + "," + screenY + " world:" + coord.x + "," + coord.y+ " Tile:"+tileX+","+tileY);
            oldX = screenX;
            oldY = screenY;
        }
        return tileX;
    }

    @Override
    int toMapCoordinateY(int screenX, int screenY, int screenZ) {
        Vector3 coord = new Vector3(screenX, screenY, screenZ);
        camera.unproject(coord);
        return (int) Math.floor((coord.y-(TILE_HEIGHT/2))/TILE_HEIGHT);
    }
}
