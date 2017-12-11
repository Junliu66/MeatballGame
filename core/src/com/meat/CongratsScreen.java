package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * This class shows the victory screen when you complete a level, it shows
 * how many of the twenty tomatoes that the player earned in the level.
 */
public class CongratsScreen implements Screen {
    private MainGame game;
    private Button nextLevel;
    private Button levelSelection;
    private Button mainMenu;
    private Label congrats;
    private final Stage stage;
    private Texture texture;
    private String lvlString;
    private int numTomatoes;
    int tomatoXPos[] = { 300, 300, 320, 320, 340, 340, 360, 360, 380, 380, 400, 400, 420, 420, 440, 440, 460, 460, 480, 480 };
    int tomatoYPos[] = { 310, 290, 310, 290, 310, 290, 310, 290, 310, 290, 310, 290, 310, 290, 310, 290, 310, 290, 310, 290 };
    Image tomatoes[] = new Image[20];
    Image emptyTomatoes[] = new Image[20];

    /**
     * Constructor for the victory screen.
     * @param game Main game class, used for passing values between screens
     * @param lvlName name of the level that called this screen
     * @param numTomatoes number of tomatoes earned in the last level
     */
    public  CongratsScreen(MainGame game, String lvlName, int numTomatoes) {
        this.game = game;
        this.lvlString = lvlName;
        this.numTomatoes = numTomatoes;
        stage = new Stage(new ScreenViewport(), game.batch);
    }

    /**
     * sets up the scene for the victory screen
     */
    @Override
    public void show() {
        texture = new Texture("clouds_bg.png");
        Image image= new Image(texture);
        image.setSize(1000,600);

        final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        congrats = new Label("LEVEL COMPLETE !",skin);
        congrats.setFontScale(3);
        congrats.setColor(1, 1, 0, 1);
        congrats.setPosition(280, 550, 0);

        nextLevel = new TextButton("NEXT LEVEL", skin);
        nextLevel.addListener(getNextLevel());
        nextLevel.setPosition(400, 150, 0);

        levelSelection = new TextButton("LEVEL SELECT", skin);
        levelSelection.addListener(getLevelSelectionListener());
        levelSelection.setPosition(400, 100, 0);

        mainMenu = new TextButton("MAIN MENU", skin);
        mainMenu.addListener(getMenuListener());
        mainMenu.setPosition(400, 50, 0);


        stage.addActor(image);
        showTomatoes();
        stage.addActor(congrats);
        stage.addActor(nextLevel);
        stage.addActor(mainMenu);
        stage.addActor(levelSelection);
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * This function shows the amount of tomatoes that were earned in the last
     * round and how many were still missing.
     */
    private void showTomatoes() {
        for (int i = 0; i < 20; i++) {
            if (numTomatoes > 0)
            {
                texture = new Texture ("tomato.png");
                tomatoes[i] = new Image (texture);
                tomatoes[i].setPosition(tomatoXPos[i], tomatoYPos[i], 0);
                stage.addActor(tomatoes[i]);
                numTomatoes--;
            }
            else
            {
                texture = new Texture ("emptyTomato.png");
                emptyTomatoes[i] = new Image (texture);
                emptyTomatoes[i].setPosition(tomatoXPos[i], tomatoYPos[i], 0);
                stage.addActor(emptyTomatoes[i]);
            }
        }
    }

    /**
     * Fucntion that attaches to the next level button, checks the level completed and calls
     * a new meatgame object with the next level file.
     * @return
     */
    private ClickListener getNextLevel() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnClick.mp3"));
                sound.play(1F);
                switch (lvlString) {
                    case "LevelOne.tmx":
                        game.setScreen(new MeatGame(game, "LevelTwo.tmx"));
                        break;
                    case "LevelTwo.tmx":
                        game.setScreen(new MeatGame(game, "LevelThree.tmx"));
                        break;
                    case "LevelThree.tmx":
                        game.setScreen(new MeatGame(game, "LevelFour.tmx"));
                        break;
                    case "LevelFour.tmx":
                        game.setScreen(new MeatGame(game, "LevelFive.tmx"));
                        break;
                    case "LevelFive.tmx":
                        game.setScreen(new MeatGame(game, "LevelSix.tmx"));
                        break;
                    case "LevelSix.tmx":
                        game.setScreen(new LevelSelectScreen(game));
                        break;
                    default:
                        game.setScreen(new LevelSelectScreen(game));
                        break;

                }
            }
        };
    }


    /**
     * Function for the level select button, sets up the level select screen when clicked.
     * @return
     */
    private EventListener getLevelSelectionListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnClick.mp3"));
                sound.play(1F);
                game.setScreen(new LevelSelectScreen(game));

            }
        };
    }

    /**
     * Function for the menu button, sets up the menu screen when clicked.
     * @return
     */
    private EventListener getMenuListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnClick.mp3"));
                sound.play(1F);
                game.setScreen(new MainMenu(game));
            }
        };
    }

    /**
     * function called every frame to update the scene.
     * @param delta
     */
    @Override
    public void render(float delta) {
        stage.act(Gdx.graphics.getDeltaTime());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}



