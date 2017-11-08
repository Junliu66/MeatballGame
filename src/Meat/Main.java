package Meat;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

public class Main extends BasicGame
{
    private Player player;

    public Main(String gamename)
    {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        player = new Player("&", new Vector2f(100, 100), 1, 0.5f, 1f);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        // input
        boolean up, down, left, right;
        up = gc.getInput().isKeyDown(Input.KEY_UP);
        down = gc.getInput().isKeyDown(Input.KEY_DOWN);
        left = gc.getInput().isKeyDown(Input.KEY_LEFT);
        right = gc.getInput().isKeyDown(Input.KEY_RIGHT);
        Vector2f force = new Vector2f();
        if (up && down)
            force.y = 0;
        else if (up)
            force.y = -1;
        else if (down)
            force.y = 1;
        if (left && right)
            force.x = 0;
        else if (left)
            force.x = -1;
        else if (right)
            force.x = 1;
        force.normalise();
        player.setForce(force);

        // update player velocity and location
        player.update(i);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        player.render(g);
    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Simple Slick Game"));
            appgc.setShowFPS(false);
            appgc.setDisplayMode(1280, 720, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}