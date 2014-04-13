package com.teamplay.input.intention;

import com.teamplay.Game;
import com.teamplay.data.Level;
import com.teamplay.managers.GameStateManager;
import com.teamplay.menu.Menu;
import com.teamplay.menu.MenuOption;
import com.teamplay.player.Player;

public class CancelIntention implements Intention {
    @Override
    public void invoke(Level level, Player player) {

    }

    @Override
    public void invoke(Menu menu) {
        menu.setActiveMenuOption(menu.getActiveOption().getParent());
    }

    @Override
    public String toString(){
        return "Intending to cancel";
    }
}
