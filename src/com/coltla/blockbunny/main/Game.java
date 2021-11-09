package com.coltla.blockbunny.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.coltla.blockbunny.handlers.GameStateManager;

public class Game implements ApplicationListener {

	public static final String TITLE = "Block Bunny";
	public static final int V_WIDTH = 320;
	public static final int V_HEIGHT = 240;
	public static final int SCALE = 2;

	public static final float STEP = 1 / 60.0f;
	private float accum;

	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;

	private GameStateManager gsm;

	@Override
	public void create() {
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		gsm = new GameStateManager(this);
	}

	@Override
	public void render() {
		accum += Gdx.graphics.getDeltaTime();

		while (accum >= STEP) {
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
		}
	}

	@Override
	public void dispose() {
	}

	@Override
	public void resize(int w, int h) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	public SpriteBatch getSpriteBatch() {
		return sb;
	}

	public OrthographicCamera getCamera() {
		return cam;
	}

	public OrthographicCamera getHudCamera() {
		return hudCam;
	}

}
