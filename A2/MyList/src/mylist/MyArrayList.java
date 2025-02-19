package mylist;

/**
 *
 * @author Chilka Castro and Christian David
 */
public class MyArrayList {
 
     public static int[] myList = new int[5];


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//  
    }
    
    public static Boolean add(int n) {
        for (int i = 0; i < myList.length; i++) {
            if (myList[i] != 0) {
                myList[i] = n;
                return true;
            }
        }
        return false;
    }
    
    public static void add(int index, int n) {
        for (int i = 0; i < myList.length; i++) {
            if (i == index) {
                myList[i] = n;
            }
        }
    }
    
    public static void clear() {
        int[] temp = new int[myList.length];
        myList = temp;
    }
    
    public static int size() {
        return myList.length;
    }
    
    public String toString() {
        StringBuilder temp = new StringBuilder("Numbers are: ");
        for (int i = 0; i < myList.length; i++) {
            temp.append(myList[i] + ", ");
        }
        return temp.toString();
    }
    
    public static void doubleSize() {
        int[] temp = new int[myList.length * 2];
        for (int i = 0; i < myList.length; i++) {
            temp[i] = myList[i];
        }
        myList = temp;
    }
    
    public static void halfSize() {
        int[] temp = new int[myList.length / 2];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = myList[i];
        }
        myList = temp;
    }

}
