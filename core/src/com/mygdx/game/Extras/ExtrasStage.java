package com.mygdx.game.Extras;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Physics.Car;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class ExtrasStage extends MyStage {

    private TextButton textButton, textButton3;
    private OneSpriteStaticActor car;
    private Slider slider1, slider2, slider3, slider4;
    private static float slider1value = Car.carTexture.r, slider2value = Car.carTexture.g, slider3value = Car.carTexture.b;
    private static int carTypeNumber = Car.carTexture.cartype, carStyleNumber = Car.carTexture.carTextureType;
    private OneSpriteStaticActor arrow, arrow2;

    //itt kell megadni, a pozicionálást!!!
    private float width, heigthBetween, heigth;

    public ExtrasStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {

        addBackEventStackListener();

        resized();

        textButton = new MyButton("Back", game.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
        textButton.setPosition(width - textButton.getWidth()/2 ,0);
        addActor(textButton);

        car(); //autó létrehozása

        sliders(); //színbeállító létrehozása

        arrows(); //autó váltó nyilak lértehozása

    }

    void car(){
        Car.carTexture.setCarType(carTypeNumber);
        Car.carTexture.setCarTextureTypeType(carStyleNumber);
        car = new OneSpriteStaticActor(Car.carTexture.getPaint());
        car.setSize(car.getWidth()*3,car.getHeight()*3);
        car.setPosition(width-(car.getWidth()/2), heigth-car.getHeight()-10);
        addActor(car);
    }

    void sliders(){

        //stílus
        slider4 = new Slider(0, 3, 1, false, game.getSliderStyle());
        addActor(slider4);
        slider4.setValue(carStyleNumber);
        slider4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                carStyleNumber = (int)slider4.getValue();
                car.remove();
                car();
            }
        });
        slider4.setWidth(width + width/2);
        slider4.setPosition(width-slider4.getWidth()/2, car.getY()-slider4.getHeight()-10);

        //red
        slider1 = new Slider(0, 255, 1, false, game.getSliderStyle());
        addActor(slider1);
        slider1.setValue(slider1value);
        slider1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Car.carTexture.r = (int)slider1.getValue();
                Car.carTexture.setColorTo();
                car.remove();
                car();
                slider1value = slider1.getValue();
            }
        });
        slider1.setWidth(width + width/2);
        slider1.setPosition(width-slider1.getWidth()/2, slider4.getY()-slider1.getHeight()-10);

        //green
        slider2 = new Slider(0, 255, 1, false, game.getSliderStyle());
        addActor(slider2);
        slider2.setValue(slider2value);
        slider2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Car.carTexture.g = (int)slider2.getValue();
                Car.carTexture.setColorTo();
                car.remove();
                car();
                slider2value = slider2.getValue();
            }
        });
        slider2.setWidth(width + width/2);
        slider2.setPosition(width-slider2.getWidth()/2, slider1.getY()-slider2.getHeight()-10);

        //blue
        slider3 = new Slider(0, 255, 1, false, game.getSliderStyle());
        addActor(slider3);
        slider3.setValue(slider3value);
        slider3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Car.carTexture.b = (int)slider3.getValue();
                Car.carTexture.setColorTo();
                car.remove();
                car();
                slider3value = slider3.getValue();
            }
        });
        slider3.setWidth(width + width/2);
        slider3.setPosition(width-slider3.getWidth()/2, slider2.getY()-slider3.getHeight()-10);

    }

    void arrows(){
        //right
        arrow = new OneSpriteStaticActor(Assets.manager.get(Assets.ARROW));
        arrow.setSize(arrow.getWidth()*2, arrow.getHeight()*2);
        arrow.setPosition(width*2-arrow.getWidth(), car.getY()+car.getY()/2-arrow.getHeight()/2);
        arrow.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(carTypeNumber>1) {
                    carTypeNumber--;
                    car.remove();
                    car();
                }
            }
        });
        addActor(arrow);

        //left
        arrow2 = new OneSpriteStaticActor(Assets.manager.get(Assets.ARROW));
        arrow2.setRotation(180);
        arrow2.setSize(arrow2.getWidth()*2, arrow2.getHeight()*2);
        arrow2.setPosition(0, car.getY()+car.getY()/2-arrow2.getHeight()/2);
        arrow2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(carTypeNumber<4) {
                    carTypeNumber++;
                    car.remove();
                    car();
                }
            }
        });
        addActor(arrow2);

    }


    @Override
    protected void resized() {
        super.resized();

        width = (((ExtendViewport)getViewport()).getMinWorldWidth())/2; //vízszintesen középre
        heigthBetween = (((ExtendViewport)getViewport()).getMinWorldHeight())/10; //egyenletesen elosztva 3 menüponthoz
        heigth = (((ExtendViewport)getViewport()).getMinWorldHeight()); //magasság
        heigth -= heigthBetween;
    }
}