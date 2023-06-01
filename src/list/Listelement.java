package list;

import list.Dataelement;

public abstract class Listelement {

    public Listelement()
    {

    }

    public abstract Listelement insertAtEnd(Dataelement dNeu);

    public abstract Dataelement getDataelement();

    public abstract Listelement getSuccessor();
}
