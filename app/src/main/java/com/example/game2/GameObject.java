package com.example.game2;

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
    public abstract void draw(Canvas canvas);
    public abstract void update();
}
