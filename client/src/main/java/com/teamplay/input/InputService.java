package com.teamplay.input;

import com.badlogic.gdx.InputAdapter;
import com.teamplay.input.intention.Intention;
import com.teamplay.navigation.Position;

import java.util.Set;

public abstract class InputService extends InputAdapter{
    public abstract Set<Intention> getPlayerIntention(Position playerPosition);
}
