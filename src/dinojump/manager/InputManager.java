package dinojump.manager;

import dinojump.DinoJump;
import dinojump.util.Avatar;
import dinojump.util.Stage;

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
        if (instance == null)
        {
            instance = new InputManager();
        }
        return instance;
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
            case 65 -> Avatar.getInstance().setXAcceleration(-1); // A is pressed
            case 68 -> Avatar.getInstance().setXAcceleration(1); // D is pressed
            case 10 ->
            {
                System.out.println(DinoJump.getInstance().isRunning());
                if (!DinoJump.getInstance().isRunning())
                {
                    DinoJump.getInstance().startGameLoop(60); // Enter is pressed, starts game
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // check which button is released
        switch (e.getKeyCode())
        {
            case 65, 68 -> Avatar.getInstance().setXAcceleration(0); // A or D is released
        }
    }

    public void buttonPressed(String type)
    {
        switch (type)
        {
            case "start" -> DinoJump.getInstance().startGameLoop(60); // "start" is pressed
            case "settings" -> Stage.getInstance().showSettingsScreen(); // "settings" is pressed
        }
    }
}
