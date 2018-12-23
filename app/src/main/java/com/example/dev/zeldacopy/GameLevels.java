package com.example.dev.zeldacopy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

public class GameLevels {
    private Context context;
    private int level;
    private TileMapData mapData;
    private Bitmap generatedMapImage;

    public GameLevels(Context context, int level) {
        this.context = context;
        this.level = level;
        this.mapData = TMXLoader.readTMX("level" + level + ".tmx", context);
        generatedMapImage = TMXLoader.createBitmap(mapData, context, 0, mapData.layers.size());
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Bitmap getGeneratedMapImage() {
        return generatedMapImage;
    }

    public Bitmap getScaledMapImage(){
        return Bitmap.createScaledBitmap(generatedMapImage, 700, 525, true);
    }

    public ArrayList<Rect> getCollisionBoxes(){
        ArrayList<Rect> collisionBoxes = new ArrayList<>();
        for(TileMapData.TMXObject currentObj : mapData.objects){
            if(currentObj.objectGroup.equals("colliders"))
                collisionBoxes.add(new Rect(currentObj.x/2, currentObj.y/2, (currentObj.x + currentObj.width)/2, (currentObj.y + currentObj.height)/2));
        }
        return collisionBoxes;
    }

    public ArrayList<Rect> getTriggerBoxes(){
        ArrayList<Rect> triggerBoxes = new ArrayList<>();
        for(TileMapData.TMXObject currentObj : mapData.objects){
            if(currentObj.objectGroup.equals("interact"))
                triggerBoxes.add(new Rect(currentObj.x/2, currentObj.y/2, (currentObj.x + currentObj.width)/2, (currentObj.y + currentObj.height)/2));
        }
        return triggerBoxes;
    }

    public ArrayList<Point> getAllLevelPoints(){
        ArrayList<Point> levelPoints = new ArrayList<>();
        for(TileMapData.TMXObject currentObj : mapData.objects){
            if(currentObj.objectGroup.equals("points"))
                levelPoints.add(new Point(currentObj.x/2, currentObj.y/2));
        }
        return levelPoints;
    }

    public ArrayList<Point> getSpawnPoints(){
        ArrayList<Point> spawnPoints = new ArrayList<>();
        for(TileMapData.TMXObject currentObj : mapData.objects){
            if(currentObj.objectGroup.equals("points") || currentObj.name.equals("spawn"))
                spawnPoints.add(new Point(currentObj.x/2, currentObj.y/2));
        }
        return spawnPoints;
    }
}
