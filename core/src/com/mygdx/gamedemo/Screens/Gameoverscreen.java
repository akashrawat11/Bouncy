package com.mygdx.gamedemo.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gamedemo.gamedemo;

public class Gameoverscreen implements Screen {
    private gamedemo game;
    private OrthographicCamera gameovercam;
    private Viewport gameoverport;
    private int score;
    private Texture overtexture,playbutton,exitbutton;
    public Gameoverscreen(gamedemo game,int highscore){
        this.game = game;
        this.score = highscore;
        gameovercam = new OrthographicCamera();
        gameoverport = new FitViewport(900,480,gameovercam);
        gameovercam.position.set(new Vector3(gameoverport.getWorldWidth()/2,gameoverport.getWorldHeight()/2,0));
        overtexture = new Texture("gameoverscreen.png");
        playbutton = new Texture("retrybutton.png");
        exitbutton = new Texture("exitbutton.png");
    }



    public void update(float dt){
        if (Gdx.input.justTouched()){
            game.setScreen(new PlayScreen(game,score));
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gameovercam.combined);
        game.batch.begin();
        game.batch.draw(overtexture,0,0);
        game.batch.draw(playbutton,150,150,200,150);
        game.batch.draw(exitbutton,550,150,200,150);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gameoverport.update(width,height);
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

    }
}
