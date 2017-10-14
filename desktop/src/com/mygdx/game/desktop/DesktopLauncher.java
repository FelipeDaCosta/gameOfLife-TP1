package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Util;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Util util = new Util();
		config.width = util.SCREEN_WIDTH();
		config.height = util.SCREEN_HEIGHT();
		new LwjglApplication(new MyGdxGame(), config);
	}
}
