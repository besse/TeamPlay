package com.teamplay.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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


    private Animation walkAnimation;      // #3
    private Texture walkSheet;      // #4
    private TextureRegion[] walkFrames;     // #5
    private TextureRegion currentFrame;

    private float stateTime;

    private final float MAX_SPEED = 2.0f;
    private BitmapFont font;
    private float dx = 0, dy = 0, dz = 1.0f, accel = 0.1f;
    private OrthogonalTiledMapRenderer tileMapRenderer;


    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {

        walkSheet = new Texture(Gdx.files.internal("manwalking.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, 32, 32);
        // #10
        walkFrames = new TextureRegion[8];
        int index = 0;
        for (int i = 0; i < 8; i++) {
            walkFrames[index++] = tmp[0][i];
        }

        walkAnimation = new Animation(0.1f, walkFrames);      // #11
        stateTime = 0f;


        tileMap = new TmxMapLoader().load("test.tmx");

        tileMapRenderer = new OrthogonalTiledMapRenderer(tileMap);

        font = new BitmapFont();
    }

    @Override
    public void update(float dt) {
        stateTime += dt*dx;
        handleInput();
        GameKeys.update();

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
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);  // #16


        spriteBatch.draw(currentFrame, 160 - 16, 120 - 16);
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
            dy += accel;
        }
        if (GameKeys.isDown(GameKeys.DOWN)) {
            dy -= accel;
        }
        if (dx > MAX_SPEED) {
            dx = MAX_SPEED;
        }
        if (dx < -MAX_SPEED) {
            dx = -MAX_SPEED;
        }
        if (dy > MAX_SPEED) {
            dy = MAX_SPEED;
        }
        if (dy < -MAX_SPEED) {
            dy = -MAX_SPEED;
        }


        if (GameKeys.isReleased(GameKeys.LEFT)) {
            dx = 0.0f;
        }
        if (GameKeys.isReleased(GameKeys.RIGHT)) {
            dx = 0.0f;
        }
        if (GameKeys.isReleased(GameKeys.UP)) {
            dy = 0.0f;
        }
        if (GameKeys.isReleased(GameKeys.DOWN)) {
            dy = 0.0f;
        }


    }

    @Override
    public void dispose() {
    }
}
