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
    public ListElement insertAtEnd(DataElement data)
    {
        successor = successor.insertAtEnd(data);
        return this;
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

