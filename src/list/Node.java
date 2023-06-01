package list;

import list.Dataelement;

public class Node extends Listelement
{
    private Dataelement data;
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
    public Dataelement getDataelement()
    {
        return data;
    }

    @Override
    public Listelement getSuccessor()
    {
        return successor;
    }
}

