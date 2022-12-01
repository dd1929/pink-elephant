package org.dd1929.pink_elephant.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import org.dd1929.pink_elephant.screens.PlayScreen;

public class Elephant {
    private static final int GRAVITY = -20;
    private static final int SINGLE_JUMP = 500;
    private static final int ACCELERATION_X = 2;
    private static final int MAX_VELOCITY_X = 300;
    private static final int FRAME_COLS = 4;

    private Vector3 mPosition;
    private Vector3 mVelocity;

    private Animation<TextureRegion> mAnimation;
    private Texture mAnimTexture;
    private Texture mJumpTexture;
    private Sound mTrumpetSound;

    private Rectangle mBounds;

    private boolean isJumping = false;
    private float stateTime = 0f;

    public Elephant(float x, float y){
        mPosition = new Vector3(x, y, 0);
        mVelocity = new Vector3(0, 0, 0);
        mAnimTexture = new Texture("elephant_run_anim.png");
        mJumpTexture = new Texture("elephant_jump.png");
        mTrumpetSound = Gdx.audio.newSound(Gdx.files.internal("sfx_trumpet.ogg"));
        mBounds = new Rectangle(x, y, mAnimTexture.getWidth()/FRAME_COLS, mAnimTexture.getHeight()/FRAME_COLS);

        TextureRegion[][] textureRegions = TextureRegion.split(mAnimTexture,
                mAnimTexture.getWidth()/FRAME_COLS,
                mAnimTexture.getHeight());
        TextureRegion[] elephantFrames = textureRegions[0];

        mAnimation = new Animation<>(0.12f, elephantFrames);
    }

    public void update(float deltaTime){
        stateTime += deltaTime;

        if (mVelocity.x < MAX_VELOCITY_X){
            mVelocity.add(ACCELERATION_X, GRAVITY, 0);
        } else {
            mVelocity.add(0, GRAVITY, 0);
        }

        mVelocity.scl(deltaTime);
        mPosition.add(mVelocity.x, mVelocity.y, 0);

        if (mPosition.y <= PlayScreen.ELEPHANT_Y){
            mPosition.y = PlayScreen.ELEPHANT_Y;
            isJumping = false;
        }

        mVelocity.scl(1/deltaTime);
        mBounds.setPosition(mPosition.x, mPosition.y);
    }

    public void jump(){
        if (!isJumping){
            mVelocity.y = SINGLE_JUMP;
            mTrumpetSound.play();
            isJumping = true;
        }
    }

    public TextureRegion getFrame(){
        if (isJumping){
            return new TextureRegion(mJumpTexture);
        } else {
            return mAnimation.getKeyFrame(stateTime, true);
        }
    }

    public Vector3 getPosition() {
        return mPosition;
    }

    public Rectangle getBounds() {
        return mBounds;
    }

    public void dispose(){
        mAnimTexture.dispose();
        mJumpTexture.dispose();
        mTrumpetSound.dispose();
    }
}
