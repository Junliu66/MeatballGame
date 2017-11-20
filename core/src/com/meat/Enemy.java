package com.meat;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.meat.MeatGame.TO_PIXELS;

public abstract class Enemy {

    protected Body body;
    float speed;
    boolean isAggressive;
    Player playerRef;
    float minRadius;        //the radius that will trigger enemy aggro
    float minRadiusSquare;
    float maxRadius;        //the radius that the enemy loses aggro
    float maxRadiusSquare;

    public Enemy(){
        body = null;
    }

    public Enemy(Vector2 initialPos, World world, float speed, Player player, float minRadius, float maxRadius){
        this.speed = speed;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(initialPos);
        bodyDef.fixedRotation = true;
        bodyDef.linearDamping = 1.0f;
        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(16/50f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.0f;
        fixtureDef.density = 15f;
        body.createFixture(fixtureDef);

        isAggressive = false;
        playerRef = player;
        this.minRadius = minRadius;
        minRadiusSquare = minRadius * minRadius;
        this.maxRadius = maxRadius;
        maxRadiusSquare = maxRadius * maxRadius;
    }

    public abstract void update();

    protected boolean isNearPlayer(){
        Vector2 playerPosition = playerRef.getPosition();
        return ((float)Math.pow(playerPosition.x, playerPosition.y) <= minRadiusSquare);
    }

    protected boolean hasLostPlayer(){
        Vector2 playerPosition = playerRef.getPosition();
        return ((float)Math.pow(playerPosition.x, playerPosition.y) >= maxRadiusSquare);
    }
}
