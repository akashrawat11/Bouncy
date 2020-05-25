package com.mygdx.gamedemo.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gamedemo.Sprites.Obstacles;
import com.mygdx.gamedemo.Sprites.coins;
import com.mygdx.gamedemo.Sprites.upperground;
import com.mygdx.gamedemo.Tools.WorlContactListener;
import com.mygdx.gamedemo.Sprites.Player;
import com.mygdx.gamedemo.Sprites.ground;
import com.mygdx.gamedemo.gamedemo;

import java.util.ArrayList;
import java.util.Random;

public class PlayScreen implements Screen {

    private BitmapFont score,highscore;
    public static int highscoretracker = 0, cointracker = 0;
    private gamedemo game;
    public Texture text, background,ground;
    private OrthographicCamera gamecam,uicam;
    private Viewport gameport,uiviewport;
    private World world;
    private Box2DDebugRenderer b2dr;
    private ArrayList<com.mygdx.gamedemo.Sprites.ground> obsarray;

    private ArrayList<Obstacles> stairsarray;
    private Obstacles kanobi;

    private Player Pone;
    private com.mygdx.gamedemo.Sprites.ground obs;
    private Random random;
    public float curtime;
    public ArrayList<coins> coinsarray;
    public ArrayList<upperground> uppergroundarray;
    public boolean isdead = false;


    public PlayScreen(gamedemo game,int highestscore) {
        cointracker = 0;
        this.game = game;
        text = new Texture("stairs.png");
        gamecam = new OrthographicCamera();
        uicam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gameport = new FitViewport(900/gamedemo.PPM,480/gamedemo.PPM,gamecam);
        uiviewport = new FitViewport(900,480);
        gamecam.position.set(new Vector3(gameport.getWorldWidth()/2,gameport.getWorldHeight()/2,0));

        uicam.position.set(new Vector3(uiviewport.getWorldWidth()/2,uiviewport.getWorldHeight()/2,0));

        world = new World(new Vector2(0,-250/gamedemo.PPM),true);
        world.setContactListener(new WorlContactListener());
        background = new Texture("game background.png");
        Pone = new Player(world,this);
        b2dr = new Box2DDebugRenderer();
        obsarray = new ArrayList<com.mygdx.gamedemo.Sprites.ground>();
        stairsarray = new ArrayList<Obstacles>();
        score = new BitmapFont();
        highscore = new BitmapFont();
        highscoretracker = highestscore;
        random = new Random();
        uppergroundarray = new ArrayList<upperground>();
        coinsarray = new ArrayList<coins>();
        for (int i = 0;i<=10;i++){
            coins coin = new coins(world,new Vector2(-1000,-1000));
            coinsarray.add(coin);
        }
        for(int i = 0;i <= 10; i++){
            obs = new ground(world,new Vector2((200*i - 800)/gamedemo.PPM,0));
            obsarray.add(obs);
            upperground u = new upperground(world,new Vector2((200*i - 800)/gamedemo.PPM,500/gamedemo.PPM));
            uppergroundarray.add(u);
        }
        for(int i = 0;i <= 10; i++){
            int n = random.nextInt(200);
            float y = (-100 + n);
            kanobi = new Obstacles(world,new Vector2((400*i -800)/gamedemo.PPM,(240 + y)/gamedemo.PPM));
            stairsarray.add(kanobi);
        }
        }


    public void handleInput(float dt){
        if (Pone.isdead == false){
            if(Pone.playerbody.getLinearVelocity().x < 2f)
                Pone.playerbody.applyLinearImpulse(new Vector2(0.02f,0),Pone.playerbody.getWorldCenter(),true);
            Pone.update(dt);
            if(Gdx.input.justTouched()){
                Pone.playerbody.applyLinearImpulse(new Vector2(0,2.5f),Pone.playerbody.getWorldCenter(),true);
            }
        }
        }


    public void update(float dt) {
        curtime += dt;
        handleInput(dt);
        world.step(1 / 60f, 6, 2);
        if (Pone.isdead == false){
            gamecam.position.x = Pone.playerbody.getPosition().x + 100 / gamedemo.PPM;
            gamecam.update();
        }

        int n = random.nextInt(150);
        float y = (-75 + n);
        for (com.mygdx.gamedemo.Sprites.ground obs : obsarray) {
            if (gamecam.position.x > obs.obstaclebody.getPosition().x + obs.getWidth() + 400 / gamedemo.PPM) {
                obs.obstaclebody.setTransform(new Vector2(obs.obstaclebody.getPosition().x + (200 * 11) / gamedemo.PPM, obs.obstaclebody.getPosition().y), 0);
                obs.update(dt);
            }
        }
        if (Pone.playerbody.getLinearVelocity().y < -2f) {
            Pone.playerbody.applyLinearImpulse(new Vector2(0, 0.5f), Pone.playerbody.getWorldCenter(), true);
        }
        if (Pone.playerbody.getLinearVelocity().y > 2f) {
            Pone.playerbody.applyLinearImpulse(new Vector2(0, -0.5f), Pone.playerbody.getWorldCenter(), true);
        }
        int q = 0;
        for (Obstacles obs : stairsarray) {
            if (gamecam.position.x > obs.obstaclebody.getPosition().x + obs.getWidth() + 400 / gamedemo.PPM) {
                obs.obstaclebody.setTransform(new Vector2(obs.obstaclebody.getPosition().x + (400 * 11) / gamedemo.PPM, (250 + y) / gamedemo.PPM), 0);
                obs.update(dt);
            }

            for (upperground up : uppergroundarray) {
                if (gamecam.position.x > up.obstaclebody.getPosition().x + up.getWidth() + 400 / gamedemo.PPM) {
                    up.obstaclebody.setTransform(new Vector2(up.obstaclebody.getPosition().x + (200 * 11) / gamedemo.PPM, up.obstaclebody.getPosition().y), 0);
                    up.update(dt);
                }
            }
            if (curtime > 10) {
                for (int i = 0; i < 11; i++) {
                    if (stairsarray.get(i).obstaclebody.getPosition().x > gamecam.position.x + 500 / gamedemo.PPM) {
                        int ran = random.nextInt(50);
                        if (ran > 25 ) {
                            if (stairsarray.get(i).obstaclebody.getPosition().y + 100 / gamedemo.PPM < 400/gamedemo.PPM){
                                coinsarray.get(i).coinbody.setTransform(new Vector2(stairsarray.get(i).obstaclebody.getPosition().x, stairsarray.get(i).obstaclebody.getPosition().y + 100 / gamedemo.PPM), 0);
                            }
                        else  if(stairsarray.get(i).obstaclebody.getPosition().y - 100 / gamedemo.PPM > 100/gamedemo.PPM){
                            coinsarray.get(i).coinbody.setTransform(new Vector2(stairsarray.get(i).obstaclebody.getPosition().x, stairsarray.get(i).obstaclebody.getPosition().y - 100 / gamedemo.PPM), 0);
                            }
                        }
                        coinsarray.get(i).update(dt);
                    }
                }
                curtime = 0;
            }

        }
        if ((int)Pone.playerbody.getPosition().x + cointracker > highscoretracker){
            highscoretracker = (int) Pone.playerbody.getPosition().x + cointracker;
            gamedemo.highestscore = highscoretracker;
        }
        if (Pone.isdead == true && Pone.playerbody.getPosition().y > 75/gamedemo.PPM){
            if (Pone.playerbody.getLinearVelocity().y > -2f) {
                Pone.whendead();
            }
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

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.batch.draw(background,gamecam.position.x - gameport.getWorldWidth()/2,gamecam.position.y - gameport.getWorldHeight()/2,gameport.getWorldWidth(),gameport.getWorldHeight());
        for (com.mygdx.gamedemo.Sprites.ground obs: obsarray)
            obs.draw(game.batch);
        for (Obstacles obs: stairsarray)
            obs.draw(game.batch);
        for (upperground up: uppergroundarray)
            up.draw(game.batch);
        Pone.draw(game.batch);
        for (coins coin : coinsarray){
            coin.draw(game.batch);
        }
        game.batch.end();
        game.batch.setProjectionMatrix(uicam.combined);
        game.batch.begin();
        score.getData().setScale(4);
        highscore.getData().setScale(4);
        score.draw(game.batch, "SCORE : "+Integer.toString((int)Pone.playerbody.getPosition().x + cointracker),uicam.position.x ,uicam.position.y + gamedemo.PPM );
        highscore.draw(game.batch,"HIGHSCORE : " + Integer.toString(gamedemo.highestscore),uicam.position.x-13*gamedemo.PPM, uicam.position.y + gamedemo.PPM);
        game.batch.end();
        if (Pone.isdead == true){
            game.setScreen(new Gameoverscreen(game,highscoretracker));
        }
        //b2dr.render(world,gamecam.combined);
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);
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
