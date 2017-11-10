package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import static com.meat.MeatGame.TO_PIXELS;

/**
 * The player. Every level needs one.
 */
public class Player {
    private Vector2 input;
    private Texture meatTexture;
    private float acceleration;
    private Body body;
    private int textureOffsetX;
    private int textureOffsetY;

    /**
     *
     * @param spawnLoc The location the player spawns in the game.
     * @param acceleration How fast the player accelerates.
     */
    public Player(Vector2 spawnLoc, float acceleration, World world) {
        this.meatTexture = new Texture("meatball_texture.png");
        this.acceleration = acceleration;
        this.input = new Vector2();

        textureOffsetX = 0;
        textureOffsetY = 0;
        BodyDef playerDef = new BodyDef();
        playerDef.type = BodyDef.BodyType.DynamicBody;
        playerDef.position.set(4, 10);
        playerDef.fixedRotation = true;
        playerDef.linearDamping = 0.5f;
        body = world.createBody(playerDef);
        CircleShape circle = new CircleShape();
        circle.setRadius(16/50f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 0.75f;
        fixtureDef.friction = 0.5f;
        fixtureDef.density = 300f;
        body.createFixture(fixtureDef);
        circle.dispose();
    }


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
    }

    public void render(SpriteBatch batch)
    {
        int y = Math.round(2* TO_PIXELS * body.getPosition().y % 64f);
        int x = 64 - Math.round(2* TO_PIXELS * body.getPosition().x % 64f);
        Pixmap p = roundPixmap(new Pixmap(Gdx.files.internal("meatball_texture.png")), x+textureOffsetX, y+textureOffsetY, 16);
        batch.draw(new Texture(p), body.getPosition().x * TO_PIXELS - 16, body.getPosition().y * TO_PIXELS - 16);
    }

    public static Pixmap roundPixmap(Pixmap pixmap, int xOff, int yOff, int radius)
    {
        Gdx.app.log("xOff", "" + xOff);
        Gdx.app.log("yOff", "" + yOff);
        Pixmap round = new Pixmap(radius*2, radius*2, Pixmap.Format.RGBA8888);
        for(int y=yOff; y<radius*2+yOff; y++)
        {
            for(int x=xOff; x<radius*2+xOff; x++)
            {
                //check if pixel is outside circle. Set pixel to transparant;
                double dist_x = radius - x + xOff;
                double dist_y = radius - y + yOff;
                double dist = Math.sqrt((dist_x*dist_x) + (dist_y*dist_y));
                if(dist < radius)
                    round.drawPixel(x-xOff, y-yOff, pixmap.getPixel(x, y));
                if (dist < radius-3) {}
                else if (dist < radius-2)
                    round.drawPixel(x-xOff, y-yOff, Color.rgba8888(0,0,0,0.3f));
                else if (dist < radius-1)
                    round.drawPixel(x-xOff, y-yOff, Color.rgba8888(0,0,0,0.7f));
                else if (dist < radius)
                    round.drawPixel(x-xOff, y-yOff, Color.rgba8888(0,0,0,1));
                else
                    round.drawPixel(x-xOff, y-yOff, 0);
            }
        }
        return round;
    }

    public void dispose()
    {
        meatTexture.dispose();
    }
}
