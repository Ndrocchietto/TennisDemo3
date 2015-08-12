

package ivano.android.com.tennisdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by ivano on 7/23/2015.
 */
public class Game {

    private SurfaceHolder holder;
    private Resources resources;
    private Ball ball;
    public static Canvas canvas;

    private Bat player, opponent;

    public Game(int width, int height,SurfaceHolder holder, Resources resources){
        this.holder = holder;
        this.resources= resources;

        ball = new Ball(width,height );
        player = new Bat (width, height, Bat.Position.LEFT);
        opponent = new Bat(width, height,Bat.Position.RIGHT);
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
    public void update(long elapsed){

// to see if the center pixel of the left edge of the ball is touched
//        if(player.getScreenRect().contains(ball.getScreenRect().left,ball.getScreenRect().centerY())){
//            ball.moveRight();
//        } else if (opponent.getScreenRect().contains(ball.getScreenRect().right, ball.getScreenRect().centerY())) {
//            ball.moveLeft();
//        }
//
//        //we do not know what happens if the ball goes off of the center of the screen
//        else if(ball.getScreenRect().left<player.getScreenRect().right) {
//            Log.d("IVO", "lost");
//        }
//        else if(ball.getScreenRect().right>opponent.getScreenRect().left) {
//            Log.d("IVO", "won");
//        }

//uncomment this that start the ball and make it run
        ball.update(elapsed);
        //not player.update because the user will control the movement
        //of the bat
        opponent.update(elapsed,ball );

    }
    public void draw(){

        canvas = holder.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            ball.draw(canvas);
            //TODO try to put a debug point here
            player.draw(canvas);
            opponent.draw(canvas);
        }
        holder.unlockCanvasAndPost(canvas);
    }


    public void onTouchEvent(MotionEvent event) {
        player.setPosition(event.getY());

    }
}
