package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

        currentStartPoint = body.getPosition().cpy();

        texture = new Texture(Gdx.files.internal("fly.png"));
        textureRegion = new TextureRegion(texture);

    }

    public void update(){
        if(isAggressive){
            if(hasLostPlayer()){
                isAggressive = false;
                body.setLinearVelocity(0.0f, 0.0f);
            }
            else{
                //move toward player

                Vector2 next = getNextTrajectory();
                if(next != null){
                    body.setLinearVelocity(next);
                    //rotationAngle = next.angleRad() + PI2;
                    rotationAngle = next.angle();
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
