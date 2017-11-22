package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class RestartScreen implements Screen {
    private MainGame game;
    private Button restart;
    private final Stage stage;
    public  RestartScreen(MainGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport(), game.batch);
    }

    @Override
    public void show() {
        final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        restart = new TextButton("RESTART", skin);
        restart.addListener(getRestartListener());
        restart.setPosition(580, 270, 0);



        stage.addActor(restart);
        Gdx.input.setInputProcessor(stage);
    }
    private ClickListener getRestartListener() {
        return new ClickListener(){

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                restart.setHeight(60);
                restart.setWidth(60);
                restart.setPosition(400,120,0);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                restart.setHeight(100);
                restart.setWidth(100);
                restart.setPosition(400,120,0);

                stage.draw();
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.meatGame);
                game.meatGame.resetLevel();
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
