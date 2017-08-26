

package ivano.android.com.tennisdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ivano on 7/20/2015.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap button;
    private GameRunner gameRunner;
    private Game game;

    public GameView(Context context, AttributeSet attrs) {


        super(context, attrs);

        SurfaceHolder holder =getHolder();
        holder.addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //SurfaceHolder holder =getHolder();
        game.onTouchEvent(event);
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("IVO", "SurfaceCreated!");
        game = new Game(getContext(),getWidth(),getHeight(),holder,getResources());

        gameRunner = new GameRunner(game);

        gameRunner.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("IVO", "SurfaceChanged!");


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("IVO", "Destroyed!");
        if(gameRunner != null) {
            gameRunner.shutdown();
            while(gameRunner !=null){
                try {
                    Log.d("IVO", "SurfaceDestroyed  while(gamerunner!=null)");

                    gameRunner.join();
                    gameRunner = null;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

