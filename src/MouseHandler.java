import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    public boolean is_AttackPressed = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked: ("+e.getX()+", "+e.getY() +")");
    }

    @Override
    public void mousePressed(MouseEvent e){
        System.out.println(e);
        int button  = e.getButton();
        System.out.println("Mouseevent: "+button);
        if(button  == MouseEvent.BUTTON1){
            is_AttackPressed = true;
            System.out.println("The Button 1 was pressed!");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(is_AttackPressed) {
            is_AttackPressed = false;
        }
        }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
