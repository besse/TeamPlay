package com.teamplay.entity;

import com.badlogic.gdx.graphics.Texture;
import com.teamplay.navigation.Direction;

import java.util.HashSet;
import java.util.Set;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-10
 * Time: 10:21 AM
 */
public class ButtonEntity extends DrawableEntity {

    private static final String TEXTURE_FILE_NAME = "button.png";
    private static final long DELAY_TIME_MILLIS = 300;

    private long triggeredTime = 0;

    private ButtonState buttonState = ButtonState.OUT;

    private final Set<Entity> connectedEntities = new HashSet<Entity>();

    public ButtonEntity(String name, int x, int y, Direction direction) {
        super(name, x, y, direction, TEXTURE_FILE_NAME);
    }

    @Override
    public void update(float dt) {
        if (!isTriggerable()){
            if (timeSinceTriggered() > DELAY_TIME_MILLIS){
                switch (buttonState){
                    case ON_ITS_WAY_IN:
                        buttonState = ButtonState.IN;
                        break;
                    case ON_ITS_WAY_OUT:
                        buttonState = ButtonState.OUT;
                        break;
                }
                for (Entity entity : connectedEntities){
                    entity.changeState();
                }
                updateTexture();
            }
        }
    }

    private long timeSinceTriggered() {
        return System.currentTimeMillis() - triggeredTime;
    }

    private void updateTexture() {
        switch (buttonState){
            case IN:
                setTextureFileName("button_pressed.png");
                break;
            case OUT:
                setTextureFileName("button.png");
                break;
            case ON_ITS_WAY_IN:
            case ON_ITS_WAY_OUT:
                setTextureFileName("button_halfway.png");
                break;
        }
    }

    private boolean isTriggerable() {
        return buttonState == ButtonState.IN || buttonState == ButtonState.OUT;
    }

    @Override
    public void trigger() {
        if (isTriggerable()){
            switch (buttonState){
                case IN:
                    buttonState = ButtonState.ON_ITS_WAY_OUT;
                    break;
                case OUT:
                    buttonState = ButtonState.ON_ITS_WAY_IN;
                    break;
            }
            triggeredTime = System.currentTimeMillis();
            updateTexture();
        }
    }

    @Override
    public Texture getCurrentFrame() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void changeState() {
        //Do nothing;
    }

    public void addConnectedEntity(Entity connectedEntity){
        connectedEntities.add(connectedEntity);
    }

    private enum ButtonState {
        IN,
        OUT,
        ON_ITS_WAY_IN,
        ON_ITS_WAY_OUT;

    }
}
