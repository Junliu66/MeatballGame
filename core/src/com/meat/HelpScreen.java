package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
 * Create screen that give instructions to the user of how to play the game
 */
public class HelpScreen implements Screen {

    MainGame game;
    Stage stage;
    Button btnBack, btnLeft, btnRight, btnUp, btnDown, btnTomato, btnPepperBomb, btnBug,
            btnGarlic, btnLava, btnWater,btnFly,btnWall,btnBeer,btnPizza;
    Texture myTexture;
    TextureRegion myTextureRegion;
    TextureRegionDrawable myTexRegionDrawable;
    Texture texture;
    Label labelArrow;
    Label labelElements;
    Skin labelSkin;
    Image imgBack, imgLeft, imgRight, imgUp, imgDown, imgTomato, imgPepperBomb, imgBug,
            imgGarlic, imgLava, imgWater,imgFly,imgWall,imgBeer,imgPizza;

    /**
     * Constructor for the level select screen.
     * @param game Holds the maingame class to pass values between scenes.
     */
    public HelpScreen(MainGame game) {
        this.game = game;
    }

    /**
     * This function sets up the screen with images and buttons.
     */
    @Override
    public void show() {

        labelSkin = new Skin(Gdx.files.internal("uiskin.json"));
        labelArrow = new Label("", labelSkin);
        labelElements = new Label("", labelSkin);
        labelArrow.setPosition(300,150);
        labelElements.setPosition(300,150);
        labelArrow.setFontScale(1.5f);
        labelArrow.setColor(0,0,0,1);
        labelElements.setFontScale(1.5f);
        labelElements.setColor(0,0,0,1);

        stage = new Stage(new ScreenViewport());
        texture = new Texture("helpScreen.png");
        Image image = new Image(texture);


        myTexture = new Texture(Gdx.files.internal("btnBack.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnBack = new ImageButton(myTexRegionDrawable);
        btnBack.setColor(1, 0, 1, 1);
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

        myTexture = new Texture(Gdx.files.internal("fly.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(60);
        myTexRegionDrawable.setMinWidth(60);
        btnFly = new ImageButton(myTexRegionDrawable);
        btnFly.setColor(0, 0, 0, 0);
        imgFly = new Image(myTexture);
        imgFly.setSize(60,60);

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

        myTexture = new Texture(Gdx.files.internal("wall.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(40);
        myTexRegionDrawable.setMinWidth(40);
        btnWall = new ImageButton(myTexRegionDrawable);
        btnWall.setColor(0, 0, 0, 0);
        imgWall = new Image(myTexture);
        imgWall.setSize(40,40);

        myTexture = new Texture(Gdx.files.internal("beer.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(60);
        myTexRegionDrawable.setMinWidth(60);
        btnBeer = new ImageButton(myTexRegionDrawable);
        btnBeer.setColor(0, 0, 0, 0);
        imgBeer = new Image(myTexture);
        imgBeer.setSize(60,60);

        myTexture = new Texture(Gdx.files.internal("pizza.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable.setMinHeight(100);
        myTexRegionDrawable.setMinWidth(100);
        btnPizza = new ImageButton(myTexRegionDrawable);
        btnPizza.setColor(0, 0, 0, 0);
        imgPizza = new Image(myTexture);
        imgPizza.setSize(100,100);

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
        btnFly.addListener(getFlyListener());
        btnWall.addListener(getWallListener());
        btnBeer.addListener(getBeerListener());
        btnPizza.addListener(getPizzaListener());

        imgBack.setPosition(400, 80, 0);
        imgLeft.setPosition(180, 400, 0);
        imgRight.setPosition(340, 400,0);
        imgUp.setPosition(260, 480,0);
        imgDown.setPosition(260, 400,0);
        imgTomato.setPosition(480,450,0);
        imgPepperBomb.setPosition(570,450,0);
        imgGarlic.setPosition(660,450,0);
        imgBug.setPosition(525,330,0);
        imgFly.setPosition(615,330,0);
        imgLava.setPosition(525,230,0);
        imgWater.setPosition(615,230,0);
        imgWall.setPosition(170,230,0);
        imgBeer.setPosition(260,230,0);
        imgPizza.setPosition(350,230,0);


        btnBack.setPosition(400, 80, 0);
        btnLeft.setPosition(180, 400, 0);
        btnRight.setPosition(340, 400,0);
        btnUp.setPosition(260, 480,0);
        btnDown.setPosition(260, 400,0);
        btnTomato.setPosition(480,450,0);
        btnPepperBomb.setPosition(570,450,0);
        btnGarlic.setPosition(660,450,0);
        btnBug.setPosition(525,330,0);
        btnFly.setPosition(615,330,0);
        btnLava.setPosition(525,230,0);
        btnWater.setPosition(615,230,0);
        btnWall.setPosition(170,230,0);
        btnBeer.setPosition(260,230,0);
        btnPizza.setPosition(350,230,0);

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
        stage.addActor(imgFly);
        stage.addActor(imgLava);
        stage.addActor(imgWater);
        stage.addActor(imgWall);
        stage.addActor(imgBeer);
        stage.addActor(imgPizza);

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
        stage.addActor(btnFly);
        stage.addActor(btnWall);
        stage.addActor(btnBeer);
        stage.addActor(btnPizza);

        Gdx.input.setInputProcessor(stage);
    }

    /**
     * function called every frame to update scene
     * @param delta time between frames
     */
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

    /**
     * Button function for the back button
     * @return click listener for back button
     */
    private ClickListener getBackListener() {
        return new ClickListener(){
            boolean playing = false;

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                imgBack.setHeight(30);
                imgBack.setWidth(70);
                imgBack.setPosition(400,80,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                imgBack.setHeight(34);
                imgBack.setWidth(76);
                imgBack.setPosition(400,80,0);

                playing = false;

                stage.draw();
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {

                Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnClick.mp3"));
                sound.play(1F);
                stage.clear();
                game.setScreen(new MainMenu(game));
            }
        };
    }

    /**
     * Button function for the left button
     * @return click listener for left button
     */
    private ClickListener getLeftListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgLeft.setHeight(72);
                imgLeft.setWidth(72);
                imgLeft.setPosition(180,400,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelArrow.setText("Go Left");
                stage.addActor(labelArrow);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgLeft.setHeight(79);
                imgLeft.setWidth(79);
                imgLeft.setPosition(180,400,0);

                playing = false;

                labelArrow.setText("");
                stage.addActor(labelArrow);

                stage.draw();
            }
        };
    }

    /**
     * Button function for right button
     * @return click listener for right button
     */
    private ClickListener getRightListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgRight.setHeight(72);
                imgRight.setWidth(72);
                imgRight.setPosition(340,400,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelArrow.setText("Go Right");
                stage.addActor(labelArrow);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgRight.setHeight(79);
                imgRight.setWidth(79);
                imgRight.setPosition(340,400,0);
                playing = false;

                labelArrow.setText("");

                stage.draw();
            }
        };
    }

    /**
     * Button function for the up button
     * @return click listener for up button
     */
    private ClickListener getUpListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgUp.setHeight(72);
                imgUp.setWidth(72);
                imgUp.setPosition(260,480,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelArrow.setText("Go Up");
                stage.addActor(labelArrow);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgUp.setHeight(79);
                imgUp.setWidth(79);
                imgUp.setPosition(260,480,0);

                playing = false;

                labelArrow.setText("");

                stage.draw();
            }
        };
    }

    /**
     * Button function for the down button
     * @return click listener for down button
     */
    private ClickListener getDownListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgDown.setHeight(72);
                imgDown.setWidth(72);
                imgDown.setPosition(260,400,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelArrow.setText("Go Down");
                stage.addActor(labelArrow);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgDown.setHeight(79);
                imgDown.setWidth(79);
                imgDown.setPosition(260,400,0);

                playing = false;

                labelArrow.setText("");
                stage.draw();
            }
        };
    }

    /**
     * Button function for the tomato button
     * @return click listener for tomato button
     */
    private ClickListener getTomatoListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgTomato.setPosition(480,470,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelElements.setText("Tomato: Rewards for the score.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgTomato.setPosition(480,450,0);

                playing = false;

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    /**
     * Button function for the pepper bomb button
     * @return click listener for pepper bomb button
     */
    private ClickListener getPepperBombListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgPepperBomb.setPosition(570,470,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelElements.setText("Pepper: Accelerate MeatBall.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgPepperBomb.setPosition(570,450,0);

                playing = false;

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    /**
     * Button function for the garlic button
     * @return click listener for garlic button
     */
    public EventListener getGarlicSlowListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgGarlic.setPosition(660,470,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelElements.setText("Garlic: Decelerate of MeatBall.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgGarlic.setPosition(660,450,0);

                playing = false;

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    /**
     * Button function for the bug button
     * @return click listener for bug button
     */
    private ClickListener getBugListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgBug.setPosition(525,350,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelElements.setText("Bug: Reduce health of MeatBall.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgBug.setPosition(525,330,0);

                playing = false;

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    /**
     * Button function for the fly button
     * @return click listener for fly button
     */
    private ClickListener getFlyListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgFly.setPosition(615,350,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelElements.setText("Fly: Chase MeatBall & Push it to edge.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgFly.setPosition(615,330,0);

                playing = false;

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    /**
     * Button function for the lava button
     * @return click listener for lava button
     */
    public EventListener getLavaListener() {
            return new ClickListener() {
                boolean playing = false;
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                    imgLava.setPosition(525,250,0);

                    if (!playing) {
                        Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                        sound.play(1F);
                        playing = true;
                    }
                    labelElements.setText("Lava: Reduce health of MeatBall.");
                    stage.addActor(labelElements);
                    stage.draw();
                }

                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                    imgLava.setPosition(525,230,0);

                    playing = false;

                    labelElements.setText("");
                    stage.draw();
                }
            };
        }

    /**
     * Button function for the water button
     * @return click listener for water button
     */
    public EventListener getWaterListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgWater.setPosition(615,250,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelElements.setText("Water: Reduce health of MeatBall.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgWater.setPosition(615,230,0);

                playing = false;

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    /**
     * Button function for the wall button
     * @return click listener for wall button
     */
    private ClickListener getWallListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgWall.setPosition(170,250,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelElements.setText("Wall: Block the way.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgWall.setPosition(170,230,0);

                playing = false;

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    /**
     * Button function for the beer button
     * @return click listener for beer button
     */
    private ClickListener getBeerListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgBeer.setPosition(260,250,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelElements.setText("Beer: Block the way.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgBeer.setPosition(260,230,0);

                playing = false;

                labelElements.setText("");
                stage.draw();
            }
        };
    }

    /**
     * Button function for the pizza button
     * @return click listener for pizza button
     */
    private ClickListener getPizzaListener() {
        return new ClickListener() {
            boolean playing = false;
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                imgPizza.setPosition(350,250,0);

                if (!playing) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btnEnter.mp3"));
                    sound.play(1F);
                    playing = true;
                }
                labelElements.setText("Pizza: Block the way.");
                stage.addActor(labelElements);
                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                imgPizza.setPosition(350,230,0);

                playing = false;

                labelElements.setText("");
                stage.draw();
            }
        };
    }
}




