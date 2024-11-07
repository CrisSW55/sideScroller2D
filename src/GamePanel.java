import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    //Dimension class will get the resolution of the monitor
    //My monitor screenSize is set to
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double monitor_Width = screenSize.getWidth(); // 1280 pixels
    double monitor_Height = screenSize.getHeight();  // 720 pixels
    //Position the window at the center of the monitor
    public int screen_Width = (int)monitor_Width; //monitor_Width = 1280 pixels
    public int screen_Height = (int)monitor_Height; // monitor_Height = 720 pixels

    public int screenX = 0;
    public int screenY = 0;
    int tile_Width = 48;
    int tile_Height = 48;

    Thread gThread;
    KeyHandler kH;
    MouseHandler mH;
    Player player;
    Minion minion1;
    SwordItem swordItem;
    Color brightHorizon;
    TileManager tileMgr;
    public  GamePanel(){
        tileMgr = new TileManager();
        tileMgr.load_TileImages();
        tileMgr.read_TileMap();
        player = new Player(screen_Width/2-(tile_Width*2),screen_Height/2+(tile_Height/2),tile_Width*2,tile_Height*2,"right");
        player.loadImages();
        minion1 = new Minion(tile_Width*11,tile_Height*8,tile_Width*2,tile_Height*2,"right");
        minion1.loadImages();
        swordItem = new SwordItem((tile_Width*10),((tile_Height*8)+tile_Height/2)+(tile_Height/2),tile_Width,tile_Height);
        swordItem.loadItemImages();
        kH = player.kH;
        mH = player.mH;
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
                repaint();
                one_Frame--;
            }
            startTime = endTime;


        }
    }

    //Handles any logic, such as entity positions,directions
    public void update() {
        player.update(this);
        minion1.update(this);
        player.collisions(this);
    }

    //Handles graphics painted on the game panel, such as the rectangle shaped entity
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;

        for(int i = 0; i<tileMgr.tileIndex.length;i++){
            for(int j = 0; j<tileMgr.total_Cols; j++){
                if(tileMgr.tiles.get(tileMgr.tileIndex[i][j]) != null){
                    tileMgr.tiles.get(tileMgr.tileIndex[i][j]).set_Position(j * tile_Width,i*tile_Height);
                    g2.drawImage(tileMgr.tiles.get(tileMgr.tileIndex[i][j]).img,j * tile_Width,i*tile_Height,tile_Width,tile_Height,null);
                }
            }
        }
        player.repaint(g2);
        minion1.repaint(g2);
        swordItem.repaint(g2);
        g2.dispose();
    }
}

