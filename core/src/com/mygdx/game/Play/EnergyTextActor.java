package com.mygdx.game.Play;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.DemoMenu.MenuStage;
import com.mygdx.game.MyBaseClasses.MyLabel;

import static com.badlogic.gdx.scenes.scene2d.utils.ScissorStack.getViewport;

/**
 * Created by Kicsi on 2016. 12. 09..
 */

public class EnergyTextActor extends MyLabel {
    public EnergyTextActor(LabelStyle style) {
        super("LOW ENERGY!", style);
        setPosition(135-this.getWidth()/2,410f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setVisible(((int) (elapsedtime * 5)) % 2 == 0);
    }
}
