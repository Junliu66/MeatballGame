package com.meat;

import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import javafx.util.Pair;

import java.util.ArrayList;


public class FixedPathEnemy extends Enemy {

    Vector2 lastStartPoint;
    Pair<Vector2, Float> currentTrajectory;
    float distSquare;
    int currentIndex;
    ArrayList<Pair<Vector2, Float>> path;

    public FixedPathEnemy(){

    }

    public FixedPathEnemy(Vector2 initialPos, World world, float speed, Player player,
                          ArrayList<Pair<Vector2, Float>> path){
        super(initialPos, world, speed, player);

        this.path = path;
        currentIndex = -1;
        if (path.size() >= 1){
            setNextTrajectory();
        }
        else{
            //do some error stuff
        }

    }

    public FixedPathEnemy(World world, float speed, Player player, PolygonMapObject path){
        super(new Vector2(), world, speed, player);
        Polygon polygon = path.getPolygon();
        float[] vertices = polygon.getTransformedVertices();
        Vector2 initialPos = new Vector2(vertices[0]/MeatGame.TO_PIXELS, vertices[1]/ MeatGame.TO_PIXELS);
        body.getPosition().set(initialPos);
    }

    public void update(){
        /*
        Vector2 currentPosition = body.getPosition();

        float currentDistance = (float)(Math.pow(currentPosition.x - lastStartPoint.x,2) +
                                        Math.pow(currentPosition.y - lastStartPoint.y,2));
        if (currentDistance >= distSquare){
            setNextTrajectory();
        }
        */
    }

    public void setNextTrajectory(){
        currentIndex = (currentIndex + 1) % path.size();
        currentTrajectory = path.get(currentIndex);

        lastStartPoint = new Vector2(body.getPosition().x, body.getPosition().y);
        Vector2 currentForce = currentTrajectory.getKey().scl(speed);
        distSquare = (float)Math.pow(currentTrajectory.getValue(), 2);
        body.setLinearVelocity(currentForce.scl(speed));
    }


    public void setPath(){

    }

}
