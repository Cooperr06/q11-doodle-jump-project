package util;

import javax.swing.*;

public class Stage
{
    private static Stage instance;

    private JButton startButton;

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
        startButton.addActionListener(e ->
        {
            DinoJump.getInstance().startGameLoop();
            Renderer.resetScreen();
        });
    }

    public void showSettingsScreen()
    {

    }

    public void showGameOverScreen()
    {

    }
}
