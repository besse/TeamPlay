package com.teamplay.input;

import com.badlogic.gdx.InputAdapter;
import com.teamplay.input.intention.CancelIntention;
import com.teamplay.input.intention.Intention;
import com.teamplay.menu.Menu;
import com.teamplay.navigation.Position;

import java.util.Set;

public abstract class InputService extends InputAdapter {
    protected CancelIntention cancelIntention;

    public abstract Set<Intention> getPlayerIntention(Position playerPosition);
    public abstract Set<Intention> getMenuIntention(Menu menu);

    public void setCancelIntention(CancelIntention cancelIntention) {
        this.cancelIntention = cancelIntention;
    }
}
