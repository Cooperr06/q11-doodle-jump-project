package dinojump.util;

import dinojump.Renderer;
import dinojump.manager.InputManager;

public class Score
{
    private int score;
    private Position position;
    private int scoreTextSize;

    private static Score instance;
    public static  Score getInstance()
    {
        if (instance == null)
        {
            instance = new Score();
        }
        return instance;
    }
    private Score()
    {
        score = 0;
        scoreTextSize = 20;
        position.setX(10);
        position.setY(10);
        this.renderScore();
    }

    // resets score to 0, call on exit/retry
    public void resetScore()
    {
        score = 0;
    }

    // returns current score
    public int getScore()
    {
        return score;
    }

    // renders Score
    public void renderScore()
    {
        Renderer.getInstance().renderText(String.valueOf(getScore()), position, scoreTextSize);
    }

    public void addScore(int add)
    {
        score += add;
    }
}
