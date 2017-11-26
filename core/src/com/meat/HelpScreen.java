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

/**
 * Created by zhangJunliu on 11/15/17.
 */
public class HelpScreen implements Screen {

    MainGame game;
    Stage stage;
    Button btnBack, btnLeft, btnRight, btnUp, btnDown, btnTomato, btnPepperBomb, btnBug,
            btnGarlic, btnLava, btnWater;
    Texture myTexture;
    TextureRegion myTextureRegion;
    TextureRegionDrawable myTexRegionDrawable;
    Texture texture;
    Label labelArrow;
    Label labelElements;
    Skin labelSkin;
    Image imgBack, imgLeft, imgRight, imgUp, imgDown, imgTomato, imgPepperBomb, imgBug,
            imgGarlic, imgLava, imgWater;

    public HelpScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {

        labelSkin = new Skin(Gdx.files.internal("uiskin.json"));
        labelArrow = new Label("", labelSkin);
        labelElements = new Label("", labelSkin);
        labelArrow.setPosition(500,400);
        labelElements.setPosition(400,200);
        labelArrow.setFontScale(1.5f);
        labelElements.setFontScale(1.2f);

        stage = new Stage(new ScreenViewport());
        texture = new Texture("helpScreen.png");
        Image image = new Image(texture);


        myTexture = new Texture(Gdx.files.internal("btnBack.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnBack = new ImageButton(myTexRegionDrawable);
        btnBack.setColor(0, 0, 0, 0);
        imgBack = new Image(myTexture);

        myTexture = new Texture(Gdx.files.internal("btnLeft.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnLeft = new ImageButton(myTexRegionDrawable);
        btnLeft.setColor(0, 0, 0, 0);
        imgLeft = new Image(myTexture);

        myTexture = new Texture(Gdx.files.internal("btnRight.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnRight = new ImageButton(myTexRegionDrawable);
        btnRight.setColor(0, 0, 0, 0);
        imgRight = new Image(myTexture);

        myTexture = new Texture(Gdx.files.internal("btnUp.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnUp = new ImageButton(myTexRegionDrawable);
        btnUp.setColor(0, 0, 0, 0);
        imgUp = new Image(myTexture);

        myTexture = new Texture(Gdx.files.internal("btnDown.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnDown = new ImageButton(myTexRegionDrawable);
        btnDown.setColor(0, 0, 0, 0);
        imgDown = new Image(myTexture);

        myTexture = new Texture(Gdx.files.internal("tomato.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(60);
        myTexRegionDrawable.setMinWidth(60);
        btnTomato = new ImageButton(myTexRegionDrawable);
        btnTomato.setColor(0, 0, 0, 0);
        imgTomato = new Image(myTexture);
        imgTomato.setSize(60,60);

        myTexture = new Texture(Gdx.files.internal("pepperbomb.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(60);
        myTexRegionDrawable.setMinWidth(60);
        btnPepperBomb = new ImageButton(myTexRegionDrawable);
        btnPepperBomb.setColor(0, 0, 0, 0);
        imgPepperBomb = new Image(myTexture);
        imgPepperBomb.setSize(60,60);

        myTexture = new Texture(Gdx.files.internal("garlicSlow.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(60);
        myTexRegionDrawable.setMinWidth(60);
        btnGarlic = new ImageButton(myTexRegionDrawable);
        btnGarlic.setColor(0, 0, 0, 0);
        imgGarlic = new Image(myTexture);
        imgGarlic.setSize(60,60);

        myTexture = new Texture(Gdx.files.internal("bug.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(60);
        myTexRegionDrawable.setMinWidth(60);
        btnBug = new ImageButton(myTexRegionDrawable);
        btnBug.setColor(0, 0, 0, 0);
        imgBug = new Image(myTexture);
        imgBug.setSize(60,60);

        myTexture = new Texture(Gdx.files.internal("lava.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(60);
        myTexRegionDrawable.setMinWidth(60);
        btnLava = new ImageButton(myTexRegionDrawable);
        btnLava.setColor(0, 0, 0, 0);
        imgLava = new Image(myTexture);
        imgLava.setSize(60,60);

        myTexture = new Texture(Gdx.files.internal("water.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(60);
        myTexRegionDrawable.setMinWidth(60);
        btnWater = new ImageButton(myTexRegionDrawable);
        btnWater.setColor(0, 0, 0, 0);
        imgWater = new Image(myTexture);
        imgWater.setSize(60,60);

        btnBack.addListener(getBackListener());
        btnLeft.addListener(getLeftListener());
        btnRight.addListener(getRightListener());
        btnUp.addListener(getUpListener());
        btnDown.addListener(getDownListener());
        btnTomato.addListener(getTomatoListener());
        btnPepperBomb.addListener(getPepperBombListener());
        btnGarlic.addListener(getGarlicSlowListener());
        btnLava.addListener(getLavaListener());
        btnWater.addListener(getWaterListener());
        btnBug.addListener(getBugListener());

        imgBack.setPosition(400, 80, 0);
        imgLeft.setPosition(180, 400, 0);
        imgRight.setPosition(340, 400,0);
        imgUp.setPosition(260, 480,0);
        imgDown.setPosition(260, 400,0);
        imgTomato.setPosition(180,270,0);
        imgPepperBomb.setPosition(270,270,0);
        imgGarlic.setPosition(360,270,0);
        imgBug.setPosition(180,150,0);
        imgLava.setPosition(270,150,0);
        imgWater.setPosition(360,150,0);

        btnBack.setPosition(400, 80, 0);
        btnLeft.setPosition(180, 400, 0);
        btnRight.setPosition(340, 400,0);
        btnUp.setPosition(260, 480,0);
        btnDown.setPosition(260, 400,0);
        btnTomato.setPosition(180,270,0);
        btnPepperBomb.setPosition(270,270,0);
        btnGarlic.setPosition(360,270,0);
        btnBug.setPosition(180,150,0);
        btnLava.setPosition(270,150,0);
        btnWater.setPosition(360,150,0);

        stage.addActor(image);
        stage.addActor(imgBack);
        stage.addActor(imgLeft);
        stage.addActor(imgRight);
        stage.addActor(imgUp);
        stage.addActor(imgDown);
        stage.addActor(imgTomato);
        stage.addActor(imgPepperBomb);
        stage.addActor(imgGarlic);
        stage.addActor(imgBug);
        stage.addActor(imgLava);
        stage.addActor(imgWater);


        stage.addActor(btnBack);
        stage.addActor(btnLeft);
        stage.addActor(btnRight);
        stage.addActor(btnUp);
        stage.addActor(btnDown);
        stage.addActor(btnTomato);
        stage.addActor(btnPepperBomb);
        stage.addActor(btnGarlic);
        stage.addActor(btnLava);
        stage.addActor(btnWater);
        stage.addActor(btnBug);

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

    }

    private ClickListener getBackListener() {
        return new ClickListener(){

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                imgBack.setHeight(30);
                imgBack.setWidth(70);
                imgBack.setPosition(400,80,0);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                imgBack.setHeight(34);
                imgBack.setWidth(76);
                imgBack.setPosition(400,80,0);

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

                imgLeft.setHeight(72);
                imgLeft.setWidth(72);
                imgLeft.setPosition(180,400,0);

                labelArrow.setText("Go Left");
                stage.addActor(labelArrow);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgLeft.setHeight(79);
                imgLeft.setWidth(79);
                imgLeft.setPosition(180,400,0);

                labelArrow.setText("");
                stage.addActor(labelArrow);

                stage.draw();
            }
        };
    }

    private ClickListener getRightListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgRight.setHeight(72);
                imgRight.setWidth(72);
                imgRight.setPosition(340,400,0);

                labelArrow.setText("Go Right");
                stage.addActor(labelArrow);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgRight.setHeight(79);
                imgRight.setWidth(79);
                imgRight.setPosition(340,400,0);

                labelArrow.setText("");

                stage.draw();
            }
        };
    }

    private ClickListener getUpListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgUp.setHeight(72);
                imgUp.setWidth(72);
                imgUp.setPosition(260,480,0);

                labelArrow.setText("Go Up");
                stage.addActor(labelArrow);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgUp.setHeight(79);
                imgUp.setWidth(79);
                imgUp.setPosition(260,480,0);

                labelArrow.setText("");

                stage.draw();
            }
        };
    }

    private ClickListener getDownListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgDown.setHeight(72);
                imgDown.setWidth(72);
                imgDown.setPosition(260,400,0);

                labelArrow.setText("Go Down");
                stage.addActor(labelArrow);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgDown.setHeight(79);
                imgDown.setWidth(79);
                imgDown.setPosition(260,400,0);

                labelArrow.setText("");
                stage.draw();
            }
        };
    }

    private ClickListener getTomatoListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgTomato.setPosition(180,290,0);

                labelElements.setText("Tomato: Rewards for the score.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgTomato.setPosition(180,270,0);

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    private ClickListener getPepperBombListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgPepperBomb.setPosition(270,290,0);

                labelElements.setText("Pepper: Accelerate MeatBall.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgPepperBomb.setPosition(270,270,0);

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    public EventListener getGarlicSlowListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgGarlic.setPosition(360,290,0);

                labelElements.setText("Garlic: Decelerate of MeatBall.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgGarlic.setPosition(360,270,0);

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    private ClickListener getBugListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgBug.setPosition(180,170,0);

                labelElements.setText("Bug: Reduce health of MeatBall.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgBug.setPosition(180,150,0);

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    public EventListener getLavaListener() {
            return new ClickListener() {
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                    imgLava.setPosition(270,170,0);

                    labelElements.setText("Lava: Reduce health of MeatBall.");
                    stage.addActor(labelElements);
                    stage.draw();
                }

                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                    imgLava.setPosition(270,150,0);

                    labelElements.setText("");
                    stage.draw();
                }
            };
        }



    public EventListener getWaterListener() {
        return new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgWater.setPosition(360,170,0);

                labelElements.setText("Water: Reduce health of MeatBall.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgWater.setPosition(360,150,0);

                labelElements.setText("");
                stage.draw();
            }
        };
    }
}




