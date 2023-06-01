package list;

public class List {
    private Listelement beginning;

    public List()
    {
        beginning = new End();
    }

    public void insertAtEnd(Dataelement dNeu) {
       beginning = beginning.insertAtEnd(dNeu);
    }

    public Datenelement removeBeginning()
    {
        Dataelement temp = beginning.getDataelement();
        beginning = beginning.getSuccessor();
        return temp;
    }
}
