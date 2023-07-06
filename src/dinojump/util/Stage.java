package dinojump.util;

import dinojump.Renderer;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Stage
{
    private static Stage instance;
    private static final Skin background = Skin.of(0);

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
            Audio.getInstance().playLobby();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        Renderer.getInstance().renderText("Press Enter To start", new Position(Renderer.getInstance().getScreenWidth() / 2 - 200,
                Renderer.getInstance().getScreenHeight() / 2), 50);
    }

    public void showSettingsScreen()
    {

    }

    public void showGameOverScreen()
    {
        Renderer.getInstance().renderText("Game Over! Press Enter to restart!", new Position(Renderer.getInstance().getScreenWidth() / 2 - 375,
                Renderer.getInstance().getScreenHeight() / 2), 50);
        Audio.getInstance().stopMusic();
        Audio.getInstance().playSound("gameOver");
    }
}
