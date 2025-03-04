package mylist;

/**
 * MyArrayList class
 * @author Chilka Castro and Christian David
 */
public class MyArrayList<E> implements MyList<E> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs an empty list with an initial capacity of ten
     */
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Appends the specified element to the end of this list
     * @param e element to be appended to this list
     * @return True if the element was added
     */
    public Boolean add(E e) {
        if (size == elements.length) {  // if the size is equal to the length of the array, double the size
            elements = doubleSize(elements);
        }
        elements[size] = e; // add the element to the end of the array and increment the size
        size++;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list
     *
     * @param index index at which the specified element is to be inserted
     * @param e element to be inserted
     */
    public void add(int index, E e) {
        if (index > size || index < 0) { // if the index is out of bounds, throw an exception
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (size == elements.length) {  // if the size is equal to the length of the array, double the size
            elements = doubleSize(elements);
        }
        if (index < size) {           // if the index is less than the size, shift the elements to the right
            for (int i = size - 1; i >= index; i--) {
                elements[i + 1] = elements[i];
            }
        }
        elements[index] = e;           // insert the element at the specified index
        size++;
    }

    /**
     * Removes all elements from this list
     */
    public void clear() {
        for (int i = 0; i < size; i++)
            elements[i] = null;    // set all elements to null
        size = 0;
    }

    /**
     * Removes the element at the specified position in this list
     * @param index index of the element to be removed
     * @return the element that was removed
     */
    public E remove(int index) {
        if (size == 0) {
            throw new NullPointerException("List is empty because of size: " + size());
        }
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        E removedElement = (E) elements[index];  // store the element to return
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        elements[size] = null;   // set the last element to null

        if (size > DEFAULT_CAPACITY && size < elements.length / 4) { // if the size is less than 1/4 of the length of the array, half the size
            elements = halfSize(elements);
        }
        return removedElement;
    }

    /**
     * Removes the first occurrence of the specified element from this list
     * @param o object to remove
     * @return True or False
     */
    public Boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("Object is null");
        }
        for (int i = 0; i < size ; i++) {
            if (o.equals((E) elements[i])) {
                // deletes the object and moves the values
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                size--;
                elements[size] = null;
                if (size > DEFAULT_CAPACITY && size < elements.length / 4) {
                    elements = halfSize(elements);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of elements in this list
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the object
     * @return String representation of the object
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            if (elements[i] != null) {                            // if the element is not null, add it to the result string
                result += elements[i];
                if (i < size - 1 && elements[i + 1] != null) {   // if the next element is not null, add a comma
                    result += ", ";
                }
            }
            else {
                result += "," + elements[i];
            }
        }
        return String.format("[%s]", result);
    }

    /**
     * Doubles the size of the array
     * @param myList array to be doubled
     * @return the doubled array
     */
    public static Object[] doubleSize(Object[] myList) {
        Object[] temp = new Object[myList.length * 2];
        for (int i = 0; i < myList.length; i++) {
            temp[i] = myList[i];
        }
        return temp;
    }

    /**
     * Halves the size of the array
     * @param myList array to be halved
     * @return the halved array
     */
    public static Object[] halfSize(Object[] myList) {
        Object[] temp = new Object[myList.length / 2];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = myList[i];
        }
        return temp;
    }
}
