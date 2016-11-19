package com.mygdx.game.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Graphics.ButtonCaller;
import com.mygdx.game.Physics.Car;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class PlayStage extends MyStage {

    //private TextButton textButton;

    //itt kell megadni, a pozicionálást!!!
    private float width, heigthBetween, heigth;

    public static boolean settingclick = false;

    private ButtonCaller p, f, textButton5; //gáz, fékpedál

    private OneSpriteStaticActor oneSpriteStaticActor; //út

    private OneSpriteStaticActor heart[]; //szivek eltárolása
    private int currentHeart; //jelenlegi szív

    private Car car;

    public PlayStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK)
        {
            settingclick = true;
        }
        return false;
    }

    public static boolean setSettingclick(boolean b){
        settingclick = b;
        return settingclick;
    }

    public void init() {
        addBackEventStackListener();

        resized();

        heart = new OneSpriteStaticActor[Car.maxheart]; //szivek

        //út
        float blockHeight = (((ExtendViewport)getViewport()).getMinWorldHeight());

        for(int i = 0; i<4 ;i++){
            oneSpriteStaticActor = new OneSpriteStaticActor(Assets.manager.get(Assets.ROAD_BLOCK));
            float arany = (((ExtendViewport)getViewport()).getMinWorldWidth())/oneSpriteStaticActor.getWidth();
            oneSpriteStaticActor.setSize(oneSpriteStaticActor.getWidth()*arany,oneSpriteStaticActor.getHeight()*arany);
            blockHeight -= oneSpriteStaticActor.getHeight();
            oneSpriteStaticActor.setPosition(0,blockHeight);
            addActor(oneSpriteStaticActor);
        }

        //fogaskerék
        textButton5 = new ButtonCaller("", Assets.CONF_ICON);
        textButton5.setSize(45,45);
        textButton5.setY((((ExtendViewport)getViewport()).getMinWorldHeight())
                - textButton5.getHeight());
        textButton5.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                //TESZT szívcsökkentés
                if (currentHeart < Car.maxheart) {
                    OneSpriteStaticActor emptyHeart = new OneSpriteStaticActor(Assets.manager.get(Assets.NOHEART));
                    emptyHeart.setPosition(heart[currentHeart].getX(), heart[currentHeart].getY());
                    emptyHeart.setSize(heart[currentHeart].getWidth(), heart[currentHeart].getHeight());
                    heart[currentHeart].remove();
                    addActor(emptyHeart);
                    currentHeart++;
                }
                //TESZT szívcsökkentés

                setSettingclick(true);
            }
        });

        addActor(textButton5);

        OneSpriteStaticActor felulet = new OneSpriteStaticActor(Assets.manager.get(Assets.ROAD_MENU));
        float arany = width/felulet.getWidth();
        felulet.setSize(felulet.getWidth()*arany,felulet.getHeight()*arany);
        addActor(felulet);

       //gázpedál
        p = new ButtonCaller("", Assets.GAZ_ICON);
        p.setSize(54,57); //18*3, 19*3
        p.setX((((ExtendViewport)getViewport()).getMinWorldWidth()) - p.getWidth()*0.90f);

        addActor(p);

        //fékpedál
        f = new ButtonCaller("", Assets.FEK_ICON);
        f.setSize(54,57); //18*3, 19*3

        addActor(f);

        car = new Car(width/2 - Car.carTexture.getPaint().getWidth()/2,heigth/10,Car.carTexture.getPaint().getWidth(),Car.carTexture.getPaint().getHeight());
        addActor(car.carActor);
        car.carActor.act(Gdx.graphics.getDeltaTime());

        OneSpriteStaticActor kocka = new OneSpriteStaticActor(Assets.manager.get(Assets.MONEY_TEXTURE));
        kocka.setWidth(width/5);
        kocka.setPosition(width/5*3,350);
        addActor(kocka);

        //szivek
        int x = 30 * Car.maxheart; //szivek szélessége
        currentHeart = 0; //jelenlegi szív

        for (int i = 0; i < Car.maxheart; i++){ //tömbként generálom
            heart[i] = new OneSpriteStaticActor(Assets.manager.get(Assets.HEART));
            heart[i].setSize(30,30);
            heart[i].setX(width-x);
            heart[i].setY(heigth-30);
            addActor(heart[i]);
            x-=30;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        car.carActor.setPosition(car.carActor.getX()-(Gdx.input.getAccelerometerX()/4),car.carActor.getY());
        if(car.carActor.getX()+car.carActor.getWidth() > width/5*4) car.carActor.setPosition(width/5*4-car.carActor.getWidth(),car.carActor.getY());
        if(car.carActor.getX()< width/5) car.carActor.setPosition(width/5,car.carActor.getY());
    }

    @Override
    protected void resized() {
        super.resized();
        width = (((ExtendViewport)getViewport()).getMinWorldWidth());
        heigth = (((ExtendViewport)getViewport()).getMinWorldHeight());

    }
}