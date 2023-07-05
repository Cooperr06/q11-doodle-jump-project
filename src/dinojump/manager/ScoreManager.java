package dinojump.manager;

import dinojump.Renderer;
import dinojump.util.Position;

public class ScoreManager
{
    private static ScoreManager instance;

    private int score;
    private Position position;
    private int scoreTextSize;
    
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
        score = 0;
        scoreTextSize = 20;
        position = new Position(10, 10);
        this.renderScore();
    }

    public void resetScore()
    {
        score = 0;
    }

    public void renderScore()
    {
        Renderer.getInstance().renderText(String.valueOf(score), position, scoreTextSize);
    }

    public void addScore(int add)
    {
        score += add;
    }

    public int getScore()
    {
        return score;
    }
}
