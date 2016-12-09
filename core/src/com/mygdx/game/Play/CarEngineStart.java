package com.mygdx.game.Play;

import com.badlogic.gdx.audio.Music;
import com.mygdx.game.GlobalClasses.Assets;

/**
 * Created by tanulo on 2016. 12. 09..
 */
public class CarEngineStart {

    private Music carstart;
    public static boolean played;

    public CarEngineStart() {
        if (!played) {
            carstart = Assets.manager.get(Assets.STARTCAR);
            carstart.play();
            played = true;
        }
    }
}
