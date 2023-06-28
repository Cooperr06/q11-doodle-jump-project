package list;

import util.Position;
import util.Skin;

public interface DataElement
{
    void iterateLoop();

    boolean compareKey(String key);

    void moveTo(int x, int y);

    Skin getSkin();

    Position getPosition();

    void setPosition(Position position);
}
