package list;

public class Node extends Listelement
{
    private final Dataelement data;
    private Listelement successor;

    public Node(Dataelement dNew, Listelement nNew)
    {
        data = dNew;
    }


    @Override
    public Listelement insertAtEnd(Dataelement dNeu)
    {
        successor = successor.insertAtEnd(dNeu);
        return this;
    }

    @Override
    public Listelement getSuccessor()
    {
        return successor;
    }

    @Override
    public int getLength()
    {
        return successor.getLength() + 1;
    }

    @Override
    public Dataelement getDataelement()
    {
        return data;
    }
}

