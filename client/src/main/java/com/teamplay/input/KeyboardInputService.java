package com.teamplay.input;

import com.teamplay.input.intention.*;
import com.teamplay.navigation.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class KeyboardInputService extends InputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyboardInputService.class);

    private static final Set<KeyboardKey> downKeys = new HashSet<KeyboardKey>();
    private static final Set<KeyboardKey> pressedKeys= new HashSet<KeyboardKey>();
    private static final Set<KeyboardKey> releasedKeys = new HashSet<KeyboardKey>();

    @Override
    public boolean keyDown(int keycode) {
        if (isValidKey(keycode)){
            KeyboardKey key = KeyboardKey.fromKeycode(keycode);
            keyDown(key);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (isValidKey(keycode)){
            KeyboardKey key = KeyboardKey.fromKeycode(keycode);
            keyUp(key);
        }
        return true;
    }

    public void keyDown(KeyboardKey key){
        downKeys.add(key);
        pressedKeys.add(key);
    }

    public void keyUp(KeyboardKey key){
        downKeys.remove(key);
        releasedKeys.add(key);
    }



    private boolean isValidKey(int keycode) {
        return KeyboardKey.isValid(keycode);
    }

    @Override
    public Set<Intention> getPlayerIntention(Position playerPosition) {
        Set<Intention> intentions = new HashSet<Intention>();
        for (KeyboardKey keyPressed : pressedKeys){
            switch (keyPressed){
                case SPACE:
                    intentions.add(new InteractIntention());
                    break;
                case ESCAPE:
                    intentions.add(new CancelIntention());
                    break;
            }
        }
        for (KeyboardKey keyDown: downKeys){
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

        for (KeyboardKey keyReleased : releasedKeys){
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
