package com.meat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.meat.MeatGame.TO_PIXELS;

public abstract class Enemy {

    protected Body body;
    float speed;
    boolean isAggressive;
    Player playerRef;
    World worldRef;
    Texture texture;

    public Enemy(){
        body = null;
    }

    public Enemy(Vector2 initialPos, World world, float speed, Player player){
        this.speed = speed;
        isAggressive = false;
        playerRef = player;
        worldRef = world;

        createBody(initialPos);
    }

    public void createBody(Vector2 initialPos){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(initialPos);
        bodyDef.fixedRotation = true;
        bodyDef.linearDamping = 1.0f;
        body = worldRef.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(16/50f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.0f;
        fixtureDef.density = 15f;
        body.createFixture(fixtureDef);

    }

    public abstract void update();

    public void draw(SpriteBatch batch){

        batch.draw(texture, body.getPosition().x * TO_PIXELS - 16, body.getPosition().y * TO_PIXELS - 16);
    }
}
