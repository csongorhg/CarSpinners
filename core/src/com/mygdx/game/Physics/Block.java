package com.mygdx.game.Physics;

/**
 * Created by tanulo on 2016. 11. 17..
 */
public class Block {

    Pont poz = new Pont(0f,0f);
    Pont size = new Pont(0f,0f);
    float weight = 0;

    Block(float x,float y,float w,float h){
        poz = new Pont(x,y);
        size = new Pont(w,h);
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setPoz(float f1,float f2) {
        poz.setX(f1);
        poz.setY(f2);
    }

    public void setSize(float f1,float f2) {
        size.setX(f1);
        size.setY(f2);
    }

    public Pont getSize() {
        return size;
    }

    public Pont getPoz() {
        return poz;
    }

    public float getWeight() {
        return weight;
    }
}
