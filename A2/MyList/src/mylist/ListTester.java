package mylist;

/**
 *
 * @author Chilka Castro and Christian David
 */
public class ListTester {
     private static long  start;
     private static long end;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyArrayList<Integer> myList = new MyArrayList<>();
        for (int i = 0; i < 50; i++) {
            myList.add(i);
        }

        System.out.println("Array Size before removals: " + myList.size());
        System.out.println(myList.toString());

        // Remove elements to trigger halfSize
        for (int i = 0; i < 20; i++) {
            myList.remove(i);
        }

        System.out.println("Array Size after removals: " + myList.size());
        System.out.println(myList.toString());
    }


//        myList.add(50);
//        myList.add(70);
//        myList.add(80);
//        myList.add(100);
//        myList.add(90);
//        myList.add(600);
//        myList.add(130);
//        myList.add(90);
//        myList.add(400);
//        myList.add(500);
//        myList.add(700);
//        myList.add(7410);
//        myList.add(7450);
//        myList.add(790);
//        myList.add(950);
//        myList.add(800);
//        myList.add(1000);
//        myList.add(1100);
//        myList.add(134200);
//        myList.add(6923);
//        myList.add(6923);
//        myList.add(71230);
//
//
//        myList.add(81230);
//        myList.add(81230);
//        myList.add(81230);
//        myList.add(81230);
//        myList.add(81230);
//        myList.add(103210);
//        myList.add(93233);
//        myList.add(623);
//        myList.add(623);
//        myList.add(623);
//        myList.add(123013);
//        myList.add(123011243);
//
//        myList.add(6923);
//        myList.add(623);
//        myList.add(4321);
//        myList.add(9876);
//        myList.add(40042);
//
//        myList.add((Integer) 700);
//        myList.add((Integer) 7410);
//        myList.add((Integer) 7450);
//        myList.add((Integer) 790);
//        myList.add((Integer) 950);
//        myList.add((Integer) 800);
//        myList.add((Integer) 1000);
//        myList.add((Integer) 1100);
//        myList.add((Integer) 134200);
//        myList.add((Integer) 1213);
//        myList.add((Integer) 13131);
//        myList.add((Integer) 7554);
//        myList.add((Integer) 94384);
//        myList.add((Integer) 1921);
//        myList.add((Integer) 8239);
//        myList.add((Integer) 123);
//        myList.add((Integer) 1234);
//        myList.add((Integer) 5678);
//        myList.add((Integer) 1234);
//        myList.add((Integer) 5678);
//        myList.add((Integer) 1234);
//        myList.add((Integer) 5678);
//        myList.add((Integer) 1234);
//        System.out.println(myList.add((Integer) 1200));
//
//        System.out.println("Array Size:" + myList.size());
//        System.out.println(myList.toString());
//        myList.add(4, 30);
//        System.out.println("Array Size:" + myList.size());
//        System.out.println(myList.toString());
//        myList.remove(3);
//        System.out.println(myList.toString());
//        System.out.println("Array Size:" + myList.size());
//        System.out.println(myList.remove((Integer)90));
//        System.out.println(myList.toString());


    public static void startMeasureExecutionTime() {
        start = System.currentTimeMillis();
    }

    public static void endMeasureExecutionTime() {
        end = System.currentTimeMillis();
        System.out.println(end - start + " ms");
    }


}
