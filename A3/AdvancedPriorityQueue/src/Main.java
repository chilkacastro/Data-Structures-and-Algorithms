public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello, World!");

        AdvancedPriorityQueue pq = new AdvancedPriorityQueue();
        System.out.println(pq.toString());
        System.out.println(pq.toString());
        pq.toggle();
        pq.insert(5, "five");


        pq.insert(3, "three");
        pq.insert(2, "two");
        pq.insert(20, "four");
        pq.insert(1, "one");
        System.out.println(pq.toString());
//       System.out.println("KEY OF REMOVED TOP: " + pq.removeTop().key);
        Entry newEntry = new Entry(3, "three");
        Entry newEntry2 = new Entry(29, "three");
//        System.out.println("KEY OF REMOVED ENTRY: " + pq.remove(newEntry).key);
       pq.toggle();
        // TRYING REPLACE KEY WITH AN ENTRY
        System.out.println("KEY OF REPLACED ENTRY: " + pq.replaceKey(newEntry, 29));
        pq.toggle();
        System.out.println(newEntry.key + " " +  newEntry.value);
        pq.toggle();
        System.out.println("KEY OF REMOVED ENTRY: " + pq.replaceValue(newEntry2, "newValue"));
        System.out.println(pq.toString());
        pq.toggle();
        System.out.println(pq.top().key + " " + pq.top().value);
        System.out.println(pq.toString());
       pq.toggle();
        System.out.println("Key at index: " + pq.peekAt(1).key + ", " + "Value at index: " + pq.peekAt(1).value);

//        System.out.println(pq.removeTop().key);

    }
}