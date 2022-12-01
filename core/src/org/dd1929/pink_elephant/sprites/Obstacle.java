package org.dd1929.pink_elephant.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstacle {

    private Vector2 mPosition;

    private Texture mObstacle;
    private Rectangle mBounds;

    private Random mRandom = new Random();

    public Obstacle(float x, float y){
        setRandomTexture();

        mPosition = new Vector2(x, y);
        mBounds = new Rectangle(x, y, mObstacle.getWidth(), mObstacle.getHeight());

    }

    public Vector2 getPosition() {
        return mPosition;
    }

    public Texture getObstacle() {
        return mObstacle;
    }

    public boolean hasCollided(Rectangle elephant){
        return elephant.overlaps(mBounds);
    }

    public void reposition(float x, float y){
        setRandomTexture();
        mPosition.set(x, y);
    }

    private void setRandomTexture(){
        switch (mRandom.nextInt(4)){
            case 0:
                mObstacle =  new Texture("bush_1.png");
                break;
            case 1:
                mObstacle = new Texture("bush_2.png");
                break;
            case 2:
                mObstacle = new Texture("rock_1.png");
                break;
            case 3:
                mObstacle = new Texture("rock_2.png");
                break;
            default:
                mObstacle =  new Texture("bush_1.png");

        }
    }

    public void dispose(){
        mObstacle.dispose();
    }
}
