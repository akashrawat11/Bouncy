package com.mygdx.gamedemo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gamedemo.gamedemo;

public class ground extends Sprite {
    public World world;
    public Body obstaclebody;
    public Vector2 position;
    public Texture ground;

    public ground(World world, Vector2 position) {
        this.position = position;
        this.world = world;
        defineobstacle();
        ground = new Texture("cactusground.png");
        setRegion(ground);
        setBounds(position.x-100/gamedemo.PPM,position.y,200/gamedemo.PPM,75/gamedemo.PPM);
}
    public void update(float dt){
        setPosition(obstaclebody.getPosition().x,obstaclebody.getPosition().y);
        setRegion(ground);
        setBounds(obstaclebody.getPosition().x-100/gamedemo.PPM,obstaclebody.getPosition().y,200/gamedemo.PPM,75/gamedemo.PPM);
    }
    public void defineobstacle(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(position);
        bdef.type = BodyDef.BodyType.StaticBody;
        obstaclebody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        Rectangle rect  = new Rectangle(0,0,100/gamedemo.PPM,75/gamedemo.PPM);
        shape.setAsBox(rect.getWidth(),rect.getHeight());
        fdef.shape = shape;
        obstaclebody.createFixture(fdef);

        PolygonShape s = new PolygonShape();
        Rectangle r = new Rectangle(0,0,100/gamedemo.PPM,75/gamedemo.PPM);
        s.setAsBox(r.getWidth(),r.getHeight());
        fdef.shape = s;
        fdef.isSensor = true;
        obstaclebody.createFixture(fdef).setUserData(this);
    }

}
