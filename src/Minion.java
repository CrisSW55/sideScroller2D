import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Minion extends Entity{
    ArrayList<BufferedImage> minionImages;
    BufferedImage currentImage;
    public int spriteIndex = 0;
    public int spriteNum = 1;
    public int init_posX;
    public Minion(int x, int y, int w, int h,String init_Direction) {
        super(x, y, w, h,init_Direction);
        init_posX = x;
        this.setBounds(this.posX,this.posY,this.width,this.height);
        this.speed = 2;
        minionImages =  new ArrayList<BufferedImage>();
        this.gravity = 1/2;
    }
    public void loadImages(){
        try {
            //Right direction player frames, spriteIndexes: 0 - 3
            stand = ImageIO.read(getClass().getResourceAsStream("/entities/stand_Minion.png"));
            run1 = ImageIO.read(getClass().getResourceAsStream("/entities/run1_Minion.png"));
            run2 = ImageIO.read(getClass().getResourceAsStream("/entities/run2_Minion.png"));
            run3 = ImageIO.read(getClass().getResourceAsStream("/entities/run3_Minion.png"));
            minionImages.add(stand);
            minionImages.add(run1);
            minionImages.add(run2);
            minionImages.add(run3);
            //Left direction player frames, spriteIndexes: 4 - 7
            leftstand = ImageIO.read(getClass().getResourceAsStream("/entities/leftstand_Minion.png"));
            leftrun1 = ImageIO.read(getClass().getResourceAsStream("/entities/leftrun1_Minion.png"));
            leftrun2 = ImageIO.read(getClass().getResourceAsStream("/entities/leftrun2_Minion.png"));
            leftrun3 = ImageIO.read(getClass().getResourceAsStream("/entities/leftrun3_Minion.png"));
            minionImages.add(leftstand);
            minionImages.add(leftrun1);
            minionImages.add(leftrun2);
            minionImages.add(leftrun3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(GamePanel gp){
        //Minion sprite movements
        if(direction.equals("right")){
            if (posX >= init_posX+(gp.tile_Width*3)){
                direction = "left";
            }
            else if(posX < init_posX + (gp.tile_Width*3)){direction = "right";posX += speed;}
        }

        if(direction.equals("left")){
            if (posX <= init_posX){
                direction = "right";
            }
            else{direction = "left";posX -= speed;}
        }
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
//            //Minion standing
//        else if(direction.equals("right")){
//            // Handle right idle state
//            spriteNum = 0; // Assuming 0 is for standing
//        }
//        else if(direction.equals("left")){
//            // Handle left idle state
//            spriteNum = 4; //Assuming 4 is for leftstanding
//        }
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
                break;

        }

        if(!collision){g2.drawImage(currentImage,posX,posY,width,height,null);}



    }

}
