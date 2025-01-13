import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UserInterface {
    Font arial_40;
    GamePanel gp;

    BufferedImage hearts,minionFace;
    public UserInterface(GamePanel gp) {
        arial_40 = new Font("Arial",Font.PLAIN,40);
        this.gp = gp;
        try {
            hearts = ImageIO.read(getClass().getResourceAsStream("/userinterface/threehearts.png"));
            minionFace = ImageIO.read(getClass().getResourceAsStream("/userinterface/minionFace.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void repaint(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(minionFace,gp.screen_Width-(gp.tile_Width*4),gp.tile_Height,gp.tile_Width,gp.tile_Height,null);
        g2.drawString("x" + gp.minionCount,gp.screen_Width-(gp.tile_Width*3),gp.tile_Height*2);
        g2.drawImage(hearts,50,50,gp.tile_Width*2,gp.tile_Height,null);

    }





}
