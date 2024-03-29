package dinojump.util;

import dinojump.Renderer;
import dinojump.manager.ScoreManager;

public class Stage
{
    private static Stage instance;

    private final Skin background = Skin.of(0);

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
        Renderer.getInstance().renderBackground(background.getImage());
        Audio.getInstance().playLobby();
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
                new Position(Renderer.getInstance().getScreenWidth() / 2 - 375, Renderer.getInstance().getScreenHeight() / 4), 50);
        Renderer.getInstance().renderText("Your score: " + ScoreManager.getInstance().getScore(),
                new Position(Renderer.getInstance().getScreenWidth() / 2 - 90, Renderer.getInstance().getScreenHeight() / 4 + 50), 30);
        Renderer.getInstance().renderText("Your Highscore: " + Account.getInstance().getHighscore(),
                new Position(Renderer.getInstance().getScreenWidth() / 2 - 130, Renderer.getInstance().getScreenHeight() / 4 + 100), 30);
        Audio.getInstance().stopMusic();
        Audio.getInstance().playSound("gameOver");
    }
}
