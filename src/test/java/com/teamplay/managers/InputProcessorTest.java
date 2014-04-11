package com.teamplay.managers;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static com.teamplay.managers.InputProcessor.isDown;
import static com.teamplay.managers.InputProcessor.isPressed;
import static com.teamplay.managers.InputProcessor.isReleased;
import static org.junit.Assert.assertTrue;

public class InputProcessorTest {

    InputProcessor inputProcessor = new InputProcessor();

    @Test
    public void pressedKeyShouldBePressedAndHeldUntilReleased(){
        GameKey key = GameKey.DOWN;
        assertFalse(isPressed(key));
        assertFalse(isDown(key));
        assertFalse(isReleased(key));

        inputProcessor.keyDown(key);

        assertTrue(isPressed(key));
        assertTrue(isDown(key));
        assertFalse(isReleased(key));

        InputProcessor.refresh();

        assertFalse(isPressed(key));
        assertTrue(isDown(key));
        assertFalse(isReleased(key));

        inputProcessor.keyUp(key);

        assertFalse(isPressed(key));
        assertFalse(isDown(key));
        assertTrue(isReleased(key));

        InputProcessor.refresh();

        assertFalse(isPressed(key));
        assertFalse(isDown(key));
        assertFalse(isReleased(key));
    }
}
