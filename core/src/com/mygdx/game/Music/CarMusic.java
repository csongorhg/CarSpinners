package com.mygdx.game.Music;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

/**
 * Created by tuskeb on 2016. 12. 09..
 */

public class CarMusic implements Disposable{

    private static boolean musicPlay = true;
    private static float volume = 1;
    private ArrayList<Music> musics = new ArrayList<Music>();
    private int nowPlayed = -1;


    public static void setVolume(float volume) {
        CarMusic.volume = volume;
        //System.out.println("Setvolume " + volume);
    }

    public static void setMusicPlay(boolean musicPlay) {
        CarMusic.musicPlay = musicPlay;
        if (!musicPlay){
        }
    }


    public static boolean isMusicPlay() {
        return musicPlay;
    }

    public static float getVolume() {
        return volume;
    }


    public void addMusic(Music music){
        music.setVolume(volume);
        music.setLooping(false);
        music.stop();
        musics.add(music);
    }

    public void act(){
        boolean play = false;
        for (Music m: musics) {
            if (m.isPlaying()){
                if (!musicPlay){
                    m.stop();
                }else{
                    play = true;
                }
            }
            if (m.getVolume()!=volume){
                m.setVolume(volume);
            }
        }
        if (nowPlayed==-1 && !play && musicPlay){
            if (musics.size()>0){
                musics.get(nowPlayed = (int)Math.floor(Math.random()*musics.size())).play();
            }
        }
    }

    public void dispose() {
        for (Music m : musics) {
            m.stop();
        }
    }
}
