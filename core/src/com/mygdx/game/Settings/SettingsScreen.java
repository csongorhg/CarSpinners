package com.mygdx.game.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Music.CarMusic;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Kicsi on 2016. 11. 12..
 */

public class SettingsScreen extends MyScreen{


    protected SettingsStage settingsStage;
    protected MyStage bgStage;


    public SettingsScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.setProjectionMatrix(bgStage.getCamera().combined);
        bgStage.act(delta);
        bgStage.draw();

        spriteBatch.setProjectionMatrix(settingsStage.getCamera().combined);
        settingsStage.act(delta);
        settingsStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        settingsStage.resize(width, height);
        bgStage.resize(width, height);
    }

    /*@Override
    public void dispose() {
        settingsStage.dispose();
        super.dispose();
    }*/

    @Override
    public void init() {
        bgStage = new MyStage(new StretchViewport(72,128, new OrthographicCamera(72,128)), spriteBatch, game) {
            private OneSpriteStaticActor backGroudActor;
            @Override
            public void init() {
                addActor(backGroudActor = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND_TEXTURE)));
            }

            @Override
            protected void resized() {

            }
        };
        settingsStage  = new SettingsStage(new ExtendViewport(270,480,new OrthographicCamera(270,480)), spriteBatch, game);
        Gdx.input.setInputProcessor(settingsStage);
    }


}
