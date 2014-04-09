package com.teamplay.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.teamplay.data.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 11:14 AM
 */
public class Player {


    //Debug rektanglar

    List<Rectangle> collidableTiles = new ArrayList<Rectangle>();

    private float xPos;
    private float yPos;
    private boolean accelerateX;
    private boolean accelerateY;

    private float deltaX;
    private float deltaY;

    private float elapsedTime = 0.0f;

    private final static float MAX_SPEED = 100.0f;
    private final static float FRICTION = 20.0f;


    Rectangle boundingBox;


    //Graphics and animation;
    private Animation walkAnimation;
    private Texture walkSheet;
    private TextureRegion[] walkFrames;
    private TextureRegion currentFrame;


    public Player(float xPos, float yPos) {
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
        return walkAnimation.getKeyFrame(elapsedTime * Math.abs(deltaX / 50), true);  // #16

    }

    public void accelerateX(float amount) {
        accelerateX = true;
        deltaX += amount;
        if (deltaX > MAX_SPEED) {
            deltaX = MAX_SPEED;
        } else if (deltaX < -MAX_SPEED) {
            deltaX = -MAX_SPEED;
        }

    }

    public void accelerateY(float amount) {
        accelerateY = true;
        deltaY += amount;
        if (deltaY > MAX_SPEED) {
            deltaY = MAX_SPEED;
        } else if (deltaY < -MAX_SPEED) {
            deltaY = -MAX_SPEED;
        }

    }

    public void decelerateX() {
        accelerateX = false;
        if (deltaX > 0) {
            deltaX -= FRICTION;
            if (deltaX < 0) {
                deltaX = 0;
            }
        }
        if (deltaX < 0) {
            deltaX += FRICTION;
            if (deltaX > 0) {
                deltaX = 0;
            }
        }

    }

    public void decelerateY() {
        accelerateY = false;
        if (deltaY > 0) {
            deltaY -= FRICTION;
            if (deltaY < 0) {
                deltaY = 0;
            }
        }
        if (deltaY < 0) {
            deltaY += FRICTION;
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
        float startY = boundingBox.getY();
        float endY = startY + boundingBox.getHeight();


        //Horisontell kollisioncheck
        if (deltaX < 0) {
            startX = endX = boundingBox.getX() + deltaX * dt;
        } else {
            startX = endX = boundingBox.getX() + boundingBox.getWidth() + deltaX * dt;
        }
        boundingBox.x += deltaX * dt;

        collidableTiles = (level.getCollidableRectangles(startX, startY, endX, endY));
        for (Rectangle rectangle : level.getCollidableRectangles(startX, startY, endX, endY)) {
            if (boundingBox.overlaps(rectangle)) {
                deltaX = 0;
                break;
            }
        }

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
                deltaY = 0;
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

    @Override
    public String toString() {
        return "Player{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", deltaX=" + deltaX +
                ", deltaY=" + deltaY +
                '}';
    }
}
