package com.teamplay.input.intention;

import com.teamplay.data.Level;
import com.teamplay.player.Player;

public interface Intention {
    void invoke(Level level, Player player);
}
