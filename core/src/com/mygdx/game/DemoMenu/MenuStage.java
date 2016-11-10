package com.mygdx.game.DemoMenu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Credits.CreditsScreen;
import com.mygdx.game.Extras.ExtrasScreen;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.Play.PlayScreen;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends MyStage {

    private TextButton textButton, textButton2, textButton3;

    private float width, heigthBetween, heigth; //menüpontok pozicionálása

    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init()
    {
        width = (getViewport().getScreenWidth())/2; //vízszintesen középre
        heigthBetween = (getViewport().getScreenHeight())/4; //egyenletesen elosztva 3 menüponthoz
        heigth = (getViewport().getScreenHeight()); //magasság

        addBackEventStackListener();

        //Játék
        textButton = new MyButton("Play", game.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new PlayScreen(game));
            }
        });

        heigth -= heigthBetween;

        textButton.setPosition(width - ((textButton.getWidth())/2),heigth);
        textButton.debug();
        addActor(textButton);



        //Extrák
        textButton2 = new MyButton("Extras", game.getTextButtonStyle());
        textButton2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new ExtrasScreen(game));
            }
        });

        heigth -= heigthBetween;

        textButton2.setPosition(width - ((textButton2.getWidth())/2),heigth);
        textButton2.debug();
        addActor(textButton2);



        //Készítők
        textButton3 = new MyButton("Credits", game.getTextButtonStyle());
        textButton3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new CreditsScreen(game));
            }
        });

        heigth -= heigthBetween;

        textButton3.setPosition(width - ((textButton3.getWidth())/2),heigth);
        textButton3.debug();
        addActor(textButton3);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
