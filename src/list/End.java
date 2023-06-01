package list;

public class End extends Listenelement
{
    public End()
    {

    }

    public Listenelement insertAtEnd(Datenelement dNeu)
    {
        return new node(dNeu, this);
    }

    public Dataelement getDataelement()
    {
        return null;
    }

    public Listelement getSuccessor()
    {
        return this;
    }
}
