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

    public void insertLast(DataElement data)
    {
        first = first.insertLast(data);
    }

    public int size()
    {
        return first.size();
    }

    public DataElement removeLast()
    {
        DataElement temp = first.getLast(null);
        first = first.removeLast(temp);
        return temp;
    }

    public DataElement findDataElement(String key)
    {
        return first.findDataElement(key);
    }
}
