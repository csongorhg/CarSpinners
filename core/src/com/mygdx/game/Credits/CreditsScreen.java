package com.mygdx.game.Credits;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Extras.ExtrasStage;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyGdxGame;

/**
 * Created by mordes on 2016.11.10..
 */
public class CreditsScreen extends MyScreen{

    protected CreditsStage creditsStage;

    public CreditsScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        creditsStage.act(delta);
        creditsStage.draw();

    }

    @Override
    public void init() {
        r = 1;
        g = 0.5f;
        b = 0.3f;
        creditsStage = new CreditsStage(new ExtendViewport(270,480,new OrthographicCamera(270,480)), spriteBatch, game);
        Gdx.input.setInputProcessor(creditsStage);
    }

}
