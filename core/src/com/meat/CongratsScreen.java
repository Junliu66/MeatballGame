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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.Color;

public class CongratsScreen implements Screen {
    private MainGame game;
    private Button nextLevel;
    private Button levelSelection;
    private Button mainMenu;
    private Label congrats;
    private final Stage stage;
    private Texture texture;
    private String lvlString;


    public  CongratsScreen(MainGame game, String lvlName) {
        this.game = game;
        this.lvlString = lvlName;
        stage = new Stage(new ScreenViewport(), game.batch);
    }

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
        stage.addActor(congrats);
        stage.addActor(nextLevel);
        stage.addActor(mainMenu);
        stage.addActor(levelSelection);
        Gdx.input.setInputProcessor(stage);
    }

    //TODO: fix it to connect next level
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
                        game.setScreen(new MeatGame(game, "LevelOne.tmx"));
                    default:
                        game.setScreen(new LevelSelectScreen(game));
                        break;

                }
            }
        };
    }


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



