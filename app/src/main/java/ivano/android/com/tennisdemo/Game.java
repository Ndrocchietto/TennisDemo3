package ivano.android.com.tennisdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

/**
 * Created by ivano on 7/23/2015.
 */
public class Game {

    private SurfaceHolder holder;
    private Resources resources;
    private Ball ball;


public Game(int width, int height,SurfaceHolder holder, Resources resources){
    this.holder = holder;
    this.resources= resources;

    ball = new Ball(width,height );

}
    public void init() {


        Bitmap ballImage= BitmapFactory.decodeResource(resources, R.drawable.button);
        Bitmap ballShadow= BitmapFactory.decodeResource(resources, R.drawable.buttonshadow);

        ball.init(ballImage,ballShadow,-3,0);
    }
    public void update(long elapsed){
        //TODO why here start the ball
        //TODO first update to GIT
ball.update(elapsed);

    }
    public void draw(){

        Canvas canvas=holder.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            ball.draw(canvas);
        }
        holder.unlockCanvasAndPost(canvas);
    }


}
