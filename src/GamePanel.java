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
                entity.posX += entity.speed;
                System.out.println("entity screenposX moving right: " + entity.posX);
            }
        if(kH.is_LeftPressed){
            entity.posX -= - entity.speed;
            System.out.println("entity screenposX moving left:  " + entity.posX);
        }
    }

    //Handles graphics painted on the game panel, such as the rectangle shaped entity
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;
        g2.setColor(Color.BLUE);
        g2.fillRect(entity.posX, entity.posY, entity.width, entity.height);
        g2.dispose();

    }
}

