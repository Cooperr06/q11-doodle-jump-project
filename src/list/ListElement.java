package list;

import java.util.function.Consumer;

public abstract class ListElement
{
    public ListElement()
    {
    }

    /**
     * Returns the data element of the list element with the specific index
     *
     * @param index current index
     * @return data element of the list element with the specific index
     */
    public abstract DataElement get(int index);

    /**
     * Inserts a data element at the end of the list
     *
     * @param data data to insert
     * @return node or leaf as new successor or new first element in list
     */
    public abstract ListElement insertLast(DataElement data);

    /**
     * Removes the list element with the specific data element from this list
     *
     * @param data list element's data element
     * @return new first list element or new successor
     */
    public abstract ListElement remove(DataElement data);

    /**
     * Performs an action on all list elements of a list
     *
     * @param action action to perform
     */
    public abstract void forEach(Consumer<DataElement> action);

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
