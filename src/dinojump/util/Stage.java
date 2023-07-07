package dinojump.util;

import dinojump.Renderer;
import dinojump.manager.ScoreManager;

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
        Renderer.getInstance().renderText("Press Enter to start",
                new Position(Renderer.getInstance().getScreenWidth() / 2 - 200,
                        Renderer.getInstance().getScreenHeight() / 2), 50);
        Renderer.getInstance().renderText("Your Highscore: " + Account.getInstance().getHighscore(),
                new Position(Renderer.getInstance().getScreenWidth() / 2 - 120,
                        Renderer.getInstance().getScreenHeight() / 2 + 50), 30);
    }

    public void showGameOverScreen()
    {
        Renderer.getInstance().renderText("Game Over! Press Enter to restart!",
                new Position(Renderer.getInstance().getScreenWidth() / 2 - 375, Renderer.getInstance().getScreenHeight() / 2), 50);
        Renderer.getInstance().renderText("Your score: " + ScoreManager.getInstance().getScore(),
                new Position(Renderer.getInstance().getScreenWidth() / 2 - 50, Renderer.getInstance().getScreenHeight() / 2 + 50), 30);
        Renderer.getInstance().renderText("Your Highscore: " + Account.getInstance().getHighscore(),
                new Position(Renderer.getInstance().getScreenWidth() / 2 - 100, Renderer.getInstance().getScreenHeight() / 2 + 100), 30);
        Audio.getInstance().stopMusic();
        Audio.getInstance().playSound("gameOver");
    }
}
