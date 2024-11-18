import java.awt.image.BufferedImage;

public class Tile {
    int posX;
    int posY;
    int width;
    int height;
    public BufferedImage img = null;
    boolean collision = false;

    public void set_Position(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
    }

}
