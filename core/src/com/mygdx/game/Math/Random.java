package com.mygdx.game.Math;

/**
 * Created by mordes on 2016.11.12..
 */
public class Random {

    private int genNumber;
    private float genNumberf;

    public Random (int a,int b){
        genNumber = (int) Math.floor (Math.random() * (b - a + 1) + a);
        genNumberf = (float)Math.floor (Math.random() * (b - a + 1) + a);
    }

    public Random(float a, float b) {
        genNumber = (int) Math.floor (Math.random() * (b - a + 1) + a);
        genNumberf = (float)Math.floor (Math.random() * (b - a + 1) + a);
    }

    public int getGenNumber() {
        return genNumber;
    }

    public float getGenNumberf() {
        return genNumberf;
    }
}
