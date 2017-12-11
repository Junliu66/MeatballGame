package com.meat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The main game class, used for passing values between levels and other screens, also has a simple save game feature.
 */
public class MainGame extends Game
{
    public SpriteBatch batch;
    public BitmapFont font;
    int totalScore, numTrophies;
    int visitHelp = 0;
    //TODO: change it to 1 so the save function works.
    int lvlTrophies[] = {2, 1, 3, 2, 0, 0};

    public MeatGame meatGame = null;

    /**
     * On creation of the main game class, sets the scene to the SplashScreen
     */
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new SplashScreen(this));
    }

    /**
     * function called every frame to update scene, not used for MainGame
     */
    public void render() {
        super.render();
        batch.begin();
        batch.end();
    }

    /**
     * garbage collecting function
     */
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    /**
     * Save game function, saves how many trophies that you've received in each level.
     * @throws IOException
     */
    public void saveGame()
            throws IOException {
        String str = totalScore + "\n" + numTrophies + "\n";
        for (int i = 0; i < 6; i++)
        {
            str += lvlTrophies[i] + "\n";
        }

        Path path = Paths.get("save.txt");
        byte[] strToBytes = str.getBytes();
        Files.deleteIfExists(path);
        Files.write(path, strToBytes);

        String read = Files.readAllLines(path).get(0);
    }

    /**
     * Retrieves the number of tomatoes received in the last completed level if the level of
     * trophy is higher than what was earned previously, adds it to the trophy array for that level.
     * @param numTomatoes Number of tomatoes earned in the last completed level.
     * @param lvlString Name of the level completed.
     */
    public void setScore(int numTomatoes, String lvlString)
    {
        int earnedTrophies = (numTomatoes / 10) + 1;
        System.out.println(earnedTrophies);

        switch (lvlString) {
            case "LevelOne.tmx":
                if (lvlTrophies[0] < earnedTrophies)
                {
                    lvlTrophies[0] = earnedTrophies;
                }
                break;
            case "LevelTwo.tmx":
                if (lvlTrophies[1] < earnedTrophies)
                {
                    lvlTrophies[1] = earnedTrophies;
                }
                break;
            case "LevelThree.tmx":
                if (lvlTrophies[2] < earnedTrophies)
                {
                    lvlTrophies[2] = earnedTrophies;
                }
                break;
            case "LevelFour.tmx":
                if (lvlTrophies[3] < earnedTrophies)
                {
                    lvlTrophies[3] = earnedTrophies;
                }
                break;
            case "LevelFive.tmx":
                if (lvlTrophies[4] < earnedTrophies)
                {
                    lvlTrophies[4] = earnedTrophies;
                }
                break;
            case "LevelSix.tmx":
                if (lvlTrophies[5] < earnedTrophies)
                {
                    lvlTrophies[5] = earnedTrophies;
                }
                break;
            default:
                break;
        }
    }

    /**
     * retrieves the save information from the save file. Updates the lvlTrophies array with
     * data from the text file.
     * @throws FileNotFoundException
     */
    public void loadGame ()
            throws FileNotFoundException {
        File file = new File("save.txt");
        Scanner sc = new Scanner(file);
        totalScore = Integer.parseInt(sc.nextLine());
        numTrophies = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < 6; i++)
        {
            lvlTrophies[i] = Integer.parseInt(sc.nextLine());
        }
    }
}