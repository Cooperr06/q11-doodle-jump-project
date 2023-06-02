package list;

public class List
{
    private Listelement beginning;
    {
        beginning = new End();
    }

    public void insertAtEnd(Dataelement dNeu)
    {
        beginning = beginning.insertAtEnd(dNeu);
    }

    public Dataelement removeBeginning()
    {
        Dataelement temp = null;
        temp = beginning.getDataelement();
        beginning = beginning.getSuccessor();
        return temp;
    }

    public void insertAtBeginning(Dataelement dNew)
    {
        beginning = new Node(dNew, beginning);
    }

    public int getLength()
    {
        return beginning.getLength();
    }
}
