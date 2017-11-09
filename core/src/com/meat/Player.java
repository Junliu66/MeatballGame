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
    private Vector2 input;
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
        this.input = new Vector2();
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
        this.input = force;
    }

    public void update(Body body) {
        input = new Vector2();
        boolean up, down, left, right;
        up = (Gdx.input.isKeyPressed(Input.Keys.UP));
        down = (Gdx.input.isKeyPressed(Input.Keys.DOWN));
        left = (Gdx.input.isKeyPressed(Input.Keys.LEFT));
        right = (Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        if (up && down)
            input.y = 0;
        else if (up)
            input.y = 1;
        else if (down)
            input.y = -1;
        if (left && right)
            input.x = 0;
        else if (left)
            input.x = -1;
        else if (right)
            input.x = 1;

        Vector2 velocity = body.getLinearVelocity();
        float vX = velocity.x;
        float vY = velocity.y;
        Vector2 velocityNormal = new Vector2(vX, vY).nor();

        if (Math.abs(input.x) > 0) {
            vX = vX + input.x * acceleration;
        } else {
            vX = vX - velocityNormal.x * deAcceleration;
        }
        if (Math.abs(input.y) > 0) {
            vY = vY + input.y * acceleration;
        } else {
            vY = vY - velocityNormal.y * deAcceleration;
        }

        body.setLinearVelocity(vX, vY);

        if (body.getLinearVelocity().len() > maxSpeed)
            body.getLinearVelocity().nor().scl(maxSpeed);

        int y = Math.round(2*MeatGame.TO_PIXELS * body.getPosition().y % 64f);
        int x = 64 - Math.round(2*MeatGame.TO_PIXELS * body.getPosition().x % 64f);
//        System.out.println("y: " + y + ", x: " + x);
        tex = new TextureRegion(origTex, x, y, 32, 32);
    }


    public void dispose()
    {
        origTex.dispose();
    }
}
