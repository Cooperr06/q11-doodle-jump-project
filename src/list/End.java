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
    public Dataelement getDataelement()
    {
        return null;
    }

    @Override
    public Listelement getSuccessor()
    {
        return this;
    }


}
