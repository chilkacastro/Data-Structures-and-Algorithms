public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello, World!");

        AdvancedPriorityQueue pq = new AdvancedPriorityQueue();
        System.out.println(pq.toString());
        pq.toggle(); // Switch to max heap
        System.out.println(pq.toString());
//
        pq.insert(5, "five");


        pq.insert(3, "three");
        pq.insert(2, "two");
        pq.insert(20, "four");
        pq.insert(1, "one");
        System.out.println(pq.toString());
        pq.toggle();
        System.out.println(pq.toString());

    }
}