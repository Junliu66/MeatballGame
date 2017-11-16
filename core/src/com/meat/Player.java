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
    private boolean isPlayerOne;
    private static Pixmap bloodTrail = new Pixmap(800, 600, Pixmap.Format.RGBA8888);;
    private Pixmap blood;
    private Vector2 lastPos;

    /**
     *
     * @param spawnLoc The location the player spawns in the game.
     * @param acceleration How fast the player accelerates.
     */
    public Player(Vector2 spawnLoc, float acceleration, World world, boolean isPlayerOne) {
        this.meatTexture = new Texture("meatball_texture.png");
        this.acceleration = acceleration;
        this.input = new Vector2();
        this.isPlayerOne = isPlayerOne;
        blood = new Pixmap(Gdx.files.internal("meat_splatter.png"));

        textureOffsetX = 0;
        textureOffsetY = 0;
        BodyDef playerDef = new BodyDef();
        playerDef.type = BodyDef.BodyType.DynamicBody;
        playerDef.position.set(spawnLoc);
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
        lastPos = new Vector2(body.getPosition().x, body.getPosition().y);
        circle.dispose();
    }

    public void update() {
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
    }

    public void render(SpriteBatch batch)
    {
        int y = Math.round(2* TO_PIXELS * body.getPosition().y % 64f);
        int x = -Math.round(2* TO_PIXELS * body.getPosition().x % 64f);
        if (x < 0) {
            x += 64;
        }
        if (y < 0) {
            y += 64;
        }
        Pixmap p = roundPixmap(new Pixmap(Gdx.files.internal("meatball_texture.png")), x+textureOffsetX, y+textureOffsetY, 16);
        meatTexture = new Texture(p);

        if (!isPlayerOne)
            batch.setColor(Color.GRAY);
        if (lastPos.dst(body.getPosition()) > 0.2) {
            Pixmap bloodRotated = rotatePixmap(blood, (float) ( (Math.atan2(body.getLinearVelocity().x, body.getLinearVelocity().y) + 0) / (2*Math.PI) ) * 360f + 90f);
            bloodTrail.drawPixmap(bloodRotated, (int) (body.getPosition().x * TO_PIXELS - 8), (int) (600 - body.getPosition().y * TO_PIXELS - 8));
             bloodRotated.dispose();
            lastPos = new Vector2(body.getPosition().x, body.getPosition().y);
        }

        Texture bT = new Texture(bloodTrail);
        if (isPlayerOne)
            batch.draw(bT, 0, 0);
        batch.draw(meatTexture, body.getPosition().x * TO_PIXELS - 16, body.getPosition().y * TO_PIXELS - 16);
        if (!isPlayerOne)
            batch.setColor(Color.WHITE);
        bT.dispose();
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

    public Pixmap rotatePixmap (Pixmap src, float angle){
        Gdx.app.log("angle", ""+angle);
        final int width = src.getWidth();
        final int height = src.getHeight();
        Pixmap rotated = new Pixmap(width, height, src.getFormat());

        final double radians = Math.toRadians(angle), cos = Math.cos(radians), sin = Math.sin(radians);


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final int
                        centerx = width/2, centery = height / 2,
                        m = x - centerx,
                        n = y - centery,
                        j = ((int) (m * cos + n * sin)) + centerx,
                        k = ((int) (n * cos - m * sin)) + centery;
                if (j >= 0 && j < width && k >= 0 && k < height){
                    rotated.drawPixel(x, y, src.getPixel(k, j));
                }
            }
        }
        return rotated;

    }

    public void dispose()
    {
        meatTexture.dispose();
        blood.dispose();
        bloodTrail.dispose();
    }
}
