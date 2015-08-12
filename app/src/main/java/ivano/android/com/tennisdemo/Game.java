

package ivano.android.com.tennisdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by ivano on 7/23/2015.
 */
public class Game {



private enum State{
    PAUSED, WON, LOST, RUNNING
}
    //initially is State.paused
    private State state= State.PAUSED;
    private SurfaceHolder holder;
    private Resources resources;
    private Ball ball;
    private Paint textPaint;
    public static Canvas canvas;

    private Bat player, opponent;

    public Game(int width, int height,SurfaceHolder holder, Resources resources){
        this.holder = holder;
        this.resources= resources;

        ball = new Ball(width,height );
        player = new Bat (width, height, Bat.Position.LEFT);
        opponent = new Bat(width, height,Bat.Position.RIGHT);
        textPaint=new Paint();
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLUE);
        textPaint.setTextSize(26);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);

    }
    public void init() {


        Bitmap ballImage= BitmapFactory.decodeResource(resources, R.drawable.button);
        Bitmap ballShadow= BitmapFactory.decodeResource(resources, R.drawable.buttonshadow);

        //Load Bitmaps bat
        Bitmap batImage = BitmapFactory.decodeResource(resources, R.drawable.bat);
        Bitmap batShadow = BitmapFactory.decodeResource(resources, R.drawable.batshadow);


        ball.init(ballImage,ballShadow,-3,0);
        player.init(batImage, batShadow, -3, 0);
        opponent.init(batImage, batShadow, -3, 0);
    }
    public void update(long elapsed) {

        if (state==State.RUNNING) {
            updateGame(elapsed);
        }
        switch (state) {

        }

    }



    private void updateGame(long elapsed){
        //collision detection
        if(player.getScreenRect().contains(ball.getScreenRect().left,ball.getScreenRect().centerY())){
            ball.moveRight();
        }else if(opponent.getScreenRect().contains(ball.getScreenRect().right, ball.getScreenRect().centerY())) {
            ball.moveLeft();
        }else if (ball.getScreenRect().left < player.getScreenRect().right) {
           state=State.LOST;
            //if we Lost the game we put the ball again at center of the screen
            //so we do putting the two bats central
            // if we do not do it, when we Run the game touching the device
            //we found again the ball in Lost or WON position and they
            //risk to be called again as canvas images that need to be touched again
            initObjectPositions();
        } else if (ball.getScreenRect().right > opponent.getScreenRect().left) {
            state=State.WON;
            initObjectPositions();

        }


//uncomment this that start the ball and make it run
        ball.update(elapsed);
        //not player.update because the user will control the movement
        //of the bat
        opponent.update(elapsed,ball );
    }

    private void initObjectPositions(){
        ball.initPosition();
        player.initPosition();
        opponent.initPosition();
    }
    private void drawText(Canvas canvas,String text) {
canvas.drawText(text,canvas.getWidth()/2,canvas.getHeight()/2,textPaint);
    }
    public void draw() {


        canvas = holder.lockCanvas();

        if (canvas != null) {

            canvas.drawColor(Color.WHITE);
            switch (state) {

                case PAUSED:
                    drawText(canvas, "Tap screen to start");

                    break;
                case WON:

                    drawText(canvas, "You Won:))");

                    break;
                case LOST:
                    drawText(canvas, "You Lost:(");

                    break;
                case RUNNING:
                    drawGame(canvas);
                    break;
            }

            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawGame(Canvas canvas){


            ball.draw(canvas);
            //TODO try to put a debug point here
            player.draw(canvas);
            opponent.draw(canvas);
        }



    public void onTouchEvent(MotionEvent event) {
        if (state==State.RUNNING) {
            player.setPosition(event.getY());

        }else{
            //if the games is not running on touch run!
            state=State.RUNNING;
        }

    }
}
