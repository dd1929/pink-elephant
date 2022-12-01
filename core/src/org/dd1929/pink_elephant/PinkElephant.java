package org.dd1929.pink_elephant;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.dd1929.pink_elephant.screens.MenuScreen;

public class PinkElephant extends Game {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;
	public static final String TITLE = "Pink Elephant";

	public SpriteBatch mSpriteBatch;
	public BitmapFont mFont;

	private Music mGameMusic;
	
	@Override
	public void create () {
		mSpriteBatch = new SpriteBatch();
		mFont = new BitmapFont();
		mGameMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

		mGameMusic.setLooping(true);
		mGameMusic.play();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void dispose () {
		mSpriteBatch.dispose();
		mFont.dispose();
		mGameMusic.dispose();
	}
}
