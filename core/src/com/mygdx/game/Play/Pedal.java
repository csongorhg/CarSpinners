package com.mygdx.game.Play;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.InitableInterface;

/**
 * Created by Pocok on 2016.11.13..
 */

public class Pedal extends TextButton implements InitableInterface {



    public Pedal(String text, AssetDescriptor<Texture> assets) {
        super(text, getTextButtonStyle(assets));
        init();
    }

     static TextButtonStyle getTextButtonStyle(AssetDescriptor<Texture> assets) {

         TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
         textButtonStyle.font = Assets.manager.get(Assets.FONT_C64);


         Pixmap p = new Pixmap(18,19, Pixmap.Format.RGBA8888);
         Texture t = Assets.manager.get(assets);
         t.getTextureData().prepare();
         p.drawPixmap(t.getTextureData().consumePixmap(),0,0);
         textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(p)));

         Pixmap p2 = new Pixmap(18,19, Pixmap.Format.RGBA8888);
         Texture t2 = Assets.manager.get(assets);
         t2.getTextureData().prepare();
         p2.drawPixmap(t2.getTextureData().consumePixmap(),0,2);
         textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(p2)));
         return textButtonStyle;
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
