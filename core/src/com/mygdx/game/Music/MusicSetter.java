package com.mygdx.game.Music;

import com.badlogic.gdx.audio.Music;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Math.Random;
import com.mygdx.game.Settings.SettingsScreen;
import com.mygdx.game.Settings.SettingsStage;

/**
 * Created by mordes on 2016.11.12..
 */
public class MusicSetter {

    //zenék
    //http://www.bensound.com/royalty-free-music/track/pop-dance
    //http://www.bensound.com/royalty-free-music/track/moose
    //http://www.bensound.com/royalty-free-music/track/happy-rock
    //http://www.bensound.com/royalty-free-music/track/extreme-action

    //zenéket private-ra raktam, minek tudjanak róla a többiek x)
    private final Music menumusic = Assets.manager.get(Assets.MOOSE);
    private final Music gamemusic1 = Assets.manager.get(Assets.POPDANCE);
    private final Music gamemusic2 = Assets.manager.get(Assets.HAPPYROCK);
    private final Music gamemusic3 = Assets.manager.get(Assets.EXTREMEACTION);
    private final Music gamemusic4 = Assets.manager.get(Assets.DANCE);
    private final Music gamemusic5 = Assets.manager.get(Assets.BADASS);

    private static int isSameMusic; //ugyan az a zene, melyik zene
/*
    public MusicSetter(boolean b) {
        stopMusics();
        if(b) menumusic.play();
    }

    public MusicSetter(){
    }

    public void musicVolume(float f){
        menumusic.setVolume(f);
        gamemusic1.setVolume(f);
        gamemusic2.setVolume(f);
        gamemusic3.setVolume(f);
        gamemusic4.setVolume(f);
        gamemusic5.setVolume(f);
    }

    public void MenuMusic(){
        System.out.println(SettingsStage.isB());
        if(!menumusic.isPlaying() && SettingsStage.isB()){
            menumusic.stop();
            menumusic.play();
        }
    }

    public void MenuMusic2(){
        menumusic.stop();
        menumusic.play();
    }

    public float getMenuVolume(){return menumusic.getVolume();}
    public float getGameVolume(){return gamemusic1.getVolume();}

    public void gameMusic(int whichMusic) {

        if(!(gamemusic1.isPlaying() || gamemusic2.isPlaying() || gamemusic3.isPlaying() || gamemusic4.isPlaying() || gamemusic5.isPlaying())) {
            while (isSameMusic == whichMusic) { //amíg nem lesz más mint az előbb játszott zene
                //kiraktam egy osztályba a random fv.-t, mivel több helyen is használjuk
                whichMusic = new Random(1, 5).getGenNumber();
            }

            stopMusics();

            switch (whichMusic) {
                case 1:
                    gamemusic1.play();
                    isSameMusic = 1;
                    break;
                case 2:
                    gamemusic2.play();
                    isSameMusic = 2;
                    break;
                case 3:
                    gamemusic3.play();
                    isSameMusic = 3;
                    break;
                case 4:
                    gamemusic4.play();
                    isSameMusic = 4;
                    break;
                case 5:
                    gamemusic5.play();
                    isSameMusic = 5;
                    break;
            }
        }
    }

    public MusicSetter(int whichMusic) {

        if(!(gamemusic1.isPlaying() || gamemusic2.isPlaying() || gamemusic3.isPlaying() || gamemusic4.isPlaying() || gamemusic5.isPlaying())) {
            while (isSameMusic == whichMusic) { //amíg nem lesz más mint az előbb játszott zene
                //kiraktam egy osztályba a random fv.-t, mivel több helyen is használjuk
                whichMusic = new Random(1, 5).getGenNumber();
            }

            stopMusics();

            switch (whichMusic) {
                case 1:
                    gamemusic1.play();
                    isSameMusic = 1;
                    break;
                case 2:
                    gamemusic2.play();
                    isSameMusic = 2;
                    break;
                case 3:
                    gamemusic3.play();
                    isSameMusic = 3;
                    break;
                case 4:
                    gamemusic4.play();
                    isSameMusic = 4;
                    break;
                case 5:
                    gamemusic5.play();
                    isSameMusic = 5;
                    break;
            }
        }
    }

    public void stopMusics() {
        menumusic.stop();
        gamemusic1.stop();
        gamemusic2.stop();
        gamemusic3.stop();
        gamemusic4.stop();
        gamemusic5.stop();
    }
*/
}
