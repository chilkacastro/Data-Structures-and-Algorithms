package mylist;

/**
 *
 * @author Chilka Castro and Christian David
 */
public class ListTester {
    private static long start;
    private static long end;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        System.out.println(linkedList.add((Integer) 5));
        System.out.println(linkedList.remove(3));

        System.out.println(linkedList.toString());
        System.out.println(linkedList.size());

    }

    /**
     * Starts measuring the execution time of a block of code.
     */
    public static void startMeasureExecutionTime() {
        start = System.currentTimeMillis();
    }

    /**
     * Ends measuring the execution time of a block of code and prints the result.
     */
    public static void endMeasureExecutionTime() {
        end = System.currentTimeMillis();
        System.out.println(end - start + " ms");
    }
}
