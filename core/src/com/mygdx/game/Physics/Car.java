package com.mygdx.game.Physics;

import com.mygdx.game.Graphics.CarTexture;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by Pocok on 2016.11.12..
 */

public class Car{

    public static CarTexture carTexture = new CarTexture();
    public OneSpriteStaticActor carActor = new OneSpriteStaticActor(Car.carTexture.getPaint());
    public final static int maxheart = 5;
    private int heart = maxheart;

    public Car(float x, float y, float w, float h) {
        carActor.setPosition(x,y);
    }

    public int getHeart() {
        return heart;
    }

    public void heal(){
        heart = maxheart;
    }

    public void heal(int i){
        heart += i;
        if(heart>maxheart) heart = maxheart;
    }

    public boolean damage(){
        heart--;
        if(heart<=0){
            return false;
        }
        return true;
    }
}
