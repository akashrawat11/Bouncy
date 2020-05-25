package com.mygdx.gamedemo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gamedemo.Screens.PlayScreen;
import com.mygdx.gamedemo.gamedemo;

import java.util.ArrayList;

public class Player extends Sprite {
    public World world;
    public Body playerbody;
    public Vector2 playerposition;
    private Texture ball,balldead;
    public boolean isdead = false;

    public Player(World world, PlayScreen screen){
        this.world = world;
        ball = new Texture("ball.png");
        balldead = new Texture("ball explosion.png");
        setBounds(0,0,30/gamedemo.PPM,30/gamedemo.PPM);
        definePlayer();
    }
    public void update(float dt){
        setPosition(playerbody.getPosition().x,playerbody.getPosition().y);
        setBounds(playerbody.getPosition().x - this.getWidth()/2,playerbody.getPosition().y - this.getHeight()/2,30/gamedemo.PPM,30/gamedemo.PPM);
        setRegion(ball);
    }
    public void whendead(){
        setPosition(playerbody.getPosition().x,playerbody.getPosition().y);
        setBounds(playerbody.getPosition().x - this.getWidth()/2,playerbody.getPosition().y - this.getHeight()/2,30/gamedemo.PPM,30/gamedemo.PPM);
        setRegion(balldead);
    }


    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(0,200/gamedemo.PPM);
        playerposition = bdef.position;
        bdef.type = BodyDef.BodyType.DynamicBody;
        playerbody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15/gamedemo.PPM);
        fdef.shape = shape;
        playerbody.createFixture(fdef);


        CircleShape c = new CircleShape();
        c.setRadius(15/gamedemo.PPM);
        fdef.shape = c;
        fdef.isSensor = true;
        playerbody.createFixture(fdef).setUserData(this);

    }

}
