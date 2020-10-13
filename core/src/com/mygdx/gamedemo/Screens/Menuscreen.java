package com.mygdx.gamedemo.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gamedemo.gamedemo;

public class Menuscreen implements Screen {
    public gamedemo game;
    private OrthographicCamera menucam;
    private Viewport menuviewport;
    private Texture menutexture,playbutton,exitbutton;
    private int highscore;
    private int h;

    public Menuscreen(gamedemo game, int highscore){
        this.game = game;
        this.highscore = highscore;
        menucam = new OrthographicCamera();
        menuviewport = new FitViewport(900,480,menucam);
        menucam.position.set(menuviewport.getWorldWidth()/2,menuviewport.getWorldHeight()/2,0);
        menutexture = new Texture("endscreen.png");
        playbutton = new Texture("playbutton.png");
        exitbutton = new Texture("exitbutton.png");
    }


    public void update(float dt){
        if (Gdx.input.justTouched()){
            game.setScreen(new PlayScreen(game,highscore));
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
        game.batch.setProjectionMatrix(menucam.combined);
        game.batch.begin();
        game.batch.draw(menutexture,0,0);
        game.batch.draw(playbutton,150,150,200,150);
        game.batch.draw(exitbutton,550,150,200,150);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        menuviewport.update(width,height);
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
