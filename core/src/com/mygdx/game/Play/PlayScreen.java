package com.mygdx.game.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Math.Random;
import com.mygdx.game.Music.MusicSetter;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Settings.IngameSettingsStage;
import com.mygdx.game.Settings.SettingsStage;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class PlayScreen extends MyScreen {
    protected PlayStage playStage;
    protected IngameSettingsStage settingsStage;
    private MusicSetter musicSetter = new MusicSetter();

    public PlayScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if(settingsStage.isB())new MusicSetter(new Random(1,5).getGenNumber());
        playStage.act(delta);
        playStage.draw();
        if(playStage.settingclick){
            config(delta);
        }
        else backtogame();
    }

    public void backtogame(){
        Gdx.input.setInputProcessor(playStage);
    }

    public void config(float delta){
        settingsStage.act(delta);
        settingsStage.draw();
        Gdx.input.setInputProcessor(settingsStage);
    }

    @Override
    public void init() {
        playStage.settingclick = false;
        r = 1;
        g = 0.5f;
        b = 0.3f;
        playStage = new PlayStage(new ExtendViewport(270,480,new OrthographicCamera(270,480)), spriteBatch, game);
        settingsStage = new IngameSettingsStage(new ExtendViewport(270,480,new OrthographicCamera(270/2,480/2)), spriteBatch, game);
        Gdx.input.setInputProcessor(playStage);

        /*inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(playStage);
        inputMultiplexer.addProcessor(settingsStage);
        Gdx.input.setInputProcessor(inputMultiplexer);*/

        //playStage.isMenuScreen = false;
    }

    @Override
    public void hide() {
        super.hide();
        SettingsStage.musicPlay = false;
    }

    @Override
    public void show() {
        super.show();
        musicSetter.stopMusics();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        playStage.resize(width,height);
    }
}