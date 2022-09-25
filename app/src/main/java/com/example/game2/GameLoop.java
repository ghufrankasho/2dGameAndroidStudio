package com.example.game2;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.game2.Game;

import kotlin.jvm.Synchronized;

public class GameLoop extends  Thread{
    private boolean isRunning=false;
    private SurfaceHolder surfaceHolder;
    private Game game;
    private double averageUPS;
    Canvas canvas;
    private double averageFPS;
    private static final double Max_ups =30.0;
    private static final double UPS_PERIOD =1E+3/Max_ups;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.surfaceHolder=surfaceHolder;
        this.game=game;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();
        //Declare time and cycle count variables
        int updateCount=0;
        int frameCount=0;

        long startTime;
        long sleepTime;
        long elapsedTime;

        //Game loop



        startTime = System.currentTimeMillis();

        while (isRunning)
        {
            try {
                // try to update and render game
                canvas = surfaceHolder.lockCanvas();

                    game.update();
                    game.draw(canvas);
                   updateCount++;


            }
            catch (IllegalArgumentException e){
                e.printStackTrace();
            }
            finally {
                if(canvas != null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }


            //Pause game loop to not exceed target UPS
            elapsedTime =System.currentTimeMillis()-startTime;
            sleepTime=(long)(updateCount*UPS_PERIOD - elapsedTime);
            if(sleepTime >0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Skip frame to keep up target UPS
            while(sleepTime<0 && updateCount<Max_ups){

                game.update();
                updateCount++;
                elapsedTime=System.currentTimeMillis()-startTime;
                sleepTime=(long)(updateCount*UPS_PERIOD-elapsedTime);
            }
            //Calculate average UPS and FPS

            elapsedTime = System.currentTimeMillis()-startTime;

            if (elapsedTime >=1000){
                averageUPS =updateCount / (1E-3 * elapsedTime);
                averageFPS = frameCount / (1E-3 * elapsedTime);
                updateCount=0;
                frameCount=0;
                startTime=System.currentTimeMillis();
            }




        }
    }}
