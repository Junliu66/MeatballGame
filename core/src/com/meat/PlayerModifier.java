package com.meat;

/**
 * Abstract class that defines a modification to the player
 */
public abstract class PlayerModifier {

    private float duration;
    private boolean useDuration;
    private boolean firstRun;
    protected Player player;

    public enum Status {RUNNING, FINISHED};

    /**
     * Creates a PlayerModifier with a duration
     * @param duration the length of time the effect should last
     * @param player The player
     */
    public PlayerModifier(float duration, Player player)
    {
        firstRun = true;
        useDuration = true;
        this.duration = duration;
        this.player = player;
    }

    /**
     * Creates a PlayerModifier without a duration
     * @param player the player
     */
    public PlayerModifier(Player player)
    {
        firstRun = true;
        useDuration = false;
        this.player = player;
    }

    /**
     * updates the effect, applying
     * @param dt the delta time
     * @return a Status determining whether the effect is finished
     */
    public Status update(float dt)
    {
        if (firstRun)
        {
            firstRun = false;
            if (firstRun() == Status.FINISHED) {
                finished();
                return Status.FINISHED;
            }
        }

        if (effect(dt) == Status.FINISHED) {
            finished();
            return Status.FINISHED;
        }

        if (useDuration) {
            duration -= dt;
            if (duration <= 0)
            {
                finished();
            return Status.FINISHED;
        }
        }
        return Status.RUNNING;
    }

    /**
     * Modifies the player in some way on the first run of the PlayerModifier
     * @return a Status determining whether the effect should terminate immediately after first run
     */
    protected abstract Status firstRun();

    /**
     * Modifies the player in some way along the duration of the effect
     * This method is called every update()
     * @param dt the delta-time
     * @return a Status determining whether the effect should finish early
     */
    protected abstract Status effect(float dt);

    /**
     * Called when the effect is finished. Should undo any temporary effects of the PlayerModifier.
     */
    protected abstract void finished();
}
