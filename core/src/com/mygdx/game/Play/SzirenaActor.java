package com.mygdx.game.Play;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;

/**
 * Created by tanulo on 2016. 12. 08..
 */
public class SzirenaActor extends OneSpriteAnimatedActor {
    public SzirenaActor() {
        //super("explosion.atlas");
        super(Assets.manager.get(Assets.SZIRENA_TEXTUREATLAS));
        setFps(30);

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
