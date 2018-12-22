package com.example.dev.zeldacopy;

import android.graphics.Bitmap;

public class Animation {

    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long[] CompDelay ;
    private  boolean playedOnce;



    public void setFrames(Bitmap[] frames)
    {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    public void setDelay(long[] Delay){
        CompDelay = new long[Delay.length];

        if(Delay.length == frames.length) {
            for (int i = 0; i < frames.length; i++) {
                CompDelay[i] = Delay[i];
            }
        }
        else
            System.out.println("One of your animtaion is missing a delay number");
    }
    public void setDelay(long Delay){
        if(frames != null)
            CompDelay = new long[frames.length];

        else
            CompDelay = new long[4];

        for (int i = 0; i < frames.length; i++)
        {
            this.CompDelay[i] = Delay;
        }
    }

    public  void setFrame(int i){currentFrame = i;}

    public  void update()
    {
        long elapsed = (System.nanoTime() - startTime)/1000000;

        if(elapsed>CompDelay[currentFrame]) {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }
    }

    public  Bitmap getImage(){
        return  frames[currentFrame];
    }
    public  int getFrame(){ return currentFrame; }
    public boolean playedOnce(){return playedOnce; }
}


