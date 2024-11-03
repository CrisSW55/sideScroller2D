import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity {
    ArrayList<BufferedImage> playerImages;
    BufferedImage currentImage;
    public int spriteIndex = 0;
    public int spriteNum = 1;
    KeyHandler kH;
    public Player(int x, int y, int w, int h,String init_Direction) {
        super(x, y, w, h,init_Direction);
        this.speed = 5;
        playerImages =  new ArrayList<BufferedImage>();
        this.gravity = 1/2;
        kH = new KeyHandler();
    }

    public void loadImages(){
        try {
            //Right direction player frames, spriteIndexes: 0 - 3
            stand = ImageIO.read(getClass().getResourceAsStream("/entities/stand_Blueguy.png"));
            run1 = ImageIO.read(getClass().getResourceAsStream("/entities/run1_Blueguy.png"));
            run2 = ImageIO.read(getClass().getResourceAsStream("/entities/run2_Blueguy.png"));
            run3 = ImageIO.read(getClass().getResourceAsStream("/entities/run3_Blueguy.png"));
            playerImages.add(stand);
            playerImages.add(run1);
            playerImages.add(run2);
            playerImages.add(run3);
            //Left direction player frames, spriteIndexes: 4 - 7
            leftstand = ImageIO.read(getClass().getResourceAsStream("/entities/leftstand_Blueguy.png"));
            leftrun1 = ImageIO.read(getClass().getResourceAsStream("/entities/leftrun1_Blueguy.png"));
            leftrun2 = ImageIO.read(getClass().getResourceAsStream("/entities/leftrun2_Blueguy.png"));
            leftrun3 = ImageIO.read(getClass().getResourceAsStream("/entities/leftrun3_Blueguy.png"));
            playerImages.add(leftstand);
            playerImages.add(leftrun1);
            playerImages.add(leftrun2);
            playerImages.add(leftrun3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if(kH.is_RightPressed || kH.is_LeftPressed){
            if(kH.is_RightPressed){direction = "right";posX += speed;}
            else if(kH.is_LeftPressed){direction = "left";posX -= speed;}

            spriteIndex++;

            if(spriteIndex>10){
                //Correct code below!
                if(direction.equals("left")){
                    if(spriteNum == 5){spriteNum = 6;}
                    else if(spriteNum == 6){spriteNum = 7;}
                    else if(spriteNum == 7){spriteNum = 5;}
                    spriteIndex = 0;
                }
                if(direction.equals("right")){
                    if(spriteNum == 1){spriteNum = 2;}
                    else if(spriteNum == 2){spriteNum = 3;}
                    else if(spriteNum == 3){spriteNum = 1;}
                    spriteIndex = 0;
                }
            }
        }
    }
    
    public void repaint(Graphics2D g2){
        currentImage = null;
        if(direction.equals("left")){
            if(!kH.is_LeftPressed) {
                currentImage = playerImages.get(4);
                g2.drawImage(currentImage,posX,posY,width,height,null);
            }
            if(kH.is_LeftPressed){
                if(spriteNum == 5){
                    currentImage = playerImages.get(5);
                    g2.drawImage(currentImage,posX,posY,width,height,null);
                }
                else if(spriteNum == 6){
                    currentImage = playerImages.get(6);
                    g2.drawImage(currentImage,posX,posY,width,height,null);
                }
                else if(spriteNum == 7){
                    currentImage = playerImages.get(7);
                    g2.drawImage(currentImage,posX,posY,width,height,null);
                }
            }
        }
        if(direction.equals("right")){
            if(!kH.is_RightPressed) {
                currentImage = playerImages.getFirst();
                g2.drawImage(currentImage,posX,posY,width,height,null);
            }
            if(kH.is_RightPressed){
                if(spriteNum == 1){
                    currentImage = playerImages.get(1);
                    g2.drawImage(currentImage,posX,posY,width,height,null);
                }
                else if(spriteNum == 2){
                    currentImage = playerImages.get(2);
                    g2.drawImage(currentImage,posX,posY,width,height,null);
                }
                else if(spriteNum == 3){
                    currentImage = playerImages.get(3);
                    g2.drawImage(currentImage,posX,posY,width,height,null);
                }
            }
        }
        
    }

}
