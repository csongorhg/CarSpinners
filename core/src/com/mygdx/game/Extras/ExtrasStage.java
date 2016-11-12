package com.mygdx.game.Extras;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.CarClasses.CarTunningScreen;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Settings.SettingsScreen;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class ExtrasStage extends MyStage {

    private TextButton textButton, textButton2, textButton3;
    private OneSpriteStaticActor car;

    //itt kell megadni, a pozicionálást!!!
    private float width, heigthBetween, heigth;

    public ExtrasStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {
        addBackEventStackListener();

        textButton2 = new MyButton("Settings", game.getTextButtonStyle());
        textButton2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new SettingsScreen(game));
            }
        });
        addActor(textButton2);

        textButton3 = new MyButton("Car tunning", game.getTextButtonStyle());
        textButton3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new CarTunningScreen(game));
            }
        });
        addActor(textButton3);

        car = new OneSpriteStaticActor(Assets.manager.get(Assets.CAR_TEXTURE));
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
        addActor(textButton);

        resized();
    }

    @Override
    protected void resized() {
        super.resized();
        width = (((ExtendViewport)getViewport()).getMinWorldWidth())/2; //vízszintesen középre
        heigthBetween = (((ExtendViewport)getViewport()).getMinWorldHeight())/5; //egyenletesen elosztva 3 menüponthoz
        heigth = (((ExtendViewport)getViewport()).getMinWorldHeight()); //magasság
        heigth -= heigthBetween;
        textButton2.setPosition(width - ((textButton2.getWidth())/2), heigth);
        heigth -= heigthBetween;
        textButton3.setPosition(width - ((textButton3.getWidth())/2), heigth);

        textButton.setPosition(width - ((textButton.getWidth())/2),0);

        car.setWidth(width*2 - 10);
        car.setPosition(width - ((car.getWidth())/2), ((textButton3.getY()-textButton.getHeight())/2 - car.getHeight()/2)+textButton.getHeight());

    }
}