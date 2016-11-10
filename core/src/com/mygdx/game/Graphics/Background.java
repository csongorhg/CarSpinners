package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by tanulo on 2016. 10. 27..
 */
/*public class Background extends Actor {
    protected Sprite sprite;
    protected Texture texture;
    protected float Xmax, Ymax;

    private static int r = 73;
    private static int g = 172;
    private static int b = 45;

    private static Color lineC = new Color(getColor(r),getColor(g),getColor(b),1f);

    public Background() {
        texture = new Texture(getPixmap());
        sprite = new Sprite(texture);
        setOriginCenter();
    }

    private Pixmap getPixmap() {
        Pixmap img = new Pixmap((int) MyScreen.WORLD_WIDTH,(int)MyScreen.WORLD_HEIGHT, Pixmap.Format.RGBA8888);
        img.setColor(new Color(0.9f,0.9f,0.9f,1));
        img.fill();

        int m = (int)(MyScreen.WORLD_HEIGHT/15);
        int x = m*2+10;
        int y = m-2;
        int s = 0;

        while(y<MyScreen.WORLD_HEIGHT){
            s++;
            img.setColor(lineC);
            img.drawLine(x,y,(int)(x+MyScreen.WORLD_WIDTH),y);
            img.setColor(Color.GRAY);
            if(s%3==0)img.fillCircle(x-m,y,m/3);
            y+=m;
        }

        while(x<MyScreen.WORLD_WIDTH){
            img.setColor(lineC);
            img.drawLine(x,0,x,(int)MyScreen.WORLD_HEIGHT);
            x+=m;
        }

        return img;
    }

    private static float getColor(int s){
        float f = s/255f;
        if(f>1) f = 1f;
        if(f<0) f = 0f;
        return f;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch, parentAlpha);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        sprite.setY(y);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        sprite.setX(x);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        sprite.setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        sprite.setSize(getWidth(), getHeight());
        setOriginCenter();
    }

    public void setYmax(float ymax) { Ymax = ymax; }

    public void setXmax(float xmax) { Xmax = xmax; }

    public float getXmax() { return Xmax; }

    public float getYmax() { return Ymax; }

    @Override
    public void rotateBy(float amountInDegrees) {
        super.rotateBy(amountInDegrees);
        sprite.setRotation(getRotation());
    }

    public void setOriginCenter()
    {
        setOrigin(getX()+getWidth()/2, getY()+getHeight()/2);
    }

    @Override
    public void setOrigin(float originX, float originY) {

        super.setOrigin(originX, originY);
        sprite.setOrigin(getWidth()/2, getHeight()/2);
    }
}*/