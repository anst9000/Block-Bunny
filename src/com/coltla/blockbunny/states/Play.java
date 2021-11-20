package com.coltla.blockbunny.states;

import static com.coltla.blockbunny.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.coltla.blockbunny.handlers.GameStateManager;
import com.coltla.blockbunny.main.Game;

public class Play extends GameState {

	private World world;
	private Box2DDebugRenderer box2dDebugRenderer;

	private OrthographicCamera b2dCamera;

	public Play(GameStateManager gsm) {
		super(gsm);

		world = new World(new Vector2(0, -9.81f), true);
		box2dDebugRenderer = new Box2DDebugRenderer();

		// Create platform
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(160 / PPM, 120 / PPM);
		bodyDef.type = BodyType.StaticBody;

		Body body = world.createBody(bodyDef);

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(50 / PPM, 5 / PPM);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		body.createFixture(fixtureDef);

		// Create falling box
		bodyDef.position.set(160 / PPM, 200 / PPM);
		bodyDef.type = BodyType.DynamicBody;
		body = world.createBody(bodyDef);

		polygonShape.setAsBox(5 / PPM, 5 / PPM);
		fixtureDef.shape = polygonShape;
		body.createFixture(fixtureDef);

		// Set up the box2D camera
		b2dCamera = new OrthographicCamera();
		b2dCamera.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_WIDTH / PPM);
	}

	@Override
	public void handleInput() {

	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
	}

	@Override
	public void render() {
		// Clear screen
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Draw box2D world
		box2dDebugRenderer.render(world, b2dCamera.combined);
	}

	@Override
	public void dispose() {

	}
}
