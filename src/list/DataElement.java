package list;

public interface DataElement
{
    public abstract void moveTo(int x, int y);
    public abstract Skin getSkin();
    public abstract Position getPosition();
    public abstract void setPosition(Position position);
    public abstract void iterateLoop();

}
