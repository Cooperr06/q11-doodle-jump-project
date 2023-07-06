package dinojump.util;

import dinojump.Renderer;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Stage
{
    private static Stage instance;
    private static final Skin background = Skin.of(5);

    private boolean running;

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

    public boolean isRunning()
    {
        return running;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }
}
