package com.mygdx.game.Play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;

/**
 * Created by mordes on 2016.12.03..
 */
public class MoneyActor extends OneSpriteAnimatedActor {
    private float dx, dy;

    public MoneyActor() {
        //super("explosion.atlas");
        super(Assets.manager.get(Assets.MONEY_TEXTUREATLAS));
        setSize(16,16);
        setFps(10);
        addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                running = !running;
            }
        });
        dx = (float)Math.random()*400f-200f;
        dy = (float)Math.random()*100f-30f;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        Color c = sprite.getColor();
        if (elapsedTime/3 < 1) {
            sprite.setColor(c.r, c.g, c.b, 1 - elapsedTime/3);
            setPosition(getX() + dx*delta, getY() + dy*delta);
        } else {
            getStage().getActors().removeValue(this, true);
        }
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        setZIndex(Integer.MAX_VALUE);
    }

}
