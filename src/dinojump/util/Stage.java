package dinojump.util;

import dinojump.DinoJump;
import dinojump.Renderer;
import dinojump.manager.InputManager;

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
        Renderer.getInstance().renderButton(start, 0.5F, 0.2F);
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
