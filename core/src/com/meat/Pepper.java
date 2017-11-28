package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Pepper extends Pickup {

    private static float SPEED_BOOST = 30;
    private static float BOOST_TIME = 5;

    public Pepper(float x, float y, Player player) {
        super(new Texture("pepperbomb.png"), x, y, player);
    }

    public Pepper(Vector2 position, Player player) {
        super(new Texture("pepperbomb.png"), position.x, position.y, player);
    }

    @Override
    public void effect() {
        Gdx.app.log("got", "Pepper");
        player.addModifier(new SpeedUp(5, player, 2.5f));
    }
}
