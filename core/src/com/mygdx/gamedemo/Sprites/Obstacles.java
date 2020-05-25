package com.mygdx.gamedemo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gamedemo.gamedemo;

public class Obstacles extends Sprite {
    public World world;
    public Body obstaclebody;
    public Vector2 position;
    public Texture ground;

    public Obstacles(World world, Vector2 position) {
        this.position = position;
        this.world = world;
        defineobstacle();
        ground = new Texture("stairs.png");
        setRegion(ground);
        setBounds(position.x-(200/gamedemo.PPM)/2,position.y-(50/gamedemo.PPM)/2,200/gamedemo.PPM,50/gamedemo.PPM);
    }
    public void update(float dt){
        setPosition(obstaclebody.getPosition().x,obstaclebody.getPosition().y);
        setRegion(ground);
        setBounds(obstaclebody.getPosition().x - (100/gamedemo.PPM),obstaclebody.getPosition().y - (50/gamedemo.PPM)/2,200/gamedemo.PPM,50/gamedemo.PPM);
    }
    public void defineobstacle(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(position);
        bdef.type = BodyDef.BodyType.StaticBody;
        obstaclebody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        Rectangle rect  = new Rectangle(0,0,100/gamedemo.PPM,25/gamedemo.PPM);
        shape.setAsBox(rect.getWidth(),rect.getHeight());
        fdef.shape = shape;
        obstaclebody.createFixture(fdef);
    }
}
