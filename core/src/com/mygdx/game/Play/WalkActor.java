package com.mygdx.game.Play;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;

/**
 * Created by tanulo on 2016. 12. 08..
 */
public class WalkActor extends OneSpriteAnimatedActor {
    public WalkActor() {
        //super("explosion.atlas");
        super(Assets.manager.get(Assets.WALK_TEXTUREATLAS));
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
