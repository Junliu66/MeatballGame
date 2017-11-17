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
import com.badlogic.gdx.physics.box2d.*;


public class MeatGame implements Screen {
    Texture img;
    TiledMap tiledMap, collisionMap;
    TiledMapRenderer tiledMapRenderer, collisionMapRenderer;
    TiledMapTileLayer collisionLayer;
    private SpriteBatch batch;
    private Player player;
    private OrthographicCamera camera;
    private OrthographicCamera box2DCamera;
    private World world;
    private float accumulator;
    private static float TIME_STEP = 1 / 60f;
    private static int VELOCITY_ITERATIONS = 6;
    private static int POSITION_ITERATIONS = 2;
    private Box2DDebugRenderer debugRenderer;
    public static float TO_PIXELS = 50f;

    public MeatGame(final GameHandler game) {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        accumulator = 0f;
        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, 16, 12);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        tiledMap = new TmxMapLoader().load("LevelOne.tmx");
        collisionMap = new TmxMapLoader().load("LevelOneCollisionMap.tmx");

        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        collisionMapRenderer = new OrthogonalTiledMapRenderer(collisionMap);
        collisionLayer = (TiledMapTileLayer) collisionMap.getLayers().get("Tile Layer 1");

        //Gdx.input.setInputProcessor(this);
        world = new World(new Vector2(), true);
        debugRenderer = new Box2DDebugRenderer();

        batch = new SpriteBatch();

        player = new Player(new Vector2(60 * 32 / TO_PIXELS, 65 * 32 / TO_PIXELS), collisionLayer, 200f, world, true);
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
        Gdx.app.log("FPS", (1/dt)+"");

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(player.getPosition().scl(TO_PIXELS), 0);
        box2DCamera.position.set(player.getPosition(), 0);
        camera.update();
        box2DCamera.update();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        collisionMapRenderer.setView(camera);
        collisionMapRenderer.render();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        player.render(batch);
        batch.end();

        debugRenderer.render(world, box2DCamera.combined);

        player.update();
        doPhysicsStep(dt);
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
        player.dispose();
    }

    private void buildWalls() {
        float pixels = 2f;

        BodyDef wallDef = new BodyDef();
        wallDef.type = BodyDef.BodyType.StaticBody;
        wallDef.position.set(0, 0);
        Body wall = world.createBody(wallDef);
//        List<Polygon> plist = new ArrayList<Polygon>();
        for (int x = 0; x < collisionLayer.getWidth(); x++) {
            for (int y = 0; y < collisionLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = collisionLayer.getCell(x, y);
                if (cell != null && cell.getTile() != null
                        && cell.getTile().getProperties().containsKey("wall")) {
                    PolygonShape poly = new PolygonShape();
                    poly.setAsBox(2 / TO_PIXELS, 2 / TO_PIXELS, new Vector2(x*pixels/TO_PIXELS, y*pixels/TO_PIXELS), 0f);
                    FixtureDef fixtureDef = new FixtureDef();
                    fixtureDef.shape = poly;
                    wall.createFixture(fixtureDef);
                    poly.dispose();
                }
            }
        }

    }

//    private void buildWalls() {
//        float pixels = 2f;
//        for ( int x = 0; x <  collisionLayer.getWidth(); x++)
//        {
//            for ( int y = 0; y < collisionLayer.getHeight(); y++)
//            {
////                System.out.println(collisionLayer.getCell(x,y).getTile().getOffsetX());
//                TiledMapTileLayer.Cell cell = collisionLayer.getCell(x,y);
//                if ( cell != null && cell.getTile() != null
//                        && cell.getTile().getProperties().containsKey("wall"))
//                {
//                    System.out.println(x * pixels / TO_PIXELS);
//                    Body wall;
//                    BodyDef wallDef = new BodyDef();
//                    wallDef.type = BodyDef.BodyType.StaticBody;
//                    wallDef.position.set( x * pixels / TO_PIXELS, y * pixels / TO_PIXELS );
//                    wall = world.createBody(wallDef);
//                    PolygonShape poly = new PolygonShape();
//                    poly.setAsBox(2 / TO_PIXELS, 2 / TO_PIXELS);
//                    FixtureDef fixtureDef = new FixtureDef();
//                    fixtureDef.shape = poly;
//                    wall.createFixture(fixtureDef);
//                    poly.dispose();
//                }
//            }
//        }
//    }

}
