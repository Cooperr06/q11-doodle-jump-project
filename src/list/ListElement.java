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
    public abstract ListElement insertAtEnd(DataElement data);

    public abstract DataElement getDataElement();

    public abstract ListElement getSuccessor();
}
