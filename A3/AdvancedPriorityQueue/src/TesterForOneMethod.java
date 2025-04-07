public class TesterForOneMethod {
    public static void main(String[] args) {
//        AdvancedPriorityQueue apq = new AdvancedPriorityQueue();
//        apq.toggle();
//        apq.insert(1, "A");
//        apq.insert(10, "B");
//        Entry e1 = apq.insert(5, "C");
//        apq.insert(20, "D");
//        apq.insert(15, "E");
//        apq.insert(7, "F");
//        apq.insert(3, "G");
//        apq.insert(2, "H");
//        apq.insert(4, "I");
//        //  apq.toggle();
////        apq.toggle();
//        apq.printEntries();   // readable than toString method
//        apq.toggle();
//        System.out.println("Top: " + apq.top());
//        System.out.println("Queue Peek: " + apq.peekAt(1));
//        apq.printEntries();
//        apq.toggle();
//        System.out.println(apq.replaceKey(e1, 9));
//        apq.printEntries();
//        apq.remove(e1);
//        apq.printEntries();
//        //
//        System.out.println("TESTING PEEK");
//        apq.printEntries();
//
//        System.out.println("Queue Peek: " + apq.peekAt(9));
//
//        apq.toggle();
//        apq.printEntries();
//        System.out.println("Queue Peek: " + apq.peekAt(9));


        AdvancedPriorityQueue apq = new AdvancedPriorityQueue();
        apq.toggle();
         System.out.println("Queue: " + apq.toString());


//        apq.toggle();
        Entry e1 = apq.insert(10, "A");
        Entry e2 = apq.insert(5, "B");
        apq.insert(20, "C");
        apq.insert(15, "D");
        Entry e4 = apq.insert(7, "E");
        apq.insert(7, "S");
        apq.printEntries();
        apq.toggle();
        System.out.println("Replace Key: " + apq.replaceKey(e2, 3));
        System.out.println("Replace Key: " + apq.replaceKey(e4, 9));
        apq.toggle();
        System.out.println("Queue Peek: " + apq.peekAt(3));
        apq.printEntries();



    }

}

