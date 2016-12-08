package com.mygdx.game.End;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DemoMenu.MenuScreen;
import com.mygdx.game.DemoMenu.MenuStage;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Music.MusicSetter;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayStage;

/**
 * Created by mordes on 2016.12.03..
 */
public class EndStage extends MyStage {

    private TextButton textButton;

    private float width, heigthBetween, heigth;

    private MyLabel myLabel, myLabel2, myLabel3, myLabel4, myLabel5, myLabel6;

    private Preferences preferences;

    public static final String SCORE = "Score";

    public EndStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {
        preferences = Gdx.app.getPreferences(MenuScreen.PREFS); //ez a kilépés után mentés
        addBackEventStackListener();

        resized();

        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.fontColor = Color.YELLOW;

        //átméretezés
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("c64.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter meret = new FreeTypeFontGenerator.FreeTypeFontParameter();
        meret.size = 20;
        meret.characters = Assets.CHARS;
        BitmapFont font = generator.generateFont(meret);
        generator.dispose();
        style.font = font;
        //átméretezés vége

        textButton = new MyButton("Menu", game.getTextButtonStyle());
        textButton.setPosition(width - textButton.getWidth()/2,heigth);
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                new MusicSetter(true);
                game.setBackButtonStack();
                game.setScreen(new MenuScreen(game));
            }
        });

        addActor(textButton);
        float arany = (((ExtendViewport)getViewport()).getMinWorldWidth())/Assets.manager.get(Assets.ROAD_BLOCK).getWidth();

        myLabel = new MyLabel(PlayStage.scoreNumber+"", style);
        myLabel.setPosition(((ExtendViewport)getViewport()).getMinWorldWidth()-myLabel.getWidth()-10,
                (((ExtendViewport)getViewport()).getMinWorldHeight()) * (3/4f) - myLabel.getHeight()/2);
        addActor(myLabel);

        myLabel2 = new MyLabel("Score: ", style);
        myLabel2.setPosition(10,
                (((ExtendViewport)getViewport()).getMinWorldHeight()) * (3/4f) - myLabel2.getHeight()/2);
        addActor(myLabel2);

        myLabel3 = new MyLabel(preferences.getInteger(SCORE, 0) + "", style);
        myLabel3.setPosition(((ExtendViewport)getViewport()).getMinWorldWidth()-myLabel3.getWidth()-10,
                myLabel.getY() - myLabel.getHeight()*2);
        addActor(myLabel3);

        myLabel4 = new MyLabel("Best score: ", style);
        myLabel4.setPosition(10,
                myLabel2.getY() - myLabel2.getHeight()*2);
        addActor(myLabel4);

        myLabel5 = new MyLabel(PlayStage.timerOut[0]+":"+PlayStage.timerOut[1], style);
        myLabel5.setPosition(((ExtendViewport)getViewport()).getMinWorldWidth()-myLabel5.getWidth()-10,
                myLabel3.getY() - myLabel3.getHeight()*2);
        addActor(myLabel5);

        myLabel6 = new MyLabel("Time: ", style);
        myLabel6.setPosition(10,
                myLabel4.getY() - myLabel4.getHeight()*2);
        addActor(myLabel6);



    }

    @Override
    protected void resized() {
        super.resized();
        width = (((ExtendViewport)getViewport()).getMinWorldWidth())/2; //vízszintesen középre
        //heigthBetween = (((ExtendViewport)getViewport()).getMinWorldHeight())/4; //egyenletesen elosztva 3 menüponthoz
        heigth = 0; //magasság
        //heigth -= heigthBetween;


        //creditText.setPosition(width - ((creditText.getWidth())/2), textButton.getHeight() + creditText.getHeight());
    }
}
