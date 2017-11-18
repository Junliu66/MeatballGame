package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by zhangJunliu on 11/15/17.
 */
public class HelpScreen implements Screen {

    Stage stage;
    Button btnBack;
    Button btnLeft;
    Button btnRight;
    Button btnUp;
    Button btnDown;
    Button btnTomato;
    Button btnPepperBomb;
    Button btnBug;
    Texture myTexture;
    TextureRegion myTextureRegion;
    TextureRegionDrawable myTexRegionDrawable;
    Texture texture;
    Label labelArrow;
    Label labelElements;
    Skin labelSkin;

    public HelpScreen(MainGame game) {
        this.game = game;
    }

    private MainGame game;

    @Override
    public void show() {

        labelSkin = new Skin(Gdx.files.internal("uiskin.json"));
        labelArrow = new Label("", labelSkin);
        labelElements = new Label("", labelSkin);
        labelArrow.setPosition(500,400);
        labelElements.setPosition(180,190);
        labelArrow.setFontScale(1.5f);
        labelElements.setFontScale(1.2f);

        stage = new Stage(new ScreenViewport());
        texture = new Texture("helpScreen.png");
        Image image = new Image(texture);
        stage.addActor(image);

        myTexture = new Texture(Gdx.files.internal("btnBack.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnBack = new ImageButton(myTexRegionDrawable);

        myTexture = new Texture(Gdx.files.internal("btnLeft.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnLeft = new ImageButton(myTexRegionDrawable);

        myTexture = new Texture(Gdx.files.internal("btnRight.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnRight = new ImageButton(myTexRegionDrawable);

        myTexture = new Texture(Gdx.files.internal("btnUp.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnUp = new ImageButton(myTexRegionDrawable);

        myTexture = new Texture(Gdx.files.internal("btnDown.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnDown = new ImageButton(myTexRegionDrawable);

        myTexture = new Texture(Gdx.files.internal("tomato.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(60);
        myTexRegionDrawable.setMinWidth(60);
        btnTomato = new ImageButton(myTexRegionDrawable);

        myTexture = new Texture(Gdx.files.internal("pepperbomb.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(60);
        myTexRegionDrawable.setMinWidth(60);
        btnPepperBomb = new ImageButton(myTexRegionDrawable);

        myTexture = new Texture(Gdx.files.internal("bug.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(60);
        myTexRegionDrawable.setMinWidth(60);
        btnBug = new ImageButton(myTexRegionDrawable);


        btnBack.addListener(getBackListener());
        btnLeft.addListener(getLeftListener());
        btnRight.addListener(getRightListener());
        btnUp.addListener(getUpListener());
        btnDown.addListener(getDownListener());
        btnTomato.addListener(getTomatoListener());
        btnPepperBomb.addListener(getPepperBombListener());
        btnBug.addListener(getBugListener());

        btnBack.setPosition(400, 120, 0);
        btnLeft.setPosition(180, 400, 0);
        btnRight.setPosition(340, 400,0);
        btnUp.setPosition(260, 480,0);
        btnDown.setPosition(260, 400,0);
        btnTomato.setPosition(180,270,0);
        btnPepperBomb.setPosition(270,270,0);
        btnBug.setPosition(360,270,0);

        stage.addActor(btnBack);
        stage.addActor(btnLeft);
        stage.addActor(btnRight);
        stage.addActor(btnUp);
        stage.addActor(btnDown);
        stage.addActor(btnTomato);
        stage.addActor(btnPepperBomb);
        stage.addActor(btnBug);



        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(1, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

    }

    private ClickListener getBackListener() {
        return new ClickListener(){

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                btnBack.setHeight(60);
                btnBack.setWidth(60);
                btnBack.setPosition(400,120,0);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                btnBack.setHeight(100);
                btnBack.setWidth(100);
                btnBack.setPosition(400,120,0);

                stage.draw();
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new MainMenu(game));
            }
        };
    }

    private ClickListener getLeftListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                btnLeft.setHeight(70);
                btnLeft.setWidth(70);
                btnLeft.setPosition(180,400,0);

                labelArrow.setText("Go Left");
                stage.addActor(labelArrow);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                btnLeft.setHeight(100);
                btnLeft.setWidth(100);
                btnLeft.setPosition(180,400,0);

                labelArrow.setText("");
                stage.addActor(labelArrow);

                stage.draw();
            }
        };
    }

    private ClickListener getRightListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                btnRight.setHeight(70);
                btnRight.setWidth(70);
                btnRight.setPosition(340,400,0);

                labelArrow.setText("Go Right");
                stage.addActor(labelArrow);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                btnRight.setHeight(100);
                btnRight.setWidth(100);
                btnRight.setPosition(340,400,0);

                labelArrow.setText("");

                stage.draw();
            }
        };
    }

    private ClickListener getUpListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                btnUp.setHeight(70);
                btnUp.setWidth(70);
                btnUp.setPosition(260,480,0);

                labelArrow.setText("Go Up");
                stage.addActor(labelArrow);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                btnUp.setHeight(100);
                btnUp.setWidth(100);
                btnUp.setPosition(260,480,0);

                labelArrow.setText("");

                stage.draw();
            }
        };
    }

    private ClickListener getDownListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                btnDown.setHeight(70);
                btnDown.setWidth(70);
                btnDown.setPosition(260,400,0);

                labelArrow.setText("Go Down");
                stage.addActor(labelArrow);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                btnDown.setHeight(100);
                btnDown.setWidth(100);
                btnDown.setPosition(260,400,0);

                labelArrow.setText("");
                stage.draw();
            }
        };
    }

    private ClickListener getTomatoListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                btnTomato.setPosition(180,290,0);

                labelElements.setText("Tomato: more words...");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                btnTomato.setPosition(180,270,0);

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    private ClickListener getPepperBombListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                btnPepperBomb.setPosition(270,290,0);

                labelElements.setText("Pepper Bomb: more words...");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                btnPepperBomb.setPosition(270,270,0);

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    private ClickListener getBugListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                btnBug.setPosition(360,290,0);

                labelElements.setText("Bug: more words...");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                btnBug.setPosition(360,270,0);

                labelElements.setText("");
                stage.draw();
            }
        };
    }
}




