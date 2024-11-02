import java.awt.image.BufferedImage;

public class Tile {
    int posX;
    int posY;
    int width = 48;
    int height = 48;
    public BufferedImage img;
    public Tile(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
    }

}
