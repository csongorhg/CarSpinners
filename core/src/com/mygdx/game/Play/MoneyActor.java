package com.mygdx.game.Play;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;

/**
 * Created by mordes on 2016.12.03..
 */
public class MoneyActor extends OneSpriteAnimatedActor {
    public MoneyActor() {
        //super("explosion.atlas");
        super(Assets.manager.get(Assets.MONEY_TEXTUREATLAS));
        setFps(10);
        addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                running = !running;
            }
        });
    }

}
