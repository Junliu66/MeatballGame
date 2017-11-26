package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;



/**
 * Created by zhangJunliu on 11/24/17.
 */
public class SplashScreen implements Screen{


        private SpriteBatch batch;
        private MainGame game;
        private Texture texture;
        private OrthographicCamera camera;
        private long startTime;
        private int rendCount;


    public SplashScreen(MainGame g)
        {
            Gdx.app.log("my Spash Screen", "constructor called");
            game = g;
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 800, 600);
            batch = new SpriteBatch();

        }



        @Override
        public void render(float delta) {

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.setProjectionMatrix(camera.combined);
            batch.begin();

            batch.draw(texture, 0, 0);
            batch.end();
            rendCount++;
            if (TimeUtils.millis() > (startTime + 2000)) game.setScreen(new MainMenu(game));

        }


        @Override
        public void resize(int width, int height) {
        }

        @Override
        public void show() {
            Gdx.app.log("my Splash Screen", "show called");
            texture = new Texture(Gdx.files.internal("mainMenu0.png"));
            startTime = TimeUtils.millis();
        }

        @Override
        public void hide() {
            Gdx.app.log("my Splash Screen", "hide called");
            Gdx.app.log("my Splash Screen", "rendered " + rendCount + " times.");
        }

        @Override
        public void pause() {
        }

        @Override
        public void resume() {
        }

        @Override
        public void dispose() {
            texture.dispose();
            batch.dispose();
        }
    }

