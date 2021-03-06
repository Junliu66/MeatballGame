package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Garlic slows the player down
 */
public class Garlic extends Pickup {

    public Garlic(float x, float y, Player player) {
        super(new Texture("garlicSlow.png"), x, y, player);
    }

    public Garlic(Vector2 position, Player player) {
        super(new Texture("garlicSlow.png"), position.x, position.y, player);
    }

    /**
     * Adds a SpeedUp modifier set to 0.5x
     */
    @Override
    public void effect() {
        Gdx.app.log("got", "Garlic");
        player.addModifier(new SpeedUp(7, player, 0.5f));
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("garlic.mp3"));
        sound.play(1F);
    }
}
