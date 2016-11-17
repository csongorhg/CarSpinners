package com.mygdx.game.Settings;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
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
    private OneSpriteStaticActor hang, fel, le;
    private Array<OneSpriteStaticActor> hangero;
    public static float actualVol = 1;
    static boolean b = true;
    MusicSetter musicSetter = new MusicSetter();

    private float width, heigth;

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
                game.setScreenBackByStackPop();

            }
        });
        addActor(textButton);

        resized();

        musicOnOff();
        musicVolume();
    }

    @Override
    protected void resized() {
        super.resized();
        width = (((ExtendViewport)getViewport()).getMinWorldWidth())/2; //vízszintesen középre
        //heigthBetween = (((ExtendViewport)getViewport()).getMinWorldHeight())/5; //egyenletesen elosztva 3 menüponthoz
        heigth = (((ExtendViewport)getViewport()).getMinWorldHeight()); //magasság
        //heigth -= heigthBetween;

        textButton.setPosition(width - ((textButton.getWidth()) / 2), 0);

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
                IngameSettingsStage.b = b;
                new MusicSetter(b);
                musicOnOff();
            }
        });
        hang.setSize(width / 3, width / 3);
        hang.setPosition(0, heigth - hang.getHeight());
    }


    void musicVolume(){
        fel = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUS_VOL));
        le = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUS_VOL));
        addActor(fel); addActor(le);
        fel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(actualVol<=0.9){
                    actualVol+=0.1;
                    musicSetter.musicVolume(actualVol);
                    cuclik();
                }
            }
        });
        le.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(actualVol>=0){
                    actualVol-=0.1;
                    musicSetter.musicVolume(actualVol<=0?0:actualVol);
                    cuclik();
                }
            }
        });
        cuclik();
    }

    void cuclik(){
        hangero = new Array<OneSpriteStaticActor>();
        for(float i=0; i<1; i+=0.1){
            if(i<=actualVol){
                hangero.add(new OneSpriteStaticActor(Assets.manager.get(Assets.FILLED_VOL)));
                addActor(hangero.get((int)(i*10)));
            }else{
                hangero.add(new OneSpriteStaticActor(Assets.manager.get(Assets.EMPTY_VOL)));
                addActor(hangero.get((int)(i*10)));
            }
        }
        meretezes(width, heigth/2);
    }

    void meretezes(float width, float heigth){
        float meretGomb = heigth/6;
        le.setSize(meretGomb, meretGomb);
        fel.setSize(meretGomb, meretGomb);
        le.setPosition(0,heigth);
        fel.setPosition(width*2-fel.getWidth(), heigth);
        for(int i=0; i<hangero.size; i++){
            hangero.get(i).setSize((fel.getX()-le.getWidth())/10, meretGomb);
            //hangero.get(i).setSize((fel.getX()-le.getWidth())/10,(fel.getX()-le.getWidth())/10);
            hangero.get(i).setPosition(le.getWidth()+i*hangero.get(i).getWidth(),heigth);
            //hangero.get(i).setPosition(le.getWidth()+i*hangero.get(i).getWidth(), heigth+hangero.get(i).getHeight()/2);
        }
    }

    public static boolean isB() {
        return b;
    }


}
