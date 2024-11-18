import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean is_RightPressed;
    public boolean is_LeftPressed;
    public boolean is_UpPressed;
    public boolean is_DownPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D){
            is_RightPressed = true;
        }
        if(key == KeyEvent.VK_A){
            is_LeftPressed = true;
        }
        if(key == KeyEvent.VK_W){
            is_UpPressed = true;
        }
        if(key == KeyEvent.VK_S){
            is_DownPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(is_RightPressed == true){
            is_RightPressed = false;
        }
        if(is_LeftPressed == true){
            is_LeftPressed = false;
        }
        if(is_UpPressed == true){
            is_UpPressed = false;
        }
        if(is_DownPressed == true){
            is_DownPressed = false;
        }

    }
}
