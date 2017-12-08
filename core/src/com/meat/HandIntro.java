package com.meat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.meat.Player;

public abstract class HandIntro extends Sprite {

    private float hoverTimer = 0f;
    private float origX;
    public enum Status {RUNNING, FINISHED}
    protected Player player;

    public HandIntro(Texture texture, float x, float y, Player player)
    {
        super(texture);
        this.player = player;
        this.origX = x;
        this.setPosition(x, y);
    }

    public void update(float dt)
    {
        hoverTimer += 4*dt;
        if (hoverTimer >= 2*Math.PI)
            hoverTimer = 0;
        setX(origX + getHeight()/4 * (float) Math.sin(hoverTimer) - getHeight()/8);
    }

    public void draw(SpriteBatch batch)
    {
        super.draw(batch);
    }

    public abstract void effect();

    public Vector2 getCenter()
    {
        return new Vector2(getX()+getWidth()/2, getY()+getHeight()/2);
    }
}