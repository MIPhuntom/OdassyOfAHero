package com.example.dev.zeldacopy;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private int FPS = 30;
    private double avrageFPS;
    private  SurfaceHolder surfaceHolder;
    private  GameView gameView;
    private  boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    public  void run()
    {
        long startTime;
        long timeMills;
        long waitTime;
        long totalTime = 0;
        int FrameCount = 0;
        long targetTime = 1000 / FPS;


        while(running)
        {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            }catch(Exception e) {
            } finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMills = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMills;

            try {
                this.sleep(waitTime);
            }catch (Exception e){}

            totalTime += System.nanoTime() - startTime;
            FrameCount++;
            if(FrameCount == FPS){
                avrageFPS = 1000/((totalTime/ FrameCount) / 1000000);
                FrameCount = 0;
                totalTime = 0;
                System.out.println(avrageFPS);
            }
        }
    }
    public void setRunning( boolean b){ running = b;}
}

