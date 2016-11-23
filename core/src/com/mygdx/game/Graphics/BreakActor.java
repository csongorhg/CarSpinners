package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;


public class BreakActor extends OneSpriteStaticActor {
    public BreakActor() {
        super(Assets.manager.get(Assets.BREAK));
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Color c = sprite.getColor();
        if (elapsedTime/3 < 1) {
            sprite.setColor(c.r, c.g, c.b, 1 - elapsedTime/3);
        } else {
            getStage().getActors().removeValue(this, true);
        }
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        setZIndex(5);
    }
}
