package com.teamplay.input;

import com.teamplay.input.intention.Intention;
import com.teamplay.input.intention.MoveIntention;
import com.teamplay.input.intention.StopIntention;
import com.teamplay.navigation.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public abstract class PositionBasedInputService extends InputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionBasedInputService.class);

    private Integer requestedX = null;
    private Integer requestedY = null;

    @Override
    public Set<Intention> getPlayerIntention(Position playerPosition) {
        Set<Intention> intentions = new HashSet<Intention>();

        if (requestedX != null && requestedY != null){

            int x = toMapCoordinateX(requestedX, requestedY, 0);
            int y = toMapCoordinateY(requestedX, requestedY, 0);

            if (x < playerPosition.x){
                intentions.add(MoveIntention.EAST);
            } else if (x > playerPosition.x){
                intentions.add(MoveIntention.WEST);
            } else {
                intentions.add(StopIntention.EAST);
            }

            if (y < playerPosition.y){
                intentions.add(MoveIntention.SOUTH);
            } else if (y > playerPosition.y){
                intentions.add(MoveIntention.NORTH);
            } else {
                intentions.add(StopIntention.NORTH);
            }
        }
        return intentions;
    }

    abstract int toMapCoordinateX(int screenX, int screenY, int screenZ);
    abstract int toMapCoordinateY(int screenX, int screenY, int screenZ);

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        requestedX = screenX;
        requestedY = screenY;
        return true;
    }
}
