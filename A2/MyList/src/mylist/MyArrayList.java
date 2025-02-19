package mylist;

/**
 *
 * @author Chilka Castro and Christian David
 */
public class MyArrayList<E> implements List<E> {

    // public static int[] myList = new int[5];
    private Object[] myList = new Object[5];
    private int size = myList.length;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

    public void add(int index, E e) {
        if (index >= myList.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        } else {
            myList[index] = e;
        }
    }

    public Boolean add(E e) {
        for (int i = 0; i < myList.length; i++) {
            if (myList[i] == null) {
                myList[i] = e;
                return true;
            }
        }
        return false;
    }

    public void clear() {
        myList = new Object[5];
    }

    public E remove(int index) {
        if (index >= myList.length || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (myList[index] == null) {
            return null;
        }

        E temp = (E) myList[index];
        for (int i = index; i < myList.length - 1; i++) {
            myList[i] = myList[i + 1];
        }
        myList[myList.length - 1] = null;
        return temp;
    }

    public Boolean remove(Object o) {
        int index = 0;
        // finds the object
        for (int i = 0; i < myList.length; i++) {
            if (o.equals((E) myList[i])) {
                index = i;
                // deletes the object and moves the values
                for (int j = index; j < myList.length - 1; j++) {
                    myList[j] = myList[j + 1];
                }
                myList[myList.length - 1] = null;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return myList.length;
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < myList.length; i++) {
            if (myList[i] != null) {
                temp += myList[i] + " ";
            }
        }
        return temp;
    }

    public static Object[] doubleSize(Object[] myList) {
        Object[] temp = new Object[myList.length * 2];
        for (int i = 0; i < myList.length; i++) {
            temp[i] = myList[i];
        }
        return temp;
    }

    public static Object halfSize(Object[] myList) {
        Object[] temp = new Object[myList.length / 2];
        for (int i = 0; i < myList.length; i++) {
            temp[i] = myList[i];
        }
        return temp;

    }

    public static Boolean isOverSized(Object[] myList) {
        int counter = 0;
        for (int i = 0; i < myList.length; i++) {
            if (myList[i] == null) {
                counter++;
            }
        }
        if ((counter / myList.length * 100) <= (myList.length / myList.length * 100)) {
            halfSize(myList);
            return true;
        }
        return false;
    }

    // public static Boolean add(int n) {
    //// if (myList[myList.length - 1] != 0) {
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
