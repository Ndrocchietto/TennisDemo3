

package ivano.android.com.tennisdemo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by ivano on 7/23/2015.
 */
public class Game {



private enum State{
    PAUSED, WON, LOST, RUNNING
}

    private SoundPool soundPool;

    //initially is State.paused
    private State state= State.PAUSED;
    private SurfaceHolder holder;
    private Resources resources;
    private Ball ball;
    private Paint textPaint;
    private Context context;
    public static Canvas canvas;
    private int[] sounds = new int[5];
    private Bat player, opponent;

    public Game(Context context,int width, int height,SurfaceHolder holder, Resources resources){
        this.holder = holder;
        this.resources= resources;
        this.context=context;

        soundPool=new SoundPool(5, AudioManager.STREAM_MUSIC,0);

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

      sounds[Sounds.START]=soundPool.load(context, R.raw.start, 1);
      sounds[Sounds.WIN]=soundPool.load(context, R.raw.win, 1);
      sounds[Sounds.LOSE]=soundPool.load(context, R.raw.lose, 1);
      sounds[Sounds.BOUNCE1]=soundPool.load(context, R.raw.bounce1, 1);
      sounds[Sounds.BOUNCE2]=soundPool.load(context, R.raw.bounce2, 1);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                                                @Override
                                                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                                                    if (sounds[Sounds.START] == sampleId) {
                                                        soundPool.play(sampleId, 1, 1, 1, 0, 1);
                                                    }
                                                }
                                            }
        );
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
        //left bat with ball
        if(player.getScreenRect().contains(ball.getScreenRect().left,ball.getScreenRect().centerY())){
            ball.moveRight();
            soundPool.play(sounds[Sounds.BOUNCE1], 1, 1, 1, 0, 1);
//right ball with ball
        }else if(opponent.getScreenRect().contains(ball.getScreenRect().right, ball.getScreenRect().centerY())) {
            ball.moveLeft();
            soundPool.play(sounds[Sounds.BOUNCE2], 1, 1, 1, 0, 1);
//ball with left wall
        }else if (ball.getScreenRect().left < player.getScreenRect().right) {

           state=State.LOST;

            soundPool.play(sounds[Sounds.LOSE], 1, 1, 1, 0, 1);

            //if we Lost the game we put the ball again at center of the screen
            //so we do putting the two bats central
            // if we do not do it, when we Run the game touching the device
            //we found again the ball in Lost or WON position and they
            //risk to be called again as canvas images that need to be touched again
            initObjectPositions();
            //ball with right wall
} else if (ball.getScreenRect().right > opponent.getScreenRect().left) {
            state=State.WON;
            soundPool.play(sounds[Sounds.WIN], 1, 1, 1, 0, 1);

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
