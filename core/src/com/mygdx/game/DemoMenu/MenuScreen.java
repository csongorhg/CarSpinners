package com.mygdx.game.DemoMenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Music.MusicSetter;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuScreen extends MyScreen {

    protected MenuStage menuStage;
    protected MyStage bgStage;

    public static final String PREFS = "Score";
    private Preferences preferences = Gdx.app.getPreferences(PREFS);

    public MenuScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.setProjectionMatrix(bgStage.getCamera().combined);
        bgStage.act(delta);
        bgStage.draw();

        spriteBatch.setProjectionMatrix(menuStage.getCamera().combined);
        menuStage.act(delta);
        menuStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        menuStage.resize(width, height);
        bgStage.resize(width, height);
    }

    @Override
    public void dispose() {
        menuStage.dispose();
        preferences.flush();
        super.dispose();
    }

    @Override
    public void hide() {
        preferences.flush();
        super.hide();
    }

    @Override
    public void init() {
         bgStage = new MyStage(new StretchViewport(72,128, new OrthographicCamera(72,128)), spriteBatch, game) {

            private OneSpriteStaticActor backGroudActor;
            private OneSpriteStaticActor money;

            @Override
            public void init() {
                addActor(backGroudActor = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND_TEXTURE)));
            }

             @Override
             protected void resized() {

             }
         };
        menuStage  = new MenuStage(new ExtendViewport(270,480,new OrthographicCamera(270,480)), spriteBatch, game);
        Gdx.input.setInputProcessor(menuStage);
    }
}
