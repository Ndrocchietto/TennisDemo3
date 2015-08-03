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
    private GameRunner runner;
     private Game game;

    public GameView(Context context, AttributeSet attrs) {


        super(context, attrs);

        SurfaceHolder holder =getHolder();
 holder.addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //SurfaceHolder holder =getHolder();

        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("IVO", "Created!");
        game = new Game(getWidth(),getHeight(),holder,getResources());

        runner= new GameRunner(game);
        runner.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("IVO", "Changed!");


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("IVO", "Destroyed!");
        if(runner!= null) {
            runner.shutdown();
            while(runner!=null){
                try {
                    Log.d("IVO", "runner!=null");

                    runner.join();
                    runner = null;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

