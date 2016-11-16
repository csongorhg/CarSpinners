package com.mygdx.game.DemoMenu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2016. 11. 16..
 */

public class CityStream extends OneSpriteStaticActor {
    private boolean createNew=false;
    public CityStream() {
        super(Assets.manager.get(Assets.CITY_ACTION_BACKGROUND));
    }

    @Override
    public void init() {
        super.init();
        //setScale(3);
        setSize(getWidth()*3, getHeight()*3);
        setPosition(-getWidth(),0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX()+120*delta);
        if (getX()>getStage().getViewport().getWorldWidth()){
            getStage().getActors().removeValue(this,true);
        }
        if (getX()>=0 && !createNew){
            createNew = true;
            getStage().addActor(new CityStream());
            Actor a = getStage().getActors().get(getStage().getActors().size-1);
            a.setPosition(getX()-a.getWidth(),0);
        }
    }
}
