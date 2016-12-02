package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.*;
import com.mygdx.game.GlobalClasses.Assets;


/**
 * Created by Pocok on 2016.11.12..
 */

public class CarTexture {

    public Pixmap p;
    public Pixmap text;
    public int r=20, g=20, b=255;
    public int r2=0, g2=0, b2=0;

    Color c;
    Color c2;
    int cartype = 1;
    int carTextureType = 0;

    public CarTexture(int ct,int ctt){
        setCarType(ct);
        setCarTextureTypeType(ctt);
        setColor(r,g,b);
        setColorText(r2,g2,b2);
    }

    public void setCarType(int i) {
        if(i>0 && i<5) {
            Texture t;
            if(i == 1){
                t = Assets.manager.get(Assets.CAR_TEXTURE_1);
                }else if(i == 2){
                t = Assets.manager.get(Assets.CAR_TEXTURE_2);
                }else if(i == 3){
                t = Assets.manager.get(Assets.CAR_TEXTURE_3);
                }else{
                t = Assets.manager.get(Assets.CAR_TEXTURE_4);
                }
            t.getTextureData().prepare();
            p = t.getTextureData().consumePixmap();
            cartype = i;
            }
    }

    public void setCarTextureTypeType(int i) {
        if(i>=0 && i<4) {
            Texture t;
            if(i == 0){
                t = Assets.manager.get(Assets.BLOCAKDE_0);
                }else
                if(cartype == 1){
                if(i == 1){
                    t = Assets.manager.get(Assets.TEXT_1_1);
                    }else if(i == 2){
                    t = Assets.manager.get(Assets.TEXT_1_2);
                    }else{
                    t = Assets.manager.get(Assets.TEXT_1_3);
                    }
                }else if(cartype == 2){
                if(i == 1){
                    t = Assets.manager.get(Assets.TEXT_2_1);
                    }else if(i == 2){
                    t = Assets.manager.get(Assets.TEXT_2_2);
                    }else{
                    t = Assets.manager.get(Assets.TEXT_2_3);
                    }
                }else if(cartype == 3){
                if(i == 1){
                    t = Assets.manager.get(Assets.TEXT_3_1);
                    }else if(i == 2){
                    t = Assets.manager.get(Assets.TEXT_3_2);
                    }else{
                    t = Assets.manager.get(Assets.TEXT_3_3);
                    }
                }else{
                if(i == 1){
                    t = Assets.manager.get(Assets.TEXT_4_1);
                    }else if(i == 2){
                    t = Assets.manager.get(Assets.TEXT_4_2);
                    }else{
                    t = Assets.manager.get(Assets.TEXT_4_3);
                    }
                }
            t.getTextureData().prepare();
            text = t.getTextureData().consumePixmap();
            carTextureType = i;
            }
    }

    public Texture getPaint() {
        Pixmap pixmap = new Pixmap(p.getWidth(),p.getHeight(),p.getFormat());
        pixmap.drawPixmap(p,0,0);
        pixmap = Graphics.reColor(pixmap,Graphics.fullColor(255,255,255,1),c);
        Pixmap pixmap2 = new Pixmap(text.getWidth(),text.getHeight(),text.getFormat());
        pixmap2.drawPixmap(text,0,0);
        pixmap2 = Graphics.reColor(pixmap2,Graphics.fullColor(0,0,255,1),c2); // a szín nem kappol át kell raknom fehérre
        pixmap.drawPixmap(pixmap2,0,0);
        return new Texture(pixmap);
    }

    public void setColorTo(){
        setColorText(r2,g2,b2);setColor(r,g,b);
    }

    public void setColorText(int i, int i1, int i2) {
        c2 = Graphics.fullColor(i,i1,i2,1);
        r2 = i;
        g2 = i1;
        b2 = i2;
        }

    public void setColor(int i, int i1, int i2) {
        c = Graphics.fullColor(i,i1,i2,1);
        r = i;
        g = i1;
        b = i2;
    }


}
