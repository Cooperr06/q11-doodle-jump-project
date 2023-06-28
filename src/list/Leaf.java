package list;

public class Leaf extends ListElement
{
    public Leaf()
    {
        super();
    }

    @Override
    public ListElement insertLast(DataElement data)
    {
        return new Node(data, this);
    }

    @Override
    public ListElement removeLast(DataElement temp)
    {
        return this;
    }

    @Override
    public DataElement findDataElement(String key)
    {
        return null;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public DataElement getLast(DataElement temp)
    {
        return temp;
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
