package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Display tomatoes to the game map，tomato is the reward of meatball to unlock the next level.
 */
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
        player.addTomato();
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("tomato.mp3"));//add sound effection of tomato
        sound.play(1F);
    }
}