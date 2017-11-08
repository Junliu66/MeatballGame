package Meat;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 * The player. Every level needs one.
 */
public class Player {
    private Vector2f location;
    private Vector2f velocity;
    private Vector2f force;
    private String img;
    private float acceleration;
    private float deAcceleration;
    private float maxSpeed;

    /**
     *
     * @param img A single character string representing the player. TODO: change to image
     * @param spawnLoc The location the player spawns in the game.
     * @param acceleration How fast the player accelerates.
     * @param deAcceleration How quickly the player slows down.
     */
    public Player(String img, Vector2f spawnLoc, float acceleration, float deAcceleration, float maxSpeed) {
        this.img = img;
        this.location = spawnLoc;
        this.acceleration = acceleration;
        this.velocity = new Vector2f();
        this.force = new Vector2f();
        this.deAcceleration = deAcceleration;
        this.maxSpeed = maxSpeed;
    }

    /**
     * Renders the player to the screen.
     * @param g The active Graphics context.
     */
    public void render(Graphics g) {
        g.drawString(img, location.x, location.y);
    }

    /**
     * Sets the force of the player.
     * @param force the new force
     */
    public void setForce(Vector2f force) {
        this.force = force;
    }

    /**
     * Updates the player's velocity vector and location.
     * @param i The delta-time, in milliseconds.
     */
    public void update(int i)
    {
        float dt = ((float)i) / 1000f;
        float vX = velocity.x;
        float vY = velocity.y;
        Vector2f velocityNormal = new Vector2f(velocity.x, velocity.y).normalise();


        if (Math.abs(force.x) > 0) {
            vX = vX + force.x*dt*acceleration;
        } else {
            vX = vX - velocityNormal.x*dt*deAcceleration;
        }
        if (Math.abs(force.y) > 0) {
            vY = vY + force.y*dt*acceleration;
        } else {
            vY = vY - velocityNormal.y*dt*deAcceleration;
        }

        velocity = new Vector2f(vX, vY);
        if (velocity.length() > maxSpeed)
            velocity.normalise().scale(maxSpeed);

        System.out.println("vel_len: " + velocity.length());
        location.set(location.getX() + velocity.getX(), location.getY() + velocity.getY());
    }
}
