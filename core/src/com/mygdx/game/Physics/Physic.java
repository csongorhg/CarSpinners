package com.mygdx.game.Physics;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Pocok on 2016.11.18..
 */

public class Physic {

    public static float carspeed = 0;
    public static float MINcarspeed = 0;
    public static float MAXcarspeed = 10;
    public static float breakpower = 0.99f;
    public static float acceleration = 1.01f;
    public static float policespeed = 5;
    public static float policedis = 100;

    public static boolean hit(Actor a1,Actor a2){
        return  a1.getX() < a2.getX()+a2.getWidth() &&
                a2.getX() < a1.getX()+a1.getWidth() &&
                a1.getY()+a1.getHeight() > a2.getY() &&
                a2.getY()+a2.getHeight() > a1.getY();
    }

    public static int round(float f){
        int a = (int)f;
        return a;
    }


}
