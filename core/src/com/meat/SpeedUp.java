package com.meat;

public class SpeedUp extends PlayerModifier {

    private float multiplier;

    /**
     * Multiplies the player's acceleration for a duration
     * @param duration the time the increase should last
     * @param player the amount to multiply the player's acceleration by, should be greater than 1f
     */
    public SpeedUp(float duration, Player player, float multiplier) {
        super(duration, player);
        this.multiplier = multiplier;

    }

    @Override
    /**
     * Multiply's the player's acceleration by the multiplier
     */
    protected Status firstRun() {
        player.setAcceleration(player.getAcceleration() * multiplier);
        return Status.RUNNING;
    }

    @Override
    protected Status effect(float dt) {
        return Status.RUNNING;
    }

    /**
     * Reset the player's acceleration to default
     */
    @Override
    protected void finished() {
        player.setAcceleration(player.getAcceleration() / multiplier);
    }
}
