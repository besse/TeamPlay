package com.teamplay.input.intention;

import com.teamplay.data.Level;
import com.teamplay.player.Player;

public class InteractIntention implements Intention {
    @Override
    public void invoke(Level level, Player player) {
        level.trigger(player);
    }
    @Override
    public String toString(){
        return "Intending to interact";
    }
}
