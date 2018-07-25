package com.iostest.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private float x;
	private float y;
	private int xDir = 1;
	private int yDir = 1;
	private long createTime;
	private long lastLogTime;
	private long nextHideDialogTime;
	private long lastGCTime;
	private Array<String> strings = new Array<String>();
	
	private INativeDialog dialog = null;
	private Random rand = new Random();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		createTime = System.currentTimeMillis();
		
		rand.setSeed(1l);
		nextHideDialogTime = System.currentTimeMillis() + rand.nextInt(1000);
	}

	@Override
	public void render () {
		if(System.currentTimeMillis() - lastGCTime > 100) {
			causeGC();
			lastGCTime = System.currentTimeMillis();
		}
		
		if(dialog != null && System.currentTimeMillis() > nextHideDialogTime) {
			dialog.hideDialog();
			dialog.showDialog();
			nextHideDialogTime = System.currentTimeMillis() + rand.nextInt(1000);
		}
		
		if(System.currentTimeMillis() - lastLogTime > 1000) {
			logAliveTime();
			lastLogTime = System.currentTimeMillis();
		}
		
		updateImagePosition();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}
	
	private void updateImagePosition() {
		float delta = Gdx.graphics.getDeltaTime();
		x += Gdx.graphics.getWidth() * delta * 0.2f * xDir;
		y += Gdx.graphics.getHeight() * delta * 0.2f * yDir;
		
		if(x > Gdx.graphics.getWidth() - img.getWidth() || x < 0) {
			xDir *= -1;
			x = MathUtils.clamp(x, 0, (float)Gdx.graphics.getWidth() - img.getWidth());
		}
		if(y > Gdx.graphics.getHeight() - img.getHeight() || y < 0) {
			yDir *= -1;
			y = MathUtils.clamp(y, 0, (float)Gdx.graphics.getHeight() - img.getHeight());
		}
	}
	
	private void logAliveTime() {
		int timeSinceCreate = (int)((System.currentTimeMillis() - createTime) / 1000);
		Gdx.app.log("MyGdxGame", "Alive time: " + timeSinceCreate + " sec");
		lastLogTime = System.currentTimeMillis();
	}
	
	private void causeGC() {
		strings.clear();
		
		for(int i = 0; i<700000; i++) {
			strings.add(String.valueOf(i));
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public INativeDialog getDialog() {
		return dialog;
	}

	public void setDialog(INativeDialog dialog) {
		this.dialog = dialog;
	}
}
