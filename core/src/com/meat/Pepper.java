package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Pepper extends Pickup {

    private static float SPEED_BOOST = 30;
    private static float BOOST_TIME = 5;

    public Pepper(Texture texture, float x, float y) {
        super(texture, x, y);
    }

    @Override
    public void effect() {
        Gdx.app.log("got", "Pepper");
    }
}
