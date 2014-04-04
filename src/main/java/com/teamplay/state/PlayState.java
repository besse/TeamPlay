package com.teamplay.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.teamplay.managers.GameKeys;
import com.teamplay.managers.GameStateManager;

/**
 * Created with IntelliJ IDEA.
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayState extends GameState {

    private TiledMap tileMap;
    private String cameradataString = "";

    private Texture playerTexture;

    private BitmapFont font;
    private float dx = 0, dy = 0, dz = 1.0f, accel = 0.1f;
    private OrthogonalTiledMapRenderer tileMapRenderer;


    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {

        playerTexture = new Texture(Gdx.files.internal("droplet.png"));

        tileMap = new TmxMapLoader().load("test.tmx");

        tileMapRenderer = new OrthogonalTiledMapRenderer(tileMap);

        font = new BitmapFont();
    }

    @Override
    public void update(float dt) {
        GameKeys.update();
        handleInput();
    }

    @Override
    public void render() {

        camera.position.x += dx;
        camera.position.y += dy;
        tileMapRenderer.setView(camera);
        spriteBatch.setProjectionMatrix(camera.combined);

        camera.update();
        tileMapRenderer.render();

        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(hudCamera.combined);
        spriteBatch.draw(playerTexture, 160 - playerTexture.getWidth() / 2, 120 - playerTexture.getHeight() / 2);
        font.draw(spriteBatch, cameradataString, 10, 20);
        spriteBatch.end();
        cameradataString = "X: " + camera.position.x + ". Y: " + camera.position.y + "\n"
                + "dx: " + dx;
    }

    @Override
    public void handleInput() {

        if (GameKeys.isDown(GameKeys.LEFT)) {
            dx -= accel;
        }
        if (GameKeys.isDown(GameKeys.RIGHT)) {
            dx += accel;
        }
        if (GameKeys.isDown(GameKeys.UP)) {
            dy -= accel;
        }
        if (GameKeys.isDown(GameKeys.DOWN)) {
            dy += accel;
        }


    }

    @Override
    public void dispose() {
    }
}
