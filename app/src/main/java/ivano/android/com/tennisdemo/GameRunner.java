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
        game.init();

        super.run();
        long lastTime=System.currentTimeMillis();

        while(running) {

            Log.d("IVO", "Thread running");
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
long now= System.currentTimeMillis();
            long elapsed = now-lastTime;

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
