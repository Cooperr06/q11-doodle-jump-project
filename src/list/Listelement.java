import list.Dataelement;

public abstract class Listelement {

    public Listelement()
    {

    }

    public abstract Listelement insertAtEnd(Dataelement dNeu);

    public abstract Dataelement getDataelmenet();

    public abstract Listelement getSuccessor();
}
