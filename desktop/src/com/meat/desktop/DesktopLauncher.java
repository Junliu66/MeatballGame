package com.meat.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.meat.GameHandler;
import com.meat.MeatGame;


public class DesktopLauncher {
	private static LwjglApplication application;
	private static LwjglApplicationConfiguration config;
	public static void main (String[] arg) {
		config = new LwjglApplicationConfiguration();
		config.title = "Super Meat Ball";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new GameHandler());
	}
}