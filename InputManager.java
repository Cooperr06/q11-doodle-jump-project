import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener
{
    private static InputManager instance;

    private InputManager()
    {

    }

    public static InputManager getInstance()
    {
        if (InputManager.instance == null)
        {
            InputManager.instance = new InputManager();
        }

        return InputManager.instance;
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
            case 37 -> DinoJump.getInstance().getAvatar().setAcceleration(-1);
            // A is pressed

            case 39 -> DinoJump.getInstance().getAvatar().setAcceleration(1);
            // D is pressed
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // check which button is pressed
        switch (e.getKeyCode())
        {
            case 37 -> DinoJump.getInstance().getAvatar().setAcceleration(0);
            // A is released

            case 39 -> DinoJump.getInstance().getAvatar().setAcceleration(0);
            // D is released
        }
    }

    public void buttonPressed(String type)
    {
        switch (type)
        {
            // "start" is pressed
            case "start" -> DinoJump.getInstance().startGameLoop();

            // "settings" is pressed
            case "settings" -> DinoJump.getInstance().getStage().showSettingsScreen();
        }
    }

}
