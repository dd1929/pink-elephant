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

public class MenuScreen implements Screen {
    private static final int ELEPHANT_Y_OFFSET = 75;

    private final PinkElephant mGame;
    private Texture mBackground;
    private Texture mElephant;
    private Texture mGroundBackground, mGroundForeground;
    private Texture mTitleTexture;
    private BitmapFont mTapInstructionFont;

    public OrthographicCamera mCamera;
    public Viewport mViewport;

    public MenuScreen (final PinkElephant game){
        mGame = game;
        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false, mGame.WIDTH, mGame.HEIGHT);
        mViewport = new FitViewport(mGame.WIDTH, mGame.HEIGHT, mCamera);
        mBackground = new Texture("background.png");
        mElephant = new Texture("elephant.png");
        mGroundBackground = new Texture("ground_background.png");
        mGroundForeground = new Texture("ground_foreground.png");
        mTitleTexture = new Texture("title.png");
        mTapInstructionFont = new BitmapFont();
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
        mGame.mSpriteBatch.draw(mGroundBackground, 0, 0);
        mGame.mSpriteBatch.draw(mGroundForeground, 0, 0);
        mGame.mSpriteBatch.draw(mElephant, mGame.WIDTH/2 - mElephant.getWidth()/2, ELEPHANT_Y_OFFSET);
        mGame.mSpriteBatch.draw(mTitleTexture,
                mGame.WIDTH/2 - mTitleTexture.getWidth()/2 + 17,
                mGame.HEIGHT - mTitleTexture.getHeight(),
                mTitleTexture.getWidth() - 38,
                mTitleTexture.getHeight() - 15);
        mTapInstructionFont.draw(mGame.mSpriteBatch, "Tap anywhere to begin!", 0, 50);

        mGame.mSpriteBatch.end();

        if (Gdx.input.justTouched()
                || Gdx.input.isKeyPressed(Input.Keys.UP)
                || Gdx.input.isKeyPressed(Input.Keys.SPACE)){
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
        mElephant.dispose();
        mGroundBackground.dispose();
        mGroundForeground.dispose();
        mTitleTexture.dispose();
        mTapInstructionFont.dispose();
    }
}
