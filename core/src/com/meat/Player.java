package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.meat.Objects.Obstacle;

import java.util.ArrayList;

import static com.meat.MeatGame.TO_PIXELS;

/**
 * The player. Every level needs one.
 */
public class Player {
    private Vector2 input;
    private Texture meatTexture;
    private float acceleration;
    public Body body;
    private int textureOffsetX;
    private int textureOffsetY;
    private boolean isPlayerOne;
    private ArrayList<PlayerModifier> modifers;
    private boolean invincible = false;
    private float invincibleCounter;
    private Pixmap origMeatPixmap;

    public final int GOAL = 2;
    public final int HOLE = 1;
    public final int WALL = 3;

    /**
     *  @param spawnLoc The location the player spawns in the game.
     * @param collisionLayer
     * @param acceleration How fast the player accelerates.
     */
    public Player(Vector2 spawnLoc, TiledMapTileLayer collisionLayer, float acceleration, World world, boolean isPlayerOne) {
        this.meatTexture = new Texture(32, 32, Pixmap.Format.RGBA8888);
        this.acceleration = acceleration;
        this.input = new Vector2();
        this.isPlayerOne = isPlayerOne;
        invincibleCounter = 0f;
        modifers = new ArrayList<PlayerModifier>();

        origMeatPixmap = new Pixmap(Gdx.files.internal("meatball_texture.png"));
        textureOffsetX = 0;
        textureOffsetY = 0;
        BodyDef playerDef = new BodyDef();
        playerDef.type = BodyDef.BodyType.DynamicBody;
        playerDef.position.set(spawnLoc);
        playerDef.fixedRotation = true;
        playerDef.linearDamping = 1.0f;
        body = world.createBody(playerDef);
        CircleShape circle = new CircleShape();
        circle.setRadius(16/50f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.0f;
        fixtureDef.density = 15f;
        body.createFixture(fixtureDef);
        circle.dispose();
    }

    public void update(MeatGame game, float dt) {
        if (invincible)
            invincibleCounter += dt;

        checkCollisionMap(game);
        input = new Vector2();
        boolean up, down, left, right;
        if (isPlayerOne)
        {
            up = (Gdx.input.isKeyPressed(Config.player1Up));
            down = (Gdx.input.isKeyPressed(Config.player1Down));
            left = (Gdx.input.isKeyPressed(Config.player1Left));
            right = (Gdx.input.isKeyPressed(Config.player1Right));
        } else {
            up = (Gdx.input.isKeyPressed(Config.player2Up));
            down = (Gdx.input.isKeyPressed(Config.player2Down));
            left = (Gdx.input.isKeyPressed(Config.player2Left));
            right = (Gdx.input.isKeyPressed(Config.player1Right));
        }
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

        for (int i=0; i < modifers.size(); i++) {
            if (modifers.get(i).update(dt) == PlayerModifier.Status.FINISHED) {
                modifers.remove(i);
            }
        }
    }

    public void render(SpriteBatch batch)
    {
        if (invincible) {
            if (invincibleCounter < (1/8f)) {
                draw(batch);
            } else if (invincibleCounter < (1/4f)) {
            } else {
                invincibleCounter = 0f;
            }
        } else
            draw(batch);

    }

    public void draw(SpriteBatch batch)
    {
        int y = Math.round(1.25f * TO_PIXELS * body.getPosition().y % 64f);
        int x = -Math.round(1.25f * TO_PIXELS * body.getPosition().x % 64f);
        if (x < 0) {
            x += 64;
        }
        if (y < 0) {
            y += 64;
        }
        Pixmap p = roundPixmap(origMeatPixmap, x+textureOffsetX, y+textureOffsetY, 16);
        meatTexture.draw(p, 0, 0);
        batch.draw(meatTexture, body.getPosition().x * TO_PIXELS - 16, body.getPosition().y * TO_PIXELS - 16);
        p.dispose();
    }

    public Vector2 getPosition() {return body.getPosition();}

    public static Pixmap roundPixmap(Pixmap pixmap, int xOff, int yOff, int radius)
    {
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

    public void checkCollisionMap(MeatGame meatGame) {
        float x = body.getWorldCenter().x * TO_PIXELS;
        float y = body.getWorldCenter().y * TO_PIXELS;

        int collisionWithMap = 0;

        collisionWithMap = isCellBlocked(meatGame, x, y);

        switch (collisionWithMap) {
            case HOLE:
                System.out.println("YOU LOSE!");
                meatGame.lose();
                break;
            case GOAL:
                System.out.println("CONGRATULATIONS");
                meatGame.congrats();
                break;
        }

        checkObstacle(meatGame, x, y);
    }

    private void checkObstacle(MeatGame meatGame, float x, float y) {
        for (Obstacle ob : meatGame.getObstacles().values())
        {
            for (Shape2D s : ob.getObstacleArea()) {
                if (s.contains(x, y)) {
                    if (!invincible) {
                        body.setLinearVelocity(body.getLinearVelocity().scl(-1f));
                        meatGame.reduceBlood();
                        modifers.add(new Invincibility(3, this));
                    }
                }

            }
        }
    }

    public int isCellBlocked(MeatGame meatGame, float x, float y) {
        for (Shape2D s : meatGame.goals)
        {
            if (s.contains(x, y))
                return GOAL;
        }
        for (Shape2D s : meatGame.holes)
        {
            if (s.contains(x, y))
                return HOLE;
        }

        return 0;
    }

    public void dispose()
    {
        meatTexture.dispose();
    }

    public void setPosition(Vector2 position) {
        body.setTransform(position, 0f);
    }

    public void setVelocity(Vector2 velocity) {
        this.body.setLinearVelocity(velocity);
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public void addModifier(PlayerModifier modifier) {
        modifers.add(modifier);
    }

    public void clearModifiers() {
        for (PlayerModifier m : modifers)
            m.finished();
        modifers.clear();
    }

    public Vector2 getPixelPosition() {
        return new Vector2(body.getPosition().x * TO_PIXELS, body.getPosition().y * TO_PIXELS);
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
        invincibleCounter = 0f;
    }
}
