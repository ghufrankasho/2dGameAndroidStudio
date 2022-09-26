package com.example.game2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;

public class Player extends Circle {
    private final JoyStick joyStick;
    double actuatorX;
    double actuatorY;
    private static final double SPEED_PIXELS_PER_SECOND=400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;


   public Player(Context context,JoyStick joyStick,double positionx,double positiony,double radius){
     super(context,ContextCompat.getColor(context,R.color.magnata),positionx,positiony,radius);
     this.joyStick=joyStick;


   }






    public void setPosition(double positionx, double positiony) {
   this.positionx=positionx;
    this.positiony=positiony;
    }
    public void update() {

        // Update velocity based on actuator of joystick

        velocityX = joyStick.getActuatorX() * MAX_SPEED;
        velocityY = joyStick.getActuatorY()*MAX_SPEED;

        // Update position
        positionx += velocityX;
        positiony += velocityY;

        // Update direction
//        if (velocityX != 0 || velocityY != 0) {
//            // Normalize velocity to get direction (unit vector of velocity)
//            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
//            directionX = velocityX/distance;
//            directionY = velocityY/distance;
//
//        }
    }


}
