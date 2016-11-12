package com.mygdx.game.Math;

/**
 * Created by mordes on 2016.11.12..
 */
public class Random {

    private int genNumber;

    public Random (int a,int b){
        genNumber = (int) Math.floor (Math.random() * (b - a + 1) + a);
    }

    public int getGenNumber() {
        return genNumber;
    }
}
