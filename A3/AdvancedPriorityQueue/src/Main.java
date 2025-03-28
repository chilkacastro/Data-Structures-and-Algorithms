public class Main {
    public static void main(String[] args)
    {
        AdvancedPriorityQueue pq = new AdvancedPriorityQueue();
        System.out.println(pq.toString());
        Entry e1 = pq.insert(5, "five");
        Entry e2 = pq.insert(3, "three");
        Entry e3= pq.insert(2, "two");
        Entry e4 = pq.insert(20, "four");
        Entry e5 = pq.insert(1, "one");
        pq.toggle();
        System.out.println(pq.toString());

//        pq.toggle();
//        System.out.println(pq.toString());
////       System.out.println("KEY OF REMOVED TOP: " + pq.removeTop().key);

////        System.out.println("KEY OF REMOVED ENTRY: " + pq.remove(newEntry).key);
        // TRYING REPLACE KEY WITH AN ENTRY
        System.out.println("KEY OF REPLACED ENTRY: " + pq.replaceKey(e2, 29));
        System.out.println(pq.toString());
//        System.out.println(newEntry.key + " " +  newEntry.value);
//        System.out.println("KEY OF REMOVED ENTRY: " + pq.replaceValue(newEntry2, "newValue"));
//        System.out.println(pq.toString());
////        pq.toggle();
////        System.out.println(pq.top().key + " " + pq.top().value);
////        System.out.println(pq.toString());
        pq.toggle();
        System.out.println(pq.toString());
//        System.out.println("Key at index: " + pq.peekAt(2).key + ", " + "Value at index: " + pq.peekAt(2).value);
//
////        System.out.println(pq.removeTop().key);
//        AdvancedPriorityQueue pq2 = new AdvancedPriorityQueue();
//        pq2.insert(43, "fourty-three");
//        pq2.insert(12, "twelve");
//        pq2.insert(52, "fifty-two");
//        pq2.insert(8, "eight");

//        pq.merge(pq2);
//        pq.toggle();
//        System.out.println("Merged Priority Queue: " + pq.toString());
//        System.out.println("Merged Priority Queue: " + pq2.toString());
    }
}