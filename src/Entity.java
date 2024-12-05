import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    int levelX;
    int levelY;
    int width;
    int height;
    public String direction;
    boolean collision = false;
    boolean left_Collision = false;
    boolean right_Collision = false;
    boolean up_Collision = false;
    boolean down_Collision = false;
    BufferedImage stand,run1,run2,run3,leftstand,leftrun1,leftrun2,leftrun3,swordattack,leftswordattack;

    boolean gravityOn = true;
    int gravity;
    int speed;
    Rectangle rectangle;

    //Entity jump variables
    boolean reachedJumpHeight;
    int jumpHeight;


    public Entity(int x,int y, int w, int h,String direction){
        this.levelX = x;
        this.levelY = y;
        this.width = w;
        this.height = h;
        this.direction = direction;

    }

}
