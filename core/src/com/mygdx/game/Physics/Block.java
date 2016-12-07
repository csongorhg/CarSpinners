package com.mygdx.game.Physics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by tanulo on 2016. 11. 17..
 */
public class Block {

    Texture t;
    public OneSpriteStaticActor actor;
    float weight = 0;
    int id;

    Block(Block b){
        this.id = b.id;
        this.weight = b.weight;
        this.actor = new OneSpriteStaticActor(b.t);
    }

    Block(int id, float weight, Texture t){
        this.id = id;
        this.weight = weight;
        this.t = t;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setPoz(float f1,float f2) {
        actor.setPosition(f1,f2);
    }
    public float[] getPoz() {
        float t[] = new float[2];
        t[0] = actor.getX();
        t[1] = actor.getY();
        return t;
    }

    public void setSize(float f1,float f2) {
        actor.setSize(f1,f2);
    }

    public float getWeight() {
        return weight;
    }
    public int getId(){return id;}

    public float getWidth() {
        return actor.getWidth();
    }
    public float getHeight() {
        return actor.getHeight();
    }

    public void setId(int id) {
        this.id = id;
    }
}
