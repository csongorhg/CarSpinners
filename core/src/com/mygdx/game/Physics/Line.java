package com.mygdx.game.Physics;


import com.mygdx.game.GlobalClasses.Assets;

/**
 * Created by Pocok on 2016.11.18..
 */

public class Line {

    public static int[][] generate = {
            {0,0,0},
            {0,0,1},
            {0,1,0},
            {0,1,1},
            {1,0,0},
            {1,0,1},
            {1,1,0},
    };
    public static Block[] allblocks = {
        new Block(1,0,Assets.manager.get(Assets.BLOCAKDE_0)),
        new Block(1,1,Assets.manager.get(Assets.BLOCAKDE_1))
    };
    public static float[] positions = new float[3];
    public Block[] blocks = new Block[3];

    public float heightpoz = 0;
    public float widthkoz = 0;
    public float size = 0;

    public Line(float w,float h){
        widthkoz = w/5;
        size = widthkoz*0.9f;
        heightpoz = h;
        int[] t = generate[(int)Math.floor(generate.length*Math.random())];
        for (int i = 0; i < blocks.length; i++){
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
}
