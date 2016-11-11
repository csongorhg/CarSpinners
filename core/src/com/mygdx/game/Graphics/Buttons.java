package com.mygdx.game.Graphics;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created by Pocok on 2016.10.18..
 */

public class Buttons {

    private static float buttonEdge = 0.1f;
    private static int diff = 25;

    private static int r = 10;
    private static int g = 10;
    private static int b = 10;

    private static int rA = 222;
    private static int gA = 222;
    private static int bA = 222;

    private static Color bcNormal = new Color(getColor(r),getColor(g),getColor(b),1f);
    private static Color bcShadow = new Color(getColor(r-diff),getColor(g-diff),getColor(b-diff),1f);
    private static Color bcLight = new Color(getColor(r+diff),getColor(g+diff),getColor(b+diff),1f);

    public static Pixmap getImg(int width, int height, boolean active){

        if(active) {
            bcNormal = new Color(getColor(rA),getColor(gA),getColor(bA),1f);
            bcShadow = new Color(getColor(rA-diff),getColor(gA-diff),getColor(bA-diff),1f);
            bcLight = new Color(getColor(rA+diff),getColor(gA+diff),getColor(bA+diff),1f);
        }

        Pixmap img = new Pixmap(width,height, Pixmap.Format.RGBA8888);
        img.setColor(bcNormal);

        int s = (width<height) ? (int)(width*buttonEdge) : (int)(height*buttonEdge) ;

        img.setColor(bcLight);
        img.fillCircle(s,s,s);
        img.setColor(bcNormal);
        img.fillCircle(width-s,s,s);
        img.fillCircle(s,height-s,s);
        img.setColor(bcShadow);
        img.fillCircle(width-s,height-s,s);

        img.setColor(bcNormal);
        img.fillRectangle(s,0,width-2*s,height);
        img.fillRectangle(0,s,width,height-2*s);

        img.setColor(bcLight);
        img.fillRectangle(s,0,width-2*s,s);
        img.fillRectangle(0,s,s,height-2*s);

        img.setColor(bcShadow);
        img.fillRectangle(width-s,s,s,height-2*s);
        img.fillRectangle(s,height-s,width-2*s,s);

        return img;
    }

    private static float getColor(int s){
        float f = s/255f;
        if(f>1) f = 1f;
        if(f<0) f = 0f;
        return f;
    }

}
