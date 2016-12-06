package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.*;
import com.mygdx.game.GlobalClasses.Assets;

/**
 * Created by Pocok on 2016.12.06..
 */

public class Road {

    public static Texture getRoad(){
        Pixmap p = new Pixmap(332,64, Pixmap.Format.RGBA8888);
        Texture t = Assets.manager.get(Assets.ROAD_BLOCK);
        t.getTextureData().prepare();
        Pixmap p2 = t.getTextureData().consumePixmap();
        p.drawPixmap(p2,94,0);
        p2 = getSide(false);
        p.drawPixmap(p2,0,0);
        p2 = getSide(true);
        p.drawPixmap(p2,188,0);

        return new Texture(p);
    }

    private static Pixmap getSide(boolean right){
        Texture t = Assets.manager.get(Assets.HOUSE);
        t.getTextureData().prepare();
        Pixmap p = t.getTextureData().consumePixmap();
        int db = vel(0,3);
        while(db > 0){
            int i = vel(0,9);
            Texture t2;
            if(i==0){
                t2 = Assets.manager.get(Assets.ENV_0);
            } else if (i == 1) {
                t2 = Assets.manager.get(Assets.ENV_1);
            } else if (i == 2) {
                t2 = Assets.manager.get(Assets.ENV_2);
            } else if (i == 3) {
                t2 = Assets.manager.get(Assets.ENV_3);
            } else if (i == 4) {
                t2 = Assets.manager.get(Assets.ENV_4);
            } else if (i == 5) {
                t2 = Assets.manager.get(Assets.ENV_5);
            } else if (i == 6) {
                t2 = Assets.manager.get(Assets.ENV_6);
            } else if (i == 7) {
                t2 = Assets.manager.get(Assets.ENV_7);
            } else if (i == 8) {
                t2 = Assets.manager.get(Assets.ENV_8);
            } else{
                t2 = Assets.manager.get(Assets.ENV_9);
            }
            t2.getTextureData().prepare();
            Pixmap p2 = t2.getTextureData().consumePixmap();
            p.drawPixmap(p2,0,0);
            db--;
        }
        if(!right){
            final int width = p.getWidth();
            final int height = p.getHeight();
            Pixmap forditott = new Pixmap(width, height, p.getFormat());

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    forditott.drawPixel(x, y, p.getPixel(width - x - 1, y));
                }
            }
            p = new Pixmap(forditott.getWidth(),forditott.getHeight(),forditott.getFormat());
            p.drawPixmap(forditott,0,0);
        }
        return p;
    }

    private static int vel(int a,int b){return (int)(Math.random()*(b-a+1)+a);}

}
