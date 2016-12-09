package com.mygdx.game.Play;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;

/**
 * Created by tanulo on 2016. 12. 09..
 */
public class PoliceActor extends OneSpriteAnimatedActor {
    public PoliceActor() {
        //super("explosion.atlas");
        super(Assets.manager.get(Assets.POLICE_TEXTUREATLAS));
        setFps(2);
        start();
        setLooping(true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
