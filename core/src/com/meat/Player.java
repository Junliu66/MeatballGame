package com.meat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * The player. Every level needs one.
 */
public class Player {
    private Vector2 location;
    private Vector2 velocity;
    private Vector2 force;
    private Texture tex;
    private float acceleration;
    private float deAcceleration;
    private float maxSpeed;

    /**
     *
     * @param tex A Texture representing the player.
     * @param spawnLoc The location the player spawns in the game.
     * @param acceleration How fast the player accelerates.
     * @param deAcceleration How quickly the player slows down.
     */
    public Player(Texture tex, Vector2 spawnLoc, float acceleration, float deAcceleration, float maxSpeed) {
        this.tex = tex;
        this.location = spawnLoc;
        this.acceleration = acceleration;
        this.velocity = new Vector2();
        this.force = new Vector2();
        this.deAcceleration = deAcceleration;
        this.maxSpeed = maxSpeed;
    }

    public Texture getTex() {return tex;}

    /**
     * Sets the force of the player.
     * @param force the new force
     */
    public void setForce(Vector2 force) {
        this.force = force;
    }

    /**
     * Updates the player's velocity vector and location.
     * @param dt The delta-time.
     */
    public void update(float dt)
    {
        float vX = velocity.x;
        float vY = velocity.y;
        Vector2 velocityNormal = new Vector2(velocity.x, velocity.y).nor();

        if (Math.abs(force.x) > 0) {
            vX = vX + force.x*dt*acceleration;
        } else {
            vX = vX - velocityNormal.x*dt*deAcceleration;
        }
        if (Math.abs(force.y) > 0) {
            vY = vY + force.y*dt*acceleration;
        } else {
            vY = vY - velocityNormal.y*dt*deAcceleration;
        }

        velocity = new Vector2(vX, vY);
        if (velocity.len() > maxSpeed)
            velocity.nor().scl(maxSpeed);

        System.out.println("vel_len: " + velocity.len());
        location.set(location.x + velocity.x, location.y + velocity.y);
    }

    public void dispose()
    {
        tex.dispose();
    }

    public Vector2 getLocation() {
        return location;
    }
}
