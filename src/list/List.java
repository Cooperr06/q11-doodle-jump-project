package list;

public class List
{
    private ListElement first = new Leaf();

    public List()
    {
    }

    public void insertAtEnd(DataElement data)
    {
        first = first.insertAtEnd(data);
    }

    public DataElement removeBeginning()
    {
        DataElement temp = first.getDataElement();
        first = first.getSuccessor();
        return temp;
    }
}
