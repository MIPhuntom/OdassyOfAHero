package com.example.dev.zeldacopy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity implements JoystickView.JoystickListener {

    private CharacterAnimation CA;
    private double Xpart,Ypart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        CA = new CharacterAnimation(this);

    }

    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int id) {

        System.out.println("Xprecent: " + xPercent + " Yprecent: " + yPercent);
        Xpart = (double)(xPercent);
        Ypart = (double) (yPercent);
        if(xPercent != 0 || yPercent != 0) {
            GameView.player.setUP(true);

                GameView.player.dy = (int)(Ypart*2);
                GameView.player.dx = (int)(Xpart*2);
                if(Math.abs(xPercent) > Math.abs(yPercent)) {
                    if (xPercent > 0) {
                        GameView.player.setAnimation(CA.getHRunSideR());
                    } else if (xPercent < 0) {
                        GameView.player.setAnimation(CA.getHRunSideL());
                    }
                }
                else
                {
                    if (yPercent > 0) {
                        GameView.player.setAnimation(CA.getHRunDown());
                    } else if (yPercent < 0) {
                        GameView.player.setAnimation(CA.getHRunUp());
                    }
                }

        }
        else{

            GameView.player.setUP(false);
            GameView.player.setDx(0);
            GameView.player.setDy(0);
                GameView.player.setAnimation(CA.getHIdle(),CA.getDelayTaunt());

        }
    }

}
