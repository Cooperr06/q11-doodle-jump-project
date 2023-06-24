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
            case 37 -> Avatar.getInstance().setXAcceleration(-1); // A is pressed
            case 39 -> Avatar.getInstance().setXAcceleration(1); // D is pressed
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // check which button is released
        switch (e.getKeyCode())
        {
            case 37, 39 -> Avatar.getInstance().setXAcceleration(0); // A or D is released
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
