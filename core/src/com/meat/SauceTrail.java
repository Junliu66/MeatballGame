package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import static com.meat.MeatGame.TO_PIXELS;

/**
 * Handles drawing of the sauce trail behind the player
 */
public class SauceTrail {

    private static int SECTION_WIDTH = 800;
    private static int SECTION_HEIGHT = 800;
    private static int MAX_NUM_SECTIONS = 9;

    private ArrayList<Texture> sections;
    private ArrayList<Rectangle> coords;
    private Player player;
    private Vector2 lastPos;
    private Rectangle lastInside;
    private Pixmap blood;

    /**
     * Sets up the SauceTrail
     * @param player the Player
     */
    public SauceTrail(Player player)
    {
        this.player = player;
        blood = new Pixmap(Gdx.files.internal("meat_splatter.png"));
        sections = new ArrayList<Texture>();
        sections.add(new Texture(SECTION_WIDTH, SECTION_HEIGHT, Pixmap.Format.RGBA8888));
        coords = new ArrayList<Rectangle>();
        lastInside = new Rectangle(player.getPixelPosition().x - (((float) SECTION_WIDTH) / 2f), player.getPixelPosition().y - (((float) SECTION_HEIGHT) / 2f), SECTION_WIDTH, SECTION_HEIGHT);
        lastPos = new Vector2();
        coords.add(lastInside);
    }

    /**
     * Draws new pieces of the sauce trail to the current Texture.
     * Checks if another Texture needs to be allocated.
     * @param dt the delta time
     */
    public void update(float dt)
    {
        Vector2 pos = player.getPixelPosition().add(-8, 8);
        Texture currentTexture = null;
        boolean inside = false;
        for (int i = 0; i < coords.size(); i++) {
            if (coords.get(i).contains(pos))
            {
                inside = true;
                lastInside = coords.get(i);
                currentTexture = sections.get(i);
                break;
            }
        }
        if (!inside)
        {
            float newX = lastInside.x;
            float newY = lastInside.y;
            if (pos.x > lastInside.x + lastInside.width)
                newX = lastInside.x + lastInside.width;
            else if (pos.x < lastInside.x)
                newX = lastInside.x - lastInside.width;
            if (pos.y > lastInside.y + lastInside.height)
                newY = lastInside.y + lastInside.height;
            else if (pos.y < lastInside.y)
                newY = lastInside.y - lastInside.height;
            int furthest = 0;
            if (sections.size() >= MAX_NUM_SECTIONS) {
                float furthestDist = 0;
                for (int i = 0; i < sections.size(); i++)
                    if (coords.get(i).getCenter(new Vector2()).dst(pos) > furthestDist) {
                        furthest = i;
                        furthestDist = coords.get(i).getCenter(new Vector2()).dst(pos);
                    }
                sections.get(furthest).dispose();
                sections.remove(furthest);
                coords.remove(furthest);
                furthest--;
            }
            currentTexture = sections.get(furthest);
            sections.add(furthest, new Texture(SECTION_WIDTH, SECTION_HEIGHT, Pixmap.Format.RGBA8888));
            coords.add(furthest, new Rectangle(newX, newY, SECTION_WIDTH, SECTION_HEIGHT));
        }

        if (lastPos.dst(pos) > 8 && currentTexture != null) {
            Pixmap bloodRotated = rotatePixmap(blood, (float) ( (Math.atan2(player.body.getLinearVelocity().x, player.body.getLinearVelocity().y) + 0) / (2*Math.PI) ) * 360f + 90f);
//            Gdx.app.log("Player pos", ""+pos);
//            Gdx.app.log("lastinside", ""+lastInside.toString());
//            Gdx.app.log("Draw pos", "x: " + (int) (pos.x - lastInside.x) + "\ty: " + (int) (SECTION_HEIGHT + lastInside.y - pos.y));
            currentTexture.draw(bloodRotated, (int) (pos.x - lastInside.x), (int) (SECTION_HEIGHT + lastInside.y - pos.y))   ;
            bloodRotated.dispose();
            lastPos = pos;
        }
    }

    /**
     * Draws the SauceTrail
     * @param batch the global SpriteBatch
     */
    public void draw(SpriteBatch batch)
    {
        for (int i = 0; i < sections.size(); i++)
        {
            batch.draw(sections.get(i), coords.get(i).x, coords.get(i).y);
        }
    }

    /**
     * Rotates a pixmap
     * @param src the Pixmap to rotate
     * @param angle the angle to rotate at
     * @return the rotated Pixmap
     */
    public Pixmap rotatePixmap (Pixmap src, float angle){
        final int width = src.getWidth();
        final int height = src.getHeight();
        Pixmap rotated = new Pixmap(width, height, src.getFormat());
        final double radians = Math.toRadians(angle), cos = Math.cos(radians), sin = Math.sin(radians);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final int
                        centerx = width/2, centery = height / 2,
                        m = x - centerx,
                        n = y - centery,
                        j = ((int) (m * cos + n * sin)) + centerx,
                        k = ((int) (n * cos - m * sin)) + centery;
                if (j >= 0 && j < width && k >= 0 && k < height){
                    rotated.drawPixel(x, y, src.getPixel(k, j));
                }
            }
        }
        return rotated;
    }

    public void dispose()
    {
        for (Texture t : sections)
            t.dispose();
        blood.dispose();
    }
}
