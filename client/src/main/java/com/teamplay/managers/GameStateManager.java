package com.teamplay.managers;

import com.teamplay.Game;
import com.teamplay.state.PlayState;
import com.teamplay.state.GameState;
import com.teamplay.state.MenuState;

public class GameStateManager {

    private Game game;

    private GameState currentGameState;

    public GameStateManager(Game game) {
        this.game = game;
        setState(State.PLAY);
    }

    public void setState(State state) {
        if (currentGameState != null) {
            currentGameState.dispose();
        }
        switch (state){
            case MENU:
                currentGameState = new MenuState(this);
                break;
            case PLAY:
                currentGameState = new PlayState(this);
                break;
            default:
                throw new IllegalStateException("The state "+state.name() +" is not a valid gamestate");
        }
    }

    public void update(float dt) {
        currentGameState.update(dt);
    }

    public void draw() {
        currentGameState.render();
    }


    public Game getGame() {
        return game;
    }

    private enum State{
        MENU,
        PLAY
    }
}
