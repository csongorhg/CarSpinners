package com.mygdx.game.Credits;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Music.MusicSetter;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;

/**
 * Created by mordes on 2016.11.10..
 */
public class CreditsStage extends MyStage{

    private TextButton textButton;
    private MyLabel creditText;

    //itt kell megadni, a pozicionálást!!!
    private float width, heigthBetween, heigth;

    public CreditsStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {
        addBackEventStackListener();

        /*creditText = new MyLabel("Készítők:\nHegedűs Csongor\nHorváth Patrik\nEgyed Vince\nKiss Norbert\nFelkészítő tanár:\nTüske Balázs", game.getLabelStyle());
        addActor(creditText);*/

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
        //heigthBetween = (((ExtendViewport)getViewport()).getMinWorldHeight())/4; //egyenletesen elosztva 3 menüponthoz
        heigth = 0; //magasság
        //heigth -= heigthBetween;
        textButton.setPosition(width - ((textButton.getWidth())/2),heigth);


        //creditText.setPosition(width - ((creditText.getWidth())/2), textButton.getHeight() + creditText.getHeight());
    }
}
