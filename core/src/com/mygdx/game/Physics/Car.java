package com.mygdx.game.Physics;

import com.mygdx.game.Graphics.BreakActor;
import com.mygdx.game.Graphics.CarTexture;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by Pocok on 2016.11.12..
 */

public class Car{

    public static CarTexture carTexture = new CarTexture(1,2);
    public OneSpriteStaticActor carActor = new OneSpriteStaticActor(Car.carTexture.getPaint());
    public final static int maxheart = 5;
    public static int heart = maxheart;

    public Car(float x, float y) {
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

    public void breaking(){
        carActor.getStage().addActor(new BreakActor(){
            @Override
            public void init() {
                super.init();
                setPosition(carActor.getX(), carActor.getY());
                }
            });
        carActor.getStage().addActor(new BreakActor(){
            @Override
            public void init() {
                super.init();
                setPosition(carActor.getX()+carActor.getWidth()-getWidth(), carActor.getY());
                }
            });
        }

}
