package list;

public abstract class ListElement
{
    public ListElement()
    {
    }

    /**
     * Inserts a data element at the end of the list
     *
     * @param data data to insert
     * @return node or leaf as new successor or new first element in list
     */
    public abstract ListElement insertLast(DataElement data);

    public abstract DataElement getDataElement();

    public abstract ListElement getSuccessor();

    public abstract int size();

    public abstract DataElement getEnd(DataElement temp);

    public abstract ListElement removeEnd(DataElement temp);

    public abstract DataElement findDataElement(String key);
}
