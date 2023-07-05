package dinojump.util;

import dinojump.Renderer;
import dinojump.manager.InputManager;

import javax.swing.*;

public class Stage
{
    private static Stage instance;

    private Stage()
    {
    }

    public static Stage getInstance()
    {
        if (instance == null)
        {
            instance = new Stage();
        }
        return instance;
    }

    public void showMainScreen()
    {
        Renderer.getInstance().renderText("Press Enter To Start", new Position(Renderer.getInstance().getScreenWidth() / 2 , Renderer.getInstance().getScreenHeight() / 2), 20);
    }

    public void showSettingsScreen()
    {

    }

    public void showGameOverScreen()
    {

    }
}
