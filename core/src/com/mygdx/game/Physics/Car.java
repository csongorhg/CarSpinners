package com.mygdx.game.Physics;

import com.mygdx.game.Graphics.CarTexture;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by Pocok on 2016.11.12..
 */

public class Car extends Block{

    public static CarTexture carTexture = new CarTexture();
    public OneSpriteStaticActor carActor = new OneSpriteStaticActor(Car.carTexture.getPaint());

    public Car(float x, float y, float w, float h) {
        super(x, y, w, h);
        carActor.setPosition(x,y);
    }
}
