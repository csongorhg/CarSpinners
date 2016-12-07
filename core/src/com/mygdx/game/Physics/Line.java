package com.mygdx.game.Physics;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GlobalClasses.Assets;

/**
 * Created by Pocok on 2016.11.18..
 */

public class Line {

    static int lastEnergy = 0;

    public static Block[] allblocks = {
        new Block(1,0,Assets.manager.get(Assets.BLOCAKDE_0)),
        new Block(1,1,randomBlockade()),
        new Block(2,0,Assets.manager.get(Assets.BATTERY))
    };

    private static Texture randomBlockade() {
        int i = vel(0,5)-1;
        if(i == 0) return Assets.manager.get(Assets.BLOCAKDE_3);
        else if(i == 1) return Assets.manager.get(Assets.BLOCAKDE_2);
        else return Assets.manager.get(Assets.BLOCAKDE_1);
    }

    public static float[] positions = new float[3];
    public Block[] blocks = new Block[3];

    public float heightpoz = 0;
    public float widthkoz = 0;
    public float size = 0;

    public static int[] getLineStyle(){

        boolean zero = false;

        int[] t = new int[3];
        for (int i = 0; i < 3 ; i++){
            int x = vel(0,2)-1;
            t[i] = x;
            if(x==0) zero = true;
        }

        if(!zero){
            t[vel(0,3)-1] = 0;
        }

        if(lastEnergy > 7){
            t[vel(0,3)-1] = 2;
            lastEnergy = 0;
        }

        lastEnergy++;

        return t;
    }

    public Line(float w,float h){
        widthkoz = w/5;
        size = widthkoz*0.9f;
        heightpoz = h;
        int[] t = getLineStyle();
        for (int i = 0; i < blocks.length; i++){
            allblocks[1] = new Block(1,1,randomBlockade());
            blocks[i] = new Block(allblocks[t[i]]);
            blocks[i].actor.setSize(size,size);
            blocks[i].actor.setPosition(widthkoz*(i+1) + widthkoz*0.1f,heightpoz);
            positions[i] = widthkoz*(i+1) + widthkoz*0.05f;
        }
    }

    public void addHeight(float f) {
        heightpoz -= f;
        for (int i = 0; i < blocks.length; i++){
            blocks[i].actor.setPosition(positions[i],heightpoz);
        }
    }

    static public int vel(int a,int b){return (int)((Math.random()*b-a+1)+a);}
}
