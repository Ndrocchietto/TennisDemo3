


package ivano.android.com.tennisdemo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


/**
 * Created by ivano on 7/24/2015.
 */
public class  Sprite {
    private float x;
    private float y;
    private static Rect bounds;


    private int screenWidth,screenHeight;
    private Bitmap image,shadow;
    private int shadowOffsetX,shadowOffsetY;


    public Sprite(int screenWidth, int screenHeight) {

        this.screenWidth=screenWidth;
        this.screenHeight=screenHeight;

    }

    public void init(Bitmap image, Bitmap shadow,int shadowOffsetX, int shadowOffsetY){
        this.image =image;
        this.shadow=shadow;
        this.shadowOffsetX=shadowOffsetX;
        this.shadowOffsetY=shadowOffsetY;
        bounds=new Rect(0, 0, image.getWidth(),image.getHeight());


    }

    public void draw(Canvas canvas){
        //TODO see what is that
        canvas.drawBitmap(shadow,x+shadowOffsetX,y+shadowOffsetY, null);
        canvas.drawBitmap(image,x,y, null);
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Rect getRect(){

        return bounds;
    }
    public Rect getScreenRect  (){
        return new Rect((int) x,(int) y,(int)x+getRect().width(),(int)y+getRect().height());

    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
