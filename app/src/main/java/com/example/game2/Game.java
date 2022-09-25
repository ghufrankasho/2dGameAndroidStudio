package com.example.game2;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import androidx.core.content.ContextCompat;
public class Game extends SurfaceView implements SurfaceHolder.Callback {

   private GameLoop gameLoop;
private   Player player;


    public Game(Context context) {
        super(context);
        SurfaceHolder surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);

        gameLoop=new GameLoop(this,surfaceHolder);
        //initialize the player
         player=new Player(getContext(),500,500,30);
        setFocusable(true);
    }
    public boolean onTouchEvent(MotionEvent event) {
        //handel touch event action
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            player.setPosition((double) event.getX(),(double) event.getY());
                return  true;
            case MotionEvent.ACTION_MOVE:
                player.setPosition((double) event.getX(),(double) event.getY());
                return  true;
        }
        return  super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
         gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawups(canvas);
        drawFps(canvas);
        player.draw(canvas);
    }
    public void drawups(Canvas canvas){
        String avarageUps;
        avarageUps = Double.toString(gameLoop.getAverageUPS());
        Paint paint =new Paint();

       int color=ContextCompat.getColor(getContext() , R.color.magnata);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: "+gameLoop.getAverageUPS(),100,100,paint);
    }
    public void drawFps(Canvas canvas){
        String avarageFps=Double.toString(gameLoop.getAverageFPS());
        Paint paint =new Paint();

     int color=ContextCompat.getColor(getContext(),R.color.magnata);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: "+gameLoop.getAverageFPS(),100,200,paint);
    }

    public void update() {
        //initialize Game status
        player.update();
    }
}
