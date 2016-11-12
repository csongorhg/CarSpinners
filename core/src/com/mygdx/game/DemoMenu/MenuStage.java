package com.mygdx.game.DemoMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Credits.CreditsScreen;
import com.mygdx.game.Extras.ExtrasScreen;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Math.Random;
import com.mygdx.game.Music.MusicSetter;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.Play.PlayScreen;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends MyStage {

    private TextButton textButton, textButton2, textButton3, textButton4;

    private float width, heigthBetween, heigth; //menüpontok pozicionálása

    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init()
    {
        addBackEventStackListener();



        //Játék
        textButton = new MyButton("Play", game.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                new MusicSetter(new Random(1,5).getGenNumber());
                game.setScreen(new PlayScreen(game));

            }
        });
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
        textButton3.debug();
        addActor(textButton3);



        //Kilépés
        textButton4 = new MyButton("Exit", game.getTextButtonStyle());
        textButton4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
        textButton4.debug();
        addActor(textButton4);


        resized();
    }


    @Override
    public void act(float delta) {
        super.act(delta);

    }

    @Override
    public void dispose() {
        super.dispose();

    }

    @Override
    protected void resized() {
        super.resized();
        width = (((ExtendViewport)getViewport()).getMinWorldWidth())/2; //vízszintesen középre
        heigthBetween = (((ExtendViewport)getViewport()).getMinWorldHeight())/4; //egyenletesen elosztva 3 menüponthoz
        heigth = (((ExtendViewport)getViewport()).getMinWorldHeight()); //magasság
        heigth -= heigthBetween;
        textButton.setPosition(width - ((textButton.getWidth())/2),heigth);
        heigth -= heigthBetween;

        textButton3.setPosition(width - ((textButton3.getWidth())/2),heigth);
        heigth -= heigthBetween;

        textButton2.setPosition(width - ((textButton2.getWidth())/2),heigth);

        textButton4.setPosition(width - ((textButton4.getWidth())/2),0);
    }
}
