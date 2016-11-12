package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.*;

/**
 * Created by Pocok on 2016.11.12..
 */

public class Graphics {

    public static Pixmap reColor(Pixmap p, Color from,Color to){
        for (int i = 0; i<p.getWidth();i++){
            for (int j = 0; j<p.getHeight();j++){
                if(p.getPixel(i,j) == Color.argb8888(from)){
                    p.setColor(to);
                    p.drawPixel(i, j);
                }
            }
        }
        return p;
    }

    public static Color fullColor(int i,int i1,int i2,float a){
        return  new Color(colorInt(i),colorInt(i1),colorInt(i2),a);
    }

    public static float colorInt(int s){
        float f = s/255f;
        if(f>1) f = 1f;
        if(f<0) f = 0f;
        return f;
    }

}
