package com.teamplay.managers;

import com.teamplay.Game;
import com.teamplay.state.GameState;
import com.teamplay.state.MenuState;
import com.teamplay.state.PlayState;

/**
 * Created with IntelliJ IDEA.
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 10:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameStateManager {

    private Game game;

    private GameState currentGameState;


    public static final int MENU = 0;
    public static final int PLAY = 102;

    public GameStateManager(Game game) {
        this.game = game;
        setState(PLAY);

    }

    public void setState(int state) {
        if (currentGameState != null) {
            currentGameState.dispose();
        }
        if (state == MENU) {
            currentGameState = new MenuState(this);
        } else if (state == PLAY) {
            currentGameState = new PlayState(this);
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


}
