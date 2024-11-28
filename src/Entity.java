import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    int levelX;
    int levelY;
    int width;
    int height;
    public String direction;
    boolean collision = false;
    BufferedImage stand,run1,run2,run3,leftstand,leftrun1,leftrun2,leftrun3,swordattack,leftswordattack;
    int gravity;
    int speed;
    Rectangle rectangle;


    public Entity(int x,int y, int w, int h,String direction){
        this.levelX = x;
        this.levelY = y;
        this.width = w;
        this.height = h;
        this.direction = direction;

    }

}
