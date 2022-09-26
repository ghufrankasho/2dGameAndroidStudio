package com.example.game2.Object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.game2.GameLoop;
import com.example.game2.R;

public class Enemy extends Circle{


    private final Player player;
    private static final double SPEED_PIXELS_PER_SECOND=Player.SPEED_PIXELS_PER_SECOND*0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    public Enemy(Context context, Player player, int color, double positionx, double positiony, double radius) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionx, positiony, radius);
        this.player=player;
    }

    @Override
    public void update() {
        // =========================================================================================
        //   Update velocity of the enemy so that the velocity is in the direction of the player
        // =========================================================================================
        // Calculate vector from enemy to player (in x and y)
        double distanceToPlayerx=player.getPositionx()-positionx;
        double distanceToPlayery=player.getPositiony()-positiony;

        // Calculate (absolute) distance between enemy (this) and player
        double distanceToPlayer=GameObject.getDistanceBetweenPlayer(this,player);

        // Calculate direction from enemy to player
        double directionx=distanceToPlayerx/distanceToPlayer;
        double directiony=distanceToPlayery/distanceToPlayer;
        // Set velocity in the direction to the player
        if(distanceToPlayer >0){
            velocityX=directionx*MAX_SPEED;
            velocityY=directiony*MAX_SPEED;
        }
        else{
            velocityX=0;
            velocityY=0;
        }
        // =========================================================================================
        //   Update position of the enemy
        // =========================================================================================
        positionx +=velocityX;
        positiony +=velocityY;

    }

}
