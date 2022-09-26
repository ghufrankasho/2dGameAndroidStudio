package com.example.game2.Object;

import android.graphics.Canvas;

public abstract class GameObject {
    protected   double positionx;
    protected double positiony;
    protected double velocityX;
    protected double velocityY;
    public GameObject(double positionx,double positiony){
        this.positionx=positionx;
        this.positiony=positiony;
    }

    protected static double getDistanceBetweenPlayer(GameObject object1, GameObject object2) {
        return Math.sqrt(Math.pow(object2.getPositionx()-object1.getPositionx(),2)+
                        Math.pow(object2.getPositiony()-object1.getPositiony(),2));

    }

    public abstract void draw(Canvas canvas);
    public abstract void update();

    public double getPositionx() {
        return positionx;
    }

    public double getPositiony() {
        return positiony;
    }
}
