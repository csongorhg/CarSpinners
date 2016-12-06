package com.mygdx.game.DemoMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.Graphics.ButtonCaller;
import com.mygdx.game.Physics.Physics;
import com.mygdx.game.Play.PlayScreen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Settings.SettingsScreen;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends MyStage {

    private TextButton textButton, textButton2, textButton3, textButton4, textButton5;

    private MusicSetter music = new MusicSetter();

    private float width, heigthBetween, heigth; //menüpontok pozicionálása

    private  Array <OneSpriteStaticActor> moneyStream; //úszó pénzek
    private int numberOfMoney; //pénzek száma
    private int moneySpeeds[]; //pénzek sebbesége
    private int moneySpeedsinterval[]; //pénzek sebességének intervalluma

    private Preferences preferences = Gdx.app.getPreferences(MenuScreen.PREFS); //ez a kilépés után mentés
    private MyLabel deadufocountLabel;
    public static final String STORED_SCORE = "STORED_SCORE";

    //private OneSpriteStaticActor cityStream, cityStream2; //úszó város
    //private int cityStreamSpeed;

    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init()
    {

        addBackEventStackListener();

        addActor(deadufocountLabel = new MyLabel("", game.getLabelStyle()){
            @Override
            public void init() {
                super.init();

                setFontScale(0.5f);
                setWidth(50);
                setHeight(50);
            }

        });

        //cityStream(); //városfolyam
        CityStream cityStream;
        addActor(cityStream = new CityStream());
        cityStream.setX(getViewport().getWorldWidth()-cityStream.getWidth());

        moneyStream(getCityStreamHeight()); //pénzfolyam



        //fogaskerék
        textButton5 = new ButtonCaller("", Assets.CONF_ICON);
        textButton5.setSize(45,45);
        textButton5.setY((((ExtendViewport)getViewport()).getMinWorldHeight())
        - textButton5.getHeight());
        textButton5.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            game.setScreen(new SettingsScreen(game));
            preferences.putInteger(MenuStage.STORED_SCORE, preferences.getInteger(MenuStage.STORED_SCORE,0)+1);

        }
    });

        addActor(textButton5);


        //Játék
        textButton = new MyButton("Play", game.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Physics.carspeed = 0;
                Physics.policedis = 1000;
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

    private void moneyStream(float bottom) {

        int heigth = Math.round(((ExtendViewport)getViewport()).getMinWorldHeight());

        moneyStream = new Array<OneSpriteStaticActor>();
        numberOfMoney = 10; //párhuzamosan futó pénzek száma
        moneySpeeds = new int[numberOfMoney];
        moneySpeedsinterval = new int[2];
        moneySpeedsinterval[0] = 2; // legkisebb sebessége a pénznek
        moneySpeedsinterval[1] = 5; //legnagyobb sebessége a pénznek
        int i = 0; //segédváltozó

        while (i != numberOfMoney) {

            moneyStream.add(new OneSpriteStaticActor(Assets.manager.get(Assets.MONEY_TEXTURE)));
            moneyStream.get(i).setSize(57, 25);
            moneyStream.get(i).setPosition(0 - moneyStream.get(i).getWidth(),
                    new Random(bottom, heigth).getGenNumber());

            addActor(moneyStream.get(i));

            moneySpeeds[i] =  new Random(moneySpeedsinterval[0],moneySpeedsinterval[1]).getGenNumber();
            moneyStream.get(i).act(Gdx.graphics.getDeltaTime());
            i++;
        }
    }

    private float getCityStreamHeight(){
        for (Actor a: getActors()) {
            if (a instanceof CityStream){
                return a.getHeight();
            }
        }
        return 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        deadufocountLabel.setText(preferences.getInteger(MenuStage.STORED_SCORE, 0) + "");

        music.MenuMusic();

        //pénzfolyam
        for (int i = 0; i < moneyStream.size; i++) {
            //ha véget ér előről
            int random = moneySpeeds[i];
            moneyStream.get(i).setPosition(moneyStream.get(i).getX()+random,moneyStream.get(i).getY());
            if (moneyStream.get(i).getX()>(((ExtendViewport)getViewport()).getWorldWidth())) {
                moneyStream.get(i).setPosition(0-moneyStream.get(i).getWidth(),
                new Random(getCityStreamHeight(),(((ExtendViewport)getViewport()).getMinWorldHeight())).getGenNumber());
                moneySpeeds[i] = new Random(moneySpeedsinterval[0],moneySpeedsinterval[1]).getGenNumber();
            }
        }

        //városfolyam
        /*
        if (cityStream.getX() > ((ExtendViewport)getViewport()).getMinWorldWidth()) {
            cityStream.setX(cityStream2.getX()-cityStream.getWidth());
        }
        cityStream.setX(cityStream.getX()+cityStreamSpeed);

        if (cityStream2.getX() > ((ExtendViewport)getViewport()).getMinWorldWidth()) {
            cityStream2.setX(cityStream.getX() - cityStream.getWidth());
        }
        cityStream2.setX(cityStream2.getX()+cityStreamSpeed);*/
    }

    @Override
    public void dispose() {
        preferences.flush();
        super.dispose();
    }

    @Override
    protected void resized() {
        super.resized();
        setCameraResetToLeftBottomOfScreen();
        width = (((ExtendViewport)getViewport()).getWorldWidth())/2; //vízszintesen középre
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
