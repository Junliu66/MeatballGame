package com.meat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class MeatGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Player player;
	private OrthographicCamera camera;
	private World world;
	private float accumulator;
    private static float TIME_STEP = 1/60f;
    private static int VELOCITY_ITERATIONS = 6;
    private static int POSITION_ITERATIONS = 2;
    private Box2DDebugRenderer debugRenderer;
    //public static float TO_METERS = 1f;//0.02f;
    public static float TO_PIXELS = 50f;

	@Override
	public void create () {
	    accumulator = 0f;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 16, 12);
        world = new World(new Vector2(), true);

        debugRenderer = new Box2DDebugRenderer();

        batch = new SpriteBatch();

        player = new Player(new Vector2(4,10), 200f, world);

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
	public void render () {
	    float dt = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(player.getTex(), player.getPosition().x * TO_PIXELS - player.getTex().getRegionWidth()/2, player.getPosition().y * TO_PIXELS - player.getTex().getRegionHeight()/2);
		batch.end();
		camera.update();
        debugRenderer.render(world, camera.combined);

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
	public void dispose () {
		batch.dispose();
		player.dispose();
	}
}
