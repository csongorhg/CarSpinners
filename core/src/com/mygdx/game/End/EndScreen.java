package com.mygdx.game.End;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Credits.CreditsStage;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by mordes on 2016.12.03..
 */
public class EndScreen extends MyScreen {

    protected EndStage endStage;
    protected MyStage bgStage;

    public static final String PREFS = "Score";
    private Preferences preferences = Gdx.app.getPreferences(PREFS);

    public EndScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.setProjectionMatrix(bgStage.getCamera().combined);
        bgStage.draw();
        endStage.act(delta);
        endStage.draw();

    }

    @Override
    public void init() {
        r = 1;
        g = 0.5f;
        b = 0.3f;
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
        endStage = new EndStage(new ExtendViewport(270,480,new OrthographicCamera(270,480)), spriteBatch, game);
        Gdx.input.setInputProcessor(endStage);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        bgStage.resize(width, height);
    }

    @Override
    public void dispose() {
        preferences.flush();
        super.dispose();
    }

    @Override
    public void hide() {
        preferences.flush();
        super.hide();
    }
}
