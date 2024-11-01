import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Entity extends Rectangle {
    int posX;
    int posY;
    int width;
    int height;
    int scale;
    String direction = "right";
    public int speed;
    BufferedImage img;


    //Rectangle rect1 = new Rectangle(10, 20, 50, 30);
    public Entity(int x,int y, int w, int h,int scale){
        this.scale = scale;
        this.posX = x;
        this.posY = y;
        this.width = w * scale;
        this.height = h * scale;

    }

}
