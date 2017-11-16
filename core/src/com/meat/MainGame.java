package com.meat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class MainGame extends GameHandler {

    @Override
    public void create () {
        setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
        if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
            setScreen(new MainMenu(this));
        }
    }
}

