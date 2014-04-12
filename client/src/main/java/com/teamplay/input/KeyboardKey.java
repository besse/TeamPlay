package com.teamplay.input;

import com.badlogic.gdx.Input;
import static com.badlogic.gdx.Input.Keys;


public enum KeyboardKey {
    UP(Keys.UP),
    LEFT(Keys.LEFT),
    DOWN(Keys.DOWN),
    RIGHT(Keys.RIGHT),
    SPACE(Keys.SPACE),
    ESCAPE(Keys.ESCAPE),
    SHIFT(Keys.SHIFT_LEFT),
    ENTER(Keys.ENTER);

    private final int keycode;

    private KeyboardKey(int keycode) {
        this.keycode = keycode;
    }

    public static KeyboardKey fromKeycode(int keycode) {
        for (KeyboardKey keyboardKey : values()){
            if (keyboardKey.keycode == keycode){
                return keyboardKey;
            }
        }
        throw new IllegalArgumentException("The keycode "+ keycode+" is not mapped!");
    }

    public static boolean isValid(int keycode) {
        for (KeyboardKey keyboardKey : values()){
            if (keyboardKey.keycode == keycode){
                return true;
            }
        }
        return false;
    }
}
