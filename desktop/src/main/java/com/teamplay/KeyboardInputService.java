package com.teamplay;

import com.badlogic.gdx.InputAdapter;
import com.teamplay.input.InputService;
import com.teamplay.input.intention.*;
import com.teamplay.managers.GameKey;
import com.teamplay.navigation.Direction;
import com.teamplay.navigation.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeyboardInputService extends InputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyboardInputService.class);

    private static final Set<GameKey> downKeys = new HashSet<GameKey>();
    private static final Set<GameKey> pressedKeys= new HashSet<GameKey>();
    private static final Set<GameKey> releasedKeys = new HashSet<GameKey>();


    public void keyDown(GameKey key){
        downKeys.add(key);
        pressedKeys.add(key);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (isValidKey(keycode)){
            GameKey key = GameKey.fromKeycode(keycode);
            keyDown(key);
        }
        return true;
    }

    private boolean isValidKey(int keycode) {
        return GameKey.isValid(keycode);
    }

    public void keyUp(GameKey key){
        downKeys.remove(key);
        releasedKeys.add(key);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (isValidKey(keycode)){
            GameKey key = GameKey.fromKeycode(keycode);
            keyUp(key);
        }
        return true;
    }

    @Override
    public Set<Intention> getPlayerIntention(Position playerPosition) {
        Set<Intention> intentions = new HashSet<Intention>();
        for (GameKey keyPressed : pressedKeys){
            switch (keyPressed){
                case SPACE:
                    intentions.add(new InteractIntention());
                    break;
                case ESCAPE:
                    intentions.add(new CancelIntention());
                    break;
            }
        }
        for (GameKey keyDown: downKeys){
            switch (keyDown){
                case UP:
                    intentions.add(MoveIntention.NORTH);
                    break;
                case DOWN:
                    intentions.add(MoveIntention.SOUTH);
                    break;
                case LEFT:
                    intentions.add(MoveIntention.EAST);
                    break;
                case RIGHT:
                    intentions.add(MoveIntention.WEST);
                    break;
            }
        }

        for (GameKey keyReleased : releasedKeys){
            switch (keyReleased){
                case UP:
                    intentions.add(StopIntention.NORTH);
                    break;
                case DOWN:
                    intentions.add(StopIntention.SOUTH);
                    break;
                case LEFT:
                    intentions.add(StopIntention.EAST);
                    break;
                case RIGHT:
                    intentions.add(StopIntention.WEST);
                    break;
            }
        }
        pressedKeys.clear();
        releasedKeys.clear();

        for (Intention intention : intentions){
            LOGGER.trace(intention.toString());
        }


        return intentions;
    }
}
