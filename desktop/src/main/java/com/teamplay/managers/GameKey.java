package com.teamplay.managers;

/**
 * Created with IntelliJ IDEA.
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public enum  GameKey {
    UP(19),
    LEFT(21),
    DOWN(20),
    RIGHT(22),
    SPACE(62),
    ESCAPE(131),
    SHIFT(59),
    ENTER(66);

    private final int keycode;

    private GameKey(int keycode) {
        this.keycode = keycode;
    }

    public static GameKey fromKeycode(int keycode) {
        for (GameKey gameKey : values()){
            if (gameKey.keycode == keycode){
                return gameKey;
            }
        }
        throw new IllegalArgumentException("The keycode "+ keycode+" is not mapped!");
    }

    public static boolean isValid(int keycode) {
        for (GameKey gameKey : values()){
            if (gameKey.keycode == keycode){
                return true;
            }
        }
        return false;
    }
}
