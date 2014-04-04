package com.teamplay.managers;

/**
 * Created with IntelliJ IDEA.
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameKeys {

    private static boolean[] keys;
    private static boolean[] previousKeys;

    static {
        keys = new boolean[8];
        previousKeys = new boolean[8];
    }

    private static int NUM_KEYS = 8;

    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int SPACE = 4;
    public static final int ESCAPE = 5;
    public static final int SHIFT = 6;
    public static final int ENTER = 7;

    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            previousKeys[i] = keys[i];

        }
    }

    public static void setKey(int key, boolean b) {
        keys[key] = b;
    }

    public static boolean isDown(int key) {
        return keys[key];
    }

    public static boolean isReleased(int key) {
        return !keys[key];
    }

    public static boolean isPressed(int key) {
        return keys[key] && !previousKeys[key];
    }


}
