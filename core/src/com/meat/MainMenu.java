package com.meat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenu implements Screen {
    MainGame game;
    OrthographicCamera camera;

    SpriteBatch batch;
    Stage stage;
    Button btnPlay;
    Button btnHelp;
    Button btnSettings;
    Button btnExit;
    Texture texture;
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;

    int buttonXPlay = 400;
    int buttonXHelp = 250;
    int buttonXSettings = 400;
    int buttonXExit = 550;
    int buttonYHelp = 30;
    int buttonYSettings = 30;
    int buttonYExit = 30;
    int buttonYPlay = 100;


    public MainMenu(MainGame game){
        this.game = game;
    }

    public void show() {
        stage = new Stage(new ScreenViewport());
        texture = new Texture("mainMenu.png");
        Image image= new Image(texture);
        image.setSize(800,600);

        myTexture = new Texture(Gdx.files.internal("btnPlay.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnPlay = new ImageButton(myTexRegionDrawable);

        myTexture = new Texture(Gdx.files.internal("btnHelp.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnHelp = new ImageButton(myTexRegionDrawable);


        //Skin buttonSkin = new Skin();
        //btnSettings = new TextButton("Controls", buttonSkin);
        myTexture = new Texture(Gdx.files.internal("btnSettings.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnSettings = new ImageButton(myTexRegionDrawable);

        myTexture = new Texture(Gdx.files.internal("btnExit.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnExit = new ImageButton(myTexRegionDrawable);

        btnPlay.addListener(getPlayListener());
        btnHelp.addListener(getHelpListener());
        btnSettings.addListener(getSettingsListener());
        btnExit.addListener(getExitListener());

        btnPlay.setPosition(buttonXPlay, buttonYPlay, 0);
        btnHelp.setPosition(buttonXHelp, buttonYHelp, 0);
        btnSettings.setPosition(buttonXSettings, buttonYSettings, 0);
        btnExit.setPosition(buttonXExit, buttonYExit, 0);

        stage.addActor(image);
        stage.addActor(btnPlay);
        stage.addActor(btnHelp);
        stage.addActor(btnSettings);
        stage.addActor(btnExit);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        //batch.begin();
        //batch.draw(texture, 0, 0);
        //stage.act();
        stage.draw();
        //batch.end();
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
        batch.dispose();
    }

    private ClickListener getPlayListener(){
        return new ClickListener(){
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                btnPlay.setHeight(120);
                btnPlay.setWidth(120);
                btnPlay.setPosition(buttonXPlay,buttonYPlay,0);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                btnPlay.setHeight(140);
                btnPlay.setWidth(140);
                btnPlay.setPosition(buttonXPlay,buttonYPlay,0);

                stage.draw();
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {

                stage.draw();
                game.setScreen(new MeatGame(game));

            }
        };
    }

    private ClickListener getHelpListener(){
        return new ClickListener(){
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                btnHelp.setHeight(70);
                btnHelp.setWidth(70);
                btnHelp.setPosition(buttonXHelp,buttonYHelp,0);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                btnHelp.setHeight(100);
                btnHelp.setWidth(100);
                btnHelp.setPosition(buttonXHelp,buttonYHelp,0);

                stage.draw();
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {

                stage.draw();
                game.setScreen(new HelpScreen(game));
            }
        };
    }

    private ClickListener getSettingsListener(){
        return new ClickListener(){
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                btnSettings.setHeight(120);
                btnSettings.setWidth(120);
                btnSettings.setPosition(buttonXSettings,buttonYSettings,0);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                btnSettings.setHeight(150);
                btnSettings.setWidth(150);
                btnSettings.setPosition(buttonXSettings,buttonYSettings,0);

                stage.draw();
            }
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new ControlMenu(game));
            }
        };
    }

    private ClickListener getExitListener(){
        return new ClickListener(){
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                btnExit.setHeight(70);
                btnExit.setWidth(70);
                btnExit.setPosition(buttonXExit,buttonYExit,0);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                btnExit.setHeight(100);
                btnExit.setWidth(100);
                btnExit.setPosition(buttonXExit,buttonYExit,0);

                stage.draw();
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {

                stage.draw();
                Gdx.app.exit();
            }
        };
    }


}