package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Physics.Physics;

import java.awt.*;

/**
 * Created by Pocok on 2016.12.07..
 */

public class EnergyTexture {

    public static Texture getTexture(){
        int e = Physics.energy;
        Pixmap p = new Pixmap(5,17, Pixmap.Format.RGBA8888);
        Color c = e>10 ? Color.GREEN : e>5 ? Color.ORANGE : Color.RED;
        for (int i = 0; i < p.getWidth(); i++){
            for (int j = 0; j< p.getHeight(); j++){
                if(i == 0 || i == 4 || j == 0 || j == 16){
                    p.drawPixel(i,j,Color.rgba8888(Color.BLACK));
                }
                else{
                    if(j >= 16-e){
                        p.drawPixel(i,j,Color.rgba8888(c));
                    }
                    else{
                        p.drawPixel(i,j,Color.rgba8888(Color.BLACK));
                    }
                }
            }
        }

        return new Texture(p);
    }
}
