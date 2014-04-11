package com.teamplay.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.teamplay.data.Level;
import com.teamplay.entity.Entity;
import com.teamplay.managers.CollisionManager;
import com.teamplay.managers.GameKey;
import com.teamplay.managers.GameStateManager;
import static com.teamplay.managers.InputProcessor.isDown;
import static com.teamplay.managers.InputProcessor.isPressed;
import static com.teamplay.managers.InputProcessor.isReleased;

import com.teamplay.managers.InputProcessor;
import com.teamplay.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayState extends GameState {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayState.class);

    private Level level;
    private String cameradataString = "";
    private Player player;


    private BitmapFont font;
    private float accel = 20.0f;

    private ShapeRenderer shapeRenderer;
    private CollisionManager collisionManager;


    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {

        level = new Level("level1");

        font = new BitmapFont();
        player = new Player(160, 120);

        shapeRenderer = new ShapeRenderer();

        collisionManager = new CollisionManager(level);
    }

    @Override
    public void update(float dt) {

        handleInput();

        //Updaterar player med collisions..
        player.checkCollisionWithBlocks(dt, level);
        player.update(dt);

    }

    @Override
    public void render() {

        //Uppdatera kameran så att den stannar kvar på banan och inte visar annat bös
        setCameraPositions(camera, player.getXPos(), player.getYPos());

        level.getTileMapRenderer().setView(camera);

        spriteBatch.setProjectionMatrix(camera.combined);

        level.getTileMapRenderer().render();


        spriteBatch.begin();


        camera.update();
        spriteBatch.draw(player.getCurrentFrame(), player.getXPos(), player.getYPos());
        for (Entity currentLevelObject : level.getLevelObjects()) {
            currentLevelObject.getSprite().draw(spriteBatch);
        }

        spriteBatch.end();


        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1.0f);
        shapeRenderer.rect(player.getBoundingBox().getX(), player.getBoundingBox().getY(), player.getBoundingBox().getWidth(), player.getBoundingBox().getHeight());
        shapeRenderer.end();

        //Draw health bar
        drawHealthBar(shapeRenderer, player);


        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1.0f, 0.0f, 0.5f, 1.0f);

        for (Rectangle rectangle : player.getCollidableTiles()) {
            shapeRenderer.rect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());


        }
        shapeRenderer.end();


        spriteBatch.setProjectionMatrix(hudCamera.combined);
        spriteBatch.begin();
        font.draw(spriteBatch, cameradataString, 10, 20);
        spriteBatch.end();


        cameradataString = "FPS: " + Gdx.graphics.getFramesPerSecond() + " X: " + player.getXPos() + ". Y: " + player.getYPos();
    }

    private void drawHealthBar(ShapeRenderer sr, Player p) {
        float length = 25.0f;
        float height = 3.0f;
        float healthPercent = p.getHealthPercent();

        float startX = (p.getBoundingBox().getX() + (p.getBoundingBox().getWidth() / 2)) - (length / 2);
        float startY = p.getYPos() + p.getCurrentFrame().getRegionHeight();
        float lifeBarLength = Math.max(2, length * healthPercent);


        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.rect(startX, startY, length, height);
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        if (healthPercent >= .6f) {
            sr.setColor(Color.GREEN);
        } else if (healthPercent >= 0.3f) {
            sr.setColor(Color.YELLOW);
        } else {
            sr.setColor(Color.RED);
        }
        sr.line(startX + 1, startY + height - 1, startX + lifeBarLength - 1, startY + height - 1);
        sr.end();
    }

    private void setCameraPositions(OrthographicCamera camera, float x, float y) {

        //Ta fram max och min värden för x,y
        float viewMaxX = level.getPixelWidth() - (camera.viewportWidth / 2);
        float viewMinX = 0 + (camera.viewportWidth / 2);
        float viewMaxY = level.getPixelHeight() - (camera.viewportHeight / 2);
        float viewMinY = 0 + (camera.viewportHeight / 2);

        //Updatera kameran med de korrekta värdena
        camera.position.x = Math.max(viewMinX, Math.min(x, viewMaxX));
        camera.position.y = Math.max(viewMinY, Math.min(y, viewMaxY));
    }

    @Override
    public void handleInput() {

        if (isDown(GameKey.LEFT)){
            player.accelerateX(-accel);
        }
        if (isDown(GameKey.RIGHT)) {
            player.accelerateX(accel);
        }
        if (isDown(GameKey.UP)) {
            player.accelerateY(accel);
        }
        if (isDown(GameKey.DOWN)) {
            player.accelerateY(-accel);
        }

        if (isReleased(GameKey.LEFT)) {
            player.decelerateX();
        }
        if (isReleased(GameKey.RIGHT)) {
            player.decelerateX();

        }
        if (isReleased(GameKey.UP)) {
            player.decelerateY();
        }
        if (isReleased(GameKey.DOWN)) {
            player.decelerateY();
        }

        if (isReleased(GameKey.SPACE)) {
            LOGGER.debug("Hurting the little fellah");
            player.decreaseHealth(5.0f);
        }
        InputProcessor.refresh();

    }

    @Override
    public void dispose() {
    }
}
