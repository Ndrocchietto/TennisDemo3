package ivano.android.com.tennisdemo;

import android.util.Log;

/**
 * Created by ivano on 7/22/2015.
 */
public class GameRunner extends Thread {
    private Game game;
    private volatile boolean running=true;


    public GameRunner(Game game){
        this.game=game;
    }
    @Override
    public void run() {
        game.gameInit();

        super.run();
        long lastTime=System.currentTimeMillis();

        while(running) {



            long now= System.currentTimeMillis();
            long elapsed = now-lastTime;
            Log.d("IVO","run: lastTime "+lastTime);

            Log.d("IVO","run: now "+now);


            Log.d("IVO", "elapsed initial " + elapsed);
         if(elapsed<100){

                game.update(elapsed);
                game.draw();
            }




            lastTime=now;
        }
    }
    public void shutdown() {
        Log.d("IVO", "shutdown");
        running = false;
    }

}
