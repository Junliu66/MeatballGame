package com.meat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

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

    ArrayList<TextButton> textFields;
    TextButton upField;
    TextButton downField;
    TextButton leftField;
    TextButton rightField;
    TextButton blankButton;

    //flags for TextButtons
    TextButton currentClicked;

    float buttonWidth = 100f;
    Button btnBack;
    Texture myTexture;
    TextureRegion myTextureRegion;
    TextureRegionDrawable myTexRegionDrawable;
    Stage stage;
    Image imgBack;

    TextButton playerUp;
    TextButton playerDown;
    TextButton playerLeft;
    TextButton playerRight;



    public ControlMenu(MainGame game){
        this.game = game;
        menuText = new ArrayList<Label>();
        textFields = new ArrayList<TextButton>();

        stage = new Stage(new ScreenViewport());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, viewportWidth, viewportHeight);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        displayTable = new Table();
        //create a box that the options menu will be drawn on


        Skin labelSkin = new Skin(Gdx.files.internal("uiskin.json"));
        blankButton = new TextButton("", labelSkin);
        currentClicked = blankButton;

        Label controlLabel = new Label(controlText, labelSkin);


        Label upLabel = new Label(upText, labelSkin);
        upLabel.setPosition(textX, 300f);
        menuText.add(upLabel);
        upField = new TextButton(Input.Keys.toString(Config.player1Up), labelSkin);
        //upField.addListener();
        textFields.add(upField);

        Label downLabel = new Label(downText, labelSkin);
        menuText.add(downLabel);
        downField = new TextButton(Input.Keys.toString(Config.player1Down), labelSkin);
        textFields.add(downField);

        Label leftLabel = new Label(leftText, labelSkin);
        menuText.add(leftLabel);
        leftField = new TextButton(Input.Keys.toString(Config.player1Left), labelSkin);
        textFields.add(leftField);

        Label rightLabel = new Label(rightText, labelSkin);
        menuText.add(rightLabel);
        rightField = new TextButton(Input.Keys.toString(Config.player1Right), labelSkin);
        textFields.add(rightField);

        upField.addListener(getInputListener());

        displayTable.add(controlLabel);
        displayTable.row();
        Gdx.input.setInputProcessor(this);
        addListener(getInputListener());
        for(int i = 0; i < menuText.size(); i++){
            //this.addActor(label);
            Label label = menuText.get(i);
            TextButton textField = textFields.get(i);
            textField.getStyle().up.setMinWidth(buttonWidth);
            textField.addListener(getChangeControlListener());
            //textField.addListener(getInputListener());
            displayTable.add(label);
            displayTable.add(textField);
            displayTable.row();
        }


        myTexture = new Texture(Gdx.files.internal("btnBack.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnBack = new ImageButton(myTexRegionDrawable);
        btnBack.setColor(0, 0, 0, 0);
        myTexture = new Texture(Gdx.files.internal("btnBack.png"));
        imgBack = new Image(myTexture);
        btnBack.addListener(getBackListener());
        imgBack.setPosition(400, 80, 0);
        btnBack.setPosition(400, 80, 0);

        this.addActor(displayTable);
        displayTable.setFillParent(true);

        this.addActor(imgBack);
        this.addActor(btnBack);

        Texture background = new Texture(Gdx.files.internal("helpScreen.png"));
        displayTable.setBackground(new TextureRegionDrawable(new TextureRegion(background)));

        Gdx.input.setInputProcessor(this);
    }


    public void resize(int x, int y){

    }

    public void pause(){

    }

    public void resume(){

    }

    public void render(float delta){
        camera.update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.act(Gdx.graphics.getDeltaTime());
        this.draw();
        //displayTable.setVisible(true);
    }

    public void show(){

    }

    public void hide(){

    }

    public void dispose(){

    }

    private ClickListener getBackListener() {
        return new ClickListener(){

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                imgBack.setHeight(30);
                imgBack.setWidth(70);
                imgBack.setPosition(400,80,0);

                stage.draw();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                imgBack.setHeight(34);
                imgBack.setWidth(76);
                imgBack.setPosition(400,80,0);

                stage.draw();
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new MainMenu(game));
            }
        };
    }

    public ClickListener getChangeControlListener(){
        return new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                Actor clicked = event.getListenerActor();

                currentClicked = (TextButton) clicked;
                currentClicked.setText("");
            }
        };
    }

    public InputListener getInputListener(){
        return new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode){
                return true;
            }

            public boolean keyTyped(InputEvent event, char character){
                return true;
            }
            @Override
            public boolean keyUp(InputEvent event, int keycode){
                String keycodeString = Input.Keys.toString(keycode);
                if(currentClicked == upField){
                    Config.player1Up = keycode;
                }
                else if(currentClicked == downField){
                    Config.player1Down = keycode;
                }
                else if(currentClicked == leftField){
                    Config.player1Left = keycode;
                }
                else if(currentClicked == rightField){
                    Config.player1Right = keycode;
                }
                currentClicked.setText(keycodeString);
                currentClicked = blankButton;

                return true;
            };
        };
    }


}
