package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class HandPointer extends HandIntro {

    public HandPointer(float x, float y, Player player) {
        super(new Texture("handleft.png"), x, y, player);
    }

    public HandPointer(Vector2 position, Player player) {
        super(new Texture("handleft.png"), position.x, position.y, player);
    }

    @Override
    public void effect() {
        Gdx.app.log("got", "Hand");

    }
}


