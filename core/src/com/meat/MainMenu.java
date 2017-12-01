package com.meat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
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
    Button btnSave;
    Button btnLoad;
    Image imgPlay;
    Image imgHelp;
    Image imgSettings;
    Image imgExit;
    Image imgSave;
    Image imgLoad;

    Texture texture;
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;

    int buttonXPlay = 400;
    int buttonXHelp = 260;
    int buttonXSettings = 400;
    int buttonXExit = 540;
    int buttonYHelp = 30;
    int buttonYSettings = 30;
    int buttonYExit = 30;
    int buttonYPlay = 85;
    int buttonXSave = 260;
    int buttonXLoad = 545;
    int buttonYSave = 100;
    int buttonYLoad = 100;


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
        btnPlay.setColor(0, 0, 0, 0);
        imgPlay = new Image(myTexture);

        myTexture = new Texture(Gdx.files.internal("btnHelp.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnHelp = new ImageButton(myTexRegionDrawable);
        btnHelp.setColor(0, 0, 0, 0);
        imgHelp = new Image(myTexture);

        myTexture = new Texture(Gdx.files.internal("btnSettings.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnSettings = new ImageButton(myTexRegionDrawable);
        btnSettings.setColor(0, 0, 0, 0);
        imgSettings = new Image(myTexture);
        //imgSettings.setSize(70,40);

        myTexture = new Texture(Gdx.files.internal("btnExit.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnExit = new ImageButton(myTexRegionDrawable);
        btnExit.setColor(0, 0, 0, 0);
        imgExit = new Image(myTexture);

        myTexture = new Texture(Gdx.files.internal("btnSave.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnSave = new ImageButton(myTexRegionDrawable);
        btnSave.setColor(0, 0, 0, 0);
        imgSave = new Image(myTexture);

        myTexture = new Texture(Gdx.files.internal("btnLoad.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnLoad = new ImageButton(myTexRegionDrawable);
        btnLoad.setColor(0, 0, 0, 0);
        imgLoad = new Image(myTexture);



        btnPlay.addListener(getPlayListener());
        btnHelp.addListener(getHelpListener());
        btnSettings.addListener(getSettingsListener());
        btnExit.addListener(getExitListener());
        btnSave.addListener(getSaveListener());
        btnLoad.addListener(getLoadListener());


        imgPlay.setPosition(buttonXPlay, buttonYPlay, 0);
        imgHelp.setPosition(buttonXHelp, buttonYHelp, 0);
        imgSettings.setPosition(buttonXSettings, buttonYSettings, 0);
        imgExit.setPosition(buttonXExit, buttonYExit, 0);
        imgSave.setPosition(buttonXSave, buttonYSave, 0);
        imgLoad.setPosition(buttonXLoad, buttonYLoad, 0);

        btnPlay.setPosition(buttonXPlay, buttonYPlay, 0);
        btnHelp.setPosition(buttonXHelp, buttonYHelp, 0);
        btnSettings.setPosition(buttonXSettings, buttonYSettings, 0);
        btnExit.setPosition(buttonXExit, buttonYExit, 0);
        btnSave.setPosition(buttonXSave, buttonYSave, 0);
        btnLoad.setPosition(buttonXLoad, buttonYLoad, 0);

        stage.addActor(image);
        stage.addActor(imgPlay);
        stage.addActor(imgHelp);
        stage.addActor(imgSettings);
        stage.addActor(imgExit);
        stage.addActor(imgSave);
        stage.addActor(imgLoad);

        stage.addActor(btnPlay);
        stage.addActor(btnHelp);
        stage.addActor(btnSettings);
        stage.addActor(btnExit);
        stage.addActor(btnSave);
        stage.addActor(btnLoad);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
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
        batch.dispose();
    }

    private ClickListener getPlayListener(){
        return new ClickListener(){
            boolean playing = false;

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                imgPlay.setHeight(62);
                imgPlay.setWidth(124);
                imgPlay.setPosition(buttonXPlay,buttonYPlay,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
                    sound.play(1F);
                    playing = true;
                }

                stage.draw();

            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                imgPlay.setHeight(72);
                imgPlay.setWidth(143);
                imgPlay.setPosition(buttonXPlay,buttonYPlay,0);
                playing = false;

                stage.draw();
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnClick.mp3"));
                sound.play(1F);
                stage.draw();
                stage.clear();
                game.setScreen(new LevelSelectScreen(game));

            }
        };
    }

    private ClickListener getHelpListener(){
        return new ClickListener(){
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                imgHelp.setHeight(32);
                imgHelp.setWidth(81);
                imgHelp.setPosition(buttonXHelp,buttonYHelp,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                imgHelp.setHeight(36);
                imgHelp.setWidth(90);
                imgHelp.setPosition(buttonXHelp,buttonYHelp,0);

                playing = false;
                stage.draw();
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnClick.mp3"));
                sound.play(1F);

                stage.draw();
                stage.clear();
                game.setScreen(new HelpScreen(game));
            }
        };
    }

    private ClickListener getSettingsListener(){
        return new ClickListener(){
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                imgSettings.setHeight(28);
                imgSettings.setWidth(120);
                imgSettings.setPosition(buttonXSettings,buttonYSettings,0);
                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
                    sound.play(1F);
                    playing = true;
                }

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                imgSettings.setHeight(32);
                imgSettings.setWidth(128);
                imgSettings.setPosition(buttonXSettings,buttonYSettings,0);

                playing = false;
                stage.draw();
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnClick.mp3"));
                sound.play(1F);
                stage.clear();
                game.setScreen(new ControlMenu(game));
            }
        };
    }

    private ClickListener getExitListener(){
        return new ClickListener(){
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                imgExit.setHeight(25);
                imgExit.setWidth(72);
                imgExit.setPosition(buttonXExit,buttonYExit,0);
                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
                    sound.play(1F);
                    playing = true;
                }

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                imgExit.setHeight(28);
                imgExit.setWidth(82);
                imgExit.setPosition(buttonXExit,buttonYExit,0);

                playing = false;
                stage.draw();
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnClick.mp3"));
                sound.play(1F);
                stage.draw();
                Gdx.app.exit();
            }
        };
    }

    private ClickListener getSaveListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                imgSave.setHeight(27);
                imgSave.setWidth(71);
                imgSave.setPosition(buttonXSave, buttonYSave,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                imgSave.setHeight(32);
                imgSave.setWidth(76);
                imgSave.setPosition(buttonXSave, buttonYSave,0);

                playing = false;
                stage.draw();
            }
            public void clicked(InputEvent event, float x, float y) {

                Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnClick.mp3"));
                sound.play(1F);
            }

        };
    }

    private ClickListener getLoadListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                imgLoad.setHeight(26);
                imgLoad.setWidth(70);
                imgLoad.setPosition(buttonXLoad, buttonYLoad,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                imgLoad.setHeight(32);
                imgLoad.setWidth(74);
                imgLoad.setPosition(buttonXLoad, buttonYLoad,0);

                playing = false;
                stage.draw();
            }
            public void clicked(InputEvent event, float x, float y) {

                Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnClick.mp3"));
                sound.play(1F);
            }

        };
    }


}