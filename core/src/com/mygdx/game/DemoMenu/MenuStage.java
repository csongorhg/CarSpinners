package com.mygdx.game.DemoMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
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
import com.mygdx.game.Settings.SettingsStage;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends MyStage {

    private TextButton textButton, textButton2, textButton3, textButton4;

    private MusicSetter music = new MusicSetter();

    private float width, heigthBetween, heigth; //menüpontok pozicionálása

    private  Array <OneSpriteStaticActor> moneyStream; //úszó pénzek
    private int numberOfMoney; //pénzek száma
    private int moneySpeeds[]; //pénzek sebbesége
    private int moneySpeedsinterval[]; //pénzek sebességének intervalluma

    private OneSpriteStaticActor cityStream, cityStream2; //úszó város
    private int cityStreamSpeed;

    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init()
    {

        addBackEventStackListener();

        cityStream(); //városfolyam
        moneyStream(); //pénzfolyam

        //Játék
        textButton = new MyButton("Play", game.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //if(SettingsStage.isB())new MusicSetter(new Random(1,5).getGenNumber());
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

    private void moneyStream() {

        int heigth = Math.round(((ExtendViewport)getViewport()).getMinWorldHeight());

        moneyStream = new Array<OneSpriteStaticActor>();
        numberOfMoney = 10; //párhuzamosan futó pénzek száma
        moneySpeeds = new int[numberOfMoney];
        moneySpeedsinterval = new int[2];
        moneySpeedsinterval[0] = 2; // legkisebb sebessége a pénznek
        moneySpeedsinterval[1] = 4; //legnagyobb sebessége a pénznek
        int i = 0; //segédváltozó

        while (i != numberOfMoney) {

            moneyStream.add(new OneSpriteStaticActor(Assets.manager.get(Assets.MONEY_TEXTURE)));
            moneyStream.get(i).setSize(57, 25);
            moneyStream.get(i).setPosition(0 - moneyStream.get(i).getWidth(),
                    new Random(cityStream.getHeight(), heigth).getGenNumber());

            addActor(moneyStream.get(i));

            moneySpeeds[i] =  new Random(moneySpeedsinterval[0],moneySpeedsinterval[1]).getGenNumber();
            moneyStream.get(i).act(Gdx.graphics.getDeltaTime());
            i++;
        }
    }



    private void cityStream() {

        cityStreamSpeed = 1; //városfolyam sebessége

        cityStream = new OneSpriteStaticActor(Assets.manager.get(Assets.CITY_ACTION_BACKGROUND));
        cityStream.setWidth(((ExtendViewport)getViewport()).getMinWorldWidth());
        addActor(cityStream);

        cityStream2 = new OneSpriteStaticActor(Assets.manager.get(Assets.CITY_ACTION_BACKGROUND));
        cityStream2.setWidth(((ExtendViewport)getViewport()).getMinWorldWidth());
        cityStream2.setX(0 - cityStream.getWidth());
        addActor(cityStream2);

        cityStream.act(Gdx.graphics.getDeltaTime());
        cityStream2.act(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        music.MenuMusic();

        //pénzfolyam
        for (int i = 0; i < moneyStream.size; i++) {
            //ha véget ér előről
            int random = moneySpeeds[i];
            moneyStream.get(i).setPosition(moneyStream.get(i).getX()+random,moneyStream.get(i).getY());
            if (moneyStream.get(i).getX()>(((ExtendViewport)getViewport()).getMinWorldWidth())) {
                moneyStream.get(i).setPosition(0-moneyStream.get(i).getWidth(),
                new Random(cityStream.getHeight(),(((ExtendViewport)getViewport()).getMinWorldHeight())).getGenNumber());
                moneySpeeds[i] = new Random(moneySpeedsinterval[0],moneySpeedsinterval[1]).getGenNumber();
            }
        }

        //városfolyam
        if (cityStream.getX() > ((ExtendViewport)getViewport()).getMinWorldWidth()) {
            cityStream.setX(cityStream2.getX()-cityStream.getWidth());
            System.out.println(1);
        }
        cityStream.setX(cityStream.getX()+cityStreamSpeed);

        if (cityStream2.getX() > ((ExtendViewport)getViewport()).getMinWorldWidth()) {
            System.out.println(2);
            cityStream2.setX(0+cityStream.getX() - cityStream.getWidth());
        }
        cityStream2.setX(cityStream2.getX()+cityStreamSpeed);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    protected void resized() {
        super.resized();

        width = (((ExtendViewport)getViewport()).getMinWorldWidth())/2; //vízszintesen középre
        heigthBetween = (((ExtendViewport)getViewport()).getMinWorldHeight())/5; //egyenletesen elosztva 3 menüponthoz
        heigth = (((ExtendViewport)getViewport()).getMinWorldHeight()); //magasság

        heigth -= heigthBetween;
        textButton.setPosition(width - ((textButton.getWidth())/2),heigth- ((textButton.getHeight())/2));

        heigth -= heigthBetween;
        textButton3.setPosition(width - ((textButton3.getWidth())/2),heigth- ((textButton.getHeight())/2));

        heigth -= heigthBetween;
        textButton2.setPosition(width - ((textButton2.getWidth())/2),heigth- ((textButton.getHeight())/2));

        heigth -= heigthBetween;
        textButton4.setPosition(width - ((textButton4.getWidth())/2),heigth- ((textButton.getHeight())/2));
    }


}
