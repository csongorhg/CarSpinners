package com.mygdx.game.Play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;
import com.mygdx.game.Physics.Physics;

/**
 * Created by tanulo on 2016. 12. 08..
 */
public class SzirenaActor extends OneSpriteAnimatedActor {
    public SzirenaActor() {
        //super("explosion.atlas");
        super(Assets.manager.get(Assets.SZIRENA_TEXTUREATLAS));
        setFps(10);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                running = !running;
            }
        });
    }

    @Override
    public void act ( float delta){
        super.act(delta);
        Color c = sprite.getColor();
        float dis = Physics.policedis;
        if(dis > 500) dis = 500;
        dis = 500 - dis;
        sprite.setColor(c.r, c.g, c.b, dis/500);
    }

}
