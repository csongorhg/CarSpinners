package com.mygdx.game.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DemoMenu.ExplosionActor;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Graphics.BreakActor;
import com.mygdx.game.Graphics.ButtonCaller;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.Physics.Car;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Physics.Line;
import com.mygdx.game.Physics.Physics;

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

    private MyLabel kmh, policedistance, score; //pocoknak

    private ExplosionActor explosionActor;

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
        float blockHeight = (((ExtendViewport)getViewport()).getMinWorldHeight());

        for(int i = 0; i<4 ;i++){
            oneSpriteStaticActor = new OneSpriteStaticActor(Assets.manager.get(Assets.ROAD_BLOCK));
            float arany = (((ExtendViewport)getViewport()).getMinWorldWidth())/oneSpriteStaticActor.getWidth();
            oneSpriteStaticActor.setSize(oneSpriteStaticActor.getWidth()*arany,oneSpriteStaticActor.getHeight()*arany);
            blockHeight -= oneSpriteStaticActor.getHeight();
            oneSpriteStaticActor.setPosition(0,blockHeight);
            addActor(oneSpriteStaticActor);
            oneSpriteStaticActor.setZIndex(0);
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


                setSettingclick(true);
            }
        });

        addActor(textButton5);
        textButton5.setZIndex(Integer.MAX_VALUE);

        OneSpriteStaticActor felulet = new OneSpriteStaticActor(Assets.manager.get(Assets.ROAD_MENU));
        float arany = width/felulet.getWidth();
        felulet.setSize(felulet.getWidth()*arany,felulet.getHeight()*arany);
        addActor(felulet);
        felulet.setZIndex(Integer.MAX_VALUE);

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
        p.setZIndex(Integer.MAX_VALUE);

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
        f.setZIndex(Integer.MAX_VALUE);

        car = new Car(width/2 - Car.carTexture.getPaint().getWidth()/2,heigth/10,Car.carTexture.getPaint().getWidth(),Car.carTexture.getPaint().getHeight());
        addActor(car.carActor);
        car.carActor.setZIndex(Integer.MAX_VALUE);
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
            heart[i].setZIndex(Integer.MAX_VALUE);
            x-=30;
        }


        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.fontColor = Color.WHITE;

        //átméretezés
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("c64.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter meret = new FreeTypeFontGenerator.FreeTypeFontParameter();
        meret.size = 10;
        meret.characters = Assets.CHARS;
        BitmapFont font = generator.generateFont(meret);
        generator.dispose();
        style.font = font;
        //átméretezés vége


        //kilóméter/óra
        kmh = new MyLabel("asd1", style);
        //myLabel.setPosition(getViewport().getWorldWidth()/(590/100),getViewport().getWorldHeight()/24);
        kmh.setPosition(75,19);
        addActor(kmh);

        //rendőr távolság
        policedistance = new MyLabel("asd2",style);
        policedistance.setPosition(75,4);
        addActor(policedistance);

        //score
        score = new MyLabel("score",game.getLabelStyle());
        score.setPosition(116,7);
        addActor(score);



    }

    @Override
    public void act(float delta) {
        super.act(delta);
        carPysic();
        linePhysic();
        crashPhysic();
        strings();
    }

    private void strings() {
        kmh.setText(Physics.round(Physics.carspeed*10)+" km/h");
        policedistance.setText(Physics.round(Physics.policedis)+" m");
        score.setText("00000");
    }

    private void crashPhysic() {
        Line l = lines.get(0);
        for (int i=0; i<l.blocks.length;i++){
            if(Physics.hit(l.blocks[i].actor,car.carActor)){
                if(l.blocks[i].getWeight() == 1) {
                    if (currentHeart < Car.maxheart) {
                        car.damage();
                        OneSpriteStaticActor emptyHeart = new OneSpriteStaticActor(Assets.manager.get(Assets.NOHEART));
                        emptyHeart.setPosition(heart[currentHeart].getX(), heart[currentHeart].getY());
                        emptyHeart.setSize(heart[currentHeart].getWidth(), heart[currentHeart].getHeight());
                        heart[currentHeart].remove();
                        addActor(emptyHeart);
                        currentHeart++;
                        if (currentHeart == 5) {
                            car.carActor.remove();
                            explosionActor = new ExplosionActor();
                            explosionActor.setPosition(car.carActor.getX() + car.carActor.getWidth()/2 - explosionActor.getWidth()/2,
                                    car.carActor.getY() - car.carActor.getHeight()/2);
                            addActor(explosionActor);
                        }
                    }
                    l.blocks[i].setWeight(0);
                    l.blocks[i].actor.remove();
                }
            }
        }
    }

    private void linePhysic(){
        float speed = Physics.carspeed;
        try{
            if(!fekisdown && !gazisdown) speed *= 0.99;
            else if(fekisdown){
                if(speed >= 0 && speed <= 1)speed -= 1.01;
                speed *= Physics.breakpower;
                if (speed>1) {
                    car.breaking();
                }
            }
            else if(gazisdown){
                if(speed >= 0 && speed <= 1)speed += 1.01;
                speed *= Physics.acceleration;
            }

            if(speed < Physics.MINcarspeed) speed = Physics.MINcarspeed;
            else if(speed > Physics.MAXcarspeed) speed = Physics.MAXcarspeed;

            Physics.carspeed = speed;

            Physics.policedis += speed - Physics.policespeed;

            for (int i = 0; i < lines.size(); i++){
                lines.get(i).addHeight(speed);
            }
            for (Actor a:getActors()) {
                if (a instanceof BreakActor){
                    a.setY(a.getY()-speed);
                }
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
            l.blocks[i].actor.setZIndex(5);
        }
        lines.add(l);

    }

    private void carPysic(){
        car.carActor.setPosition(car.carActor.getX()-(Gdx.input.getAccelerometerX()/3),car.carActor.getY());
        if(car.carActor.getX()+car.carActor.getWidth() > width/5*4){
            car.carActor.setPosition(width/5*4-car.carActor.getWidth(),car.carActor.getY());
        }
        if(car.carActor.getX()< width/5){
            car.carActor.setPosition(width/5,car.carActor.getY());
        }


    }

    @Override
    protected void resized() {
        super.resized();
        width = (((ExtendViewport)getViewport()).getMinWorldWidth());
        heigth = (((ExtendViewport)getViewport()).getMinWorldHeight());

    }
}