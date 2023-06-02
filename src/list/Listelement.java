package list;

import list.Dataelement;

public abstract class Listelement {

    public Listelement()
    {

    }

    public abstract Listelement insertAtEnd(Dataelement dNew);

    public abstract Listelement getSuccessor();

    public abstract int getLength();

    public abstract Dataelement getDataelement();
}
