package dinojump.util;

import list.DataElement;

public class Platform implements Movable, DataElement
{
    private final Skin skin;

    private Position position;

    public Platform(Skin skin, Position position)
    {
        this.skin = skin;
        this.position = position;
    }

    @Override
    public void iterateLoop()
    {
        updatePosition();
    }

    public void updatePosition()
    {
        position.setY(position.getY() - Avatar.getInstance().getYVelocity());
    }

    @Override
    public void moveTo(int x, int y)
    {
        position.setX(x);
        position.setY(y);
    }

    @Override
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
}
