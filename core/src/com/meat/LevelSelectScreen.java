package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelSelectScreen implements Screen {
    MainGame game;
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    Skin labelSkin = new Skin(Gdx.files.internal("uiskin.json"));


    SpriteBatch batch;
    Stage stage;

    Button btnLvlOne;
    Button btnLvlTwo;
    Button btnLvlThree;
    Button btnLvlFour;
    Button btnLvlFive;
    Button btnLvlSix;
    Button btnBack;
    Texture texture;
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;

    int buttonXLvlOne = 200;
    int buttonYLvlOne = 425;
    int buttonXLvlTwo = 400;
    int buttonYLvlTwo = 425;
    int buttonXLvlThree = 600;
    int buttonYLvlThree = 425;
    int buttonXLvlFour = 200;
    int buttonYLvlFour = 215;
    int buttonXLvlFive = 400;
    int buttonYLvlFive = 215;
    int buttonXLvlSix = 600;
    int buttonYLvlSix = 215;
    int buttonXBack = 400;
    int buttonYBack = 30;

    boolean lvlSelected = false;
    int iconSelectedX = buttonXLvlOne;
    int iconSelectedY = buttonYLvlOne;

    Button btnPause;


    public LevelSelectScreen(MainGame game) { this.game = game; }

    public void show() {
        stage = new Stage(new ScreenViewport());
        texture = new Texture("LevelSelectBg.png");
        Image image = new Image(texture);
        image.setSize(800, 600);

        myTexture = new Texture(Gdx.files.internal("LevelOneSelect.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnLvlOne = new ImageButton(myTexRegionDrawable);
        Label lblLvlOne = new Label("LEVEL ONE", labelSkin);

        myTexture = new Texture(Gdx.files.internal("testLevelSelect.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnLvlTwo = new ImageButton(myTexRegionDrawable);
        Label lblLvlTwo = new Label("LEVEL TWO", labelSkin);

        myTexture = new Texture(Gdx.files.internal("LevelOneSelect.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnLvlThree = new ImageButton(myTexRegionDrawable);
        Label lblLvlThree = new Label("LEVEL THREE", labelSkin);

        myTexture = new Texture(Gdx.files.internal("testLevelSelect.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnLvlFour = new ImageButton(myTexRegionDrawable);
        Label lblLvlFour = new Label("LEVEL FOUR", labelSkin);

        myTexture = new Texture(Gdx.files.internal("LevelOneSelect.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnLvlFive = new ImageButton(myTexRegionDrawable);
        Label lblLvlFive = new Label("LEVEL FIVE", labelSkin);

        myTexture = new Texture(Gdx.files.internal("testLevelSelect.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnLvlSix = new ImageButton(myTexRegionDrawable);
        Label lblLvlSix = new Label("LEVEL SIX", labelSkin);

        myTexture = new Texture(Gdx.files.internal("btnBack2.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnBack = new ImageButton(myTexRegionDrawable);

        myTexture = new Texture(Gdx.files.internal("btnPause0.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(80);
        myTexRegionDrawable.setMinWidth(80);
        btnPause = new ImageButton(myTexRegionDrawable);
        btnPause.setColor(0, 0, 0, 0);
        btnPause.setPosition(680,510);


        btnLvlOne.addListener(getLvlOneListener());
        btnLvlTwo.addListener(getLvlTwoListener());
        btnLvlThree.addListener(getLvlThreeListener());
        btnLvlFour.addListener(getLvlFourListener());
        btnLvlFive.addListener(getLvlFiveListener());
        btnLvlSix.addListener(getLvlSixListener());
        btnBack.addListener(getBackListener());
        //btnPause.addListener(getPauseListener());

        btnLvlOne.setPosition(buttonXLvlOne, buttonYLvlOne, 0);
        btnLvlTwo.setPosition(buttonXLvlTwo, buttonYLvlTwo, 0);
        btnLvlThree.setPosition(buttonXLvlThree, buttonYLvlThree, 0);
        btnLvlFour.setPosition(buttonXLvlFour, buttonYLvlFour, 0);
        btnLvlFive.setPosition(buttonXLvlFive, buttonYLvlFive, 0);
        btnLvlSix.setPosition(buttonXLvlSix, buttonYLvlSix, 0);
        btnBack.setPosition(buttonXBack, buttonYBack, 0);

        lblLvlOne.setPosition(buttonXLvlOne, buttonYLvlOne-100, 0);
        lblLvlTwo.setPosition(buttonXLvlTwo, buttonYLvlTwo-100, 0);
        lblLvlThree.setPosition(buttonXLvlThree, buttonYLvlThree-100, 0);
        lblLvlFour.setPosition(buttonXLvlFour, buttonYLvlFour-100, 0);
        lblLvlFive.setPosition(buttonXLvlFive, buttonYLvlFive-100, 0);
        lblLvlSix.setPosition(buttonXLvlSix, buttonYLvlSix-100, 0);

        stage.addActor(image);
        stage.addActor(btnLvlOne);
        stage.addActor(btnLvlTwo);
        stage.addActor(btnLvlThree);
        stage.addActor(btnLvlFour);
        stage.addActor(btnLvlFive);
        stage.addActor(btnLvlSix);
        stage.addActor(btnBack);
        stage.addActor(lblLvlOne);
        stage.addActor(lblLvlTwo);
        stage.addActor(lblLvlThree);
        stage.addActor(lblLvlFour);
        stage.addActor(lblLvlFive);
        stage.addActor(lblLvlSix);
        stage.addActor(btnPause);

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
        if (lvlSelected) {
            Gdx.gl.glLineWidth(3);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(1, 1, 0, 1);
            shapeRenderer.rect(iconSelectedX - 75, iconSelectedY - 125, 150, 200);
            shapeRenderer.end();
        }
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

    private ClickListener getLvlOneListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                stage.draw();
                lvlSelected = true;
                iconSelectedX = buttonXLvlOne;
                iconSelectedY = buttonYLvlOne;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                stage.draw();
                lvlSelected = false;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {

                stage.draw();
                game.setScreen(new MeatGame(game, "LevelOne.tmx"));

            }
        };
    }

    private ClickListener getLvlTwoListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {


                stage.draw();
                lvlSelected = true;
                iconSelectedX = buttonXLvlTwo;
                iconSelectedY = buttonYLvlTwo;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {


                stage.draw();
                lvlSelected = false;

            }

            @Override
            public void clicked(InputEvent event, float x, float y) {

                stage.draw();
                game.setScreen(new MeatGame(game, "LevelTwo.tmx"));
            }
        };
    }

    private ClickListener getLvlThreeListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                stage.draw();
                lvlSelected = true;
                iconSelectedX = buttonXLvlThree;
                iconSelectedY = buttonYLvlThree;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {


                stage.draw();
                lvlSelected = false;

            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ControlMenu(game));
            }
        };
    }

    private ClickListener getLvlFourListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                stage.draw();
                lvlSelected = true;
                iconSelectedX = buttonXLvlFour;
                iconSelectedY = buttonYLvlFour;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {


                stage.draw();
                lvlSelected = false;

            }

            @Override
            public void clicked(InputEvent event, float x, float y) {

                stage.draw();
                Gdx.app.exit();
            }
        };
    }

    private ClickListener getLvlFiveListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                stage.draw();
                lvlSelected = true;
                iconSelectedX = buttonXLvlFive;
                iconSelectedY = buttonYLvlFive;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {


                stage.draw();
                lvlSelected = false;

            }

            @Override
            public void clicked(InputEvent event, float x, float y) {

                stage.draw();
                Gdx.app.exit();
            }
        };
    }

    private ClickListener getLvlSixListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                stage.draw();
                lvlSelected = true;
                iconSelectedX = buttonXLvlSix;
                iconSelectedY = buttonYLvlSix;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {


                stage.draw();
                lvlSelected = false;

            }

            @Override
            public void clicked(InputEvent event, float x, float y) {

                stage.draw();
                Gdx.app.exit();
            }
        };
    }

    private ClickListener getBackListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {


                stage.draw();
                lvlSelected = false;

            }

            @Override
            public void clicked(InputEvent event, float x, float y) {

                stage.draw();
                game.setScreen(new MainMenu(game));
            }
        };
    }

    ClickListener getPauseListener() {
        return new ClickListener(){

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                btnPause.setHeight(50);
                btnPause.setWidth(50);
                btnPause.setPosition(680,510,0);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                btnPause.setHeight(80);
                btnPause.setWidth(80);
                btnPause.setPosition(680,510,0);

                stage.draw();
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new MainMenu(game));
            }
        };
    }
}