package com.meat;

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

public class RestartScreen implements Screen {
    private MainGame game;
    private Button restart;
    private Button mainMenu;
    private Button levelSelection;
    private Label gm;
    private String lvlString;
    private Label score;//TODO
    private final Stage stage;
    private Texture texture;


    public  RestartScreen(MainGame game, String lvlName) {
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
        gm = new Label("GAME OVER !",skin);
        gm.setFontScale(3);
        gm.setColor(1, 0, 0, 1);
        gm.setPosition(300, 550, 0);


        restart = new TextButton("TRY AGAIN", skin);
        restart.addListener(getRestartListener());
        restart.setPosition(400, 150, 0);

        levelSelection = new TextButton("LEVEL SELECT", skin);
        levelSelection.addListener(getLevelSelectionListener());
        levelSelection.setPosition(400, 100, 0);

        mainMenu = new TextButton("MAIN MENU", skin);
        mainMenu.addListener(getMenuListener());
        mainMenu.setPosition(400, 50, 0);

        stage.addActor(image);
        stage.addActor(gm);
        stage.addActor(restart);
        stage.addActor(mainMenu);
        stage.addActor(levelSelection);
        Gdx.input.setInputProcessor(stage);
    }

    private EventListener getMenuListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MainMenu(game));
            }
        };
    }

    private EventListener getLevelSelectionListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LevelSelectScreen(game));

            }
        };
    }


    private ClickListener getRestartListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(game.meatGame);
                game.setScreen(new MeatGame(game, lvlString));
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
