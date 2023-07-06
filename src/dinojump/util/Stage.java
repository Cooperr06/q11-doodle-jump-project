package dinojump.util;

import dinojump.DinoJump;
import dinojump.Renderer;
import dinojump.manager.InputManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Stage
{
    private static Stage instance;
    private static Skin background = Skin.of(5);


    private static boolean inGame;

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
            try
            {
                Renderer.getInstance().renderBackground(ImageIO.read(background.getImages()[0]));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            Renderer.getInstance().renderText("Press Enter To Start", new Position(Renderer.getInstance().getScreenWidth() / 2, Renderer.getInstance().getScreenHeight() / 2), 20);
    }

    public void showSettingsScreen()
    {

    }

    public void showGameOverScreen()
    {
        Renderer.getInstance().renderText("Game Over", new Position(Renderer.getInstance().getScreenWidth() / 2, Renderer.getInstance().getScreenHeight() / 2), 20);
    }

    public static void startGame()
    {
        inGame = true;
    }

    public void endGame()
    {
        inGame = false;
    }
}
