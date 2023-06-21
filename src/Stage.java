import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stage
{
    private Stage instance;
    private JButton Start;

    private void Stage()
    {

    }

    public void getInstance()
    {
        if (instance == null)
        {
            new Stage();
        }
    }

    public void showMainScreen()
    {
        Start.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DinoJump.getInstance().startGameLoop();
            }
        });
    }
}
