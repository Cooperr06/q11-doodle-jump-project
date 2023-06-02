package list;

public class Leaf extends ListElement
{
    public Leaf()
    {
        super();
    }

    @Override
    public ListElement insertAtEnd(DataElement data)
    {
        return new Node(data, this);
    }

    @Override
    public DataElement getDataElement()
    {
        return null;
    }

    @Override
    public ListElement getSuccessor()
    {
        return this;
    }
}
