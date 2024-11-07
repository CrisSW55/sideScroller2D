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
    boolean sword_Equipped = false;
    boolean sword_Hit = false;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double monitor_Width = screenSize.getWidth();
    double monitor_Height = screenSize.getHeight();
    double screen_Width = monitor_Width; //Old window_Witdh = 640
    double screen_Height = monitor_Height;
    public Player(int x, int y, int w, int h,String init_Direction) {
        super(x, y, w, h,init_Direction);
        this.setBounds(this.posX,this.posY,this.width,this.height);
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
            //Player attack frames, spriteIndexes: 8 , 9
            swordattack = ImageIO.read(getClass().getResourceAsStream("/entities/swordattack_Blueguy.png"));
            leftswordattack = ImageIO.read(getClass().getResourceAsStream("/entities/leftswordattack_Blueguy.png"));
            playerImages.add(swordattack);
            playerImages.add(leftswordattack);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void player_ItemCollision(Item item) {
        if (item.posX <= posX + (width / 2) && item.posX+ (width / 2)>= posX) {
            sword_Equipped = true;
            item.collision = true;
        }

    }
    public void player_MinionAttackCollision(Minion minion) {
        if (minion.posX <= posX + (width*.75) && minion.posX+ (width*.75)>= posX && kH.is_AttackPressed && sword_Equipped) {
            sword_Hit = true;
            minion.collision = true;
        }

    }

    public void update(){
        //Player sprite movements
        if (kH.is_RightPressed || kH.is_LeftPressed) {
            if (kH.is_LeftPressed) {direction = "left";posX -= speed;}
            if (kH.is_RightPressed) {direction = "right";posX += speed;}
            //spriteIndex is the times update get called in this case total 60 times

            spriteIndex++;
            //So for every spriteIndex > 10, updates per 10 times, the spriteNum changes!
            if (direction.equals("left") && spriteIndex > 10) {
                spriteNum = (spriteNum % 3) + 5; // Cycle through left sprites
                spriteIndex = 0;
            }
            else if (direction.equals("right") && spriteIndex > 10) {
                spriteNum = (spriteNum % 3) + 1; // Cycle through right sprites
                spriteIndex = 0;
            }
        }
        //Player attacking
        else if (kH.is_AttackPressed && sword_Equipped) {
            if (direction.equals("right")) {
                spriteNum = 8;
            }
            else if (direction.equals("left")) {
                spriteNum = 9;
            }
        }

        //Player standing
        else if(direction.equals("right")){
            // Handle right idle state
            spriteNum = 0; // Assuming 0 is for standing
        }
        else if(direction.equals("left")){
            if (kH.is_AttackPressed) {
                spriteNum = 9;
            }
            // Handle left idle state
            spriteNum = 4; //Assuming 4 is for leftstanding
        }
    }

    public void repaint(Graphics2D g2){
        switch (direction){
            case "right":
                if(spriteNum == 0){
                    currentImage = stand;
                }
                else if(spriteNum == 1){
                    currentImage = run1;
                }
                else if(spriteNum == 2){
                    currentImage = run2;
                }
                else if(spriteNum == 3){
                    currentImage = run3;
                }
                else if(spriteNum == 8){
                    currentImage = swordattack;
                }
                break;

            case "left":
                if(spriteNum == 4){
                    currentImage = leftstand;
                }
                if(spriteNum == 5){
                    currentImage = leftrun1;
                }
                else if(spriteNum == 6){
                    currentImage = leftrun2;
                }
                else if(spriteNum == 7){
                    currentImage = leftrun3;
                }
                else if(spriteNum == 9){
                    currentImage = leftswordattack;
                }
                break;

        }
        g2.drawImage(currentImage,posX,posY,width,height,null);
    }

}
