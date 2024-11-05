import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity extends Rectangle {
    int posX;
    int posY;
    int width;
    int height;
    public String direction;
    boolean collision = false;

    BufferedImage stand,run1,run2,run3,leftstand,leftrun1,leftrun2,leftrun3,swordattack,leftswordattack;
    int gravity;
    int speed;


    public Entity(int x,int y, int w, int h,String direction){
        this.posX = x;
        this.posY = y;
        this.width = w;
        this.height = h;
        this.direction = direction;

    }

}
