package dinojump.util;

import dinojump.Renderer;
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
    public int getPixelPositionX()
    {
        return (int) ((float) position.getX() / (float) Renderer.getInstance().getColumns() * (float) Renderer.getInstance().getScreenWidth());
    }

    @Override
    public int getPixelPositionY()
    {
        return -position.getY();
    }

    @Override
    public void setPosition(Position position)
    {
        this.position = position;
    }
}
