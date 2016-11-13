package com.mygdx.game.Settings;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Music.MusicSetter;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Kicsi on 2016. 11. 12..
 */

public class SettingsStage extends MyStage{

    private TextButton textButton;
    private OneSpriteStaticActor hang;
    private static boolean b = true;
    public static boolean gamee = false;
    MusicSetter musicSetter = new MusicSetter();

    private float width, heigthBetween, heigth;

    public SettingsStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {
        addBackEventStackListener();

        textButton = new MyButton("Back", game.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gamee = true;
                if(game.getScreen().toString().indexOf("PlayScreen") > -1){
                    musicSetter.stopMusics();
                    musicSetter.MenuMusic();
                }
                game.setScreenBackByStackPop();

            }
        });
        addActor(textButton);

        musicOnOff();

        resized();
    }

    @Override
    protected void resized() {
        super.resized();
        width = (((ExtendViewport)getViewport()).getMinWorldWidth())/2; //vízszintesen középre
        //heigthBetween = (((ExtendViewport)getViewport()).getMinWorldHeight())/5; //egyenletesen elosztva 3 menüponthoz
        heigth = (((ExtendViewport)getViewport()).getMinWorldHeight()); //magasság
        //heigth -= heigthBetween;

        textButton.setPosition(width - ((textButton.getWidth()) / 2), 0);

        hang.setSize(width / 3, width / 3);
        hang.setPosition(0, heigth - hang.getHeight());
    }

    void musicOnOff(){
        hang = new OneSpriteStaticActor(Assets.manager.get(b?Assets.SOUND_ICON:Assets.MUTE_ICON));
        addActor(hang);
        hang.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                hang.remove();
                b = !b;
                new MusicSetter(b);
                musicOnOff();
            }
        });
        resized();
    }

    public static boolean isB() {
        return b;
    }


}
