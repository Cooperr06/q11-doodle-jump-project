package list;

import dinojump.util.Position;
import dinojump.util.Skin;

public interface DataElement
{
    void iterateLoop();

    void moveTo(int x, int y);

    Skin getSkin();

    Position getPosition();

    void setPosition(Position position);
}
