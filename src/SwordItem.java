import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SwordItem extends Item{

    public SwordItem(int posX,int posY,int width, int height){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }
    public void loadItemImages(){
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/items/sword.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void repaint(Graphics2D g2){
        g2.drawImage(img,posX,posY,width,height,null);

    }
}
