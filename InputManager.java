import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener
{
    public InputManager()
    {

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case 37:
                MoveLeft();
                break;
            // A is pressed

            case 39:
                MoveRight();
                break;
            // D is pressed
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    public void MoveLeft()
    {

    }

    public void MoveRight()
    {

    }
}
