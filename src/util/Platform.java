package util;
// class Platform gets created with two variables which gain values from the constructor
public class Platform implements Moveable
{
    private Skin skin;
    private Position position;
    public Platform(Skin skin, Position position)
    {
        this.skin = skin;
        this.position = position;
    }
    public void moveTo(int x,int y)
    {
        position.setX(x);
        position.setY(y);
    }
    Position getPosition()
    {
        return position;
    }
    void setPosition(Position position)
    {
        this.position = position;
    }
}
