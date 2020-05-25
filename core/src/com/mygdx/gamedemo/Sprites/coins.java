package com.mygdx.gamedemo.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gamedemo.gamedemo;

public class coins extends Sprite {
    public World world;
    public Body coinbody;
    public Vector2 position;
    public Texture cointexture;

    public coins(World world, Vector2 position) {
        this.position = position;
        this.world = world;
        definecoin();
        cointexture = new Texture("coin.png");
        setRegion(cointexture);
        setBounds(position.x - this.getWidth()/2,position.y- this.getHeight()/2,20/gamedemo.PPM,20/gamedemo.PPM);
    }
    public void update(float dt){
        setPosition(coinbody.getPosition().x - this.getWidth()/2,coinbody.getPosition().y - this.getWidth()/2);
        setRegion(cointexture);
        setBounds(coinbody.getPosition().x - this.getWidth()/2 ,coinbody.getPosition().y - this.getHeight()/2,20/gamedemo.PPM,20/gamedemo.PPM);
    }
    public void definecoin(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(position);
        bdef.type = BodyDef.BodyType.StaticBody;
        coinbody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        Rectangle c  = new Rectangle(0,0,10/gamedemo.PPM,10/gamedemo.PPM);
        shape.setAsBox(c.getWidth(),c.getHeight());
        fdef.shape = shape;
        fdef.isSensor = true;
        coinbody.createFixture(fdef).setUserData(this);
    }

}
