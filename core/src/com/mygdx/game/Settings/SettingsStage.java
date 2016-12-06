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
    public OneSpriteStaticActor volumeIconSpriteActor, volumePlusSpriteActor,volumeMinusSpriteActor;
    public Array<OneSpriteStaticActor> volumeArray;
    public static float actualVol = 1;
    private static boolean voltmar = false;
    public static boolean musicPlay = true;
    public MusicSetter musicSetter = new MusicSetter();

    public float width, heigth;

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

        this.resized();

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

    public void musicOnOff(){
        volumeIconSpriteActor = new OneSpriteStaticActor(Assets.manager.get(musicPlay?Assets.SOUND_ICON:Assets.MUTE_ICON));
        addActor(volumeIconSpriteActor);
        volumeIconSpriteActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                volumeIconSpriteActor.remove();
                musicPlay = !musicPlay;
                if(musicPlay){
                    actualVol = 1;
                    volumeArraySettings();
                }else{
                    actualVol = 1-0.1f-0.1f-0.1f-0.1f-0.1f-0.1f-0.1f-0.1f-0.1f-0.1f;
                    volumeArraySettings();
                }
                //IngameSettingsStage.musicPlay = musicPlay;
                new MusicSetter(musicPlay);
                musicOnOff();
            }
        });
        IngameSettingsStage.musicPlay = musicPlay;
        IngameSettingsStage.actualVol = actualVol;
        volumeIconSpriteActor.setSize(width / 3, width / 3);
        volumeIconSpriteActor.setPosition(0, heigth - volumeIconSpriteActor.getHeight());
    }

    public void musicVolume(){
        volumePlusSpriteActor = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUS_VOL));
        volumeMinusSpriteActor = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUS_VOL));
        addActor(volumePlusSpriteActor); addActor(volumeMinusSpriteActor);
        volumePlusSpriteActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(actualVol<=0.9){
                    actualVol+=0.1f;
                    if(actualVol>0 && !voltmar){
                        voltmar = true;
                        volumeIconSpriteActor.remove();
                        musicPlay = true;
                        new MusicSetter(musicPlay);
                        musicOnOff();
                    }
                    IngameSettingsStage.actualVol = actualVol;
                    musicSetter.musicVolume(actualVol);
                    volumeArraySettings();
                }
            }
        });
        volumeMinusSpriteActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(actualVol>=0){
                    actualVol-=0.1f;
                    if(actualVol<=0){
                        voltmar = false;
                        volumeIconSpriteActor.remove();
                        musicPlay = false;
                        musicOnOff();
                    }
                    IngameSettingsStage.actualVol = actualVol;
                    musicSetter.musicVolume(actualVol<=0?0:actualVol);
                    volumeArraySettings();
                }
            }
        });
        volumeArraySettings();
    }

    public void volumeArraySettings(){
        volumeArray = new Array<OneSpriteStaticActor>();
        for(float i=0; i<1; i+=0.1){
            if(i<=actualVol){
                volumeArray.add(new OneSpriteStaticActor(Assets.manager.get(Assets.FILLED_VOL)));
                addActor(volumeArray.get((int)(i*10)));
            }else{
                volumeArray.add(new OneSpriteStaticActor(Assets.manager.get(Assets.EMPTY_VOL)));
                addActor(volumeArray.get((int)(i*10)));
            }
        }
        volumeSize(width, heigth/2);
    }

    public void volumeSize(float width, float heigth){
        float meretGomb = heigth/6;
        volumeMinusSpriteActor.setSize(meretGomb, meretGomb);
        volumePlusSpriteActor.setSize(meretGomb, meretGomb);
        volumeMinusSpriteActor.setPosition(0,heigth);
        volumePlusSpriteActor.setPosition(width*2-volumePlusSpriteActor.getWidth(), heigth);
        for(int i=0; i<volumeArray.size; i++){
            //hangero.get(i).setSize((fel.getX()-le.getWidth())/10, meretGomb);
            //hangero.get(i).setPosition(le.getWidth()+i*hangero.get(i).getWidth(),heigth);
            volumeArray.get(i).setSize((volumePlusSpriteActor.getX()-volumeMinusSpriteActor.getWidth())/10,meretGomb);
            volumeArray.get(i).setPosition(volumeMinusSpriteActor.getWidth()+i*volumeArray.get(i).getWidth(), heigth);
        }
    }

    public static boolean isB() {
        return musicPlay;
    }


}
