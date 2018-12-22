package com.example.dev.zeldacopy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH = 512;
    public static final int HEIGHT = 288;
    private String FILENAME = "level1.tmx";
    private ArrayList<Rect> collisionRects = new ArrayList<>();
    private MainThread thread;
    private CharacterAnimation ANM;
    private Background bk;
    public static Player player;
    private Bitmap mapImage;


    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);
        TileMapData map = TMXLoader.readTMX(FILENAME,context);
        mapImage = TMXLoader.createBitmap(map,context,0,map.layers.size());

        ANM = new CharacterAnimation(context);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }
    public GameView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);

        getHolder().addCallback(this);
        TileMapData map = TMXLoader.readTMX(FILENAME,context);
        mapImage = TMXLoader.createBitmap(map,context,0,map.layers.size());

        for(TileMapData.TMXObject currentObj : map.objects){
            collisionRects.add(new Rect(currentObj.x, currentObj.y, currentObj.width, currentObj.height));
        }

        ANM = new CharacterAnimation(context);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }
    public GameView(Context context,AttributeSet attributeSet,int defStyleAttr) {
        super(context,attributeSet,defStyleAttr);

        getHolder().addCallback(this);
        TileMapData map = TMXLoader.readTMX(FILENAME,context);
        mapImage = TMXLoader.createBitmap(map,context,0,map.layers.size());

        ANM = new CharacterAnimation(context);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
      if(mapImage != null)
       bk = new Background(mapImage);
      else
      {
          Toast errorMessage = Toast.makeText(getContext(), "Map could not be loaded", Toast.LENGTH_LONG);
          errorMessage.show();      }

        player = new Player(ANM.getHTaunt());
        player.setAnimation(ANM.getHTaunt(),ANM.getDelayTaunt());
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        int counter = 0;
        while(retry && counter < 1000)
        {
            try {
                counter++;
                thread.setRunning(false);
                thread.join();
                retry = false;

            }catch (InterruptedException e) {e.printStackTrace();}
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        return  true;
    }

    public void update()
    {
        bk.update();
        player.update();
        for(Rect r : collisionRects){
            if(Rect.intersects(r,player.getRect())){
                player.hit(ANM);
            }
        }
    }


    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);

        final float scaleFactorX = (float) getWidth() / (WIDTH * 1.f);
        final float scaleFactorY = (float) getHeight() / (HEIGHT * 1.f);


        if (canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bk.draw(canvas);

            player.draw(canvas);
            canvas.restoreToCount(savedState);
            postInvalidate();

        }
        else
        {
            System.out.println("You Drew Nothing");
        }
    }

}

