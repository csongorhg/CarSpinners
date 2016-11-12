package com.mygdx.game.MyBaseClasses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Graphics.Buttons;
import com.mygdx.game.Math.Random;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MyButton extends TextButton implements InitableInterface{

    public MyButton(String text, TextButtonStyle textButtonStyle) {
        super(text, textButtonStyle);
        init();
    }

    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
