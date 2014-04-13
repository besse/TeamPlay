package com.teamplay.input.intention;

import com.teamplay.data.Level;
import com.teamplay.menu.Menu;
import com.teamplay.navigation.Direction;
import com.teamplay.player.Player;

public class StopIntention implements Intention{

    private final Direction direction;
    public static final StopIntention NORTH = new StopIntention(Direction.NORTH);
    public static final StopIntention EAST = new StopIntention(Direction.EAST);
    public static final StopIntention SOUTH = new StopIntention(Direction.SOUTH);
    public static final StopIntention WEST = new StopIntention(Direction.WEST);

    private StopIntention(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void invoke(Level level, Player player) {
        switch (direction){
            case NORTH:
            case SOUTH:
                player.stopY();
                break;
            case WEST:
            case EAST:
                player.stopX();
                break;
        }
    }

    @Override
    public void invoke(Menu menu) {

    }

    @Override
    public String toString(){
        return "Intending to stop "+direction.name()+"bound movement";
    }
}
