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
    public DataElement getDataElement()
    {
        return null;
    }

    @Override
    public ListElement getSuccessor()
    {
        return this;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public DataElement getEnd(DataElement temp)
    {
        return temp;
    }

    @Override
    public ListElement removeEnd(DataElement temp)
    {
        return this;
    }
}
