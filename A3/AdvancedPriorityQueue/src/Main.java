public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello, World!");

        AdvancedPriorityQueue pq = new AdvancedPriorityQueue();
        System.out.println(pq.toString());
        pq.insert(5, "five");
        pq.insert(3, "three");
        pq.insert(2, "two");
        pq.insert(20, "four");
        pq.insert(1, "one");
        pq.toggle();
        System.out.println(pq.toString());

//        pq.toggle();
//        System.out.println(pq.toString());
////       System.out.println("KEY OF REMOVED TOP: " + pq.removeTop().key);
//        Entry newEntry = new Entry(3, "three");
//        Entry newEntry2 = new Entry(29, "three");
////        System.out.println("KEY OF REMOVED ENTRY: " + pq.remove(newEntry).key);
//        // TRYING REPLACE KEY WITH AN ENTRY
//        System.out.println("KEY OF REPLACED ENTRY: " + pq.replaceKey(newEntry, 29));
//        System.out.println(newEntry.key + " " +  newEntry.value);
//        System.out.println("KEY OF REMOVED ENTRY: " + pq.replaceValue(newEntry2, "newValue"));
//        System.out.println(pq.toString());
////        pq.toggle();
////        System.out.println(pq.top().key + " " + pq.top().value);
////        System.out.println(pq.toString());
        pq.toggle();
        System.out.println(pq.toString());
        System.out.println("Key at index: " + pq.peekAt(2).key + ", " + "Value at index: " + pq.peekAt(2).value);

//        System.out.println(pq.removeTop().key);
        AdvancedPriorityQueue pq2 = new AdvancedPriorityQueue();
        pq2.insert(43, "fourty-three");
        pq2.insert(12, "twelve");
        pq2.insert(52, "fifty-two");
        pq2.insert(8, "eight");

        pq.merge(pq2);
        pq.toggle();
        System.out.println("Merged Priority Queue: " + pq.toString());
//        System.out.println("Merged Priority Queue: " + pq2.toString());
    }
}