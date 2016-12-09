package com.mygdx.game.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DemoMenu.MenuScreen;
import com.mygdx.game.DemoMenu.MenuStage;
import com.mygdx.game.End.EndScreen;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Graphics.BreakActor;
import com.mygdx.game.Graphics.ButtonCaller;
import com.mygdx.game.Graphics.EnergyTexture;
import com.mygdx.game.Graphics.Road;
import com.mygdx.game.Math.Random;
import com.mygdx.game.Music.CarMusic;
import com.mygdx.game.Music.MusicSetter;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Physics.Car;
import com.mygdx.game.Physics.Line;
import com.mygdx.game.Physics.Physics;
import com.mygdx.game.Settings.IngameSettingsStage;

import java.util.Vector;

import javax.swing.GroupLayout;

import static com.mygdx.game.End.EndStage.SCORE;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class PlayStage extends MyStage {

    private Preferences preferences = Gdx.app.getPreferences(MenuScreen.PREFS); //ez a kilépés után mentés

    //private TextButton textButton;

    //itt kell megadni, a pozicionálást!!!
    private float width, heigthBetween, heigth;

    public static boolean settingclick = false;
    public static boolean menuben = false;

    private ButtonCaller p, f, textButton5; //gáz, fékpedál

    private OneSpriteStaticActor felulet;

    private OneSpriteStaticActor energyActor;
    private OneSpriteStaticActor heart[]; //szivek eltárolása
    private OneSpriteStaticActor emptyheart[];

    private boolean fekisdown = false;
    private boolean gazisdown = false;

    private Car car;
    private Vector<Line> lines = new Vector();

    private MyLabel kmh, policedistance, score, counter; //pocoknak

    private ExplosionActor explosionActor;
    private float elapseTime = 0; //robbanás után időt számolja

    private static Vector<OneSpriteStaticActor> backgrounds  = new Vector<OneSpriteStaticActor>();

    private MoneyActor moneyActor;

    public static int scoreNumber; //pontok
    private Vector<Float> boxCountNew;

    private boolean dead; //halott-e az illető

    private float timer; // méri az időt
    public static int timerOut[]; //időt átadja

    private WalkActor walkActor;
    private boolean walkHasEnded;
    private boolean isSzirena;

    private SzirenaActor szirenaActor;
    private float szirenatime;

    private PoliceActor policeActor;

    private float endLimit;
    private boolean boomPolice;

    private CarMusic carMusic;

    public PlayStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    public void disposeCarMusic(){
        carMusic.dispose();
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
    protected IngameSettingsStage settingsStage;

    public void init() {
        carMusic = new CarMusic();
        carMusic.addMusic(Assets.manager.get(Assets.POPDANCE));
        carMusic.addMusic(Assets.manager.get(Assets.HAPPYROCK));
        carMusic.addMusic(Assets.manager.get(Assets.EXTREMEACTION));
        carMusic.addMusic(Assets.manager.get(Assets.DANCE));
        carMusic.addMusic(Assets.manager.get(Assets.BADASS));
        addBackEventStackListener();

        boomPolice = false;

        CarEngineStart.played = false;

        timer = 0;
        timerOut = new int[2];

        dead = false; //él a játékos
        isSzirena = true;
        boxCountNew = new Vector<Float>(); //ellenőrző összeg a pontozáshoz
        scoreNumber = 0; //pontszám

        settingsStage = new IngameSettingsStage(new ExtendViewport(270,480,new OrthographicCamera(270/2,480/2)), new SpriteBatch(), game);

        resized();

        heart = new OneSpriteStaticActor[Car.maxheart]; //szivek
        emptyheart = new OneSpriteStaticActor[Car.maxheart];

        //út
        float nowHeight = 0;

        backgrounds = new Vector<OneSpriteStaticActor>();

        for(int i = 0; i<5 ;i++){
            OneSpriteStaticActor road = new OneSpriteStaticActor(Road.getRoad());
            float arany = (((ExtendViewport)getViewport()).getMinWorldWidth())/Assets.manager.get(Assets.ROAD_BLOCK).getWidth();
            road.setSize(road.getWidth()*arany,road.getHeight()*arany);
            road.setPosition(-94*arany,nowHeight);
            nowHeight += road.getHeight();
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

                menuben = true;
                //setSettingclick(true);
                settingsStage.Show();
            }
        });

        addActor(textButton5);
        textButton5.setZIndex(Integer.MAX_VALUE);

        felulet = new OneSpriteStaticActor(Assets.manager.get(Assets.ROAD_MENU));        float arany = width/felulet.getWidth();
        felulet.setSize(felulet.getWidth()*arany,felulet.getHeight()*arany);
        addActor(felulet);
        felulet.setZIndex(Integer.MAX_VALUE);

       //gázpedál
        p = new ButtonCaller("", Assets.GAZ_ICON);
        p.setSize(54,57); //18*3, 19*3
        p.setX((((ExtendViewport)getViewport()).getMinWorldWidth()) - p.getWidth()*0.90f);
        p.setY(30);
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
        f.setY(30);
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

        car = new Car(width/2 - Car.carTexture.getPaint().getWidth()/2,heigth/10);
        addActor(car.carActor);
        car.carActor.setZIndex(Integer.MAX_VALUE);
        car.carActor.act(Gdx.graphics.getDeltaTime());

        //szivek
        int x = 30 * Car.maxheart; //szivek szélessége

        for (int i = 0; i < Car.maxheart; i++){ //tömbként generálom
            emptyheart[i] = new OneSpriteStaticActor(Assets.manager.get(Assets.NOHEART));
            emptyheart[i].setSize(30,30);
            emptyheart[i].setX(width-x);
            emptyheart[i].setY(heigth-30);
            addActor(emptyheart[i]);
            emptyheart[i].setZIndex(Integer.MAX_VALUE);

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
        kmh = new MyLabel("---", style);
        //myLabel.setPosition(getViewport().getWorldWidth()/(590/100),getViewport().getWorldHeight()/24);
        kmh.setPosition(75,19);
        addActor(kmh);

        //rendőr távolság
        policedistance = new MyLabel("---",style);
        policedistance.setPosition(75,4);
        addActor(policedistance);

        //rendőrautó
        policeActor = new PoliceActor();
        policeActor.setPosition(car.carActor.getX()-40
                , 0-policeActor.getHeight());
        addActor(policeActor);

        //score
        Label.LabelStyle labelStyle = game.getLabelStyle();
        //átméretezés
        generator = new FreeTypeFontGenerator(Gdx.files.internal("c64.ttf"));
        meret = new FreeTypeFontGenerator.FreeTypeFontParameter();
        meret.size = 25;
        meret.characters = Assets.CHARS;
        font = generator.generateFont(meret);
        generator.dispose();
        labelStyle.font = font;
        //átméretezés vége
        score = new MyLabel("----",labelStyle);
        score.setPosition(140,5);
        score.setAlignment(Align.right);
        addActor(score);

        energyActor = new OneSpriteStaticActor(EnergyTexture.getTexture());
        energyActor.setSize(8,24);
        energyActor.setPosition(57,4);
        addActor(energyActor);



        //séta
        walkActor = new WalkActor();
        walkActor.setZIndex(Integer.MAX_VALUE);
        walkActor.setPosition(0 - walkActor.getWidth(),car.carActor.getY() + car.carActor.getHeight()/2);
        addActor(walkActor);
        walkHasEnded = false;



        //sziréna
        szirenaActor = new SzirenaActor();
        szirenaActor.setSize(((ExtendViewport) getViewport()).getMinWorldWidth(),15);
        szirenaActor.setY(33);
        szirenaActor.setVisible(false);
        addActor(szirenaActor);


        //visszaszámláló
        counter = new MyLabel("3",labelStyle);
        counter.setZIndex(Integer.MAX_VALUE);
        counter.setPosition((((ExtendViewport) getViewport()).getMinWorldWidth())/2 - counter.getWidth()/2,
                (((ExtendViewport) getViewport()).getMinWorldHeight())/2 - counter.getHeight()/2);
        counter.setAlignment(Align.center);
        addActor(counter);
    }

    EnergyTextActor energyTextActor = null;

    @Override
    public void act(float delta) {
        carMusic.act();
        //PlayScreen.gameMusic = new MusicSetter(new Random(1,5).getGenNumber());
        if(!menuben) {
            super.act(delta);
            walkActor.setX(walkActor.getX()+1);
            if (walkActor.getX() >= car.carActor.getX()) {
                walkHasEnded = true;
                walkActor.remove();
                counter.setText("GO!");
            }
            if (walkHasEnded) {
                new CarEngineStart();
                if (!dead) {
                    timer += delta;
                    policeActor.setZIndex(Integer.MAX_VALUE);
                    strings();
                    layers();
                    carPhysic();
                    backgroundPhysic();
                    linePhysic();
                    if (Physics.energy<5 && energyTextActor==null){
                        addActor(energyTextActor = new EnergyTextActor(game.getLabelStyle()));
                    }
                    if (Physics.energy>=5 && energyTextActor!=null) {
                        energyTextActor.remove();
                        energyTextActor = null;
                    }


                    if(isSzirena) {
                        szirenaActor.setVisible(true);
                        isSzirena = false;
                    }

                    if (Math.random() < 0.02 * Physics.carspeed) {
                        addActor(new MoneyActor() {
                            @Override
                            public void init() {
                                super.init();
                                setPosition(car.carActor.getX() + car.carActor.getWidth()/2, car.carActor.getY() + car.carActor.getHeight()/2);
                            }
                        });
                    }

                    if (timer > 2f) counter.remove();
                }else {
                    if (policeActor.getY()+48 >= car.carActor.getY()) {
                        if (explosionActor != null) explosion(delta);
                        if (car.carActor.getX() >= width - car.carActor.getWidth()) {
                            if (!boomPolice) {
                                boomPolice = true;
                                explosionActor = new ExplosionActor();
                                explosionActor.setPosition(car.carActor.getX() - car.carActor.getWidth() - 24,
                                        car.carActor.getY() - car.carActor.getHeight()/2);
                                car.carActor.remove();
                                addActor(explosionActor);
                            }
                        } else {
                            policeActor.setX(policeActor.getX() + 2);
                            policeActor.setY(policeActor.getY() + 1);
                            car.carActor.setX(car.carActor.getX() + 2);
                            car.carActor.setY(car.carActor.getY() + 1);
                        }
                    }
                    else {
                        policeActor.setY(policeActor.getY()+2);
                    }
                }
                crashPhysic();
                if (Car.heart <= 0) {
                    explosion(delta);
                }
                //if (settingsStage.isB()) new MusicSetter(new Random(1, 5).getGenNumber());
                settingsStage.act(delta);
                isdead();
            }
            else {
                if (walkActor.getX()<car.carActor.getX()*(1/3f)) {
                    counter.setText("3");
                }
                else if (walkActor.getX()<car.carActor.getX()*(2/3f)
                        && walkActor.getX()>=car.carActor.getX()*(1/3f)) {
                    counter.setText("2");
                }
                else if (walkActor.getX()<car.carActor.getX()
                        && walkActor.getX()>=car.carActor.getX()*(2/3f)){
                    counter.setText("1");
                }
            }
        }
    }



    private void isdead() {//Physics.energy
        if(Physics.policedis <= 0){
            policedistance.setText("0 m");
            if (!dead) {
            }
            dead = true;
            /*if (scoreNumber>preferences.getInteger(SCORE, 0)){
                preferences.putInteger(SCORE, scoreNumber);
            }
            preferences.flush();
            timeOutput();
            game.setScreen(new EndScreen(game));*/

            /*addActor(new MoneyActor(){
                @Override
                public void init() {
                    super.init();
                    setPosition(100,100);
                }
            });*/
        }
        if(Physics.energy <= 0){
            dead = true;
            if (scoreNumber>preferences.getInteger(SCORE, 0)){
                preferences.putInteger(SCORE, scoreNumber);
            }
            preferences.flush();
            timeOutput();
            game.setScreen(new EndScreen(game));
        }
    }

    private void timeOutput() {
        int t = (int)(timer/60*100);
        timerOut[0] = t/60;
        timerOut[1] = t%60;
    }

    private void layers() {
        for (int i = 0; i < lines.size(); i++){
            for (int j = 0; j < 3; j++){
                lines.get(i).blocks[j].actor.setZIndex(Integer.MAX_VALUE);
                }
            }
        szirenaActor.setZIndex(Integer.MAX_VALUE);
        felulet.setZIndex(Integer.MAX_VALUE);
        f.setZIndex(Integer.MAX_VALUE);
        p.setZIndex(Integer.MAX_VALUE);
        textButton5.setZIndex(Integer.MAX_VALUE);
        car.carActor.setZIndex(Integer.MAX_VALUE);
        kmh.setZIndex(Integer.MAX_VALUE);
        policedistance.setZIndex(Integer.MAX_VALUE);
        score.setZIndex(Integer.MAX_VALUE);
        heartLayer();

        energyActor.remove();
        energyActor = new OneSpriteStaticActor(EnergyTexture.getTexture());
        energyActor.setSize(8,24);
        energyActor.setPosition(57,4);
        addActor(energyActor);

        if (energyTextActor!= null) energyTextActor.setZIndex(Integer.MAX_VALUE);
    }

    @Override
    public void draw() {
        super.draw();

        settingsStage.draw();
    }

    private void backgroundPhysic() {
        //if(backgrounds.get(0))
                for (int i = 0; i < backgrounds.size(); i++){
            backgrounds.get(i).setPosition(backgrounds.get(i).getX(),backgrounds.get(i).getY()- Physics.carspeed);
        }
        if(backgrounds.get(backgrounds.size()-1).getY() < heigth-backgrounds.get(0).getHeight()){
            OneSpriteStaticActor road = new OneSpriteStaticActor(Road.getRoad());
            float arany = (((ExtendViewport)getViewport()).getMinWorldWidth())/Assets.manager.get(Assets.ROAD_BLOCK).getWidth();
            road.setSize(road.getWidth()*arany,road.getHeight()*arany);
            road.setPosition(-94*arany,backgrounds.get(backgrounds.size()-1).getY()+backgrounds.get(0).getHeight());
            backgrounds.add(road);
            addActor(road);
            }
        if(backgrounds.get(0).getY() < -backgrounds.get(0).getHeight()){
            backgrounds.get(0).remove();
            backgrounds.remove(0);
            }
    }

    private void explosion(float delta) {
        elapseTime += delta;
        if (elapseTime > 1f) {
            explosionActor.remove();
            timeOutput();
            game.setScreen(new EndScreen(game));
        }
    }

    private void strings() {
        String nulls="0000"; //kiírásnál, hogy mindig 4 helyérték legyen
        kmh.setText(Physics.round(Physics.carspeed*10)+" km/h");
        policedistance.setText(Physics.policedis > 1000 ? Physics.round(Physics.policedis/100)/10.0+" km" : Physics.round(Physics.policedis)+" m");
        score.setText(nulls.substring(0,nulls.length()-(scoreNumber+"").length())+scoreNumber);
    }

    private void crashPhysic() {
        Line l = lines.get(0);
        for (int i=0; i<l.blocks.length;i++){
            if(Physics.hit(l.blocks[i].actor,car.carActor)){
                if(l.blocks[i].getId() == 2){
                    l.blocks[i].setId(0);
                    l.blocks[i].actor.remove();
                    Physics.maxEnergy();
                }else if(l.blocks[i].getId() == 3){
                    l.blocks[i].setId(0);
                    l.blocks[i].actor.remove();
                    car.heal();
                    heartLayer();
                }
                else if(l.blocks[i].getWeight() == 1) {
                    if (Car.heart >= 0) {
                        boxCountNew.set(0,0f);
                        Physics.carspeed -= 3;
                        if(Physics.carspeed < Physics.MINcarspeed) Physics.carspeed = Physics.MINcarspeed;
                        car.damage();
                        if (Car.heart <= 0) {
                            dead = true;
                            szirenaActor.remove();
                            car.carActor.remove();
                            explosionActor = new ExplosionActor();
                            explosionActor.setPosition(car.carActor.getX() + car.carActor.getWidth()/2 - explosionActor.getWidth()/2,
                                    car.carActor.getY());
                            addActor(explosionActor);
                        }
                    }
                    l.blocks[i].setWeight(0);
                    l.blocks[i].actor.remove();
                }
            }
        }
    }

    private void heartLayer() {
        for (int f = 0; f < Car.heart; f++) {
            heart[f].setZIndex(Integer.MAX_VALUE);
        }
        for (int i = Car.heart; i < heart.length; i++){
            emptyheart[i].setZIndex(Integer.MAX_VALUE);
        }
    }

    private void linePhysic(){
        float speed = Physics.carspeed;
        try{
            if(!fekisdown && !gazisdown) speed *= Physics.normalmoving;
            else if(fekisdown) {
                speed -= 0.01f;
                speed *= Physics.breakpower;
                if (speed>1) {
                    car.breaking();
                }
            }
            else if(gazisdown) {
                speed += 0.01f;
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
                if (a instanceof MoneyActor){
                    a.setY(a.getY()-speed);
                }
            }
            if(lines.get(0).heightpoz+lines.get(0).size < 0){
                removeLine();
                Physics.energy--;
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
        float sum=0;
        for (int i = 0; i < 3; i++){
            lines.get(0).blocks[i].actor.remove();
            sum += lines.get(0).blocks[i].getWeight();
        }
        if (sum == boxCountNew.get(0) && sum!=0) {
            scoreNumber += 1;
            if (scoreNumber>preferences.getInteger(SCORE, 0)){
                preferences.putInteger(SCORE, scoreNumber);
            }
            preferences.flush();
        }
        boxCountNew.remove(0);
        lines.get(0).remove();
        lines.remove(0);
    }

    private void addLine() {
        Line l = new Line(width,heigth);
        float sum=0;
        for (int i = 0; i < 3; i++){
            addActor(l.blocks[i].actor);
            sum += l.blocks[i].getWeight();
        }
        boxCountNew.add(sum);
        lines.add(l);

    }

    private void carPhysic(){
        if (Physics.carspeed>0.01f) {
            car.carActor.setPosition(car.carActor.getX() - ((Gdx.input.getAccelerometerX() / 2)*(float)Math.sqrt(Physics.carspeed)), car.carActor.getY());
        }
        if(car.carActor.getX()+car.carActor.getWidth() > width/5*4) car.carActor.setPosition(width/5*4-car.carActor.getWidth(),car.carActor.getY());
        if(car.carActor.getX()< width/5) car.carActor.setPosition(width/5,car.carActor.getY());


    }

    @Override
    protected void resized() {
        super.resized();
        width = (((ExtendViewport)getViewport()).getMinWorldWidth());
        heigth = (((ExtendViewport)getViewport()).getMinWorldHeight());

    }

    @Override
    public void dispose() {
        carMusic.dispose();
        settingsStage.dispose();
        super.dispose();
    }
}