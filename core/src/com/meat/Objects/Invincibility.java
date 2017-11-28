package com.meat.Objects;

import com.meat.Player;
import com.meat.PlayerModifier;

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
