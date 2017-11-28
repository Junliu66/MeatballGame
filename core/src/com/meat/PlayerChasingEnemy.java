package com.meat;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;

import java.lang.reflect.Array;
import java.util.*;

public class PlayerChasingEnemy extends Enemy {

    float minRadius;        //the radius that will trigger enemy aggro
    float minRadiusSquare;
    float maxRadius;        //the radius that the enemy loses aggro
    float maxRadiusSquare;

    Vector2 currentStartPoint;
    final float maxStep = 0.5f;
    final float maxStepSquare = (float) Math.pow(maxStep, 2);

    //defining the possible directions that an enemy can check
    ArrayList<Vector2> directions;


    public PlayerChasingEnemy(){

    }

    public PlayerChasingEnemy(Vector2 initialPos, World world, float speed, Player player, float minRadius, float maxRadius){
        super(initialPos, world, speed, player);

        this.minRadius = minRadius;
        minRadiusSquare = minRadius * minRadius;
        this.maxRadius = maxRadius;
        maxRadiusSquare = maxRadius * maxRadius;


        //defining the possible directions that an enemy can check
        directions = new ArrayList<Vector2>();
        directions.add(new Vector2(1, 0));
        directions.add(new Vector2(0, 1));
        directions.add(new Vector2(-1, 0));
        directions.add(new Vector2(0, -1));
        directions.add(new Vector2(1,1).nor());
        directions.add(new Vector2(1, -1).nor());
        directions.add(new Vector2(-1, 1).nor());
        directions.add(new Vector2(-1, -1).nor());


        currentStartPoint = body.getPosition().cpy();
    }

    public void update(){
        if(isAggressive){
            if(hasLostPlayer()){
                isAggressive = false;
            }
            else{
                //move toward player

                Vector2 next = getNextTrajectory();
                if(next != null){
                    body.setLinearVelocity(getNextTrajectory().scl(speed));
                }
                else{
                }


            }
        }
        else{
            if(isNearPlayer()){
                isAggressive = true;
            }
        }
    }

    protected boolean isNearPlayer(){
        Vector2 playerPosition = playerRef.getPosition();
        return ((float)(Math.pow(playerPosition.x - body.getPosition().x, 2) +
                        Math.pow(playerPosition.y - body.getPosition().y, 2)) <= minRadiusSquare);
    }

    protected boolean hasLostPlayer(){
        Vector2 playerPosition = playerRef.getPosition();
        return ((float)(Math.pow(playerPosition.x - body.getPosition().x, 2) +
                        Math.pow(playerPosition.y - body.getPosition().y, 2)) >= maxRadiusSquare);
    }

    protected Vector2 getNextTrajectory(){

        return playerRef.getPosition().add(body.getPosition().scl(-1)).nor().scl(speed);


    }


}
