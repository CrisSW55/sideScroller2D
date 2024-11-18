import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity {
    //Player width and height is 96 pixels
    ArrayList<BufferedImage> playerImages;
    BufferedImage currentImage;
    public int spriteIndex = 0;
    public int spriteNum = 1;


    GamePanel gp;
    KeyHandler kH;
    MouseHandler mH;
    boolean sword_Equipped = false;
    boolean sword_Hit = false;
    boolean player_FeetTileCollision = false;
    public final int screenX;
    public final int screenY;
    public Player(int x, int y, int w, int h,String init_Direction,GamePanel gp) {
        super(x, y, w, h,init_Direction);
        this.screenX = gp.screen_Width/2;
        this.screenY = gp.screen_Height/2;
        this.gp = gp;
        //this.setBounds(this.pos_LevelX,this.pos_LevelY,this.width,this.height);
        this.speed = 5;
        playerImages =  new ArrayList<BufferedImage>();
        this.gravity = 2;
        kH = new KeyHandler();
        mH = new MouseHandler();
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
    public void collisions(){
        //Player width = 96 pixels and height = 96 pixels

        //Tile and Player collision
        for(int row = 0; row<gp.totalLevelRow;row++){
            for(int col = 0; col<gp.totalLevelCol; col++){
                int levelX = col * gp.tile_Width;
                int levelY = row * gp.tile_Height;
                int screenX = levelX - pos_LevelX + this.screenX;
                int screenY = levelY - pos_LevelY + this.screenY;
                if(gp.tileMgr.tiles.get(gp.tileMgr.tileIndex[row][col]) != null){
                    if (screenY <= pos_LevelY + (height+height/2)) {
                        player_FeetTileCollision = true;
                        gp.tileMgr.tiles.get(gp.tileMgr.tileIndex[row][col]).collision = true;
//                        System.out.println("playerTileCollision inside first if statement is true of inner column loop: " + player_FeetTileCollision);
                        System.out.println("player position Y: " + pos_LevelY);
                        System.out.println("current tile position Y " + screenY +  " " + row +  " "+ col);
//                        System.out.println("player position Y: " + pos_LevelY);
//                        System.out.println("current tile position Y " + screenY +  " " + row +  " "+ col);
                        break;

                    }
                    }


            }
        }

        //SwordItem and Player collision, equipping sword
        if (gp.swordItem.pos_LevelX <= pos_LevelX + (width / 2) && gp.swordItem.pos_LevelX+ (width / 2)>= pos_LevelX &&
                gp.swordItem.pos_LevelY <= pos_LevelY + (height) && gp.swordItem.pos_LevelY+ (height/2)>= pos_LevelY) {
            sword_Equipped = true;
            gp.swordItem.collision = true;
        }
        //Minion and Player collision, attacked minion collides with player sword
        if (gp.minion1.pos_LevelX <= pos_LevelX + (width*.75) && gp.minion1.pos_LevelX+ (width*.75)>= pos_LevelX && mH.is_AttackPressed && sword_Equipped &&
        gp.minion1.pos_LevelY <= pos_LevelY + (height) && gp.minion1.pos_LevelY+ (height*.75)>= pos_LevelY) {
            sword_Hit = true;
            gp.minion1.collision = true;
        }
        if (gp.minion2.pos_LevelX <= pos_LevelX + (width*.75) && gp.minion2.pos_LevelX+ (width*.75)>= pos_LevelX && mH.is_AttackPressed && sword_Equipped &&
                gp.minion2.pos_LevelY <= pos_LevelY + (height) && gp.minion2.pos_LevelY+ (height*.75)>= pos_LevelY) {
            sword_Hit = true;
            gp.minion2.collision = true;
        }
        if (gp.minion3.pos_LevelX <= pos_LevelX + (width*.75) && gp.minion3.pos_LevelX+ (width*.75)>= pos_LevelX && mH.is_AttackPressed && sword_Equipped &&
                gp.minion3.pos_LevelY <= pos_LevelY + (height) && gp.minion3.pos_LevelY+ (height*.75)>= pos_LevelY) {
            sword_Hit = true;
            gp.minion3.collision = true;
        }
    }
    public void update(){
        //Gravity on player, player and tile collision
        if (!player_FeetTileCollision) {
            pos_LevelY += gravity;
        }


        //Player sprite movements
        if (kH.is_RightPressed || kH.is_LeftPressed || kH.is_UpPressed || kH.is_DownPressed) {
            if (kH.is_LeftPressed) {direction = "left";pos_LevelX -= speed;}
            if (kH.is_RightPressed) {direction = "right";pos_LevelX += speed;}
            if (kH.is_UpPressed) {direction = "up";pos_LevelY -= speed;}
            if (kH.is_DownPressed) {direction = "down";pos_LevelY += speed;}
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
        else if (mH.is_AttackPressed && sword_Equipped) {
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
            if (mH.is_AttackPressed) {
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
        g2.drawImage(currentImage,screenX,screenY,width,height,null);
    }

}
