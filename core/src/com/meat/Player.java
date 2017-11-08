package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * The player. Every level needs one.
 */
public class Player {
    private Vector2 force;
    private Texture origTex;
    private TextureRegion tex;
    private float acceleration;
    private float deAcceleration;
    private float maxSpeed;

    /**
     *
     * @param spawnLoc The location the player spawns in the game.
     * @param acceleration How fast the player accelerates.
     * @param deAcceleration How quickly the player slows down.
     */
    public Player(Vector2 spawnLoc, float acceleration, float deAcceleration, float maxSpeed, World world) {
        this.origTex = new Texture("meatball_face.png");
        this.acceleration = acceleration;
        this.force = new Vector2();
        this.deAcceleration = deAcceleration;
        this.maxSpeed = maxSpeed;
        this.tex = new TextureRegion(origTex, 32, 32, 32, 32);
    }

    public TextureRegion getTex() {return tex;}

    /**
     * Sets the force of the player.
     * @param force the new force
     */
    public void setForce(Vector2 force) {
        this.force = force;
    }

    public void update(Body body)
    {
        Vector2 velocity = new Vector2();
        force = new Vector2();
        boolean up, down, left, right;
        up = (Gdx.input.isKeyPressed(Input.Keys.UP));
        down = (Gdx.input.isKeyPressed(Input.Keys.DOWN));
        left = (Gdx.input.isKeyPressed(Input.Keys.LEFT));
        right = (Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        if (up&&down)
            force.y = 0;
        else if (up)
            force.y = 1;
        else if (down)
            force.y = -1;
        if (left&&right)
            force.x = 0;
        else if (left)
            force.x = -1;
        else if (right)
            force.x = 1;

        float vX = body.getLinearVelocity().x;
        float vY = body.getLinearVelocity().y;
        Vector2 velocityNormal = new Vector2(body.getLinearVelocity().x, body.getLinearVelocity().y).nor();

        if (Math.abs(force.x) > 0) {
            vX = vX + force.x*acceleration;
        } else {
            vX = vX - velocityNormal.x*deAcceleration;
        }
        if (Math.abs(force.y) > 0) {
            vY = vY + force.y*acceleration;
        } else {
            vY = vY - velocityNormal.y*deAcceleration;
        }

        body.setLinearVelocity(new Vector2(vX, vY));
        if (body.getAngularVelocity() > maxSpeed)
            body.getLinearVelocity().nor().scl(maxSpeed);
    }

    /**
     * Updates the player's velocity vector and location.
     * @param dt The delta-time.
     */
    public void update(float dt)
    {
        force = new Vector2();
        boolean up, down, left, right;
        up = (Gdx.input.isKeyPressed(Input.Keys.UP));
        down = (Gdx.input.isKeyPressed(Input.Keys.DOWN));
        left = (Gdx.input.isKeyPressed(Input.Keys.LEFT));
        right = (Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        if (up&&down)
            force.y = 0;
        else if (up)
            force.y = 1;
        else if (down)
            force.y = -1;
        if (left&&right)
            force.x = 0;
        else if (left)
            force.x = -1;
        else if (right)
            force.x = 1;
//
//        float vX = getLinearVelocity().x;
//        float vY = getLinearVelocity().y;
//        Vector2 velocityNormal = new Vector2(getLinearVelocity().x, getLinearVelocity().y).nor();
//
//        if (Math.abs(force.x) > 0) {
//            vX = vX + force.x*dt*acceleration;
//        } else {
//            vX = vX - velocityNormal.x*dt*deAcceleration;
//        }
//        if (Math.abs(force.y) > 0) {
//            vY = vY + force.y*dt*acceleration;
//        } else {
//            vY = vY - velocityNormal.y*dt*deAcceleration;
//        }

//        setLinearVelocity(new Vector2(vX, vY));
//        if (getAngularVelocity() > maxSpeed)
//            getLinearVelocity().nor().scl(maxSpeed);

//        System.out.println("vel_len: " + velocity.len());
//        location.set(location.x + velocity.x, location.y + velocity.y);

//        int y = Math.round(getPosition().y % 64f);
//        int x = 64 + Math.round (-getPosition().x % 64f);
//        System.out.println("y: " + y + ", x: " + x);
//        tex = new TextureRegion(origTex, x, y, 32, 32);
    }

    public void dispose()
    {
        origTex.dispose();
    }
}
