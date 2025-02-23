package mylist;

/**
 *
 * @author Chilka Castro and Christian David
 */
public class MyArrayList<E> implements List<E> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    // Constructor
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Appends the specified element to the end of this list
     * @param e element to be appended to this list
     * @return True or False if the element was added
     */
    public Boolean add(E e) {
//        for (int i = 0; i < size(); i++) {
//            if (elements[i] == null) {
//                elements[i] = e;
//                return true;
//            }
//        }
//        return false;
        if (size == elements.length) {  // if the size is equal to the length of the array, double the size
            elements = doubleSize(elements);
        }
        elements[size] = e; // add the element to the end of the array and increment the size
        size++;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index >= size || index < 0) { // if the index is out of bounds, throw an exception
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (size == elements.length) {  // if the size is equal to the length of the array, double the size
            elements = doubleSize(elements);
        }
        if (index < size) {           // if the index is less than the size, shift the elements to the right
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = e;           // insert the element at the specified index
        size++;                        // increment the size
    }

    /**
     * Removes all elements from this list
     */
    public void clear() {
        for (int i = 0; i < size; i++)
            elements[i] = null;    // set all elements to null
        size = 0;     // set the size to 0
    }

    /**
     *  Removes the element at the specified position in this list
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        E removedElement = (E) elements[index];  // store the element to return
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

// OPTION 2: INSTEAD OF FOR LOOP
//        int elementsToShift = size - index - 1;  // calculate the number of elements to shift to the left
//        if (elementsToShift > 0) {                  // if there are elements to shift to the left (i.e., not the last element)
//            System.arraycopy(elements, index + 1, elements, index, elementsToShift);
//        }

        size--;
        elements[size] = null;   // set the last element to null

        if (size > DEFAULT_CAPACITY && size < elements.length / 4) { // if the size is less than 1/4 of the length of the array, half the size
            elements = (Object[]) halfSize(elements);

        }

        return removedElement;
    }

    /**
     * Removes the first occurrence of the specified element from this list
     * @param o object to remove
     * @return True or False
     */
    public Boolean remove(Object o) {
        for (int i = 0; i < size ; i++) {
            if (o.equals((E) elements[i])) {
                // deletes the object and moves the values
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                size--;
                elements[size] = null;
                if (size > DEFAULT_CAPACITY && size < elements.length / 4) {
                    elements = (Object[]) halfSize(elements);
                }
                return true;
            }
        }
        return false;

    }
    /* OPTION 2: Using System.arraycopy
    public Boolean remove(Object o) {
    for (int i = 0; i < size; i++) {
        if (o.equals((E) elements[i])) {
            int elementsToShift = size - i - 1;
            if (elementsToShift > 0) {
                System.arraycopy(elements, i + 1, elements, i, elementsToShift);
            }
            size--;
            elements[size] = null;
            return true;
        }
    }
    return false;
}
     */

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
            if (elements[i] != null) {    // if the element is not null, add it to the result string
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

    public static Object[] doubleSize(Object[] myList) {
        Object[] temp = new Object[myList.length * 2];
        for (int i = 0; i < myList.length; i++) {         // for loop can be replace with this:   System.arraycopy(myList, 0, temp, 0, myList.length);
            temp[i] = myList[i];
        }
        return temp;
    }

    public static Object halfSize(Object[] myList) {
        System.out.println("entering halfSize");
        Object[] temp = new Object[myList.length / 2];
        System.out.println(temp.length);
        for (int i = 0; i < myList.length; i++) {          // for loop can be replace with this:   System.arraycopy(myList, 0, temp, 0, temp.length);
            temp[i] = myList[i];
        }
        System.out.println("Shrinking array from " + myList.length + " to " + temp.length);
        return temp;
    }



//--------------------------------------------------------------------------------



//    public static Boolean isOverSized(Object[] myList) {
//        int counter = 0;
//        for (int i = 0; i < myList.length; i++) {
//            if (myList[i] == null) {
//                counter++;
//            }
//        }
//        if ((counter / myList.length * 100) <= (myList.length / myList.length * 100)) {
//            halfSize(myList);
//            return true;
//        }
//        return false;
//    }

    // public static Boolean add(int n) {
    //// if (myList[elements.length - 1] != 0) {
    //// doubleSize();
    //// }
    //// for (int i = 0; i < myList.length; i++) {
    //// if (myList[i] != 0) {
    //// myList[i] = n;
    //// return true;
    //// }
    //// }
    // return false;
    // }
    //
    // public static void add(int index, int n) {
    //// if (myList.length - 1 != index) {
    //// doubleSize();
    //// }
    //// for (int i = 0; i < myList.length; i++) {
    //// if (i == index) {
    //// myList[i] = n;
    //// }
    //// }
    // }
    //
    // public void clear() {
    //// int[] temp = new int[5];
    //// myList = temp;
    // }
    //
    // public int size() {
    //// return myList.length;
    // }
    //
    // public int remove(int index){
    //// int temp = myList[index];
    //// myList[index] = 0;
    //// return temp;
    // return 1;
    // }
    //
    // public String toString() {
    // StringBuilder temp = new StringBuilder("Numbers are: ");
    // for (int i = 0; i < myList.length; i++) {
    // temp.append(myList[i] + ", ");
    // }
    // return temp.toString();
    // }

}
