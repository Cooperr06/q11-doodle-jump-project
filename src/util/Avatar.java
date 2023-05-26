package util;

public class Avatar implements Movable
{
    private final Skin skin;

    private Position position;
    private int score;

    public Avatar(Position position, Skin skin)
    {
        this.position = position;
        this.skin = skin;
    }

    @Override
    public void moveTo(int x, int y) {
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
    public void setPosition(Position position) {
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
}
