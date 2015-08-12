


package ivano.android.com.tennisdemo;

import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by ivano on 8/5/2015.
 */
public class Ball extends Sprite {
    private final float speedX = 0.5f;
    private final float speedy = 0.5f;


    //to control the bounce of the ball

    //TODO change values from a git version to see the differences for instance add to direction X a value
    private int directionX ,directionY;


    public Ball(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
    }

    public void update(long elapsed) {
//to calculate the new position of the ball

        //cannot access to X and Y in Sprite.class because are private,
        //so i generate getters and setters in the sprite class for x and y
        float x = getX();
        float y = getY();
        Rect screenRect = getScreenRect();
        if (screenRect.left <= 0) {
            directionX = 1;
        } else if (screenRect.right >= getScreenWidth()) {
            directionX = -1;
        }
        if (screenRect.top < 0) {
            directionY = 1;
        } else if (screenRect.bottom >= getScreenHeight()) {
            directionY = -1;
        }


        x += directionX * speedX * elapsed;
        y += directionY * speedy * elapsed;

        //Log.d("IVO", " x is:" + x);
        setX(x);
        setY(y);


    }

    //we ovveride this method to make the ball at the center of the screen


    @Override
    public void init(Bitmap image, Bitmap shadow, int shadowOffsetX, int shadowOffsetY) {
        super.init(image, shadow, shadowOffsetX, shadowOffsetY);

initPosition();
        Random random = new Random();


        directionX=random.nextInt(2)*2;
        directionY=random.nextInt(2)*2;
    }
    public void initPosition(){
        //this move the ball to the center
        setX(getScreenWidth() / 2 - getRect().centerX());
        setY(getScreenWidth() / 2 - getRect().centerY());
    }

    public void moveRight() {
        directionX=1;
    }
    public void moveLeft() {
        directionX=-1;
    }
}

