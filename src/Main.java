import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    public static void main(String[] args) {
        GamePanel gPanel = new GamePanel();
        gPanel.init_Thread();
        //Dimension class will get the resolution of the monitor
        //My monitor screenSize is set to
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double monitor_Width = screenSize.getWidth();
        double monitor_Height = screenSize.getHeight();
        //System.out.println(monitor_Width); // 1280 pixels
        //System.out.println(monitor_Height); // 720 pixels
        //Position the window at the center of the monitor
        double window_Width = 640;
        double window_Height = 480;
        double center_winPosX = monitor_Width *.5 - (window_Width*.5);
        double center_winPosY = monitor_Height * .5 - (window_Height*.5);
        //System.out.println(center_winPosX); // 320 pixels
        //System.out.println(center_winPosY); // 120 pixels

        JFrame frame = new JFrame("sideScroller2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gPanel);
        // Converted the double types to int, because parameters of
        // the method the int type
        frame.setSize((int)window_Width,(int)window_Height);
        frame.setLocation((int)center_winPosX,(int)center_winPosY);
        frame.setVisible(true);


    }
}