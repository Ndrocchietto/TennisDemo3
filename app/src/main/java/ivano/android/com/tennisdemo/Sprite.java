package ivano.android.com.tennisdemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by ivano on 7/24/2015.
 */
public class Sprite {
    private float x;
    private float y;
private int screenWidth,screenHeight;
  private Bitmap image,shadow;


    public Sprite(int screenWidth, int screenHeight) {
        this.x=30;
        this.y=30;
        this.screenWidth=screenWidth;
        this.screenHeight=screenHeight;

    }

    public void init(Bitmap image, Bitmap shadow){
this.image =image;
        this.shadow=shadow;


    }
    public void update(long elapsed){

    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(shadow,x,y, null);
        canvas.drawBitmap(image,x,y, null);
    }

}
