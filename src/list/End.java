package list;

public class End extends Listelement
{
    public End()
    {

    }

    @Override
    public Listelement insertAtEnd(Dataelement dNeu)
    {
        return new Node(dNeu, this);
    }

    @Override
    public Listelement getSuccessor()
    {
        return this;
    }

    @Override
    public int getLength()
    {
        return 0;
    }

    @Override
    public Dataelement getDataelement()
    {
        return null;
    }
}
