package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Garlic extends Pickup {

    private static float SPEED_BOOST = 0;
    private static float BOOST_TIME = 5;

    public Garlic(float x, float y, Player player) {
        super(new Texture("garlicSlow.png"), x, y, player);
    }

    public Garlic(Vector2 position, Player player) {
        super(new Texture("garlicSlow.png"), position.x, position.y, player);
    }

    @Override
    public void effect() {
        Gdx.app.log("got", "Garlic");
        player.addModifier(new SpeedUp(5, player, 2.5f));
    }
}
