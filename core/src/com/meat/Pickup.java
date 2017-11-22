package com.meat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Pickup extends Sprite {

    private float hoverTimer = 0f;
    private float origY;

    public Pickup(Texture texture, float x, float y)
    {
        super(texture);
        this.origY = y;
        this.setPosition(x, y);
//        this.setOrigin(x,y);
//        setTexture(texture);
    }

    public int render(SpriteBatch batch, float dt, Vector2 playerLoc)
    {
        hoverTimer += 4*dt;
        if (hoverTimer >= 2*Math.PI)
            hoverTimer = 0;
        setY(origY + getHeight()/4 * (float) Math.sin(hoverTimer) - getHeight()/8);
        this.draw(batch);
        if (this.getBoundingRectangle().overlaps(new Rectangle(playerLoc.x*MeatGame.TO_PIXELS+15f, playerLoc.y*MeatGame.TO_PIXELS+15f, 30f, 30f))) {
            effect();
            return -1;
        }
        return 1;
    }

    public abstract void effect();
}
