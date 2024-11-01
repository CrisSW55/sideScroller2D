import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable{
    Thread gThread;
    KeyHandler kH;
    Player player;
    Color brightHorizon;
    public  GamePanel(){
        kH = new KeyHandler();
        player = new Player(50,350,16,16,4);
        player.loadImage();
        brightHorizon = new Color(240, 192, 180);
        setBackground(brightHorizon);
        //Allows the game panel to recognize the kH being used
        this.addKeyListener(kH);
        this.setFocusable(true);

    }
    public void init_Thread(){
        gThread = new Thread(this);
        gThread.start();
        //System.out.println("After starting thread!");
    }


    //DeltaTime using nanoTime!

    @Override
    public void run() {
        //System.out.println("Inside game thread!");
        double deltaTime = 0;
        double one_Frame = 0;
        double FPS = 60;
        double fpsInterval = FPS/1000000000;
        long startTime = System.nanoTime();
        while(gThread != null){
            long endTime = System.nanoTime();
            deltaTime = endTime - startTime;
            one_Frame += deltaTime * fpsInterval;
            if(one_Frame >= 1){
                update();
                //System.out.println("Inside game thread!");
                repaint();
                one_Frame--;
            }
            startTime = endTime;


        }
    }

    //Handles any logic, such as entity positions
    public void update() {
            if(kH.is_RightPressed){
                player.posX += player.speed;
                player.direction = "right";
                System.out.println("entity screenposX moving right: " + player.posX);
            }
        if(kH.is_LeftPressed){
            player.posX -= player.speed;
            System.out.println("entity screenposX moving left:  " + player.posX);
        }
        player.spriteIndex++;
        if(player.spriteIndex>10){
            if(player.spriteNum == 1){
                player.spriteNum = 2;
            }
            else if(player.spriteNum == 2){
                player.spriteNum = 3;
            }
            else if(player.spriteNum == 3){
                player.spriteNum = 1;
            }
            player.spriteIndex = 0;
        }
    }

    //Handles graphics painted on the game panel, such as the rectangle shaped entity
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;
        g2.setColor(Color.BLUE);
        if(player.direction.equals("right") && !kH.is_RightPressed){
            g2.drawImage(player.playerImages.get(0),player.posX, player.posY, player.width, player.height,null);
        }
        if(player.direction.equals("right") && kH.is_RightPressed){
            if(player.spriteNum == 1){
                g2.drawImage(player.playerImages.get(player.spriteNum),player.posX, player.posY, player.width, player.height,null);
            }
            if(player.spriteNum == 2){
                g2.drawImage(player.playerImages.get(player.spriteNum),player.posX, player.posY, player.width, player.height,null);
            }
            if(player.spriteNum == 3){
                g2.drawImage(player.playerImages.get(player.spriteNum),player.posX, player.posY, player.width, player.height,null);
            }


        }

        g2.dispose();

    }
}

