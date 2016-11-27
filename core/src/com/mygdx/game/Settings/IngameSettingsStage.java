package com.mygdx.game.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
import com.mygdx.game.Play.PlayScreen;
import com.mygdx.game.Play.PlayStage;

/**
 * Created by Vince on 2016. 11. 13..
 */

public class IngameSettingsStage extends MyStage {

    private TextButton textButton, textButton2;
    private OneSpriteStaticActor volumeIconSpriteActor,volumePlusSpriteActor, volumeMinusSpriteActor, backgroundSpriteActor;
    public static boolean musicPlay = true;
    MusicSetter musicSetter = new MusicSetter();
    private Array<OneSpriteStaticActor> volumeArray;
    public static float actualVol = 1;
    private float width, height;
    private boolean visible = false;
    private InputProcessor prevInputProcessor = null;

    public IngameSettingsStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK)
        {
            game.setScreenBackByStackPop();
            SettingsStage.musicPlay=musicPlay;
            musicSetter.stopMusics();
            if(musicPlay)musicSetter.MenuMusic();
        }
        return false;
    }

    public void init() {
        addBackEventStackListener();

        backgroundSpriteActor = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND_TEXTURE));
        backgroundSpriteActor.setWidth(((ExtendViewport) getViewport()).getMinWorldWidth());
        backgroundSpriteActor.setHeight(((ExtendViewport) getViewport()).getMinWorldHeight()/2);
        backgroundSpriteActor.setY(((ExtendViewport) getViewport()).getMinWorldHeight()/4);
        addActor(backgroundSpriteActor);

        textButton2 = new MyButton("Back to Game", game.getTextButtonStyle());
        textButton2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                PlayStage.setSettingclick(false);
                Hide();
            }
        });
        addActor(textButton2);

        textButton = new MyButton("Back to Menu", game.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                musicSetter.stopMusics();
                if(musicPlay)musicSetter.MenuMusic();
                SettingsStage.musicPlay=musicPlay;
                game.setScreenBackByStackPop();
            }
        });
        addActor(textButton);

        resized();

        musicOnOff();
        musicVolume();
    }

    void musicOnOff(){
        volumeIconSpriteActor = new OneSpriteStaticActor(Assets.manager.get(musicPlay?Assets.SOUND_ICON:Assets.MUTE_ICON));
        addActor(volumeIconSpriteActor);
        volumeIconSpriteActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                volumeIconSpriteActor.remove();
                musicPlay = !musicPlay;
                SettingsStage.musicPlay = musicPlay;
                new MusicSetter(musicPlay);
                musicOnOff();
            }
        });
        volumeIconSpriteActor.setSize(width / 3, width / 3);
        volumeIconSpriteActor.setPosition(0, height - volumeIconSpriteActor.getHeight()-((ExtendViewport)getViewport()).getMinWorldHeight()/4);
    }

    @Override
    protected void resized() {
        super.resized();
        width = (((ExtendViewport)getViewport()).getMinWorldWidth())/2; //vízszintesen középre
        height = (((ExtendViewport)getViewport()).getMinWorldHeight()); //magasság

        textButton.setWidth(width*2);
        textButton2.setWidth(width*2);

        textButton.setPosition(width - ((textButton.getWidth()) / 2), height-((ExtendViewport)getViewport()).getMinWorldHeight()/4*3);
        textButton2.setPosition(width - ((textButton.getWidth()) / 2),height-((ExtendViewport)getViewport()).getMinWorldHeight()/5*3);
    }

    void musicVolume(){
        volumePlusSpriteActor = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUS_VOL));
        volumeMinusSpriteActor = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUS_VOL));
        addActor(volumePlusSpriteActor); addActor(volumeMinusSpriteActor);
        volumePlusSpriteActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(actualVol<=0.9){
                    actualVol+=0.1;
                    SettingsStage.actualVol = actualVol;
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
                    actualVol-=0.1;
                    SettingsStage.actualVol = actualVol;
                    musicSetter.musicVolume(actualVol<=0?0:actualVol);
                    volumeArraySettings();
                }
            }
        });
        volumeArraySettings();
    }

    void volumeArraySettings(){
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
        volumeSize(width, height/2);
    }

    void volumeSize(float width, float heigth){
        float meretGomb = heigth/6;
        volumeMinusSpriteActor.setSize(meretGomb, meretGomb);
        volumePlusSpriteActor.setSize(meretGomb, meretGomb);
        volumeMinusSpriteActor.setPosition(0,heigth);
        volumePlusSpriteActor.setPosition(width*2-volumePlusSpriteActor.getWidth(), heigth);
        for(int i=0; i<volumeArray.size; i++){
            volumeArray.get(i).setSize((volumePlusSpriteActor.getX()-volumeMinusSpriteActor.getWidth())/10,(volumePlusSpriteActor.getX()-volumeMinusSpriteActor.getWidth())/10);
            volumeArray.get(i).setPosition(volumeMinusSpriteActor.getWidth()+i*volumeArray.get(i).getWidth(), heigth+volumeArray.get(i).getHeight()/2);
        }
    }


    public static boolean isB() {
        return musicPlay;
    }


    public void Show(){
        visible = true;
        if (prevInputProcessor== null) prevInputProcessor = Gdx.input.getInputProcessor();
        Gdx.input.setInputProcessor(this);
    }

    public void Hide(){
        Gdx.input.setInputProcessor(prevInputProcessor);
        visible = false;
        prevInputProcessor = null;
    }

    @Override
    public void act(float delta) {
        if (visible) super.act(delta);
    }

    @Override
    public void draw() {
        if (visible) super.draw();
    }


}