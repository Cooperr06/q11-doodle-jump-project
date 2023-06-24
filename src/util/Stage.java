package util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        Renderer.renderButton(start, 0.5, 0.2);
        start.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DinoJump.getInstance();
                InputManager.getInstance().buttonPressed("start");
            }
        });
    }

    public void showSettingsScreen()
    {

    }

    public void showGameOverScreen()
    {

    }
}
