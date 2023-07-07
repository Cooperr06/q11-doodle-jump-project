package dinojump.manager;

import dinojump.DinoJump;
import dinojump.util.Avatar;

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
    public void keyPressed(KeyEvent e)
    {
        // check which button is pressed
        switch (e.getKeyCode())
        {
            case 65 -> Avatar.getInstance().setXAcceleration(-5); // A is pressed
            case 68 -> Avatar.getInstance().setXAcceleration(5); // D is pressed
            case 10 ->
            {
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

    @Override
    public void keyTyped(KeyEvent e)
    {
    }
}
