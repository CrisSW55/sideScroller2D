import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    public static void main(String[] args) {
        GamePanel gPanel = new GamePanel();
        gPanel.init_Thread();
        double center_winPosX = gPanel.monitor_Width *.5 - (gPanel.screen_Width*.5);
        double center_winPosY = gPanel.monitor_Height * .5 - (gPanel.screen_Height*.5);
        JFrame frame = new JFrame("sideScroller2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gPanel);
        // Converted the double types to int, because parameters of
        // the method the int type
        frame.setSize((int)gPanel.screen_Width,(int)gPanel.screen_Height);
        frame.setLocation((int)center_winPosX,(int)center_winPosY);
        frame.setVisible(true);
    }
}