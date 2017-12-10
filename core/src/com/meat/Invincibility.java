package com.meat;

import com.meat.Player;
import com.meat.PlayerModifier;

/**
 * Makes the player temporally invincible
 */
public class Invincibility extends PlayerModifier {

    public Invincibility(float duration, Player player) {
        super(duration, player);
    }

    @Override
    protected Status firstRun() {
        player.setInvincible(true);
        return Status.RUNNING;
    }

    @Override
    protected Status effect(float dt) {
        return Status.RUNNING;
    }

    @Override
    protected void finished() {
        player.setInvincible(false);
    }
}
