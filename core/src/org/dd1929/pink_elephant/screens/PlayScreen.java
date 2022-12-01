package org.dd1929.pink_elephant.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.dd1929.pink_elephant.PinkElephant;
import org.dd1929.pink_elephant.sprites.Elephant;
import org.dd1929.pink_elephant.sprites.Obstacle;

public class PlayScreen implements Screen {
    public static final int ELEPHANT_Y = 75;

    private static final int MIN_OBSTACLE_GAP = 400;

    private final PinkElephant mGame;

    private Elephant mElephant;
    private Texture mBackground;
    private Texture mGroundBackground, mGroundForeground;
    private Vector2 mGroundBgPos1, mGroundBgPos2, mGroundFgPos1, mGroundFgPos2;
    private BitmapFont mScoreFont;

    public OrthographicCamera mCamera;
    public Viewport mViewport;

    private Array<Obstacle> mObstacles;

    private int score = 0;

    public PlayScreen (PinkElephant game){
        mGame = game;
        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false, mGame.WIDTH, mGame.HEIGHT);
        mViewport = new FitViewport(mGame.WIDTH, mGame.HEIGHT, mCamera);
        mElephant = new Elephant(50, ELEPHANT_Y);
        mBackground = new Texture("background.png");
        mGroundBackground = new Texture("ground_background.png");
        mGroundForeground = new Texture("ground_foreground.png");
        mGroundBgPos1 = new Vector2(mCamera.position.x - mGame.WIDTH/2, 0);
        mGroundBgPos2 = new Vector2(mCamera.position.x - mGame.WIDTH/2 + mGroundBackground.getWidth(), 0);
        mGroundFgPos1 = new Vector2(mCamera.position.x - mGame.WIDTH/2, 0);
        mGroundFgPos2 = new Vector2(mCamera.position.x - mGame.WIDTH/2 + mGroundForeground.getWidth(), 0);
        mScoreFont = new BitmapFont();
        mScoreFont.getData().scale(2f);

        mObstacles = new Array<>();
        for (int i = 0; i < 4; i++){
            mObstacles.add(new Obstacle(mGame.WIDTH + i*MIN_OBSTACLE_GAP, ELEPHANT_Y));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Updating stuff
        mCamera.position.x = mElephant.getPosition().x + 250;
        mCamera.update();
        updateGround();
        mElephant.update(delta);

        for (int i = 0; i < mObstacles.size; i++){
            Obstacle obstacle = mObstacles.get(i);

            if (mCamera.position.x - mGame.WIDTH/2 > obstacle.getPosition().x + obstacle.getObstacle().getWidth()){
                obstacle.reposition(mGame.WIDTH + obstacle.getPosition().x + i*MIN_OBSTACLE_GAP*4, ELEPHANT_Y);
            }

            if (obstacle.hasCollided(mElephant.getBounds())){
                mGame.setScreen(new GameOverScreen(mGame, score));
                dispose();
            }
        }

        // Actual rendering begins
        mGame.mSpriteBatch.setProjectionMatrix(mCamera.combined);

        mGame.mSpriteBatch.begin();

        mGame.mSpriteBatch.draw(mBackground, mCamera.position.x - mGame.WIDTH/2, 0);
        mGame.mSpriteBatch.draw(mGroundBackground, mGroundBgPos1.x, mGroundBgPos1.y);
        mGame.mSpriteBatch.draw(mGroundBackground, mGroundBgPos2.x, mGroundBgPos2.y);
        mGame.mSpriteBatch.draw(mElephant.getFrame(), mElephant.getPosition().x, mElephant.getPosition().y);
        for (Obstacle obstacle: mObstacles){
            mGame.mSpriteBatch.draw(obstacle.getObstacle(), obstacle.getPosition().x, obstacle.getPosition().y);
        }
        mGame.mSpriteBatch.draw(mGroundForeground, mGroundFgPos1.x, mGroundFgPos1.y);
        mGame.mSpriteBatch.draw(mGroundForeground, mGroundFgPos2.x, mGroundFgPos2.y);

        mScoreFont.draw(mGame.mSpriteBatch, "Score: " + score, mCamera.position.x - mGame.WIDTH/2, mGame.HEIGHT);

        mGame.mSpriteBatch.end();

        if (Gdx.input.justTouched()
                || Gdx.input.isKeyPressed(Input.Keys.UP)
                || Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            mElephant.jump();
        }

        score++;
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
        mElephant.dispose();
        mBackground.dispose();
        mGroundBackground.dispose();
        mGroundForeground.dispose();
        for (Obstacle obstacle: mObstacles){
            obstacle.dispose();
        }
        mScoreFont.dispose();
    }

    private void updateGround(){
        if (mCamera.position.x - mGame.WIDTH/2 > mGroundBgPos1.x + mGroundBackground.getWidth()){
            mGroundBgPos1.add(mGroundBackground.getWidth()*2, 0);
        }
        if (mCamera.position.x - mGame.WIDTH/2 > mGroundBgPos2.x + mGroundBackground.getWidth()){
            mGroundBgPos2.add(mGroundBackground.getWidth()*2, 0);
        }

        if (mCamera.position.x - mGame.WIDTH/2 > mGroundFgPos1.x + mGroundForeground.getWidth()){
            mGroundFgPos1.add(mGroundForeground.getWidth()*2, 0);
        }
        if (mCamera.position.x - mGame.WIDTH/2 > mGroundFgPos2.x + mGroundForeground.getWidth()){
            mGroundFgPos2.add(mGroundForeground.getWidth()*2, 0);
        }
    }
}
