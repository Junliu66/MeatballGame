package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;


public class MeatGame implements Screen {
    Texture img;
    TiledMap tiledMap, collisionMap;
    TiledMapRenderer tiledMapRenderer, collisionMapRenderer;
    TiledMapTileLayer collisionLayer;
//    private SpriteBatch batch;
    private Player player;
    private OrthographicCamera camera;
    private OrthographicCamera box2DCamera;
    private World world;
    private float accumulator;
    private static float TIME_STEP = 1 / 60f;
    private static int VELOCITY_ITERATIONS = 6;
    private static int POSITION_ITERATIONS = 2;
    private Texture background;
    private Box2DDebugRenderer debugRenderer;
    public static float TO_PIXELS = 50f;
    final MainGame game;
    private static int DESIRED_RENDER_WIDTH = 800;
    private static int DESIRED_RENDER_HEIGHT = 600;

    public static float lerp = 5.0f;

    public MeatGame(final MainGame game) {
        this.game = game;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        background = new Texture("clouds_bg.png");
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        background.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        accumulator = 0f;
        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, 16, 12);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        tiledMap = new TmxMapLoader().load("testlevel2.tmx");
        collisionMap = new TmxMapLoader().load("testlevel2.tmx");

        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        collisionMapRenderer = new OrthogonalTiledMapRenderer(collisionMap);
        collisionLayer = (TiledMapTileLayer) collisionMap.getLayers().get("Tile Layer 1");

        //Gdx.input.setInputProcessor(this);
        world = new World(new Vector2(), true);
        debugRenderer = new Box2DDebugRenderer();

//        batch = new SpriteBatch();

        player = new Player(new Vector2(60 * 32 / TO_PIXELS, 65 * 32 / TO_PIXELS), collisionLayer, 24f, world, true);
        buildWalls();
        Body wall;
        BodyDef wallDef = new BodyDef();
        wallDef.type = BodyDef.BodyType.StaticBody;
        wallDef.position.set(5, 3);
        wall = world.createBody(wallDef);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(2, 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = poly;
        wall.createFixture(fixtureDef);
        poly.dispose();


    }

    @Override
    public void render(float dt) {
//        Gdx.app.log("FPS", (1/dt)+"");

        doPhysicsStep(dt);
        player.update();

        Vector3 position = camera.position;
        Vector3 box2dposition = box2DCamera.position;
        Vector2 player_pos = player.getPosition().scl(TO_PIXELS);

        position.x += (player_pos.x - position.x) * lerp * dt;
        position.y += (player_pos.y - position.y) * lerp * dt;

        box2dposition.x += (player.getPosition().x - box2dposition.x) * lerp * dt;
        box2dposition.y += (player.getPosition().y - box2dposition.y) * lerp * dt;

        //camera.position.set(player.getPosition().scl(TO_PIXELS), 0);
        //box2DCamera.position.set(player.getPosition(), 0);
        camera.update();
        box2DCamera.update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.draw(background, camera.position.x-camera.viewportWidth/2, camera.position.y-camera.viewportHeight/2, (int)camera.position.x/4, (int)-camera.position.y/4, (int) camera.viewportWidth, (int) camera.viewportHeight);
        game.batch.end();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        collisionMapRenderer.setView(camera);
        collisionMapRenderer.render();

        game.batch.begin();
        player.render(game.batch);
        game.batch.end();

//        debugRenderer.render(world, box2DCamera.combined);

    }

    private void doPhysicsStep(float deltaTime) {
        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= TIME_STEP) {
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            accumulator -= TIME_STEP;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        // scales to fixed viewport width
        int newHeight = (int) (height * (((double) DESIRED_RENDER_WIDTH) / ((double) width)));
        if (height < DESIRED_RENDER_HEIGHT)
        {
            newHeight = (int) (((double) DESIRED_RENDER_WIDTH) / ((double) width)) * height;
        }

        camera.setToOrtho(false, DESIRED_RENDER_WIDTH, newHeight);
        box2DCamera.setToOrtho(false, width/TO_PIXELS, height/TO_PIXELS);
        camera.position.set(player.getPosition().scl(TO_PIXELS), 0);
        camera.update();
        box2DCamera.position.set(player.getPosition(),0);
        box2DCamera.update();
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
        player.dispose();
    }

    private void buildWalls() {
        float pixels = 2f;

        BodyDef wallDef = new BodyDef();
        wallDef.type = BodyDef.BodyType.StaticBody;
        wallDef.position.set(0, 0);
        Body wall = world.createBody(wallDef);
        for (int x = 0; x < collisionLayer.getWidth(); x++) {
            for (int y = 0; y < collisionLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = collisionLayer.getCell(x, y);
                if (cell != null && cell.getTile() != null
                        && cell.getTile().getProperties().containsKey("wall")
                        && (x%16 == 0 && y%16 == 0)) {
                    PolygonShape poly = new PolygonShape();
                    poly.setAsBox(16 / TO_PIXELS, 16 / TO_PIXELS, new Vector2((pixels*x-16)/TO_PIXELS, (pixels*y+16)/TO_PIXELS), 0f);
                    FixtureDef fixtureDef = new FixtureDef();
                    fixtureDef.shape = poly;
                    wall.createFixture(fixtureDef);
                    poly.dispose();
                }
            }
        }

    }
}
