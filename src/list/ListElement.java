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

    /**
     * Removes the last list element in the list
     *
     * @param temp data element of the last list element
     * @return node or leaf as new successor or new first element in list
     */
    public abstract ListElement removeLast(DataElement temp);

    /**
     * Finds a data element by its key
     *
     * @param key key of the data element
     * @return data element with the specific key or null if none was found
     */
    public abstract DataElement findDataElement(String key);

    /**
     * Returns the size of the list
     *
     * @return size of the list
     */
    public abstract int size();

    /**
     * Returns the last list element of the list
     *
     * @param temp data element of the current list element (required because of recursion)
     * @return data element of the last list element of the list
     */
    public abstract DataElement getLast(DataElement temp);

    /**
     * Returns the data element of this list element
     *
     * @return data element of this list element
     */
    public abstract DataElement getDataElement();

    /**
     * Returns the successor of this list element
     *
     * @return successor of this list element
     */
    public abstract ListElement getSuccessor();
}
