package ivano.android.com.tennisdemo;

import android.graphics.Bitmap;

import java.util.Random;

/**
 * Created by ndrokki on 7-8-15.
 */
public class Bat extends Sprite {
    private Random random = new Random();
    private int dir;
    private float speed = 0.4f;


    public static final int MARGIN = 30;
    private Position position;

    public void setPosition(float y) {
        setY(y - getRect().centerY());


    }


    public enum Position {
        LEFT, RIGHT
    }


    public Bat(int screenWidth, int screenHeight, Position position) {
        super(screenWidth, screenHeight);

        this.position = position;
    }


    @Override
    public void init(Bitmap image, Bitmap shadow, int shadowOffsetX, int shadowOffsetY) {
        super.init(image, shadow, shadowOffsetX, shadowOffsetY);

initPosition();
        //this is the Y direction up or down that can be 1 or -1 of the bat
        //controlled by the PC
        dir = random.nextInt(2) * 2 - 1;

        if (position == Position.LEFT) {
            setX(MARGIN);

        } else if (position == Position.RIGHT) {
            setX((getScreenWidth() - MARGIN) - getRect().centerX());
        }//the bats at middle of screen end
    }
    public void initPosition(){
        //to make the bat  s at the middle of the screen
        setY(getScreenHeight() / 2 - getRect().centerY());
    }

    public void update(long elapsed, Ball ball) {
        //20 possibilities to make it move from 0 to 19
        int decision = random.nextInt(20);
        if (decision == 0) {
//the bat won't move
            dir = 0;
        } else if (decision == 1) {
            dir = random.nextInt(2) * 2 - 1;

        }
        // with this 10% of possibilities
        else if (decision < 4) {
//moving towards the ball

            //getScreenRect call the rectangle that gives the bounding
            //rectangle of the ball and I ask for the vertical center of the
            //ball
            //after the < we call the vertical center of the bat
            //..in one phrase if the vertical center of the ball is minor of the vertical center of the ball
            //        set the direction as -1 why? because will be decreased the
            //y in y++ =dir*speed*elapsed
            if (ball.getScreenRect().centerY() < getScreenRect().centerY()) {
                dir = -1;
            } else {
                dir = 1;
            }

        }
        if (getScreenRect().top <= 0) {
            dir=1;
        }else if (getScreenRect().bottom >= getScreenHeight()) {
            dir=-1;
        }

        float y = getY();

        y += dir * speed * elapsed;
        setY(y);
    }
}

