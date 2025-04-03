public class TesterForOneMethod {
    public static void main(String[] args) {
        AdvancedPriorityQueue apq = new AdvancedPriorityQueue();
        apq.toggle();
        apq.insert(1, "A");
        apq.insert(10, "B");
        apq.insert(5, "C");
        apq.insert(20, "D");
        apq.insert(15, "E");
        apq.insert(7, "F");
        apq.insert(3, "G");
        apq.insert(2, "H");
        apq.insert(4, "I");
        //  apq.toggle();
//        apq.toggle();
        apq.printEntries();   // readable than toString method
        apq.toggle();
        System.out.println("Top: " + apq.top());
//        apq.toggle();
        // System.out.println("Queue: " + apq.toString());


//        AdvancedPriorityQueue apq = new AdvancedPriorityQueue();
////        apq.toggle();
//        Entry e1 = apq.insert(10, "A");
//        Entry e2 = apq.insert(5, "B");
//        apq.insert(20, "C");
//        apq.insert(15, "D");
//        apq.insert(7, "E");
//        apq.insert(7, "S");
//        System.out.println("Queue: " + apq.toString());
//        AdvancedPriorityQueue apq2 = new AdvancedPriorityQueue();
//        apq2.insert(14, "F");
//        apq2.insert(11, "G");
//        apq2.insert(3, "H");
//        apq.insert(6, "I");
//        System.out.println("Queue: " + apq.toString());
//        apq.merge(apq2);
//        System.out.println("Queue: " + apq.toString());
//        apq.insert(1, "J");
//        System.out.println("Queue: " + apq.toString());

//        System.out.println("Queue: " + apq.toString());
//        apq.toggle();
//        System.out.println("Queue: " + apq.toString());
//        System.out.println("Top: " + apq.top());
//        apq.toggle();
//        System.out.println("Top: " + apq.top());
//        System.out.println("Queue: " + apq.toString());
//        System.out.println(apq.removeTop());
//        System.out.println("Queue: " + apq.toString());
//        System.out.println(apq.remove(e2));
//        System.out.println("Queue: " + apq.toString());
//        System.out.println(apq.replaceKey(e1, 35));
//        System.out.println(apq);
//        apq.toggle();
//        System.out.println(apq);
//        apq.removeTop();
//        System.out.println(apq);



    }
}
