public class Abschluss extends Listenelement
{
    public Abschluss()
    {

    }

    public Listenelement hintenEinfuegen(Datenelement dNeu)
    {
        return new Knoten(dNeu, this);
    }

    public Datenelement datenelmentGeben()
    {
        return null;
    }

    @Override
    public Listenelement nachfolgerGeben()
    {
        return this;
    }
}
