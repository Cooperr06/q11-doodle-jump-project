public class Liste {
    private Listenelement anfang;

    public Liste()
    {
        anfang = new Abschlus();
    }

    public void vorneEinfuegen(Datenelement dNeu) {
       anfang = anfang.hintenEinfuegen(dNeu);
    }

    public Datenelement anfangEntfernen()
    {
        Datenelement temp = anfang.datenelementGeben();
        anfang = anfang.nachfolgerGeben();
        return temp;
    }
}
