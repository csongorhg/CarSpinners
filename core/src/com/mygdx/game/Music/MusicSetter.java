package com.mygdx.game.Music;

import com.badlogic.gdx.audio.Music;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Math.Random;

/**
 * Created by mordes on 2016.11.12..
 */
public class MusicSetter {

    //zenéket private-ra raktam, minek tudjanak róla a többiek x)
    private final Music menumusic = Assets.manager.get(Assets.MOOSE);
    private final Music gamemusic1 = Assets.manager.get(Assets.POPDANCE);
    private final Music gamemusic2 = Assets.manager.get(Assets.HAPPYROCK);
    private final Music gamemusic3 = Assets.manager.get(Assets.EXTREMEACTION);
    private final Music gamemusic4 = Assets.manager.get(Assets.DANCE);
    private final Music gamemusic5 = Assets.manager.get(Assets.BADASS);

    private static int isSameMusic; //ugyan az a zene, melyik zene

    public MusicSetter() {

        stopMusics();

        menumusic.play();

    }

    public MusicSetter(int whichMusic) {

        while (isSameMusic == whichMusic){ //amíg nem lesz más mint az előbb játszott zene
            //kiraktam egy osztályba a random fv.-t, mivel több helyen is használjuk
            whichMusic = new Random(1,5).getGenNumber();
        }

        stopMusics();

        switch (whichMusic) {
            case 1: gamemusic1.play(); isSameMusic = 1; break;
            case 2: gamemusic2.play(); isSameMusic = 2; break;
            case 3: gamemusic3.play(); isSameMusic = 3; break;
            case 4: gamemusic4.play(); isSameMusic = 4; break;
            case 5: gamemusic5.play(); isSameMusic = 5; break;
        }
    }

    private void stopMusics() {
        menumusic.stop();
        gamemusic1.stop();
        gamemusic2.stop();
        gamemusic3.stop();
        gamemusic4.stop();
        gamemusic5.stop();
    }

}
