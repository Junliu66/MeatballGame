package com.meat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import java.util.ArrayList;

public class ControlMenu extends Stage implements Screen {

    final MainGame game;

    OrthographicCamera camera;

    ArrayList<Label> menuText;
    Table displayTable;

    int viewportWidth = 800;
    int viewportHeight = 800;

    float textX = viewportWidth / 4.0f;
    float middleX = (viewportWidth / 2.0f);
    String controlText = "Controls";
    String upText = "Up: ";
    String downText = "Down: ";
    String leftText = "Left: ";
    String rightText = "Right: ";

    ArrayList<TextField> textFields;
    TextField upField;
    TextField downField;
    TextField leftField;
    TextField rightField;

    TextButton playerUp;
    TextButton playerDown;
    TextButton playerLeft;
    TextButton playerRight;



    public ControlMenu(MainGame game){
        this.game = game;
        menuText = new ArrayList<Label>();
        textFields = new ArrayList<TextField>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, viewportWidth, viewportHeight);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        displayTable = new Table();
        //create a box that the options menu will be drawn on


        Skin labelSkin = new Skin(Gdx.files.internal("uiskin.json"));

        Label controlLabel = new Label(controlText, labelSkin);


        Label upLabel = new Label(upText, labelSkin);
        upLabel.setPosition(textX, 300f);
        menuText.add(upLabel);
        upField = new TextField("", labelSkin);
        textFields.add(upField);

        Label downLabel = new Label(downText, labelSkin);
        menuText.add(downLabel);
        downField = new TextField("", labelSkin);
        textFields.add(downField);

        Label leftLabel = new Label(leftText, labelSkin);
        menuText.add(leftLabel);
        leftField = new TextField("", labelSkin);
        textFields.add(leftField);

        Label rightLabel = new Label(rightText, labelSkin);
        menuText.add(rightLabel);
        rightField = new TextField("", labelSkin);
        textFields.add(rightField);

        displayTable.add(controlLabel);
        displayTable.row();
        for(int i = 0; i < menuText.size(); i++){
            //this.addActor(label);
            Label label = menuText.get(i);
            TextField textField = textFields.get(i);
            displayTable.add(label);
            displayTable.add(textField);
            displayTable.row();
        }

        this.addActor(displayTable);
        displayTable.setFillParent(true);
        displayTable.setBackground();
    }


    public void resize(int x, int y){

    }

    public void pause(){

    }

    public void resume(){

    }

    public void render(float delta){
        camera.update();
        //Gdx.gl.glClearColor(1, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //draw a box that is smaller than the splash screen and centered, then draw the screen
        //or have the screen draw everything
        this.draw();
        //displayTable.setVisible(true);
    }

    public void show(){

    }

    public void hide(){

    }

    public void dispose(){

    }
}
