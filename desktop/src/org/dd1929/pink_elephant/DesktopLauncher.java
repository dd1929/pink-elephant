package org.dd1929.pink_elephant;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(PinkElephant.WIDTH, PinkElephant.HEIGHT);
		config.setTitle(PinkElephant.TITLE);
		config.setWindowIcon("elephant.png");
		new Lwjgl3Application(new PinkElephant(), config);
	}
}
