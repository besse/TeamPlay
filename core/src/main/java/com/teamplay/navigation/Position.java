package com.teamplay.navigation;

public class Position {
    public final int x;
    public final int y;
    public Position(int x,int y){
        this.x = x;
        this.y = y;
    }

    public static Position fromPlayerCoordinates(int playerX, int playerY){

        int tileX = (int)Math.floor(playerX / 32);
        int tileY = (int)Math.floor(playerY / 32);

        return new Position(tileX, tileY);
    }


}
