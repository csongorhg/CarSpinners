package com.mygdx.game.Settings;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DemoMenu.MenuStage;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Math.Random;
import com.mygdx.game.Music.MusicSetter;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayScreen;

/**
 * Created by Kicsi on 2016. 11. 12...
 */

public class SettingsStage extends MyStage{

    private TextButton textButton;
    public OneSpriteStaticActor volumeIconSpriteActor, volumePlusSpriteActor,volumeMinusSpriteActor;
    public Array<OneSpriteStaticActor> volumeArray;
    //public static float actualVol;
    public static boolean clicked;
    public static boolean musicPlay;
    //public MusicSetter musicSetter = new MusicSetter();

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
                IngameSettingsStage.musicPlay = musicPlay;
                IngameSettingsStage.clicked = clicked;
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
        heigth = (((ExtendViewport)getViewport()).getMinWorldHeight()); //magasság
        textButton.setPosition(width - ((textButton.getWidth()) / 2), 0);

    }

    void musicOnOff(){
        volumeIconSpriteActor = new OneSpriteStaticActor(Assets.manager.get(musicPlay || IngameSettingsStage.musicPlay?Assets.SOUND_ICON:Assets.MUTE_ICON));
        //volumeIconSpriteActor = new OneSpriteStaticActor(Assets.manager.get(MenuStage.music.playing()?Assets.SOUND_ICON:Assets.MUTE_ICON));
        addActor(volumeIconSpriteActor);
        volumeIconSpriteActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                volumeIconSpriteActor.remove();
                if(MenuStage.music.getMenuVolume() > 0) musicPlay = false;
                else musicPlay = true;
                IngameSettingsStage.musicPlay = musicPlay;
                if(musicPlay){
                    //MenuStage.music.MenuMusic2();
                    //MenuStage.music = new MusicSetter(true);
                    MenuStage.music.musicVolume(1f);
                    MenuStage.music.MenuMusic();
                    volumeArraySettings();
                    //musicOnOff();
                }else{
                    clicked = false;
                    //MenuStage.music.musicVolume(-0.1f);
                    MenuStage.music.musicVolume(0f);
                    volumeArraySettings();
                    //musicOnOff();
                }
                musicOnOff();
            }
        });

        volumeIconSpriteActor.setSize(width / 3, width / 3);
        volumeIconSpriteActor.setPosition(0, heigth - volumeIconSpriteActor.getHeight()-((ExtendViewport)getViewport()).getMinWorldHeight()/4);
    }

    void musicVolume(){
        volumePlusSpriteActor = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUS_VOL));
        volumeMinusSpriteActor = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUS_VOL));
        addActor(volumePlusSpriteActor); addActor(volumeMinusSpriteActor);
        volumePlusSpriteActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(!musicPlay && !clicked && PlayScreen.gameMusic.getGameVolume() == 0){
                    volumeIconSpriteActor.remove();
                    musicPlay = true;
                    MenuStage.music.musicVolume(1f);
                    MenuStage.music.MenuMusic();
                    musicOnOff();
                }
                else if(MenuStage.music.getMenuVolume() == 0){
                    volumeIconSpriteActor.remove();
                    musicPlay = true;
                    MenuStage.music.musicVolume(0.09999993f);
                    MenuStage.music.MenuMusic2();
                    MenuStage.music = new MusicSetter(true);
                    musicOnOff();
                }
                else if(MenuStage.music.getMenuVolume()<=0.9){
                    MenuStage.music.musicVolume(MenuStage.music.getMenuVolume()+0.1f);
                }
                PlayScreen.gameMusic.musicVolume(MenuStage.music.getMenuVolume());
                volumeArraySettings();
            }
        });
        volumeMinusSpriteActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(MenuStage.music.getMenuVolume()>=0){
                    MenuStage.music.musicVolume(MenuStage.music.getMenuVolume()-0.1f);
                    volumeArraySettings();
                }
                if(MenuStage.music.getMenuVolume()<0){
                    volumeIconSpriteActor.remove();
                    musicPlay = false;
                    IngameSettingsStage.musicPlay = musicPlay;
                    MenuStage.music.musicVolume(0);
                    clicked = true;
                    musicOnOff();
                }
                PlayScreen.gameMusic.musicVolume(MenuStage.music.getMenuVolume());
            }
        });
        volumeArraySettings();
    }

    public void volumeArraySettings(){
        volumeArray = new Array<OneSpriteStaticActor>();
        for(float i=0; i<1; i+=0.1){
            if(i<MenuStage.music.getMenuVolume()){
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
