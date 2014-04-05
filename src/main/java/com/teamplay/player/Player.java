package com.teamplay.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * User: jonasbirgersson
 * Date: 2014-04-02
 * Time: 11:14 AM
 */
public class Player {


    private float xPos;
    private float yPos;
    private boolean accelerateX;
    private boolean accelerateY;
    private float elapsedTime = 0.0f;

    private final static float MAX_SPEED = 100.0f;

    private final static float FRICTION = 20.0f;

    private float deltaX;
    private float deltaY;

    private Animation walkAnimation;      // #3
    private Texture walkSheet;      // #4
    private TextureRegion[] walkFrames;     // #5
    private TextureRegion currentFrame;


    public Player(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        walkSheet = new Texture(Gdx.files.internal("manwalking.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, 32, 32);
        // #10
        walkFrames = new TextureRegion[8];
        int index = 0;
        for (int i = 0; i < 8; i++) {
            walkFrames[index++] = tmp[0][i];
        }

        walkAnimation = new Animation(0.1f, walkFrames);      // #11
    }

    public void update(float dt) {
        xPos += (deltaX * dt);
        yPos += (deltaY * dt);
        if(!accelerateX){
            decelerateX();

        } if(!accelerateY){
            decelerateY();
        }
        elapsedTime += dt;

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

    public void decelerateY(){
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

    @Override
    public String toString() {
        return "Player{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", deltaX=" + deltaX +
                ", deltaY=" + deltaY +
                '}';
    }

    public TextureRegion getCurrentFrame() {
        return walkAnimation.getKeyFrame(elapsedTime * Math.abs(deltaX/50), true);  // #16

    }
}
