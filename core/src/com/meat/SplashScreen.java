package com.meat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import javafx.util.Pair;

import java.util.ArrayList;


/**
 * Created by zhangJunliu on 11/25/17.
 */
public class SplashScreen implements Screen{


        private SpriteBatch batch;
        private MainGame game;
        private Texture texture;
        private OrthographicCamera camera;
        private long startTime;
        private int rendCount;
        private float accumulator;
        private static float TIME_STEP = 1 / 60f;
        private static int VELOCITY_ITERATIONS = 6;
        private static int POSITION_ITERATIONS = 2;

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

