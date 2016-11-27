package com.mygdx.game.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Graphics.ButtonCaller;
import com.mygdx.game.Physics.Car;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Physics.Line;
import com.mygdx.game.Physics.Physic;

import java.util.Vector;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class PlayStage extends MyStage {

    //private TextButton textButton;

    //itt kell megadni, a pozicionálást!!!
    private float width, heigthBetween, heigth;

    public static boolean settingclick = false;

    private ButtonCaller p, f, textButton5; //gáz, fékpedál

    private OneSpriteStaticActor oneSpriteStaticActor; //út

    private OneSpriteStaticActor heart[]; //szivek eltárolása
    private int currentHeart; //jelenlegi szív

    private boolean fekisdown = false;
    private boolean gazisdown = false;

    private Car car;
    private Vector<Line> lines = new Vector();
    private static Vector<OneSpriteStaticActor> backgrounds  = new Vector<OneSpriteStaticActor>();

    public PlayStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK)
        {
            settingclick = true;
        }
        return false;
    }

    public static boolean setSettingclick(boolean b){
        settingclick = b;
        return settingclick;
    }

    public void init() {
        addBackEventStackListener();

        resized();

        heart = new OneSpriteStaticActor[Car.maxheart]; //szivek

        //út
        float nowHeight = 0;

        backgrounds = new Vector<OneSpriteStaticActor>();

        for(int i = 0; i<5 ;i++){
            OneSpriteStaticActor road = new OneSpriteStaticActor(Assets.manager.get(Assets.ROAD_BLOCK));
            float arany = (((ExtendViewport)getViewport()).getMinWorldWidth())/road.getWidth();
            road.setSize(road.getWidth()*arany,road.getHeight()*arany);
            nowHeight += road.getHeight();
            road.setPosition(0,nowHeight);
            backgrounds.add(road);
            addActor(road);
        }

        //fogaskerék
        textButton5 = new ButtonCaller("", Assets.CONF_ICON);
        textButton5.setSize(45,45);
        textButton5.setY((((ExtendViewport)getViewport()).getMinWorldHeight())
                - textButton5.getHeight());
        textButton5.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                //TESZT szívcsökkentés
                if (currentHeart < Car.maxheart) {
                    OneSpriteStaticActor emptyHeart = new OneSpriteStaticActor(Assets.manager.get(Assets.NOHEART));
                    emptyHeart.setPosition(heart[currentHeart].getX(), heart[currentHeart].getY());
                    emptyHeart.setSize(heart[currentHeart].getWidth(), heart[currentHeart].getHeight());
                    heart[currentHeart].remove();
                    addActor(emptyHeart);
                    currentHeart++;
                }
                //TESZT szívcsökkentés

                setSettingclick(true);
            }
        });

        addActor(textButton5);

        OneSpriteStaticActor felulet = new OneSpriteStaticActor(Assets.manager.get(Assets.ROAD_MENU));
        float arany = width/felulet.getWidth();
        felulet.setSize(felulet.getWidth()*arany,felulet.getHeight()*arany);
        addActor(felulet);

       //gázpedál
        p = new ButtonCaller("", Assets.GAZ_ICON);
        p.setSize(54,57); //18*3, 19*3
        p.setX((((ExtendViewport)getViewport()).getMinWorldWidth()) - p.getWidth()*0.90f);
        p.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gazisdown = true;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gazisdown = false;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        addActor(p);

        //fékpedál
        f = new ButtonCaller("", Assets.FEK_ICON);
        f.setSize(54,57); //18*3, 19*3
        f.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                fekisdown = true;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                fekisdown = false;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        addActor(f);

        car = new Car(width/2 - Car.carTexture.getPaint().getWidth()/2,heigth/10,Car.carTexture.getPaint().getWidth(),Car.carTexture.getPaint().getHeight());
        addActor(car.carActor);
        car.carActor.act(Gdx.graphics.getDeltaTime());

        //szivek
        int x = 30 * Car.maxheart; //szivek szélessége
        currentHeart = 0; //jelenlegi szív

        for (int i = 0; i < Car.maxheart; i++){ //tömbként generálom
            heart[i] = new OneSpriteStaticActor(Assets.manager.get(Assets.HEART));
            heart[i].setSize(30,30);
            heart[i].setX(width-x);
            heart[i].setY(heigth-30);
            addActor(heart[i]);
            x-=30;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        carPysic();
        linePhysic();
        backgroundPhysic();
    }

    private void backgroundPhysic() {
        //if(backgrounds.get(0))
        for (int i = 0; i < backgrounds.size(); i++){
            backgrounds.get(i).setPosition(backgrounds.get(i).getX(),backgrounds.get(i).getY()-Physic.carspeed);
        }
    }

    private void linePhysic(){
        float speed = Physic.carspeed;
        try{
            if(!fekisdown && !gazisdown) speed *= 0.99;
            else if(fekisdown) {
                speed -= 0.01f;
                speed *= Physic.breakpower;
            }
            else if(gazisdown) {
                speed += 0.01f;
                speed *= Physic.acceleration;
            }

            if(speed < Physic.MINcarspeed) speed = Physic.MINcarspeed;
            else if(speed > Physic.MAXcarspeed) speed = Physic.MAXcarspeed;

            Physic.carspeed = speed;

            for (int i = 0; i < lines.size(); i++){
                lines.get(i).addHeight(speed);
            }
            if(lines.get(0).heightpoz+lines.get(0).size < 0){
                removeLine();
            }
            if(width - lines.get(lines.size()-1).heightpoz > lines.get(lines.size()-1).size){
                addLine();
            }
        }catch(Exception e){
            if(lines.size() == 0){
                addLine();
            }
        }
    }

    private void removeLine() {
        for (int i = 0; i < 3; i++){
            lines.get(0).blocks[i].actor.remove();
        }
        lines.remove(0);
    }

    private void addLine() {
        Line l = new Line(width,heigth);
        for (int i = 0; i < 3; i++){
            addActor(l.blocks[i].actor);
        }
        lines.add(l);
    }

    private void carPysic(){
        car.carActor.setPosition(car.carActor.getX()-(Gdx.input.getAccelerometerX()/4),car.carActor.getY());
        if(car.carActor.getX()+car.carActor.getWidth() > width/5*4) car.carActor.setPosition(width/5*4-car.carActor.getWidth(),car.carActor.getY());
        if(car.carActor.getX()< width/5) car.carActor.setPosition(width/5,car.carActor.getY());


    }

    @Override
    protected void resized() {
        super.resized();
        width = (((ExtendViewport)getViewport()).getMinWorldWidth());
        heigth = (((ExtendViewport)getViewport()).getMinWorldHeight());

    }
}