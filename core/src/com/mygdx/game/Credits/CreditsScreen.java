package com.mygdx.game.Credits;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Extras.ExtrasStage;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by mordes on 2016.11.10..
 */
public class CreditsScreen extends MyScreen{

    protected CreditsStage creditsStage;
    protected MyStage bgStage;

    public CreditsScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.setProjectionMatrix(bgStage.getCamera().combined);
        bgStage.draw();
        creditsStage.act(delta);
        creditsStage.draw();

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
        creditsStage = new CreditsStage(new ExtendViewport(270,480,new OrthographicCamera(270,480)), spriteBatch, game);
        Gdx.input.setInputProcessor(creditsStage);
    }

    @Override
    public void resize(int width, int height) {
             super.resize(width, height);
             bgStage.resize(width, height);
        }

}
