package list;

public class List {
    private Listelement beginning = new End();

    public List()
    {

    }

    public void insertAtEnd(Dataelement dNeu) {
       beginning = beginning.insertAtEnd(dNeu);
    }

    public Dataelement removeBeginning()
    {
        Dataelement temp = null;
        temp = beginning.getDataelement();
        beginning = beginning.getSuccessor();
        return temp;
    }
}
