package com.meat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import javafx.util.Pair;

import java.util.ArrayList;


public class MeatGame implements Screen {
    TiledMap tiledMap;//, collisionMap;
    TiledMapRenderer tiledMapRenderer;//, collisionMapRenderer;
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
    private static boolean RENDER_DEBUG = true;
    public ArrayList<Shape2D> holes;
    public ArrayList<Shape2D> goals;
    public ArrayList<Shape2D> dies;
    private ShapeRenderer shapeRenderer;
    private Vector2 playerStart;

    private ArrayList<Enemy> enemies;
    private ArrayList<Pickup> pickups;
    private ArrayList<Pickup> finishedPickups;

    public static float lerp = 5.0f;

    private static final int TOTAL_BLOOD_POINTS = 5;
    private int currentBloodPoint;
    public MeatGame(final MainGame game) {
        this.game = game;
        // TODO currentBloodPoint-- if hit any blood-losing object
        currentBloodPoint = TOTAL_BLOOD_POINTS;
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
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        world = new World(new Vector2(), true);
        holes = new ArrayList<Shape2D>();
        goals = new ArrayList<Shape2D>();
        dies = new ArrayList<Shape2D>();
        pickups = new ArrayList<Pickup>();
        finishedPickups = new ArrayList<Pickup>();
        playerStart = new Vector2(0,0);
        player = new Player(new Vector2(playerStart.x, playerStart.y), collisionLayer, 24f, world, true);

        // parse Tiled objects
        for (MapLayer layer : tiledMap.getLayers())
            if (layer instanceof MapLayer && layer.getObjects().getCount() > 0)
                parseTiledObjects(layer);

        player.setPosition(playerStart);

        enemies = new ArrayList<Enemy>();

        //*****Testing Enemy AI **** Comment out if needed
        ArrayList<Pair<Vector2, Float>> testPath = new ArrayList<Pair<Vector2, Float>>();

        Vector2 leftVector = new Vector2(-1, 0);
        Float leftDist = new Float(1.0f);
        Pair<Vector2, Float> leftPair = new Pair(leftVector, leftDist);
        testPath.add(leftPair);

        Vector2 upVector = new Vector2(0, 1);
        Float upDist = new Float(1.0f);
        Pair<Vector2, Float> upPair = new Pair(upVector, upDist);
        testPath.add(upPair);

        Vector2 rightVector = new Vector2(1, 0);
        Float rightDist = new Float(1.0f);
        Pair<Vector2, Float> rightPair = new Pair(rightVector, rightDist);
        testPath.add(rightPair);

        Vector2 downVector = new Vector2(0, -1);
        Float downDist = new Float(1.0f);
        Pair<Vector2, Float> downPair = new Pair(downVector, downDist);
        testPath.add(downPair);

        FixedPathEnemy newEnemy = new FixedPathEnemy(
                new Vector2(playerStart.x + 2, playerStart.y + 1),
                world,
                1.0f,
                player,
                testPath
        );
        enemies.add(newEnemy);
        //***** End testing Enemy ****

        //Gdx.input.setInputProcessor(this);
        debugRenderer = new Box2DDebugRenderer();

    }


    @Override
    public void render(float dt) {

        doPhysicsStep(dt);
        player.update(this, dt);
        for (int i = 0; i < enemies.size(); i++) {
            Enemy currEnemy = enemies.get(i);
            currEnemy.update();
        }

        Vector2 position = new Vector2(camera.position.x, camera.position.y);
        Vector2 box2dposition = new Vector2(box2DCamera.position.x, box2DCamera.position.y);
        Vector2 player_pos = player.getPosition().scl(TO_PIXELS);

        position.x += (player_pos.x - position.x) * lerp * dt;
        position.y += (player_pos.y - position.y) * lerp * dt;

        box2dposition.x += (player.getPosition().x - box2dposition.x) * lerp * dt;
        box2dposition.y += (player.getPosition().y - box2dposition.y) * lerp * dt;

        camera.position.set(position, 0);
        box2DCamera.position.set(box2dposition, 0);
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

        game.batch.begin();
        player.render(game.batch);
        for (int i = 0; i < pickups.size(); i++)
            if (pickups.get(i).render(game.batch, dt) == Pickup.Status.FINISHED) {
                finishedPickups.add(pickups.get(i));
                pickups.remove(i);
            }

        game.batch.end();

        if (RENDER_DEBUG)
            renderDebug();

        debugRenderer.render(world, box2DCamera.combined);
        displayBloodPoints();

    }

    private void displayBloodPoints() {
        Stage bpStage = new Stage(new ScreenViewport(), game.batch);
        Texture myTexture = new Texture(Gdx.files.internal("blod.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable blood = new TextureRegionDrawable(myTextureRegion);

        Texture emptyblodTex = new Texture(Gdx.files.internal("emptyblod.png"));
        TextureRegion emptyblodTexRegion = new TextureRegion(emptyblodTex);
        TextureRegionDrawable emptyBood = new TextureRegionDrawable(emptyblodTexRegion);

        int curXPosition = 15;
        for (int i = 0; i < currentBloodPoint; i++) {
            Button button = new ImageButton(blood);
            button.setPosition(15 * i + curXPosition, 30, 0);
            bpStage.addActor(button);
        }
        for (int i = currentBloodPoint; i < TOTAL_BLOOD_POINTS; i++) {
            Button button = new ImageButton(emptyBood);
            button.setPosition(15 * i + curXPosition, 30, 0);
            bpStage.addActor(button);
        }
        bpStage.draw();
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


    private void parseTiledObjects(MapLayer objectLayer) {
        BodyDef wallDef = new BodyDef();
        wallDef.type = BodyDef.BodyType.StaticBody;
        wallDef.position.set(0, 0);
        Body wall = world.createBody(wallDef);

        Gdx.app.log("num objects", "" + objectLayer.getObjects().getCount());
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
                } else if (obj instanceof PolygonMapObject) {
                    PolygonShape polygon = makePolygonShape(world, ((PolygonMapObject) obj).getPolygon());
                    BodyDef bodyDef = new BodyDef();
                    bodyDef.type = BodyDef.BodyType.StaticBody;
                    bodyDef.position.set(((PolygonMapObject) obj).getPolygon().getX()/TO_PIXELS, ((PolygonMapObject) obj).getPolygon().getY()/TO_PIXELS);
                    Body body = world.createBody(bodyDef);
                    FixtureDef fixtureDef = new FixtureDef();
                    fixtureDef.shape = polygon;
                    body.createFixture(fixtureDef);
                    polygon.dispose();
                } else {
                    Gdx.app.log("Shape not recognized", obj.getClass().getName());
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
            } else if (obj.getName().equals("pepper")) {
                pickups.add(new Pepper(((RectangleMapObject) obj).getRectangle().getX(), ((RectangleMapObject) obj).getRectangle().getY(), player));
            }
            else if(obj.getName().equals("die")) {
                if (obj instanceof RectangleMapObject) {
                    Rectangle rect = ((RectangleMapObject) obj).getRectangle();
                    dies.add(rect);
                } else if (obj instanceof CircleMapObject) {
                    Circle circle = new Circle();
                    circle.radius = ((CircleMapObject) obj).getCircle().radius / 2f;
                    circle.setPosition(((CircleMapObject) obj).getCircle().x, ((CircleMapObject) obj).getCircle().y);
                    dies.add(circle);
                } else if (obj instanceof EllipseMapObject) {
                    Ellipse ellipse = ((EllipseMapObject) obj).getEllipse();
                    ellipse.setPosition(ellipse.x + ellipse.width / 2f, ellipse.y + (ellipse.height / 2f));
                    dies.add(ellipse);
                } else if (obj instanceof PolygonMapObject) {
                    Polygon polygon = ((PolygonMapObject) obj).getPolygon();
                    polygon.setPosition(((PolygonMapObject) obj).getPolygon().getX(), ((PolygonMapObject) obj).getPolygon().getY());
                    polygon.setRotation(((PolygonMapObject) obj).getPolygon().getRotation());
                    dies.add(polygon);
                } else if (obj instanceof PolylineMapObject) {
                    Polygon polygon = new Polygon(((PolylineMapObject) obj).getPolyline().getVertices());
                    polygon.setPosition(
                            ((PolylineMapObject) obj).getPolyline().getX(),
                            ((PolylineMapObject) obj).getPolyline().getY());
                    polygon.setRotation(((PolylineMapObject) obj).getPolyline().getRotation());
                    dies.add(polygon);
                } else {
                    Gdx.app.log("Shape not recognized", "" + obj.getClass().getName());
                }
            }
        }
    }

    private PolygonShape makePolygonShape(World world, Polygon poly) {
        PolygonShape polygonShape = new PolygonShape();
        float[] vertices = poly.getVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            worldVertices[i] = vertices[i] / TO_PIXELS ;
            }

        polygonShape.set(worldVertices);

        return polygonShape;
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
        currentBloodPoint = TOTAL_BLOOD_POINTS;
        game.setScreen(new RestartScreen(game));
    }

    public void congrats() {
        currentBloodPoint = TOTAL_BLOOD_POINTS;
        game.setScreen(new CongratsScreen(game));
    }

    public void reduceBlood() {
        currentBloodPoint--;
        if (currentBloodPoint <= 0) {
            lose();
        }
        resetLevel();
    }

    public void resetLevel() {
        player.clearModifiers();
        player.setPosition(playerStart);
        player.setVelocity(new Vector2(0, 0));
        if (finishedPickups.size() > 0) {
            for (int i = 0; i < finishedPickups.size(); i++) {
                pickups.add(finishedPickups.get(i));
                pickups.remove(i);
            }
        }
    }
}
