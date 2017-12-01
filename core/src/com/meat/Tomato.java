package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Tomato extends Pickup {

    public Tomato(float x, float y, Player player) {
        super(new Texture("tomato.png"), x, y, player);
    }

    public Tomato(Vector2 position, Player player) {
        super(new Texture("tomato.png"), position.x, position.y, player);
    }

    @Override
    public void effect() {
        Gdx.app.log("got", "Tomato");
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("tomato.mp3"));
        sound.play(1F);
    }
}