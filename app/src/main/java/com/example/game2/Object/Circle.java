package com.example.game2.Object;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public  abstract class Circle extends GameObject{
    protected  double radius;
    protected Paint paint;
    public Circle(Context context,int color,double positionx, double positiony,double radius) {
        super(positionx, positiony);
        this.radius=radius;
        //set color of circle
        paint=new Paint();
        paint.setColor(color);
    }
    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionx,(float) positiony,(float) radius,paint);
    }
}
