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

    public FixedPathEnemy(World world, float speed, Player player, PolygonMapObject newPath){
        Polygon polygon = newPath.getPolygon();
        float lastX, lastY;

        float[] vertices = polygon.getTransformedVertices();
        lastX = vertices[0]/MeatGame.TO_PIXELS;
        lastY = vertices[1]/MeatGame.TO_PIXELS;
        Vector2 initialPos = new Vector2(lastX, lastY);
        path = new ArrayList<Pair<Vector2, Float>>();

        for(int i = 2; i < (vertices.length - 1); i+=2){
            float nextX = vertices[i] / MeatGame.TO_PIXELS;
            float nextY = vertices[i+1] / MeatGame.TO_PIXELS;

            float xDiff = nextX - lastX;
            float yDiff = nextY - lastY;
            Vector2 trajectory = new Vector2(xDiff, yDiff);
            float length = (float) Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

            Pair<Vector2, Float> nextPair = new Pair<Vector2, Float>(trajectory.nor(),length);
            path.add(nextPair);
            lastX = nextX;
            lastY = nextY;
        }
        float nextX = initialPos.x;
        float nextY = initialPos.x;

        float xDiff = nextX - lastX;
        float yDiff = nextY - lastY;
        Vector2 trajectory = new Vector2(xDiff, yDiff);
        float length = (float) Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

        Pair<Vector2, Float> nextPair = new Pair<Vector2, Float>(trajectory.nor(),length);
        path.add(nextPair);


        this.speed = speed;
        isAggressive = false;
        playerRef = player;
        worldRef = world;

        createBody(initialPos);

        currentIndex = -1;
        if (path.size() >= 1){
            setNextTrajectory();
        }
        else{
            //do some error stuff
        }
    }

    public void update(){

        Vector2 currentPosition = body.getPosition();

        float currentDistance = (float)(Math.pow(currentPosition.x - lastStartPoint.x,2) +
                                        Math.pow(currentPosition.y - lastStartPoint.y,2));
        if (currentDistance >= distSquare){
            setNextTrajectory();
        }

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
