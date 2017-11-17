package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    Texture texture;
    Label label;

    public HelpScreen(MainGame game) {
        this.game = game;
    }

    private MainGame game;

    @Override
    public void show() {

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

        btnBack.addListener(getBackListener());
        btnLeft.addListener(getLeftListener());
        btnRight.addListener(getLeftListener());
        btnUp.addListener(getLeftListener());
        btnDown.addListener(getLeftListener());

        btnBack.setPosition(400, 120, 0);
        btnLeft.setPosition(180, 400, 0);
        btnRight.setPosition(280, 400,0);
        btnUp.setPosition(230, 450,0);
        btnDown.setPosition(230, 400,0);


        btnLeft.addListener(getLeftListener());
        btnLeft.addListener(getLeftListener());
        stage.addActor(btnBack);
        stage.addActor(btnLeft);
        stage.addActor(btnRight);
        stage.addActor(btnUp);
        stage.addActor(btnDown);


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


                //label.setText("Go Left");
                //label.setPosition(400, 400,0);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                btnLeft.setHeight(100);
                btnLeft.setWidth(100);
                btnLeft.setPosition(180,400,0);

                stage.draw();
            }
        };
    }
}




