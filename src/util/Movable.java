package util;

public interface Movable
{
    void moveTo(int x, int y);

    Position getPosition();

    void setPosition(Position position);
}
