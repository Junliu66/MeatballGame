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
	private Body playerBody;
	private OrthographicCamera camera;
	private World world;
	private float accumulator;
    private float TIME_STEP = 1/60f;
    private int VELOCITY_ITERATIONS = 6;
    private int POSITION_ITERATIONS = 2;


	@Override
	public void create () {
	    accumulator = 0f;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
        batch = new SpriteBatch();

        world = new World(new Vector2(), true);

        player = new Player(new Vector2(100,100), 2, 3, 4, world);

        BodyDef playerDef = new BodyDef();
        playerDef.type = BodyDef.BodyType.KinematicBody;
        playerDef.position.set(new Vector2(100,100));
        playerBody = world.createBody(playerDef);
		CircleShape circle = new CircleShape();
        circle.setRadius(6f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        Fixture fixture = playerBody.createFixture(fixtureDef);
        circle.dispose();
	}

	@Override
	public void render () {
	    float dt = Gdx.graphics.getDeltaTime();

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(player.getTex(), playerBody.getPosition().x, playerBody.getPosition().y);
		batch.end();

		camera.update();

		player.update(playerBody);
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
