package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Pepper speeds the player up
 */
public class Pepper extends Pickup {

    public Pepper(float x, float y, Player player) {
        super(new Texture("pepperbomb.png"), x, y, player);
    }

    public Pepper(Vector2 position, Player player) {
        super(new Texture("pepperbomb.png"), position.x, position.y, player);
    }

    /**
     * Adds a SpeedUp modifier with a 2.5 x multiplier
     */
    @Override
    public void effect() {
        Gdx.app.log("got", "Pepper");
        player.addModifier(new SpeedUp(5, player, 2.5f));
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("pepper.mp3"));
        sound.play(1F);
    }
}
