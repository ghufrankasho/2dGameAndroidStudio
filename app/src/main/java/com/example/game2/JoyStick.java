package com.example.game2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class JoyStick {
    private double joystickcenterToTouchDistance;
    private int outerCircleCenterPositionx;
    private int outerCircleCenterPositiony;
    private int innerCircleCenterPositiony;
    private int  innerCircleCenterPositionx;
    private int outerCircleRadius;
    private int innerCircleRadius;
    private Paint paintinner,paintouter;
    private boolean isPressed=false;
    private  double actuatorX,actuatorY;

    public JoyStick(int centerpositionx,int centerpositiony,int outerCircleRadius,int innerCircleRadius ){
       outerCircleCenterPositionx=centerpositionx;
       outerCircleCenterPositiony=centerpositiony;
       innerCircleCenterPositionx=centerpositionx;
       innerCircleCenterPositiony=centerpositiony;
       //radii of circle
       this.innerCircleRadius=innerCircleRadius;
       this.outerCircleRadius=outerCircleRadius;
       //paint of circle
       paintinner=new Paint();
       paintinner.setColor(Color.BLUE);
       paintinner.setStyle(Paint.Style.FILL_AND_STROKE);

       paintouter=new Paint();
       paintouter.setColor(Color.GRAY);
       paintouter.setStyle(Paint.Style.FILL_AND_STROKE);


   }
    public void draw(Canvas canvas) {
       canvas.drawCircle(outerCircleCenterPositionx,outerCircleCenterPositiony,outerCircleRadius,paintouter);
        canvas.drawCircle(innerCircleCenterPositionx,innerCircleCenterPositiony,innerCircleRadius,paintinner);


    }
    public void update() {
        updateCircleCenterPosition();
    }
    private void updateCircleCenterPosition() {
        innerCircleCenterPositionx=(int)(outerCircleCenterPositionx+actuatorX*outerCircleRadius);
        innerCircleCenterPositiony=(int)(outerCircleCenterPositiony+actuatorY*outerCircleRadius);
    }
    public boolean isPressed(double touchPositionx, double touchPositiony) {
       joystickcenterToTouchDistance=Math.sqrt(
               Math.pow(outerCircleCenterPositionx-touchPositionx,2)+Math.pow(outerCircleCenterPositiony-touchPositiony,2));
       return joystickcenterToTouchDistance <outerCircleRadius;
    }
    public void setIsPressed(boolean isPressed) {
       this.isPressed=isPressed;

        }
    public boolean getIsPressed(){
        return isPressed;
        }
    public void setActuator(double touchPositionX, double touchPositionY) {
        double deltaX = touchPositionX - outerCircleCenterPositionx;
        double deltaY = touchPositionY - outerCircleCenterPositiony;
        double deltaDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));


        if(deltaDistance < outerCircleRadius) {
            actuatorX = deltaX/outerCircleRadius;
            actuatorY = deltaY/outerCircleRadius;
        } else {
            actuatorX = deltaX/deltaDistance;
            actuatorY = deltaY/deltaDistance;
        }
    }
    public double getActuatorX() {
        return actuatorX;
    }
    public double getActuatorY() {
        return actuatorY;
    }
    public void resetActuator() {
        actuatorX = 0;
        actuatorY = 0;
    }

}
