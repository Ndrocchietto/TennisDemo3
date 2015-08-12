package ivano.android.com.tennisdemo;

import android.graphics.Rect;
import android.util.Log;

/**
 * Created by ivano on 8/5/2015.
 */
public class Ball extends Sprite {
    private final float speedX = 0.5f;
    private final float speedy = 0.5f;


    //to control the bounce of the ball
    private int directionX = 1, directionY = 1;


    public Ball(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
        Log.d("IVO", "pirla");
    }

    public void update(long elapsed) {
//to calculate the new position of the ball

        //cannot access to X and Y in Sprite.class because are private,
        //so i generate getters and setters in the sprite class for x and y
        float x = getX();
        float y = getY();

        Rect screenRect = getScreenRect();
        if (screenRect.left <= 0) {
            Log.d("IVO", "\n x:"+x+"\n y:"+y+  "\n directionX: "+directionX+ "\n directionY: "+directionY );
            directionX = 1;
            Log.d("IVO", "\n sAmE ROW: \n x:"+x+"\n y:"+y+  "\n directionX: "+directionX+ "\n directionY: "+directionY );
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
    }

