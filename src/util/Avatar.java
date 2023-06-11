package util;

public class Avatar implements Movable
{
    private final Skin skin;

    private Position position;
    private int score;

    private int xVelocity;
    private int yVelocity;

    public Avatar(Skin skin, Position position)
    {
        this.skin = skin;
        this.position = position;
    }

    @Override
    public void moveTo(int x, int y)
    {
        position.setX(x);
        position.setY(y);
    }

    public Skin getSkin()
    {
        return skin;
    }

    @Override
    public Position getPosition()
    {
        return position;
    }

    @Override
    public void setPosition(Position position)
    {
        this.position = position;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getXVelocity()
    {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity)
    {
        this.xVelocity = xVelocity;
    }

    public int getYVelocity()
    {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity)
    {
        this.yVelocity = yVelocity;
    }
}
