package com.mygdx.game.MyBaseClasses;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.DemoMenu.MenuStage;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Math.Random;
import com.mygdx.game.Music.MusicSetter;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayScreen;
import com.mygdx.game.Play.PlayStage;
import com.mygdx.game.Settings.IngameSettingsStage;
import com.mygdx.game.Settings.SettingsStage;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyScreen implements Screen, InitableInterface {
    //public final static float WORLD_WIDTH = 640, WORLD_HEIGHT = 480;
    protected SpriteBatch spriteBatch = new SpriteBatch();
    //protected OrthographicCamera camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
    //protected ExtendViewport viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

    public float r=0,g=0,b=0;

    public final MyGdxGame game;



    public MyScreen(MyGdxGame game) {
        this.game = game;
        init();
    }

    @Override
    public void dispose() {
        MenuStage.music.stopMusics();
        PlayScreen.gameMusic.stopMusics();
        spriteBatch.dispose();
    }

    @Override
    public void hide() {
        MenuStage.music.stopMusics();
        PlayScreen.gameMusic.stopMusics();
    }

    @Override
    public void pause() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(r, g, b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //spriteBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        //setCameraReset(viewport, width, height);
    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {
        MenuStage.music = new MusicSetter(true);
        PlayScreen.gameMusic = new MusicSetter(new Random(1,5).getGenNumber());
    }

    public Game getGame() {
        return game;
    }

    public void setBackGroundColor(float r, float g, float b)
    {
        this.r=r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void init() {
    }
}
