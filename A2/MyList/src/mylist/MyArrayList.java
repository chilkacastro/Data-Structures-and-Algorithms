package mylist;

/**
 *
 * @author Chilka Castro and Christian David
 */
public class MyArrayList<E> implements List<E>{
 
//     public static int[] myList = new int[5];
    private Object[] myList;
    private int size;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//  
    }
    
    public static Boolean add(int n) {
//        if (myList[myList.length - 1] != 0) {
//            doubleSize();
//        }
//        for (int i = 0; i < myList.length; i++) {
//            if (myList[i] != 0) {
//                myList[i] = n;
//                return true;
//            }
//        }
        return false;
    }
    
    public static void add(int index, int n) {
//        if (myList.length - 1 != index) {
//            doubleSize();
//        }
//        for (int i = 0; i < myList.length; i++) {
//            if (i == index) {
//                myList[i] = n;
//            }
//        }
    }
    
    public void clear() {
//        int[] temp = new int[5];
//        myList = temp;
    }
    
    public int size() {
//        return myList.length;
    }
    
    public int remove(int index){
//        int temp = myList[index];
//        myList[index] = 0;
//        return temp;
        return 1;
    }
    
    public String toString() {
        StringBuilder temp = new StringBuilder("Numbers are: ");
        for (int i = 0; i < myList.length; i++) {
            temp.append(myList[i] + ", ");
        }
        return temp.toString();
    }
    
    public static void doubleSize() {
//        int[] temp = new int[myList.length * 2];
//        for (int i = 0; i < myList.length; i++) {
//            temp[i] = myList[i];
//        }
//        myList = temp;
    }
    
    public static void halfSize() {
//        if(isOverSized()) {
//            int[] temp = new int[myList.length / 2];
//            for (int i = 0; i < temp.length; i++) {
//                temp[i] = myList[i];
//            }
//            myList = temp;
//        }

    }
    
    public static Boolean isOverSized() {
//        int counter = 0;
//        for (int i = 0; i < myList.length; i++) {
//            if (myList[i] == 0) {
//               counter++; 
//            }
//        }
//        if((counter/myList.length * 100) <= (myList.length/myList.length * 100)) {
//            return true;
//        }
//        return false;
//    }

}
