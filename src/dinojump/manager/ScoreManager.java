package dinojump.manager;

import dinojump.Renderer;
import dinojump.util.Account;
import dinojump.util.Audio;
import dinojump.util.Position;

public class ScoreManager
{
    private static ScoreManager instance;

    private final int scoreTextSize = 40;
    private final Position position;

    private int score = 0;
    private int lastAchievement = 0;

    public static ScoreManager getInstance()
    {
        if (instance == null)
        {
            instance = new ScoreManager();
        }
        return instance;
    }

    private ScoreManager()
    {
        position = new Position(50, 80);
        renderScore();
    }

    public void iterateLoop()
    {
        checkAchievement();
        renderScore();
    }

    public void updateScore()
    {
        String macAddress = DatabaseManager.getInstance().getMacAddress();
        if (DatabaseManager.getInstance().getHighscore(macAddress) < score)
        {
            DatabaseManager.getInstance().updateHighscore(macAddress, score);
            Account.getInstance().setHighscore(score);
        }
    }

    public void renderScore()
    {
        Renderer.getInstance().renderText(String.valueOf(score), position, scoreTextSize);
    }

    public void resetScore()
    {
        score = 0;
        lastAchievement = 0;
    }

    public void addScore(int add)
    {
        score += add;
    }

    public int getScore()
    {
        return score;
    }

    public void checkAchievement()
    {
        if (score == 0)
        {
            return;
        }
        if (score % 1000 == 0 && lastAchievement < score)
        {
            Audio.getInstance().playSound("achievementBig");
            lastAchievement = score;
        }
        else if (score % 100 == 0 && lastAchievement < score)
        {
            Audio.getInstance().playSound("achievementSmall");
            lastAchievement = score;
        }
    }
}
