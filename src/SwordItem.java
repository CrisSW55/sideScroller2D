import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SwordItem extends Item{

    public SwordItem(int pos_LevelX,int pos_LevelY,int width, int height){
        this.pos_LevelX = pos_LevelX;
        this.pos_LevelY = pos_LevelY;
        this.width = width;
        this.height = height;
        //this.setBounds(this.pos_LevelX,this.pos_LevelY,this.width,this.height);
    }
    public void loadItemImages(){
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/items/sword.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void repaint(Graphics2D g2){
        if(!collision){g2.drawImage(img,pos_LevelX,pos_LevelY,width,height,null);}

    }
}
