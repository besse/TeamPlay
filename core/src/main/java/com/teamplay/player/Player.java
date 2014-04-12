package com.teamplay.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.teamplay.data.Level;
import com.teamplay.entity.StartingPosition;
import com.teamplay.navigation.Position;
import com.teamplay.util.GameConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 11:14 AM
 */
public class Player {
    private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);

    //Debug rektanglar

    List<Rectangle> collidableTiles = new ArrayList<Rectangle>();

    private float xPos;
    private float yPos;
    private boolean accelerateX;
    private boolean accelerateY;

    private float deltaX;
    private float deltaY;

    private float elapsedTime = 0.0f;


    Rectangle boundingBox;


    //Graphics and animation;
    private Animation walkAnimation;
    private Texture walkSheet;
    private TextureRegion[] walkFrames;
    private TextureRegion currentFrame;

    private float speed;
    private float health;
    private final float acceleration = 20f;

    public Player(float xPos, float yPos) {
        init(xPos, yPos);
    }

    public Player(StartingPosition startingPosition) {
        init(startingPosition.getX(), startingPosition.getY());
    }

    private final void init(float xPos, float yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        walkSheet = new Texture(Gdx.files.internal("manwalking.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, 32, 32);

        walkFrames = new TextureRegion[8];
        int index = 0;
        for (int i = 0; i < 8; i++) {
            walkFrames[index++] = tmp[0][i];
        }

        walkAnimation = new Animation(0.1f, walkFrames);

        boundingBox = new Rectangle(2, 1, 25, 26);

        this.health = GameConstants.PLAYER_INIT_HEALTH;
    }

    public void update(float dt) {

        xPos += (deltaX * dt);
        yPos += (deltaY * dt);

        if (!accelerateX) {
            decelerateX();

        }
        if (!accelerateY) {
            decelerateY();
        }
        elapsedTime += dt;
    }

    public TextureRegion getCurrentFrame() {
        return walkAnimation.getKeyFrame(xPos/70, true);  // #16

    }

    public void accelerateX(float amount) {
        accelerateX = true;
        deltaX += amount;
        if (deltaX > GameConstants.PLAYER_MAX_SPEED) {
            deltaX = GameConstants.PLAYER_MAX_SPEED;
        } else if (deltaX < -GameConstants.PLAYER_MAX_SPEED) {
            deltaX = -GameConstants.PLAYER_MAX_SPEED;
        }

    }

    public void accelerateY(float amount) {
        accelerateY = true;
        deltaY += amount;
        if (deltaY > GameConstants.PLAYER_MAX_SPEED) {
            deltaY = GameConstants.PLAYER_MAX_SPEED;
        } else if (deltaY < -GameConstants.PLAYER_MAX_SPEED) {
            deltaY = -GameConstants.PLAYER_MAX_SPEED;
        }

    }

    public void decelerateX() {
        accelerateX = false;
        if (deltaX > 0) {
            deltaX -= GameConstants.FRICTION;
            if (deltaX < 0) {
                deltaX = 0;
            }
        }
        if (deltaX < 0) {
            deltaX += GameConstants.FRICTION;
            if (deltaX > 0) {
                deltaX = 0;
            }
        }

    }

    public void decelerateY() {
        accelerateY = false;
        if (deltaY > 0) {
            deltaY -= GameConstants.FRICTION;
            if (deltaY < 0) {
                deltaY = 0;
            }
        }
        if (deltaY < 0) {
            deltaY += GameConstants.FRICTION;
            if (deltaY > 0) {
                deltaY = 0;
            }
        }


    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public void setxPos(float xPos, float dt) {
        this.xPos = xPos;
        if (!accelerateX) {
            decelerateX();

        }
        if (!accelerateY) {
            decelerateY();
        }
        elapsedTime += dt;
    }

    public void setyPos(float yPos, float dt) {
        this.yPos = yPos;
        if (!accelerateX) {
            decelerateX();

        }
        if (!accelerateY) {
            decelerateY();
        }
        elapsedTime += dt;
    }

    public float getVelocityX() {
        return deltaX;
    }

    public float getVelocityY() {
        return deltaY;
    }

    public void collideHorizontal() {
        deltaX = 0;
    }

    public void collideVertical() {
        deltaY = 0;
    }


    public void checkCollisionWithBlocks(float dt, Level level) {
        boundingBox.set(xPos + 2, yPos + 1, 25, 26);

        float startX, endX;
        float startY;
        float endY;

        //Vertikal kollisionscheck
        startX = boundingBox.getX();
        endX = startX + boundingBox.getWidth();
        if (deltaY < 0) {
            startY = endY = boundingBox.getY() + deltaY * dt;
        } else {
            startY = endY = boundingBox.getY() + boundingBox.getHeight() + deltaY * dt;
        }

        boundingBox.y += deltaY * dt;

        collidableTiles.addAll(level.getCollidableRectangles(startX, startY, endX, endY));
        for (Rectangle rectangle : level.getCollidableRectangles(startX, startY, endX, endY)) {
            if (boundingBox.overlaps(rectangle)) {
                LOGGER.trace("Vertikal check dy blir 0");
                deltaY = 0;
                break;
            }
        }


        //Horisontell kollisioncheck

        startY = boundingBox.getY();
        endY = startY + boundingBox.getHeight();

        if (deltaX < 0) {
            startX = endX = boundingBox.getX() + deltaX * dt;
        } else {
            startX = endX = boundingBox.getX() + boundingBox.getWidth() + deltaX * dt;
        }
        boundingBox.x += deltaX * dt;

        collidableTiles = (level.getCollidableRectangles(startX, startY, endX, endY));
        for (Rectangle rectangle : level.getCollidableRectangles(startX, startY, endX, endY)) {
            if (boundingBox.overlaps(rectangle)) {
                LOGGER.trace("Horisontell check dx blir 0");

                deltaX = 0;
                break;
            }
        }
    }


    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public List<Rectangle> getCollidableTiles() {
        return collidableTiles;
    }

    public void increaseSpeed(float s){
        this.speed =  (speed + s) >= GameConstants.PLAYER_MAX_SPEED ? speed + s : GameConstants.PLAYER_MAX_SPEED;
    }

    public void decreaseSpeed(float s){
        this.speed =  (speed - s) <= GameConstants.PLAYER_MIN_SPEED ? speed - s : GameConstants.PLAYER_MIN_SPEED;
    }

    public float getSpeed(){
        return this.speed;
    }

    public float getHealthPercent(){
        return  this.health / GameConstants.PLAYER_MAX_HEALTH;
    }

    public void increaseHealth(int h) {
        this.health =+ h;
    }

    public void decreaseHealth(float h) {
        this.health = (health - h) < 0 ? 0 : health - h;
    }

    @Override
    public String toString() {
        return "Player{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", deltaX=" + deltaX +
                ", deltaY=" + deltaY +
                '}';
    }


    public Position getPosition() {
        return new Position();
    }

    public void accelerateX() {
        accelerateX(10f);
    }

    public void accelerateY() {
        accelerateY(10f);
    }

    public void stopX() {
        deltaX = 0;
    }


    public void stopY() {
        deltaY = 0;
    }

    public float getAcceleration() {
        return acceleration;
    }
}
