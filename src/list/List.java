package list;

public class List
{
    private ListElement first = new Leaf();

    public List()
    {
    }

    public ListElement getFirst()
    {
        return first;
    }

    public void insertFirst(DataElement data)
    {
        ListElement storage = first;
        first = new Node(data, storage);
    }

    public DataElement removeFirst()
    {
        DataElement temp = first.getDataElement();
        first = first.getSuccessor();
        return temp;
    }

    public int size()
    {
        return first.size();
    }

    public void insertLast(DataElement dNew)
    {
        first = first.insertLast(dNew);
    }

    public DataElement removeLast()
    {
        DataElement temp = first.getEnd(null);
        first = first.removeEnd(temp);
        return temp;
    }

    public DataElement findDataElement(String key)
    {
        return first.findDataElement(key);
    }
}
