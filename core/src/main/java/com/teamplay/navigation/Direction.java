package com.teamplay.navigation;

public enum Direction {
    NORTH(0f),
    WEST(90f),
    SOUTH(180f),
    EAST(270f);

    public final float rotationRelativeWorldCoordinates;

    private Direction(float rotationRelativeWorldCoordinates) {
        this.rotationRelativeWorldCoordinates = rotationRelativeWorldCoordinates;
    }

    public static Direction fromString(String directionString) {
        for (Direction direction : Direction.values()){
            if (direction.name().compareToIgnoreCase(directionString) == 0){
                return direction;
            }
        }
        throw new IllegalArgumentException(directionString + " is not a valid direction");
    }
}
