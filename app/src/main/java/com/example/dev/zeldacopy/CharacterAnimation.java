package com.example.dev.zeldacopy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class CharacterAnimation {

    private  Bitmap[] HRunSideR, HRunSideL,HRunUp,HRunDown;
    private  Bitmap[] HAttackSideR, HAttackSideL, HAttackUp, HAttackDown;
    private  Bitmap[]  HIdle, HTaunt;
    private  Bitmap[] HHitSideR, HHitSideL,HHitUP,HHitDown;

    private long[] DelayTaunt, DelayAttack, DelayHit;

    private void flip(Bitmap[] d,int length, Bitmap[] Ds)
    {
        for (int i =0; i<length; i++) {
            Matrix m = new Matrix();
            m.postScale(-1, 1,d[i].getWidth()/2,d[i].getHeight()/2);
            Bitmap dst = Bitmap.createBitmap(d[i], 0, 0, d[i].getWidth(), d[i].getHeight(), m, true);
            Ds[i] = dst;
        }
    }

    public CharacterAnimation(Context context)
    {
        DelayTaunt = new long[4];
        DelayTaunt[0] = 640;
        DelayTaunt[1] = 80;
        DelayTaunt[2] = 640;
        DelayTaunt[3] = 80;

        DelayAttack = new long[4];
        DelayAttack[0] = 300;
        DelayAttack[1] = 100;
        DelayAttack[2] = 100;
        DelayAttack[3] = 200;

        DelayHit = new long[7];
        DelayHit[0] = 120;
        DelayHit[1] = 80;
        DelayHit[2] = 80;
        DelayHit[3] = 80;
        DelayHit[4] = 80;
        DelayHit[5] = 80;
        DelayHit[6] = 120;



        //Idle
        HIdle = new Bitmap[4];
        HIdle[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle_01);
        HIdle[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle_02);
        HIdle[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle_03);
        HIdle[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle_02);
        //Taunt
        HTaunt = new Bitmap[4];
        HTaunt[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.taunt_01);
        HTaunt[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.taunt_02);
        HTaunt[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.taunt_03);
        HTaunt[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.taunt_02);
        //Walk
        HRunDown = new Bitmap[4];
        HRunDown[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_down_01);
        HRunDown[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_down_02);
        HRunDown[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_down_03);
        HRunDown[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_down_04);

        HRunSideR = new Bitmap[4];
        HRunSideL = new Bitmap[4];
        HRunSideR[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_side_01);
        HRunSideR[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_side_02);
        HRunSideR[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_side_03);
        HRunSideR[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_side_04);
        flip(HRunSideR,HRunSideR.length,HRunSideL);

        HRunUp = new Bitmap[4];
        HRunUp[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_up_01);
        HRunUp[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_up_02);
        HRunUp[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_up_03);
        HRunUp[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_up_04);

        //Attack
        HAttackDown = new Bitmap[4];
        HAttackDown[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_down_01);
        HAttackDown[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_down_02);
        HAttackDown[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_down_03);
        HAttackDown[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_down_04);

        HAttackSideR = new Bitmap[4];
        HAttackSideL = new Bitmap[4];
        HAttackSideR[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_side_01);
        HAttackSideR[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_side_02);
        HAttackSideR[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_side_03);
        HAttackSideR[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_side_04);
        flip(HAttackSideR, HAttackSideR.length,HAttackSideL);

        HAttackUp = new Bitmap[4];
        HAttackUp[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_up_01);
        HAttackUp[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_up_02);
        HAttackUp[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_up_03);
        HAttackUp[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_up_04);

        //Hit
        HHitDown = new Bitmap[7];
        HHitDown[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_down_04);
        HHitDown[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_down_01);
        HHitDown[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_down_02);
        HHitDown[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_down_03);
        HHitDown[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_down_02);
        HHitDown[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_down_03);
        HHitDown[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_down_04);

        HHitSideL = new Bitmap[7];
        HHitSideR = new Bitmap[7];
        HHitSideR[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_side_04);
        HHitSideR[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_side_01);
        HHitSideR[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_side_02);
        HHitSideR[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_side_03);
        HHitSideR[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_side_02);
        HHitSideR[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_side_03);
        HHitSideR[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_side_04);
        flip(HHitSideR, HHitSideR.length,HHitSideL);

        HHitUP = new Bitmap[7];
        HHitUP[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_up_04);
        HHitUP[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_up_01);
        HHitUP[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_up_02);
        HHitUP[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_up_03);
        HHitUP[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_up_02);
        HHitUP[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_up_03);
        HHitUP[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hurt_up_04);

    }

    public long[] getDelayTaunt() {
        return DelayTaunt;
    }

    public long[] getDelayAttack() {
        return DelayAttack;
    }

    public long[] getDelayHit() {
        return DelayHit;
    }

    public  Bitmap[] getHRunSideR() {
        return HRunSideR;
    }

    public  Bitmap[] getHRunSideL() {
        return HRunSideL;
    }

    public  Bitmap[] getHRunUp() {
        return HRunUp;
    }

    public  Bitmap[] getHRunDown() {
        return HRunDown;
    }

    public  Bitmap[] getHAttackSideR() {
        return HAttackSideR;
    }

    public  Bitmap[] getHAttackSideL() {
        return HAttackSideL;
    }

    public  Bitmap[] getHAttackUp() {
        return HAttackUp;
    }

    public  Bitmap[] getHAttackDown() {
        return HAttackDown;
    }

    public  Bitmap[] getHIdle() {
        return HIdle;
    }

    public  Bitmap[] getHTaunt() {
        return HTaunt;
    }

    public  Bitmap[] getHHitSideR() {
        return HHitSideR;
    }

    public  Bitmap[] getHHitSideL() {
        return HHitSideL;
    }

    public  Bitmap[] getHHitUP() {
        return HHitUP;
    }

    public  Bitmap[] getHHitDown() {
        return HHitDown;
    }
}
