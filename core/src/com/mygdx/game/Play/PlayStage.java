package com.mygdx.game.Play;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Graphics.ButtonCaller;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Settings.SettingsStage;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class PlayStage extends MyStage {

    //private TextButton textButton;

    //itt kell megadni, a pozicionálást!!!
    private float width, heigthBetween, heigth;

    public boolean settingclick = false;

    private ButtonCaller p, f, textButton5; //gáz, fékpedál

    public PlayStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {
        SettingsStage.gamee = false;


        addBackEventStackListener();

        //fogaskerék
        textButton5 = new ButtonCaller("", Assets.CONF_ICON);
        textButton5.setSize(45,45);
        textButton5.setY((((ExtendViewport)getViewport()).getMinWorldHeight())
                - textButton5.getHeight());
        textButton5.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                settingclick = true;
                //new MusicSetter(SettingsStage.isB()); //menüzene hívás
                //game.setScreenBackByStackPop();
            }
        });

        addActor(textButton5);



        //gázpedál
        p = new ButtonCaller("", Assets.GAZ_ICON);
        p.setSize(54,57); //18*3, 19*3
        p.setX((((ExtendViewport)getViewport()).getMinWorldWidth()) - p.getWidth());

        addActor(p);

        //fékpedál
        f = new ButtonCaller("", Assets.FEK_ICON);
        f.setSize(54,57); //18*3, 19*3

        addActor(f);

        resized();

    }

    @Override
    protected void resized() {
        super.resized();
        width = (((ExtendViewport)getViewport()).getMinWorldWidth())/2; //vízszintesen középre
        //heigthBetween = (((ExtendViewport)getViewport()).getMinWorldHeight())/4; //egyenletesen elosztva 3 menüponthoz
        heigth = 0; //magasság
        //heigth -= heigthBetween;
        //textButton.setPosition(width - ((textButton.getWidth())/2),heigth);
    }
}