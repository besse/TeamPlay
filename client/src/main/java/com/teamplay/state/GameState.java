package com.teamplay.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamplay.Game;
import com.teamplay.managers.GameStateManager;
import com.teamplay.managers.ResourceManager;

/**
 * Created with IntelliJ IDEA.
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 10:52 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GameState {

    protected GameStateManager gsm;
    protected Game game;


    protected ResourceManager resourceManager;


    protected SpriteBatch spriteBatch;
    public static OrthographicCamera camera;
    protected OrthographicCamera hudCamera;

    protected GameState(GameStateManager gsm){

        this.gsm = gsm;
        this.game = gsm.getGame();

        this.spriteBatch = game.getSpriteBatch();
        this.camera = game.getCamera();
        this.hudCamera = game.getHudCamera();
        this.resourceManager = game.getResourceManager();

        init();

    }

    public abstract void init();

    public abstract void update(float dt);

    public abstract void render();

    public abstract void handleInput();

    public abstract void dispose();


}
