package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Pickup extends Sprite {

    private float hoverTimer = 0f;
    private float origY;
    public enum Status {RUNNING, FINISHED}
    protected Player player;

    public Pickup(Texture texture, float x, float y, Player player)
    {
        super(texture);
        this.player = player;
        this.origY = y;
        this.setPosition(x, y);
    }

    public Status render(SpriteBatch batch, float dt)
    {
        hoverTimer += 4*dt;
        if (hoverTimer >= 2*Math.PI)
            hoverTimer = 0;
        setY(origY + getHeight()/4 * (float) Math.sin(hoverTimer) - getHeight()/8);
        this.draw(batch);
        float distToPlayer = getCenter().dst(player.getPixelPosition());
        if (distToPlayer <= (16+getWidth()/2)) {
            effect();
            return Status.FINISHED;
        }
        return Status.RUNNING;
    }

    public abstract void effect();

    public Vector2 getCenter()
    {
        return new Vector2(getX()+getWidth()/2, getY()+getHeight()/2);
    }
}
