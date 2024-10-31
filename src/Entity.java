import java.awt.*;

public class Entity extends Rectangle {
    int posX;
    int posY;
    int width;
    int height;
    int speed = 10;

    //Rectangle rect1 = new Rectangle(10, 20, 50, 30);
    public Entity(int x,int y, int w, int h){
        this.posX = x;
        this.posY = y;
        this.width = w;
        this.height = h;

    }




}
