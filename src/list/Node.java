import list.Dataelement;

public class Node extends Listelement
{
    private Dataelement data;
    private Listenelement successor;

    public Node(Datenelement dNew, Listenelement nNew)
    {
        daten = dNew;
    }

    public Listelement insertAtEnd(Datenelement dNeu)
    {
        successor = successor.insertAtEnd(dNeu);
        return this;
    }
    public Dataelement getDataelement()
    {
        return data;
    }

    public Listelement getSuccessor()
    {
        return successor;
    }

}

