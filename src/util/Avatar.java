package util;

public class Avatar implements Movable
{

    private Position position;

    private final Skin skin;

    private int score;

    public Avatar(Skin skin, Position position)
        {
            this.skin = skin;
            this.position = position;
        }
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

    public int Score getScore()
    {
        return score;
    }

    @Override
    public void setPosition(Position position)
        {
            this.position = position;
        }


}

