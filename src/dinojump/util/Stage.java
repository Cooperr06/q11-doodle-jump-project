package dinojump.util;

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
        JButton start = new JButton();
        start.setText("start");


        start.addActionListener(e ->
        {
            InputManager.getInstance().buttonPressed("start");
        });
    }

    public void showSettingsScreen()
    {

    }

    public void showGameOverScreen()
    {

    }
}
