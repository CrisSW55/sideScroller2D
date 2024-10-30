import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable{
    Thread gThread;
    KeyHandler kH;
    Entity entity;

    public  GamePanel(){
        kH = new KeyHandler();
        entity = new Entity(500,100,50,50);
        setBackground(Color.black);
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
        long deltaTime = 0;
        int FPS = 60;
        long fpsInterval = 1000000000/FPS;
        long startTime = System.nanoTime();
        while(gThread != null){
            long endTime = System.nanoTime();
            deltaTime = deltaTime + (endTime - startTime) / fpsInterval;
            //System.out.println("DeltaTime: " + deltaTime);
            startTime = endTime;
            if(deltaTime >= 1){
                update();
                //System.out.println("Inside game thread!");
                repaint();
                deltaTime--;
            }


        }
    }

    //Handles any logic, such as entity positions
    public void update() {
            if(kH.is_RightPressed){
                entity.screenPosX += entity.speed;
                System.out.println("entity screenposX moving right: " + entity.screenPosX);
            }
        if(kH.is_LeftPressed){
            entity.screenPosX -= - entity.speed;
            System.out.println("entity screenposX moving left:  " + entity.screenPosX);
        }
    }

    //Handles graphics painted on the game panel, such as the rectangle shaped entity
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;
        g2.setColor(Color.BLUE);
        g2.fillRect(entity.screenPosX, entity.screenPosY, entity.width, entity.height);
        g2.dispose();

    }
}

