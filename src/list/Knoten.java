public class Knoten extends Listenelement
{
    private Datenelement daten;
    private Listenelement nachfolger;

    public Knoten(Datenelement dNeu, Listenelement nNeu)
    {
        daten = dNeu;
    }

    public Listenelement hintenEinfuegen(Datenelement dNeu)
    {
        nachfolger = nachfolger.hintenEinfuegen(dNeu);
        return this;
    }
    public Datenelement datenelementGeben()
    {
        return daten;
    }

    public Listenelement nachfolgerGeben()
    {
        return nachfolger;
    }

}

