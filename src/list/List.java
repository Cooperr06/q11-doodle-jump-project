package list;

public class List
{
    private ListElement first = new Leaf();

    public List()
    {
    }

    public void insertFirst(DataElement data)
    {
        first = first.insertLast(data);
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
        first.insertLast(dNew);
    }

    public DataElement removeLast()
    {
        DataElement temp = first.getEnd(null);
        first.removeEnd(temp);
        return temp;
    }
}
