import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity extends Rectangle {
    int pos_LevelX;
    int pos_LevelY;
    int width;
    int height;
    public String direction;
    boolean collision = false;
    BufferedImage stand,run1,run2,run3,leftstand,leftrun1,leftrun2,leftrun3,swordattack,leftswordattack;
    int gravity;
    int speed;


    public Entity(int x,int y, int w, int h,String direction){
        this.pos_LevelX = x;
        this.pos_LevelY = y;
        this.width = w;
        this.height = h;
        this.direction = direction;

    }

}
