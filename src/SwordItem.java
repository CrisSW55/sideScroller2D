import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SwordItem extends Item{
    public final int screenX = 0;
    public final int screenY = 0;
    GamePanel gp;
    public SwordItem(int pos_LevelX,int pos_LevelY,int width, int height,GamePanel gp){
        this.pos_LevelX = pos_LevelX;
        this.pos_LevelY = pos_LevelY;
        this.width = width;
        this.height = height;
        this.gp = gp;

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
        int screenX = pos_LevelX - gp.player.pos_LevelX + gp.player.screenX;
        int screenY = pos_LevelY - gp.player.pos_LevelY + gp.player.screenY;
        if(!collision){g2.drawImage(img,screenX,screenY,width,height,null);}

    }
}
