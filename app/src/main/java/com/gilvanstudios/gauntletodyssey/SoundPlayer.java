package com.gilvanstudios.gauntletodyssey;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {

    private static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;
    private static int over2Sound;
    public static int dieSound;


    public SoundPlayer (Context context){


        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);

        hitSound = soundPool.load(context, R.raw.asteroid,1);
        overSound = soundPool.load(context, R.raw.gemsfx, 1);
        over2Sound = soundPool.load(context, R.raw.gemsfx2,1);
        dieSound = soundPool.load(context, R.raw.die, 1);
    }

    public void playHitSound(){

        soundPool.play(hitSound, 1.0f, 1.0f,1,0,1.0f);
    }

    public void playOverSound(){

        soundPool.play(overSound, 1.0f, 1.0f,1,0,1.0f);
    }

    public void playOver2Sound(){

        soundPool.play(over2Sound, 1.0f, 1.0f,1,0,1.0f);
    }

    public void dieSound(){

        soundPool.play(dieSound, 1.0f, 1.0f,1,0,1.0f);
    }


}
