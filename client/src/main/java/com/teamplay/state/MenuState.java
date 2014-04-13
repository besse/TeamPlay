package com.teamplay.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.teamplay.data.Level;
import com.teamplay.input.intention.Intention;
import com.teamplay.managers.GameStateManager;
import com.teamplay.menu.Menu;
import com.teamplay.menu.MenuAction;
import com.teamplay.menu.MenuOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-10
 * Time: 12:53 PM
 */
public class MenuState extends GameState {

    private Level level;
    private Menu menu;

    private float scrollSpeed = 100.0f;

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuState.class);

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        level = new Level("menulevel");

        menu = new Menu();

        menu.addMenuOption(new MenuOption("Play", MenuOption.NONE, new MenuAction() {
            @Override
            public void invoke() {
                gsm.setState(GameStateManager.State.PLAY);
            }
        }));
    }

    @Override
    public void update(float dt) {
        camera.position.y = 120;
        camera.position.x += scrollSpeed * dt;
        LOGGER.debug(""+camera.position.x);
        if (camera.position.x > level.getLevelWidth() * 32) {
            scrollSpeed = -scrollSpeed;
        } else if (camera.position.x < 0) {
            scrollSpeed = -scrollSpeed;
        }

        handleInput();

        camera.update();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void render() {
        level.getTileMapRenderer().setView(camera);

        level.getTileMapRenderer().render();

        BitmapFont font = new BitmapFont();

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        {
            font.setColor(Color.BLACK);
            font.draw(spriteBatch, menu.getActiveOption().text, camera.position.x ,100);
        }
        spriteBatch.end();



    }

    @Override
    public void handleInput() {
        for (Intention intention : game.getInputService().getMenuIntention(menu)){
            intention.invoke(menu);
        }
    }

    @Override
    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
