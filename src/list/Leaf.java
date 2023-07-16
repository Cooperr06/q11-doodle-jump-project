package list;

import java.util.function.Consumer;

public class Leaf extends ListElement
{
    public Leaf()
    {
        super();
    }

    @Override
    public DataElement get(int index)
    {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ListElement insertLast(DataElement data)
    {
        return new Node(data, this);
    }

    @Override
    public ListElement insertSorted(DataElement data)
    {
        return insertLast(data);
    }

    @Override
    public ListElement remove(DataElement temp)
    {
        return this;
    }

    @Override
    public void forEach(Consumer<DataElement> action)
    {
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
