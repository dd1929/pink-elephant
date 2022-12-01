package org.dd1929.pink_elephant.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.dd1929.pink_elephant.PinkElephant;

public class GameOverScreen implements Screen {

    private final PinkElephant mGame;
    private Texture mBackground;
    private BitmapFont mGameOverFont, mScoreFont, mTapInstructionFont;
    private int mScore;

    public OrthographicCamera mCamera;
    public Viewport mViewport;

    public GameOverScreen(final PinkElephant game, int score){
        mGame = game;
        mScore = score;
        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false, mGame.WIDTH, mGame.HEIGHT);
        mViewport = new FitViewport(mGame.WIDTH, mGame.HEIGHT, mCamera);
        mBackground = new Texture("background.png");
        mGameOverFont = new BitmapFont();
        mScoreFont = new BitmapFont();
        mTapInstructionFont = new BitmapFont();

        mGameOverFont.getData().scale(3f);
        mScoreFont.getData().scale(1.5f);
        mTapInstructionFont.getData().scale(2f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        mGame.mSpriteBatch.setProjectionMatrix(mCamera.combined);

        mGame.mSpriteBatch.begin();

        mGame.mSpriteBatch.draw(mBackground, 0, 0);
        mGameOverFont.draw(mGame.mSpriteBatch, "Game Over...", 0, mGame.HEIGHT - 20);
        mScoreFont.draw(mGame.mSpriteBatch, "You scored " + mScore + " points!", 0, mGame.HEIGHT/2);
        mTapInstructionFont.draw(mGame.mSpriteBatch, "Tap anywhere to play again!", 0, 50);

        mGame.mSpriteBatch.end();

        if (Gdx.input.justTouched()){
            mGame.setScreen(new PlayScreen(mGame));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        mViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mBackground.dispose();
        mGameOverFont.dispose();
        mScoreFont.dispose();
        mTapInstructionFont.dispose();
    }
}
