package com.teamplay;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamplay.managers.GameStateManager;
import com.teamplay.managers.InputProcessor;
import com.teamplay.managers.ResourceManager;

/**
 * Main game container.
 * <p/>
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 9:22 AM
 */
public class Game implements ApplicationListener {

    private final int WIDTH = 320;
    private final int HEIGHT = 240;

    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private GameStateManager gameStateManager;
    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;
    private ResourceManager resourceManager;


    @Override
    public void create() {

        resourceManager = new ResourceManager();

        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.GREEN);

        System.out.println("Screen width: " + Gdx.graphics.getWidth());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(false, WIDTH, HEIGHT);

        Gdx.input.setInputProcessor(new InputProcessor());

        gameStateManager = new GameStateManager(this);

    }

    @Override
    public void resize(int i, int i2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.draw();

    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }


    public OrthographicCamera getHudCamera() {
        return hudCamera;
    }

    public ResourceManager getResourceManager(){
        return resourceManager;
    }
}
