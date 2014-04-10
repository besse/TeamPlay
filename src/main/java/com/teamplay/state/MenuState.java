package com.teamplay.state;

import com.teamplay.data.Level;
import com.teamplay.managers.GameStateManager;

/**
 * Created for TeamPlay
 * User: jonasbirgersson
 * Date: 2014-04-10
 * Time: 12:53 PM
 */
public class MenuState extends GameState {

    private Level level;

    private float scrollSpeed = 100.0f;

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        level = new Level("menulevel");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(float dt) {
        camera.position.x += scrollSpeed * dt;
        System.out.println(camera.position.x);
        if (camera.position.x > level.getLevelWidth() * 32) {
            scrollSpeed = -scrollSpeed;
        } else if (camera.position.x < 0) {
            scrollSpeed = -scrollSpeed;
        }


        camera.update();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void render() {
        level.getTileMapRenderer().setView(camera);

        spriteBatch.setProjectionMatrix(camera.combined);

        level.getTileMapRenderer().render();
    }

    @Override
    public void handleInput() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
