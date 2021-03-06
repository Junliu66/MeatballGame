package com.meat.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.meat.MainGame;

/**
 * The entry point of the Desktop application.
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Super Meat Ball";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new MainGame(), config);
	}
}
