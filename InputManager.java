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
        // check which button is pressed
        switch (e.getKeyCode())
        {
            case 37:
                DinoJump.getInstance().getAvatar().setAcceleration(-1);
                break;
            // A is pressed

            case 39:
                DinoJump.getInstance().getAvatar().setAcceleration(1);
                break;
            // D is pressed
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // check which button is pressed
        switch (e.getKeyCode())
        {
            case 37:
                DinoJump.getInstance().getAvatar().setAcceleration(0);
                break;
            // A is pressed

            case 39:
                DinoJump.getInstance().getAvatar().setAcceleration(0);
                break;
            // D is pressed
        }
    }

    public void buttonPressed(String type)
    {
        switch (type)
        {
            case "start":
                DinoJump.getInstance().startGameLoop();
                break;

            case "settings":
                // Open Settings
                //DinoJump.getInstance().getStage().showSettingsScreen();
                break;
        }
    }

}
