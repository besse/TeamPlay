package com.teamplay.state;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.teamplay.data.Level;
import com.teamplay.managers.GameKeys;
import com.teamplay.managers.GameStateManager;
import com.teamplay.player.Player;

/**
 * Created with IntelliJ IDEA.
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayState extends GameState {

    private Level level;
    private String cameradataString = "";
    private Player player;

    private int currentFPS = 0;

    private float worldX = 0;
    private float worldY = 0;


    private float stateTime;

    private BitmapFont font;
    private float dx = 0, dy = 0, dz = 1.0f, accel = 20.0f;

    private ShapeRenderer shapeRenderer;


    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {

        level = new Level("test");

        stateTime = 0f;


        font = new BitmapFont();
        player = new Player(160, 120);

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float dt) {
        currentFPS = (int) (dt * 3600);
        stateTime += dt;
        handleInput();
        GameKeys.update();
        player.update(dt);

    }

    @Override
    public void render() {

        camera.position.x = player.getXPos();
        camera.position.y = player.getYPos();
        level.getTileMapRenderer().setView(camera);

        spriteBatch.setProjectionMatrix(camera.combined);

        level.getTileMapRenderer().render();

        spriteBatch.begin();


        camera.update();
        spriteBatch.draw(player.getCurrentFrame(), player.getXPos(), player.getYPos());
        spriteBatch.end();


        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1.0f);
        shapeRenderer.rect(Math.round(player.getXPos() / 32) * 32, Math.round(player.getYPos() / 32) * 32, 32, 32);
        shapeRenderer.end();

        spriteBatch.setProjectionMatrix(hudCamera.combined);
        spriteBatch.begin();
        font.draw(spriteBatch, cameradataString, 10, 20);
        spriteBatch.end();


        cameradataString =  "FPS: " + currentFPS + " X: " + player.getXPos() + ". Y: " + player.getYPos();
    }

    @Override
    public void handleInput() {

        if (GameKeys.isDown(GameKeys.LEFT)) {
            player.accelerateX(-accel);
        }
        if (GameKeys.isDown(GameKeys.RIGHT)) {
            player.accelerateX(accel);
        }
        if (GameKeys.isDown(GameKeys.UP)) {
            player.accelerateY(accel);
        }
        if (GameKeys.isDown(GameKeys.DOWN)) {
            player.accelerateY(-accel);
        }

        if (GameKeys.isReleased(GameKeys.LEFT)) {
            player.decelerateX();
        }
        if (GameKeys.isReleased(GameKeys.RIGHT)) {
            player.decelerateX();

        }
        if (GameKeys.isReleased(GameKeys.UP)) {
            player.decelerateY();
        }
        if (GameKeys.isReleased(GameKeys.DOWN)) {
            player.decelerateY();
        }


    }

    @Override
    public void dispose() {
    }
}
