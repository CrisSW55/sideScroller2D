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
    boolean player_MovingUp = false;
    public final int screenX;
    public final int screenY;

    //Player jump variables
    int maxJumpHeight;
    boolean isJumping = false;

    public Player(int x, int y, int w, int h,String init_Direction,GamePanel gp) {
        super(x, y, w, h,init_Direction);
        this.screenX = gp.screen_Width/2 - (gp.tile_Width/2);
        this.screenY = gp.screen_Height/2 - (gp.tile_Height/2);
        this.gp = gp;
        rectangle = new Rectangle();
        rectangle.x = 16;
        rectangle.y = 32;
        rectangle.width = width - 32;
        rectangle.height = height -32;

        this.speed = 5;
        playerImages =  new ArrayList<BufferedImage>();
        this.gravity = 3;
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
    public void horizontal_Collisions(){
        int left_LevelX = levelX + rectangle.x;
        int right_LevelX = levelX + rectangle.x + rectangle.width;
        int top_LevelY = levelY + rectangle.y;
        int bottom_LevelY = levelY + rectangle.y + rectangle.height;

        int left_Col = left_LevelX/gp.tile_Width;
        int right_Col = right_LevelX/gp.tile_Width;
        int top_Row = top_LevelY/(gp.tile_Height);
        int bottom_Row = bottom_LevelY/gp.tile_Height;


        int tileNum1, tileNum2;

        if(direction.equals("left")){
            left_Col = (left_LevelX - speed) / gp.tile_Width;
            tileNum1 = gp.tileMgr.tileIndex[top_Row][left_Col];
            tileNum2 = gp.tileMgr.tileIndex[bottom_Row][left_Col];
            if(gp.tileMgr.tiles.get(tileNum1) != null && gp.tileMgr.tiles.get(tileNum1).collision ||
                    gp.tileMgr.tiles.get(tileNum2) != null && gp.tileMgr.tiles.get(tileNum2).collision){
                left_Collision = true;
            }
            if (gp.tileMgr.tiles.get(tileNum1) == null && gp.tileMgr.tiles.get(tileNum2) == null) {
                left_Collision = false;
            }
        }
        else if(direction.equals("right")) {
            right_Col = (right_LevelX + speed) / gp.tile_Width;
            tileNum1 = gp.tileMgr.tileIndex[top_Row][right_Col];
            tileNum2 = gp.tileMgr.tileIndex[bottom_Row][right_Col];
            if (gp.tileMgr.tiles.get(tileNum1) != null && gp.tileMgr.tiles.get(tileNum1).collision ||
                    gp.tileMgr.tiles.get(tileNum2) != null && gp.tileMgr.tiles.get(tileNum2).collision) {
                right_Collision = true;
                //System.out.println("LevelY: "+levelY);
            }
            if (gp.tileMgr.tiles.get(tileNum1) == null && gp.tileMgr.tiles.get(tileNum2) == null) {
                right_Collision = false;
            }

        }


    }
    public void vertical_Collisions(){
        int left_LevelX = levelX + rectangle.x;
        int right_LevelX = levelX + rectangle.x + rectangle.width;
        int top_LevelY = levelY + rectangle.y;
        int bottom_LevelY = levelY + rectangle.y + rectangle.height;

        int left_Col = left_LevelX/gp.tile_Width;
        int right_Col = right_LevelX/gp.tile_Width;
        int top_Row = top_LevelY/(gp.tile_Height);
        int bottom_Row = bottom_LevelY/gp.tile_Height;


        int tileNum1, tileNum2;


            //No tile below and not jumping
            if (!down_Collision && !isJumping) {
                bottom_Row = (bottom_LevelY + speed) / gp.tile_Height;
                tileNum1 = gp.tileMgr.tileIndex[bottom_Row][left_Col];
                tileNum2 = gp.tileMgr.tileIndex[bottom_Row][right_Col];
                System.out.println("Player levelY: " + levelY);
                if (levelY >= 640) {
                    gp.gThread.interrupt();
                }
                System.out.println("Is interrupted? : " + gp.gThread.isInterrupted());

                if (gp.tileMgr.tiles.get(tileNum1) != null && gp.tileMgr.tiles.get(tileNum1).collision ||
                        gp.tileMgr.tiles.get(tileNum2) != null && gp.tileMgr.tiles.get(tileNum2).collision) {
                    down_Collision = true;
                }
                if (gp.tileMgr.tiles.get(tileNum1) == null && gp.tileMgr.tiles.get(tileNum2) == null) {
                    down_Collision = false;
                    horizontal_Collisions();

                }


            }
            //Tile collision below, not jumping also set the maxJumpHeight = levelY - 200;
            else if (down_Collision && !isJumping) {
                bottom_Row = (bottom_LevelY + speed) / gp.tile_Height;
                tileNum1 = gp.tileMgr.tileIndex[bottom_Row][left_Col];
                tileNum2 = gp.tileMgr.tileIndex[bottom_Row][right_Col];
                //Set the maxJumpHeight when player colliding with bottomTiles and not jumping yet!

                if (gp.tileMgr.tiles.get(tileNum1) != null && gp.tileMgr.tiles.get(tileNum1).collision ||
                        gp.tileMgr.tiles.get(tileNum2) != null && gp.tileMgr.tiles.get(tileNum2).collision) {

                    down_Collision = true;
                }
                if (gp.tileMgr.tiles.get(tileNum1) == null && gp.tileMgr.tiles.get(tileNum2) == null) {
                    down_Collision = false;
                }

            }

            else if(!down_Collision && isJumping){
                if(direction.equals("left")){
                    left_Col = (left_LevelX - speed) / gp.tile_Width;
                    tileNum1 = gp.tileMgr.tileIndex[top_Row][left_Col];
                    tileNum2 = gp.tileMgr.tileIndex[bottom_Row][left_Col];
                    if(gp.tileMgr.tiles.get(tileNum1) != null && gp.tileMgr.tiles.get(tileNum1).collision ||
                            gp.tileMgr.tiles.get(tileNum2) != null && gp.tileMgr.tiles.get(tileNum2).collision){
                        left_Collision = true;
                    }
                    if (gp.tileMgr.tiles.get(tileNum1) == null && gp.tileMgr.tiles.get(tileNum2) == null) {
                        left_Collision = false;
                    }
                }
                else if(direction.equals("right")){
                    right_Col = (right_LevelX + speed) / gp.tile_Width;
                    tileNum1 = gp.tileMgr.tileIndex[top_Row][right_Col];
                    tileNum2 = gp.tileMgr.tileIndex[bottom_Row][right_Col];
                    if(gp.tileMgr.tiles.get(tileNum1) != null && gp.tileMgr.tiles.get(tileNum1).collision ||
                            gp.tileMgr.tiles.get(tileNum2) != null && gp.tileMgr.tiles.get(tileNum2).collision){
                        right_Collision = true;
                        //System.out.println("LevelY: "+levelY);
                    }
                    if (gp.tileMgr.tiles.get(tileNum1) == null && gp.tileMgr.tiles.get(tileNum2) == null) {
                        right_Collision = false;
                    }

                }
            }






    }
    public void other_collisions(){
        //Player width = 96 pixels and height = 96 pixels

        //SwordItem and Player collision, equipping sword
        if (gp.swordItem.pos_LevelX <= levelX + (width / 2) &&
                gp.swordItem.pos_LevelX+ (width / 2)>= levelX &&
                gp.swordItem.pos_LevelY <= levelY + (height) &&
                gp.swordItem.pos_LevelY+ (height/2)>= levelY    ) {
            sword_Equipped = true;
            gp.swordItem.collision = true;
        }
        //Minion and Player collision, attacked minion collides with player sword
        else if (gp.minion1.levelX <= levelX + (width*.75) &&
                gp.minion1.levelX+ (width*.75)>= levelX &&
                mH.is_AttackPressed &&
                sword_Equipped &&
                gp.minion1.levelY <= levelY + (height) &&
                gp.minion1.levelY+ (height*.75)>= levelY    ) {
                sword_Hit = true;
                gp.minion1.collision = true;
        }
        else if (gp.minion2.levelX <= levelX + (width*.75) && gp.minion2.levelX+ (width*.75)>= levelX && mH.is_AttackPressed && sword_Equipped &&
                gp.minion2.levelY <= levelY + (height) && gp.minion2.levelY+ (height*.75)>= levelY) {
            sword_Hit = true;
            gp.minion2.collision = true;
        }
        else if (gp.minion3.levelX <= levelX + (width*.75) && gp.minion3.levelX+ (width*.75)>= levelX && mH.is_AttackPressed && sword_Equipped &&
                gp.minion3.levelY <= levelY + (height) && gp.minion3.levelY+ (height*.75)>= levelY) {
            sword_Hit = true;
            gp.minion3.collision = true;
        }
    }
    public void update(){
        //Player sprite horizontal movement

        if(!gp.gThread.isInterrupted()){

            //Apply gravity when player is not jumping and downCollision is false
            if(!down_Collision && !isJumping){levelY += 5;vertical_Collisions();}
            //Set maxJumpHeight when player colliding with bottomTile!
            if(down_Collision && !isJumping){maxJumpHeight = levelY - 200;}
            //Only change the player's y position upwards  when jumping and levelY is greater than the maxJumpHeight
            if(down_Collision && isJumping && (levelY > maxJumpHeight)){
                levelY -= 10;
                if(levelY <= maxJumpHeight){isJumping = false;vertical_Collisions();}
            }
            if( (kH.is_UpPressed && down_Collision && !isJumping)){
                //Set the is Jumping = true;
                if(!kH.is_RightPressed && !kH.is_LeftPressed && (kH.is_UpPressed && down_Collision && !isJumping)){isJumping = true;}
                vertical_Collisions();
                horizontal_Collisions();


                System.out.println("up_Collision: " + up_Collision);
                System.out.println("down_Collision: " + down_Collision);
                System.out.println("left_Collision: " + left_Collision);
                System.out.println("right_Collision: " + right_Collision);
            }
            //Any time player presses down on keys boolean values change!
            if (kH.is_RightPressed || kH.is_LeftPressed)  {

                //Horizontal or vertical direction setters
                if (kH.is_LeftPressed && !kH.is_RightPressed && !kH.is_UpPressed  && !mH.is_AttackPressed) {direction = "left";}
                else if (kH.is_RightPressed && !kH.is_LeftPressed && !kH.is_UpPressed  && !mH.is_AttackPressed) {direction = "right";}


                //Player  attacking
                if (mH.is_AttackPressed && sword_Equipped && kH.is_RightPressed&&direction.equals("right")) {spriteNum = 8;}
                else if (mH.is_AttackPressed && sword_Equipped && kH.is_LeftPressed && direction.equals("left")) {spriteNum = 9;}

                //Collisions
                left_Collision = false;
                right_Collision = false;

                vertical_Collisions();
                horizontal_Collisions();


                System.out.println("up_Collision: " + up_Collision);
                System.out.println("down_Collision: " + down_Collision);
                System.out.println("left_Collision: " + left_Collision);
                System.out.println("right_Collision: " + right_Collision);

                //Horizontal  movements
                if(!left_Collision){
                    if(levelX >= 0 && direction.equals("left")){levelX -= speed;}
                }
                if(!right_Collision){
                    if(levelX <=  3744  && direction.equals("right")){levelX += speed;}
                }

                // spriteIndex is the times update get called in this case total 60 times

                spriteIndex++;
                //So for every spriteIndex > 10, updates per 10 times, the spriteNum changes!
                if (direction.equals("left") && spriteIndex > 10 && !mH.is_AttackPressed && down_Collision) {
                    spriteNum = (spriteNum % 3) + 5; // Cycle through left sprites
                    spriteIndex = 0;
                }
                else if (direction.equals("right") && spriteIndex > 10 && !mH.is_AttackPressed && down_Collision) {
                    spriteNum = (spriteNum % 3) + 1; // Cycle through right sprites
                    spriteIndex = 0;
                }
            }

            //Player standing attacking
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
                // Handle left idle state
                spriteNum = 4; //Assuming 4 is for leftstanding
            }
            other_collisions();
        }




    }

    public void repaint(Graphics2D g2){
        switch (direction){
            case "right":
                if(spriteNum == 0){
                    currentImage = playerImages.getFirst();
                }
                else if(spriteNum == 1){
                    currentImage = playerImages.get(1);
                }
                else if(spriteNum == 2){
                    currentImage = playerImages.get(2);
                }
                else if(spriteNum == 3){
                    currentImage = playerImages.get(3);
                }
                else if(spriteNum == 8){
                    currentImage = playerImages.get(8);
                }
                break;

            case "left":
                if(spriteNum == 4){
                    currentImage = playerImages.get(4);
                }
                if(spriteNum == 5){
                    currentImage = playerImages.get(5);
                }
                else if(spriteNum == 6){
                    currentImage = playerImages.get(6);
                }
                else if(spriteNum == 7){
                    currentImage = playerImages.get(7);
                }
                else if(spriteNum == 9){
                    currentImage = playerImages.get(9);
                }
                break;

        }
        g2.drawImage(currentImage,screenX,screenY,width,height,null);
    }

}
