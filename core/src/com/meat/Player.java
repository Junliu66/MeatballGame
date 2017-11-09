package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private Body body;

    /**
     *
     * @param spawnLoc The location the player spawns in the game.
     * @param acceleration How fast the player accelerates.
     */
    public Player(Vector2 spawnLoc, float acceleration, World world) {
        this.origTex = new Texture("meatball_face.png");
        this.acceleration = acceleration;
        this.input = new Vector2();
        this.tex = new TextureRegion(origTex, 32, 32, 32, 32);

        BodyDef playerDef = new BodyDef();
        playerDef.type = BodyDef.BodyType.DynamicBody;
        playerDef.position.set(4, 10);
        playerDef.fixedRotation = true;
        playerDef.linearDamping = 0.2f;
        body = world.createBody(playerDef);
        CircleShape circle = new CircleShape();
        circle.setRadius(16/50f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 0.75f;
        fixtureDef.friction = 0f;
        fixtureDef.density = 300f;
        body.createFixture(fixtureDef);
        circle.dispose();
    }

    public TextureRegion getTex() {return tex;}

    public Vector2 getPosition() {return body.getPosition();}

    public void update() {
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

        body.applyForceToCenter(input.scl(acceleration), true);
        int y = Math.round(2*MeatGame.TO_PIXELS * body.getPosition().y % 64f);
        int x = 64 - Math.round(2*MeatGame.TO_PIXELS * body.getPosition().x % 64f);
        tex = new TextureRegion(origTex, x, y, 32, 32);
    }


    public void dispose()
    {
        origTex.dispose();
    }
}
