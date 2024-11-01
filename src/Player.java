import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity {
    ArrayList<BufferedImage> playerImages;
    int spriteIndex = 0;
    int spriteNum = 1;

    public Player(int x, int y, int w, int h, int scale) {
        super(x, y, w, h, scale);
        this.scale = scale;
        this.posX = x;
        this.posY = y;
        this.width = w * scale;
        this.height = h * scale;
        this.speed = 5;
        playerImages =  new ArrayList<BufferedImage>();

    }

    public void loadImage(){
        try {
            playerImages.add(ImageIO.read(getClass().getResourceAsStream("/entities/standing_Blueguy.png")));
            playerImages.add(ImageIO.read(getClass().getResourceAsStream("/entities/running1_Blueguy.png")));
            playerImages.add(ImageIO.read(getClass().getResourceAsStream("/entities/running2_Blueguy.png")));
            playerImages.add(ImageIO.read(getClass().getResourceAsStream("/entities/running3_Blueguy.png")));




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
