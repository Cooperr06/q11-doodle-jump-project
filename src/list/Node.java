package list;

import java.util.function.Consumer;

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
    public DataElement get(int index)
    {
        if (index == 0)
        {
            return data;
        }
        else
        {
            return successor.get(index - 1);
        }
    }

    @Override
    public ListElement insertLast(DataElement data)
    {
        successor = successor.insertLast(data);
        return this;
    }

    @Override
    public ListElement remove(DataElement data)
    {
        if (this.data == data)
        {
            return successor;
        }
        else
        {
            successor = successor.remove(data);
            return this;
        }
    }

    @Override
    public void forEach(Consumer<DataElement> action)
    {
        action.accept(data);
        successor.forEach(action);
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
