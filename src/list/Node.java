package list;

public class Node extends ListElement
{
    private final DataElement data;

    private ListElement successor;

    public Node(DataElement data, ListElement successor)
    {
        super();
        this.data = data;
        this.successor = successor;
    }

    public Node(DataElement data)
    {
        super();
        this.data = data;
    }

    @Override
    public ListElement insertLast(DataElement data)
    {
        successor = successor.insertLast(data);
        return this;
    }

    @Override
    public ListElement removeLast(DataElement temp)
    {
        if (data == temp)
        {
            return successor;
        }
        else
        {
            successor = successor.removeLast(temp);
            return this;
        }
    }

    @Override
    public DataElement findDataElement(String key)
    {
        if (data.compareKey(key))
        {
            return data;
        }
        else
        {
            return successor.findDataElement(key);
        }
    }

    @Override
    public int size()
    {
        return successor.size() + 1;
    }

    @Override
    public DataElement getLast(DataElement temp)
    {
        return successor.getLast(data);
    }

    @Override
    public DataElement getDataElement()
    {
        return data;
    }

    @Override
    public ListElement getSuccessor()
    {
        return successor;
    }
}
