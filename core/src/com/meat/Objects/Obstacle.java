package com.meat.Objects;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Obstacle {

    private Vector2 restartPoint;
    private ArrayList<Shape2D> obstacleArea;

    public Obstacle(Vector2 restartPoint, ArrayList<Shape2D> obstacleArea) {
        this.restartPoint = restartPoint;
        this.obstacleArea = obstacleArea;
    }

    public Vector2 getRestartPoint() {
        return restartPoint;
    }

    public void setRestartPoint(Vector2 restartPoint) {
        this.restartPoint = restartPoint;
    }

    public ArrayList<Shape2D> getObstacleArea() {
        return obstacleArea;
    }

    public void setObstacleArea(ArrayList<Shape2D> obstacleArea) {
        this.obstacleArea = obstacleArea;
    }

}
