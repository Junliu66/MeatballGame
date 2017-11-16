package com.meat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenu extends Game {
    Game self = this;
    OrthographicCamera camera;


    SpriteBatch batch;
    Stage stage;
    Button btnPlay;
    Button btnHelp;
    Button btnSettings;
    Button btnExit;
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;

    int buttonsX = 400;


    Texture texture;

    @Override
    public void create () {

        stage = new Stage(new ScreenViewport());
        texture = new Texture("mainMenu.png");
        Image image= new Image(texture);

        myTexture = new Texture(Gdx.files.internal("btnPlay.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnPlay = new ImageButton(myTexRegionDrawable);

        myTexture = new Texture(Gdx.files.internal("btnHelp.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnHelp = new ImageButton(myTexRegionDrawable);

        //Skin buttonSkin = new Skin();
        //btnSettings = new TextButton("Controls", buttonSkin);
        myTexture = new Texture(Gdx.files.internal("btnHelp.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnSettings = new ImageButton(myTexRegionDrawable);


        myTexture = new Texture(Gdx.files.internal("btnExit.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btnExit = new ImageButton(myTexRegionDrawable);

        btnPlay.addListener(getPlayListener());
        btnHelp.addListener(getHelpListener());
        btnSettings.addListener(getSettingsListener());
        btnExit.addListener(getExitListener());

        btnPlay.setPosition(buttonsX, 160, 0);
        btnHelp.setPosition(buttonsX, 120, 0);
        btnSettings.setPosition(buttonsX, 80, 0);
        btnExit.setPosition(buttonsX, 40, 0);

        stage.addActor(image);
        stage.addActor(btnPlay);
        stage.addActor(btnHelp);
        stage.addActor(btnSettings);
        stage.addActor(btnExit);



        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render () {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        //batch.begin();
        //batch.draw(texture, 0, 0);
        //stage.act();
        stage.draw();
        //batch.end();
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private ClickListener getPlayListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        };
    }

    private ClickListener getHelpListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {


            }
        };
    }

    private ClickListener getSettingsListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                setScreen(new ControlMenu(self));
            }
        };
    }

    private ClickListener getExitListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        };
    }


    class SettingsClickListener extends ClickListener{
        @Override
        public void clicked(InputEvent event, float x, float y){
            setScreen(new ControlMenu(self));
        }
    }

}