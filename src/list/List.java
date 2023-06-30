package list;

import java.util.function.Consumer;

public class List
{
    private ListElement first = new Leaf();

    public List()
    {
    }

    public DataElement get(int index)
    {
        return first.get(index);
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

    public void insertLast(DataElement data)
    {
        first = first.insertLast(data);
    }

    public void remove(DataElement data)
    {
        first = first.remove(data);
    }

    public DataElement removeFirst()
    {
        DataElement temp = first.getDataElement();
        first = first.getSuccessor();
        return temp;
    }

    public void forEach(Consumer<DataElement> action)
    {
        first.forEach(action);
    }

    public int size()
    {
        return first.size();
    }
}
