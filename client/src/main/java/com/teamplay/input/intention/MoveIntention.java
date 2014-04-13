package com.teamplay.input.intention;

import com.teamplay.data.Level;
import com.teamplay.menu.Menu;
import com.teamplay.navigation.Direction;
import com.teamplay.player.Player;

public class MoveIntention implements Intention {


    public static final Intention NORTH = new MoveIntention(Direction.NORTH);
    public static final Intention EAST = new MoveIntention(Direction.EAST);
    public static final Intention SOUTH = new MoveIntention(Direction.SOUTH);
    public static final Intention WEST = new MoveIntention(Direction.WEST);

    private final Direction direction;

    private MoveIntention(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void invoke(Level level, Player player) {
        float acceleration = player.getAcceleration();
        switch (direction){
            case NORTH:
                player.accelerateY(acceleration);
                break;
            case EAST:
                player.accelerateX(acceleration*-1);
                break;
            case SOUTH:
                player.accelerateY(acceleration * -1);
                break;
            case WEST:
                player.accelerateX(acceleration);
                break;
        }
    }

    @Override
    public void invoke(Menu menu) {
        switch (direction){
            case NORTH:
                menu.moveUp();
                break;
            case SOUTH:
                menu.moveDown();
                break;
        }
    }

    public String toString(){
        return "Intend to move "+direction.name();
    }
}
