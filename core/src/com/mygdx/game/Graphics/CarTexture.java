package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.*;
import com.mygdx.game.GlobalClasses.Assets;

/**
 * Created by Pocok on 2016.11.12..
 */

public class CarTexture {

    public Pixmap p;

    Color c;
    int cartype = 0;

    public CarTexture(){
        //setCarType(0);
        setColor(100,100,255); //itt lehet beállítani a színt most még hirtelen
    }

    public void setCarType(int i) {
        Texture t = Assets.manager.get(Assets.CAR_TEXTURE);
        p = t.getTextureData().consumePixmap();
    }

    public Texture getPaint() {
        Texture t = Assets.manager.get(Assets.CAR_TEXTURE);
        t.getTextureData().prepare();
        p = t.getTextureData().consumePixmap();
        p = Graphics.reColor(p,Graphics.fullColor(255,255,255,1),c);
        return new Texture(p);
    }


    public void setColor(int i, int i1, int i2) {
        c = Graphics.fullColor(i,i1,i2,1);
    }


}
