package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import java.util.ArrayList;


public class MeatGame implements Screen {
    TiledMap tiledMap;//, collisionMap;
    TiledMapRenderer tiledMapRenderer;//, collisionMapRenderer;
    TiledMapTileLayer collisionLayer;
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
    private static boolean RENDER_DEBUG = true;
    public ArrayList<Shape2D> holes;
    public ArrayList<Shape2D> goals;
    private ShapeRenderer shapeRenderer;
    private Vector2 playerStart;

    public static float lerp = 5.0f;

    public MeatGame(final MainGame game) {
        this.game = game;

        shapeRenderer = new ShapeRenderer();

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
        tiledMap = new TmxMapLoader().load("LevelOne.tmx");
//        collisionMap = new TmxMapLoader().load("LevelOneCollisionMap.tmx");

        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
//        collisionMapRenderer = new OrthogonalTiledMapRenderer(collisionMap);
//        collisionLayer = (TiledMapTileLayer) collisionMap.getLayers().get("Tile Layer 1");
        MapLayer objectLayer = tiledMap.getLayers().get("Object Layer 1");
        playerStart = new Vector2(0,0);

        world = new World(new Vector2(), true);

        holes = new ArrayList<Shape2D>();
        goals = new ArrayList<Shape2D>();

        BodyDef wallDef = new BodyDef();
        wallDef.type = BodyDef.BodyType.StaticBody;
        wallDef.position.set(0, 0);
        Body wall = world.createBody(wallDef);

        for (MapObject obj : objectLayer.getObjects())
        {
            if (obj.getName() == null)
            {
                Gdx.app.log("Un-named Object", obj.toString());
            }
            else if (obj.getName().equals("start"))
            {
                if (obj instanceof RectangleMapObject)
                {
                    playerStart = new Vector2(((RectangleMapObject) obj).getRectangle().x / TO_PIXELS, ((RectangleMapObject) obj).getRectangle().y / TO_PIXELS);
                }
            } else if (obj.getName().equals("wall")) {
                if (obj instanceof RectangleMapObject) {
                    PolygonShape poly = new PolygonShape();
                    poly.setAsBox((((RectangleMapObject) obj).getRectangle().width/2) / TO_PIXELS, (((RectangleMapObject) obj).getRectangle().height/2) / TO_PIXELS,
                            new Vector2((((RectangleMapObject) obj).getRectangle().x + ((RectangleMapObject) obj).getRectangle().width/2) / TO_PIXELS,
                                    (((RectangleMapObject) obj).getRectangle().y + ((RectangleMapObject) obj).getRectangle().height/2) / TO_PIXELS), 0f);
                    FixtureDef fixtureDef = new FixtureDef();
                    fixtureDef.shape = poly;
                    wall.createFixture(fixtureDef);
                    poly.dispose();
                } else if (obj instanceof CircleMapObject) {
                    CircleShape circle = new CircleShape();
                    circle.setRadius((((CircleMapObject) obj).getCircle().radius/2) / TO_PIXELS);
                    circle.setPosition(new Vector2(
                            (((CircleMapObject) obj).getCircle().x + ((CircleMapObject) obj).getCircle().radius/2) / TO_PIXELS,
                            (((CircleMapObject) obj).getCircle().y + ((CircleMapObject) obj).getCircle().radius/2) / TO_PIXELS ));
                    FixtureDef fixtureDef = new FixtureDef();
                    fixtureDef.shape = circle;
                    wall.createFixture(fixtureDef);
                    circle.dispose();
                } else if (obj instanceof EllipseMapObject) {
                    CircleShape circle = new CircleShape();
                    circle.setRadius((((EllipseMapObject) obj).getEllipse().width/2) / TO_PIXELS);
                    circle.setPosition(new Vector2(
                            (((EllipseMapObject) obj).getEllipse().x + ((EllipseMapObject) obj).getEllipse().width/2) / TO_PIXELS,
                            (((EllipseMapObject) obj).getEllipse().y + ((EllipseMapObject) obj).getEllipse().width/2) / TO_PIXELS) );
                    FixtureDef fixtureDef = new FixtureDef();
                    fixtureDef.shape = circle;
                    wall.createFixture(fixtureDef);
                    circle.dispose();
                }
            } else if (obj.getName().equals("hole")) {
                if (obj instanceof RectangleMapObject) {
                    Rectangle rect = ((RectangleMapObject) obj).getRectangle();
                    holes.add(rect);
                } else if (obj instanceof CircleMapObject) {
                    Circle circle = new Circle();
                    circle.radius = ((CircleMapObject) obj).getCircle().radius / 2f;
                    circle.setPosition(((CircleMapObject) obj).getCircle().x, ((CircleMapObject) obj).getCircle().y);
                    holes.add(circle);
                } else if (obj instanceof EllipseMapObject) {
                    Ellipse ellipse = ((EllipseMapObject) obj).getEllipse();
                    ellipse.setPosition(ellipse.x + ellipse.width/2f, ellipse.y + (ellipse.height/2f));
                    holes.add(ellipse);
                } else if (obj instanceof PolygonMapObject) {
                    Polygon polygon = ((PolygonMapObject) obj).getPolygon();
                    polygon.setPosition(((PolygonMapObject) obj).getPolygon().getX(), ((PolygonMapObject) obj).getPolygon().getY());
                    polygon.setRotation(((PolygonMapObject) obj).getPolygon().getRotation());
                    holes.add(polygon);
                } else if (obj instanceof PolylineMapObject) {
                    Polygon polygon = new Polygon( ((PolylineMapObject) obj).getPolyline().getVertices() );
                    polygon.setPosition(
                            ((PolylineMapObject) obj).getPolyline().getX(),
                            ((PolylineMapObject) obj).getPolyline().getY());
                    polygon.setRotation(((PolylineMapObject) obj).getPolyline().getRotation());
                    holes.add(polygon);
                } else {
                    Gdx.app.log("Shape not recognized", "" + obj.getClass().getName());
                }
            } else if (obj.getName().equals("goal")) {
                if (obj instanceof RectangleMapObject) {
                    Rectangle rect = ((RectangleMapObject) obj).getRectangle();
                    goals.add(rect);
                } else if (obj instanceof CircleMapObject) {
                    Circle circle = new Circle();
                    circle.radius = ((CircleMapObject) obj).getCircle().radius / 2f;
                    circle.setPosition(((CircleMapObject) obj).getCircle().x, ((CircleMapObject) obj).getCircle().y);
                    goals.add(circle);
                } else if (obj instanceof EllipseMapObject) {
                    Ellipse ellipse = ((EllipseMapObject) obj).getEllipse();
                    ellipse.setPosition(ellipse.x + ellipse.width/2f, ellipse.y + (ellipse.height/2f));
                    goals.add(ellipse);
                } else if (obj instanceof PolygonMapObject) {
                    Polygon polygon = ((PolygonMapObject) obj).getPolygon();
                    polygon.setPosition(((PolygonMapObject) obj).getPolygon().getX(), ((PolygonMapObject) obj).getPolygon().getY());
                    polygon.setRotation(((PolygonMapObject) obj).getPolygon().getRotation());
                    goals.add(polygon);
                } else if (obj instanceof PolylineMapObject) {
                    Polygon polygon = new Polygon( ((PolylineMapObject) obj).getPolyline().getVertices() );
                    polygon.setPosition(
                            ((PolylineMapObject) obj).getPolyline().getX(),
                            ((PolylineMapObject) obj).getPolyline().getY());
                    polygon.setRotation(((PolylineMapObject) obj).getPolyline().getRotation());
                    goals.add(polygon);
                } else {
                    Gdx.app.log("Shape not recognized", "" + obj.getClass().getName());
                }
            }
        }

        //Gdx.input.setInputProcessor(this);
        debugRenderer = new Box2DDebugRenderer();

//        batch = new SpriteBatch();

        player = new Player(new Vector2(playerStart.x, playerStart.y), collisionLayer, 64f, world, true);

    }

    @Override
    public void render(float dt) {

        doPhysicsStep(dt);
//        world.step(dt, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        player.update(this);

        Vector3 position = camera.position;
        Vector3 box2dposition = box2DCamera.position;
        Vector2 player_pos = player.getPosition().scl(TO_PIXELS);

        position.x += (player_pos.x - position.x) * lerp * dt;
        position.y += (player_pos.y - position.y) * lerp * dt;

        box2dposition.x += (player.getPosition().x - box2dposition.x) * lerp * dt;
        box2dposition.y += (player.getPosition().y - box2dposition.y) * lerp * dt;

        camera.position.set(player.getPosition().scl(TO_PIXELS), 0);
        box2DCamera.position.set(player.getPosition(), 0);
        camera.update();
        box2DCamera.update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.draw(background, camera.position.x - camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2, (int) camera.position.x / 4, (int) -camera.position.y / 4, (int) camera.viewportWidth, (int) camera.viewportHeight);
        game.batch.end();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
//        collisionMapRenderer.setView(camera);
//        collisionMapRenderer.render();

        game.batch.begin();
        player.render(game.batch);
        game.batch.end();

        if (RENDER_DEBUG)
            renderDebug();

        debugRenderer.render(world, box2DCamera.combined);
    }

    private void doPhysicsStep(float deltaTime) {
        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= TIME_STEP) {
            Gdx.app.log("FPS", (1f/frameTime)+"");
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
        int newHeight = DESIRED_RENDER_HEIGHT;
        int newWidth = DESIRED_RENDER_WIDTH;
        if (((double) width)/DESIRED_RENDER_WIDTH < ((double) height)/DESIRED_RENDER_HEIGHT)
            newHeight = (int) (height * (((double) DESIRED_RENDER_WIDTH) / ((double) width)));
        else
            newWidth = (int) (width * (((double) DESIRED_RENDER_HEIGHT) / ((double) height)));

        camera.setToOrtho(false, newWidth, newHeight);
        box2DCamera.setToOrtho(false, newWidth/TO_PIXELS, newHeight/TO_PIXELS);
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

    private void renderDebug()
    {
        shapeRenderer.setProjectionMatrix(camera.combined);
        renderShape2Ds(goals, Color.CYAN);
        renderShape2Ds(holes, Color.RED);
    }

    private void renderShape2Ds(ArrayList<Shape2D> shapes, Color color)
    {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(color);
        for (Shape2D s : shapes)
        {
            if (s instanceof Rectangle)
            {
                shapeRenderer.rect(((Rectangle) s).getX(), ((Rectangle) s).getY(), ((Rectangle) s).width, ((Rectangle) s).height);
            }
            else if (s instanceof Circle)
            {
                shapeRenderer.circle(((Circle) s).x - ((Circle) s).radius/2, ((Circle) s).y - ((Circle) s).radius/2, ((Circle) s).radius);
            }
            else if (s instanceof Ellipse)
            {
                shapeRenderer.ellipse(((Ellipse) s).x - ((Ellipse) s).width/2, ((Ellipse) s).y - ((Ellipse) s).height/2, ((Ellipse) s).width, ((Ellipse) s).height);
            }
            else if (s instanceof Polygon)
            {
                shapeRenderer.polygon(((Polygon) s).getTransformedVertices());
            }
            else if (s instanceof Polyline)
            {
                shapeRenderer.polygon(((Polyline) s).getTransformedVertices());
            }
        }
        shapeRenderer.end();
    }

    public void lose() {
        game.setScreen(new RestartScreen(game));
    }

    public void resetLevel() {
        player.setPosition(playerStart);
        player.setVelocity(new Vector2(0,0));
    }
}
