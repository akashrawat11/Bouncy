package com.mygdx.gamedemo.Tools;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.gamedemo.Screens.PlayScreen;
import com.mygdx.gamedemo.Sprites.Player;
import com.mygdx.gamedemo.Sprites.coins;
import com.mygdx.gamedemo.Sprites.ground;
import com.mygdx.gamedemo.Sprites.upperground;

public class WorlContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Fixture fixa = contact.getFixtureA();
        Fixture fixb = contact.getFixtureB();
        if (fixa.getUserData() instanceof Player && fixb.getUserData() instanceof coins){
            ((coins) fixb.getUserData()).setPosition(1,1);
            PlayScreen.cointracker += 10;
        }
        if (fixb.getUserData() instanceof Player && fixa.getUserData() instanceof coins){
            ((coins) fixa.getUserData()).setPosition(1,1);
            PlayScreen.cointracker += 10;
        }
        if (fixb.getUserData() instanceof Player && (fixa.getUserData() instanceof ground || fixa.getUserData() instanceof upperground)){
            ((Player) fixb.getUserData()).isdead = true;
            ((Player) fixb.getUserData()).whendead();
        }
        if (fixa.getUserData() instanceof Player && (fixb.getUserData() instanceof ground || fixb.getUserData() instanceof upperground)){
            ((Player) fixa.getUserData()).isdead = true;
            ((Player) fixa.getUserData()).whendead();
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
