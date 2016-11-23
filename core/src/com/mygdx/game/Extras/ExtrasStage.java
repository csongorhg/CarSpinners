package com.mygdx.game.Extras;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.CarClasses.CarTunningScreen;
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

    private TextButton textButton, textButton2, textButton3;
    private OneSpriteStaticActor car;

    //itt kell megadni, a pozicionálást!!!
    private float width, heigthBetween, heigth;

    private OneSpriteStaticActor arrow, arrow2;

    public ExtrasStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {

        addBackEventStackListener();

        resized();

        textButton3 = new MyButton("Car tunning", game.getTextButtonStyle());
        textButton3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new CarTunningScreen(game));
            }
        });
        textButton3.setPosition(width - ((textButton3.getWidth())/2), heigth);

        addActor(textButton3);

        car = new OneSpriteStaticActor(Car.carTexture.getPaint());
        car.setSize(car.getWidth()*3,car.getHeight()*3);
        car.setPosition(width-(car.getWidth()/2), (float) (heigth/3.5));
        addActor(car);

        car.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });


        textButton = new MyButton("Back", game.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
        textButton.setPosition(width - ((textButton.getWidth())/2),0);
        addActor(textButton);



        arrow = new OneSpriteStaticActor(Assets.manager.get(Assets.ARROW));
        arrow.setSize(arrow.getWidth()*2, arrow.getHeight()*2);
        arrow.setPosition(width*2-arrow.getWidth(), car.getY()+car.getY()/2);
        arrow.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                car.remove();
                car = new OneSpriteStaticActor(Assets.manager.get(Assets.BLOCAKDE_1));
                car.setSize(car.getWidth()*3,car.getHeight()*3);
                car.setPosition(width-(car.getWidth()/2), (float) (heigth/3.5));
                addActor(car);
            }
        });

        addActor(arrow);



        arrow2 = new OneSpriteStaticActor(Assets.manager.get(Assets.ARROW));
        arrow2.setRotation(180);
        arrow2.setSize(arrow2.getWidth()*2, arrow2.getHeight()*2);
        arrow2.setPosition(0, car.getY()+car.getY()/2);

        addActor(arrow2);

    }

    @Override
    protected void resized() {
        super.resized();

        width = (((ExtendViewport)getViewport()).getMinWorldWidth())/2; //vízszintesen középre
        heigthBetween = (((ExtendViewport)getViewport()).getMinWorldHeight())/5; //egyenletesen elosztva 3 menüponthoz
        heigth = (((ExtendViewport)getViewport()).getMinWorldHeight()); //magasság
        heigth -= heigthBetween;

        //textButton2.setPosition(width - ((textButton2.getWidth())/2), heigth);

        //heigth -= heigthBetween;
    }
}