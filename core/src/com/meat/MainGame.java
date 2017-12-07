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

public class MainGame extends Game
{
    public SpriteBatch batch;
    public BitmapFont font;
    int totalScore, numTrophies;
    int visitHelp = 0;
    //TODO: change it to 1 so the save function works.
    int lvlTrophies[] = {2, 1, 3, 2, 0, 0};

    public MeatGame meatGame = null;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new SplashScreen(this));
    }

    public void render() {
        super.render();
        batch.begin();
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public int getTrophies(){
        return numTrophies;
    }

    public int[] getLvlTrophies() {
        return lvlTrophies;
    }

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

    public void setScore(int numTomatoes, String lvlString)
    {
        int earnedTrophies = numTomatoes / 10;

        switch (lvlString) {
            case "LevelOne.tmx":
                if (numTrophies > earnedTrophies)
                {
                    lvlTrophies[0] = earnedTrophies;
                }
                break;
            case "LevelTwo.tmx":
                if (numTrophies > earnedTrophies)
                {
                    lvlTrophies[1] = earnedTrophies;
                }
                break;
            case "LevelThree.tmx":
                if (numTrophies > earnedTrophies)
                {
                    lvlTrophies[2] = earnedTrophies;
                }
                break;
            case "LevelFour.tmx":
                if (numTrophies > earnedTrophies)
                {
                    lvlTrophies[3] = earnedTrophies;
                }
                break;
            case "LevelFive.tmx":
                if (numTrophies > earnedTrophies)
                {
                    lvlTrophies[4] = earnedTrophies;
                }
                break;
            case "LevelSix.tmx":
                if (numTrophies > earnedTrophies)
                {
                    lvlTrophies[5] = earnedTrophies;
                }
                break;
            default:
                break;
        }
    }

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