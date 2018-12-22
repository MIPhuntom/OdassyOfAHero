package com.example.dev.zeldacopy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player extends GameObject {

    public static final int WIDTH = 512;
    public static final int HEIGHT = 288;
    private Bitmap[] spritesheet;
    private int health;
    private boolean playing,up;
    private long dmgTime;
    private Bitmap[] saveSprite;

    private Animation animation = new Animation();
    private  long startTime;

    public  Player(Bitmap[] res)
    {

        x = (GameView.WIDTH/2);
        y = (GameView.HEIGHT/2);
        dy = 0;
        dx =0;
        health = 100;
        height = y + 16;
        width = x + 16;
        spritesheet = res;
        animation.setFrames(spritesheet);
        animation.setDelay(100);
        startTime = System.nanoTime();

    }

    public  void update()
    {
        animation.update();

        if(up)
        {
            x += dx;
            width = x + 16;
            y += dy;
            height = y + 16;
        }



    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }

    public void setUP(boolean b){ up = b;}

    public void setAnimation(Animation a)
    {
        this.animation = a;
        animation.setDelay(100);
    }
    public void setAnimation(Bitmap[] resNew){
        spritesheet =resNew;
        animation.setFrames(spritesheet);
        animation.setDelay(100);
    }
    public void setAnimation(Bitmap[] resNew, int delay){
        spritesheet =resNew;
        animation.setFrames(spritesheet);
        animation.setDelay(delay);
    }
    public void setAnimation(Bitmap[] resNew, long[] delay){
        spritesheet =resNew;
        animation.setFrames(spritesheet);
        animation.setDelay(delay);
    }
    public int getScore() {return health;}

    public void TakeDamge(int dmg, Bitmap[] dmgshit, int delay)
    {

        if(dmgTime == 0)
        {
            saveSprite = spritesheet;
            health = health - dmg;
            dmgTime = System.currentTimeMillis();
        }
      else  if((System.currentTimeMillis()-dmgTime) > 1000  ) {
            health = health - dmg;
            dmgTime = System.currentTimeMillis();
            setAnimation(dmgshit,delay);
        }
        else{
            System.out.println("Player already took dmg!");
            System.out.println(System.currentTimeMillis() + "timedmg" + dmgTime);
            setAnimation(saveSprite);

        }
    }
    public void CheckAlive(){

        if(health <= 0)
        {
            System.out.println("Game is Over!");
        }
        else
        {
            System.out.println(this.health);
        }
    }

    //TODO: Animation change wouldn't work for some reason.. Why? (I actually have a guess but fuck logic rn)
    public void hit(CharacterAnimation charAnim){
        int newX = x;
        if (x < WIDTH/2){
            setAnimation(charAnim.getHHitSideL());
            newX = x + 10;
        }
        else if (x > WIDTH/2){
            setAnimation(charAnim.getHHitSideR());
            newX = x - 10;
        }
        int newY = y;
        if (y < HEIGHT/2){
            setAnimation(charAnim.getHHitDown());
            newY = y + 10;
        }
        else if (y > HEIGHT/2){
            setAnimation(charAnim.getHHitUP());
            newY = y - 10;
        }
        x = newX;
        y = newY;
    }

    public Rect getRect()
    {
        return new Rect(x,y,width,height);
    }
    public boolean getPlaying() {return playing;}
    public void setPlaying(boolean b){ playing =b;}
    public void resetDY() {dy = 0;}
    public void Heal() {health = this.health + 50;}

}
