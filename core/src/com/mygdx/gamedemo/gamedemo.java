package com.mygdx.gamedemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gamedemo.Screens.Menuscreen;
import com.mygdx.gamedemo.Screens.PlayScreen;

public class gamedemo extends Game {
	public SpriteBatch batch;
	public static final float PPM = 100;
	public static int highestscore = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new Menuscreen(this,highestscore));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
