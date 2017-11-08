package com.meat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MeatGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Player player;
	private OrthographicCamera camera;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		player = new Player(new Texture("meatball.png"), new Vector2(100,100), 2, 3, 4);
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(player.getTex(), player.getLocation().x, player.getLocation().y);
		batch.end();

		camera.update();

		Vector2 force = new Vector2();
		boolean up, down, left, right;
		up = (Gdx.input.isKeyPressed(Keys.UP));
		down = (Gdx.input.isKeyPressed(Keys.DOWN));
		left = (Gdx.input.isKeyPressed(Keys.LEFT));
		right = (Gdx.input.isKeyPressed(Keys.RIGHT));
		if (up&&down)
			force.y = 0;
		else if (up)
			force.y = 1;
		else if (down)
			force.y = -1;
		if (left&&right)
			force.x = 0;
		else if (left)
			force.x = -1;
		else if (right)
			force.x = 1;
		player.setForce(force);
		player.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.dispose();
	}
}
