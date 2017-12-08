package com.meat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Pickup extends Sprite {

    private float hoverTimer = 0f;
    private float origY;
    private int numTomatoes = 0;
    public enum Status {RUNNING, FINISHED}
    protected Player player;

    public Pickup(Texture texture, float x, float y, Player player)
    {
        super(texture);
        this.player = player;
        this.origY = y;
        this.setPosition(x, y);
        this.setSize(30,30);
    }

    public Status update(float dt)
    {
        hoverTimer += 4*dt;
        if (hoverTimer >= 2*Math.PI)
            hoverTimer = 0;
        setY(origY + getHeight()/4 * (float) Math.sin(hoverTimer));
        float distToPlayer = getCenter().dst(player.getPixelPosition());
        if (distToPlayer <= (16+getWidth()/2)) {
            effect();
            return Status.FINISHED;
        }
        return Status.RUNNING;
    }

    public void draw(SpriteBatch batch)
    {
        super.draw(batch);
    }

    public abstract void effect();

    public void addTomato() { numTomatoes++; }

    public int getNumTomatoes() { return numTomatoes; }

    public Vector2 getCenter()
    {
        return new Vector2(getX()+getWidth()/2, getY()+getHeight()/2);
    }
}
