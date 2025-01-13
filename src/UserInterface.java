import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class UserInterface {
    Font arial_40;
    GamePanel gp;
    Stack<BufferedImage> heartsStack;
    BufferedImage threeHearts,twoHearts,oneHeart,noHearts,minionFace;
    public UserInterface(GamePanel gp) {
        arial_40 = new Font("Arial",Font.PLAIN,40);
        this.gp = gp;
        heartsStack = new Stack<>();
        try {
            threeHearts = ImageIO.read(getClass().getResourceAsStream("/userinterface/threehearts.png"));
            twoHearts = ImageIO.read(getClass().getResourceAsStream("/userinterface/twohearts.png"));
            oneHeart = ImageIO.read(getClass().getResourceAsStream("/userinterface/oneheart.png"));
            noHearts = ImageIO.read(getClass().getResourceAsStream("/userinterface/nohearts.png"));
            heartsStack.push(noHearts);
            heartsStack.push(oneHeart);
            heartsStack.push(twoHearts);
            heartsStack.push(threeHearts);

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
        if(!heartsStack.isEmpty()){
            g2.drawImage(heartsStack.peek(),50,50,gp.tile_Width*2,gp.tile_Height,null);
        }





    }





}
