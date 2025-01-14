import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    int tile_Width = 48;
    int tile_Height = 48;

    //Dimension class will get the resolution of the monitor
    //My monitor screenSize is set to
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double monitor_Width = screenSize.getWidth(); // 1280 pixels
    double monitor_Height = screenSize.getHeight();  // 720 pixels

    //Screen variables
    public int screen_Width = (int)monitor_Width; //monitor_Width = 1280 pixels
    public int screen_Height = (int)monitor_Height; // monitor_Height = 720 pixels
    public int screenX = tile_Width*13;
    public int screenY = tile_Height*8;

    //Level variables
    public int levelX = 0;
    public int levelY = 0;
    public int totalLevelCol = 80;
    public int totalLevelRow = 16;
    public int level_Width = tile_Width * totalLevelCol;
    public int level_Height = tile_Height * totalLevelRow;

    Thread gThread;
    KeyHandler kH;
    MouseHandler mH;
    Player player;
    Minion minion1, minion2,minion3;
    ArrayList<Minion> minionList;
    int minionCount;
    SwordItem swordItem;
    Color brightHorizon;
    TileManager tileMgr;
    UserInterface userInterface;
    public  GamePanel(){
        //381 collides with first bottomTile!
        //Initial x position player: tile_Width*4
        player = new Player( tile_Width*4,100,tile_Width*2,tile_Height*2,"right",this);
        player.loadImages();
        //Initialize and load minions' images
        minion1 = new Minion(tile_Width*13,tile_Height*8,tile_Width*2,tile_Height*2,"right",this);
        minion1.loadImages();
        minion2 = new Minion(tile_Width*23,tile_Height*12,tile_Width*2,tile_Height*2,"right",this);
        minion2.loadImages();
        minion3 = new Minion(tile_Width*35,tile_Height*12,tile_Width*2,tile_Height*2,"right",this);
        minion3.loadImages();

        //Initialize MinionList
        minionList = new ArrayList<>();
        minionList.add(minion1);
        minionList.add(minion2);
        minionList.add(minion3);
        minionCount = minionList.size();

        //initial position tile_Width*77
        swordItem = new SwordItem((tile_Width*77),((tile_Height*8)+tile_Height/2)+(tile_Height/2),tile_Width,tile_Height,this);
        swordItem.loadItemImages();
        tileMgr = new TileManager(this);
        tileMgr.load_TileImages();
        tileMgr.read_TileMap();
        kH = player.kH;
        mH = player.mH;
        //Userinterface
        userInterface = new UserInterface(this);

        brightHorizon = new Color(240, 192, 180);
        setBackground(brightHorizon);
        //Allows the game panel to recognize the kH being used
        this.addKeyListener(kH);
        this.addMouseListener(mH);
        this.setFocusable(true);


    }
    public void init_Thread(){
        gThread = new Thread(this);
        gThread.start();
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
        player.update();
        for(int i = 0; i<minionList.size();i++){
            //Update all minions within list
            if(minionList.get(i)!= null){
                //When player lands sword hit on minion, the minion dies and set to null
                //Also, the userInterface minionCount decrements
                if(minionList.get(i).collision && player.sword_Hit){
                    minionList.set(i,null);
                    minionCount -= 1;
                    break;
                }

                //Using heartsStack to remove top image everytime player collides with minion, while player no sword hit
                else if(minionList.get(i).collision){
                    if(userInterface.heartsStack.size() > 1 ){
                        if(minionList.get(i).direction.equals("left")){
                            player.levelX -= tile_Width*2;
                            player.tileCollisions();
                            userInterface.heartsStack.pop();
                        }
                        else if(minionList.get(i).direction.equals("right")){
                            player.levelX += tile_Width*2;
                            player.tileCollisions();
                            userInterface.heartsStack.pop();
                        }
                    }
                }
                else{minionList.get(i).update();}

            }
        }
    }

    //Handles graphics painted on the game panel, such as the rectangle shaped entity
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;
        tileMgr.repaint(g2);
        player.repaint(g2);
        for(int i = 0; i<minionList.size();i++){
            //Repaint all minions within list
            if(minionList.get(i)!= null){minionList.get(i).repaint(g2);}
        }
        swordItem.repaint(g2);
        userInterface.repaint(g2);
        g2.dispose();
    }
}

