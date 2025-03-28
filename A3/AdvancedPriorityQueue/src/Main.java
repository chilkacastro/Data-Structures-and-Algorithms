public class Main {

    /**
     * Helper method to find an Entry by key in the APQ.
     * Returns null if not found.
     */
    private static Entry findEntryByKey(AdvancedPriorityQueue apq, int key) {
        for (int i = 0; i < apq.size(); i++) {
            Entry e = apq.peekAt(i);
            if (e.getKey() == key) {
                return e;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Create a new APQ (min-heap by default)
        System.out.println("=====================================================");
        AdvancedPriorityQueue apq = new AdvancedPriorityQueue();
        System.out.println("1. Created a new APQ.");
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");

        // Insert several items
        System.out.println("2. Inserting items: (10, A), (5, B), (20, C), (15, D), (7, E).");
        apq.insert(10, "A");
        apq.insert(5, "B");
        apq.insert(20, "C");
        apq.insert(15, "D");
        apq.insert(7, "E");
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");

        // top() should return the smallest key (min-heap)
        System.out.println("3. Top element (min-heap): " + apq.top() + "\n");
        System.out.println("=====================================================");

        // removeTop() should remove the smallest element
        System.out.println("4. Removing top element: " + apq.removeTop());
        System.out.println("Queue after removal: " + apq + "\n");
        System.out.println("=====================================================");

        // Remove a specific entry (remove entry with key=15)
        System.out.println("5. Removing entry with key=15.");
        Entry e15 = findEntryByKey(apq, 15);
        if (e15 != null) {
            apq.remove(e15);
            System.out.println("Entry with key=15 removed.");
        } else {
            System.out.println("Entry with key=15 not found.");
        }
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");

        // Insert additional items to force expansion
        System.out.println("6. Inserting extra items to force double size.");
        for (int i = 1; i <= 30; i++) {
            apq.insert(i, "Extra" + i);
        }
        System.out.println("Queue after extra inserts: " + apq + "\n");
        System.out.println("=====================================================");


        // Increase an existing key using replaceKey (change key 20 to 35)
        System.out.println("7. Increasing key using replaceKey (change 20 -> 35).");
        Entry e20 = findEntryByKey(apq, 20);
        if (e20 != null) {
            int oldKey = apq.replaceKey(e20, 35);
            System.out.println("Replaced key " + oldKey + " with " + e20.getKey());
        } else {
            System.out.println("Entry with key=20 not found.");
        }
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");


        // Decrease an existing key using replaceKey (try changing key 10 to 2)
        System.out.println("8. Decreasing key using replaceKey (change 10 -> 2).");
        Entry e10 = findEntryByKey(apq, 10);
        if (e10 != null) {
            int oldKey = apq.replaceKey(e10, 2);
            System.out.println("Replaced key " + oldKey + " with " + e10.getKey());
        } else {
            System.out.println("Entry with key=10 not found.");
        }
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");

        // Change the value for an entry using replaceValue (change value for key=7 to "Z")
        System.out.println("9. Changing value for entry with key=7 to 'Z'.");
        Entry e7 = findEntryByKey(apq, 7);
        if (e7 != null) {
            String oldVal = apq.replaceValue(e7, "Z");
            System.out.println("Replaced value '" + oldVal + "' with '" + e7.getValue() + "'");
        } else {
            System.out.println("Entry with key=7 not found.");
        }
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");

        // peekAt() with a valid index
        System.out.println("10. peekAt(0) returns: " + apq.peekAt(0) + "\n");
        System.out.println("=====================================================");

        // peekAt() with an invalid index (expect an exception)
        System.out.println("11. Calling peekAt with an invalid index:");
        try {
            apq.peekAt(apq.size() + 1);
        } catch (Exception ex) {
            System.out.println("Caught exception: " + ex.getMessage());
        }
        System.out.println();
        System.out.println("=====================================================");

        // Toggle to max-heap mode
        System.out.println("12. Toggling to max-heap mode.");
        apq.toggle();
        System.out.println("Current state: " + apq.state());
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");

        // In max-heap mode, removeTop() should remove the largest element
        System.out.println("13. Removing top element in max-heap: " + apq.removeTop());
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");

        // Toggle back to min-heap mode
        System.out.println("14. Toggling back to min-heap mode.");
        apq.toggle();
        System.out.println("Current state: " + apq.state());
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");

        // Merge with a second APQ containing (50, X) and (0, Y)
        System.out.println("15. Merging with a second APQ containing (50, X) and (0, Y).");
        AdvancedPriorityQueue apq2 = new AdvancedPriorityQueue();
        apq2.insert(50, "X");
        apq2.insert(0, "Y");
        apq.merge(apq2);
        System.out.println("Queue after merge: " + apq + "\n");
        System.out.println("=====================================================");

        // Remove all entries one by one
        System.out.println("16. Removing all entries one by one.");
        while (!apq.isEmpty()) {
            System.out.println("Removed: " + apq.removeTop());
        }
        System.out.println("Queue now: " + apq);
        System.out.println("isEmpty()? " + apq.isEmpty() + "\n");
        System.out.println("=====================================================");

        // Attempt removeTop() on an empty queue (expect exception)
        System.out.println(" 17. Attempting to removeTop() from an empty queue:");
        try {
            apq.removeTop();
        } catch (Exception ex) {
            System.out.println("Caught expected exception: " + ex.getMessage());
        }
        System.out.println();
        System.out.println("=====================================================");

        // Toggle on an empty queue (should work without error)
        System.out.println("18. Toggling on an empty queue.");
        apq.toggle();
        System.out.println("Current state: " + apq.state() + "\n");
        System.out.println("=====================================================");

        // Insert into the empty queue
        System.out.println("19. Inserting (10, Q) into the empty APQ.");
        apq.insert(10, "Q");
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");

        // Insert additional items to double size
        System.out.println("20. Inserting items (11, R11) to (20, R20) to double size.");
        for (int i = 11; i <= 20; i++) {
            apq.insert(i, "R" + i);
        }
        System.out.println("Queue after expansion: " + apq + "\n");
        System.out.println("=====================================================");

        // Remove a specific entry (attempt to remove entry with key=14)
        System.out.println("21. Removing entry with key=14 (if present).");
        Entry e14 = findEntryByKey(apq, 14);
        if (e14 != null) {
            apq.remove(e14);
            System.out.println("Entry with key=14 removed.");
        } else {
            System.out.println("Entry with key=14 not found.");
        }
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");

        // Change an entry's key (replace key 12 with 6)
        System.out.println("22. Changing key for entry with key=12 to 6.");
        Entry e12 = findEntryByKey(apq, 12);
        if (e12 != null) {
            int oldKey = apq.replaceKey(e12, 6);
            System.out.println("Replaced key " + oldKey + " with " + e12.getKey());
        } else {
            System.out.println("Entry with key=12 not found.");
        }
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");

        // Change an entry's value (replace value for key=11 with 'Updated')
        System.out.println("23. Changing value for entry with key=11 to 'Updated'.");
        Entry e11 = findEntryByKey(apq, 11);
        if (e11 != null) {
            String oldVal = apq.replaceValue(e11, "Updated");
            System.out.println("Replaced value '" + oldVal + "' with '" + e11.getValue() + "'");
        } else {
            System.out.println("Entry with key=11 not found.");
        }
        System.out.println("Queue: " + apq + "\n");
        System.out.println("=====================================================");

        // Check size() and isEmpty()
        System.out.println("24. Current size: " + apq.size() + ", isEmpty(): " + apq.isEmpty() + "\n");
        System.out.println("=====================================================");

        // Remove all remaining entries
        System.out.println("25. Removing all remaining entries.");
        while (!apq.isEmpty()) {
            System.out.println("Removed: " + apq.removeTop());
        }
        System.out.println("Queue empty? " + apq.isEmpty() + "\n");
        System.out.println("=====================================================");

        // Insert items and merge with another APQ
        System.out.println("26. Inserting items into a new APQ and merging with another APQ.");
        AdvancedPriorityQueue apq3 = new AdvancedPriorityQueue();
        apq3.insert(30, "M1");
        apq3.insert(25, "M2");
        AdvancedPriorityQueue apq4 = new AdvancedPriorityQueue();
        apq4.insert(5, "N1");
        apq4.insert(40, "N2");
        apq3.merge(apq4);
        System.out.println("Merged queue: " + apq3 + "\n");
        System.out.println("=====================================================");

        // Toggle merged APQ to max-heap, then remove the top element
        System.out.println("27. Toggling merged queue to max-heap.");
        apq3.toggle();
        System.out.println("Current state: " + apq3.state());
        System.out.println("Removed top (max-heap): " + apq3.removeTop());
        System.out.println("Queue: " + apq3 + "\n");
        System.out.println("=====================================================");

        // Toggle back to min-heap
        System.out.println("28. Toggling back to min-heap.");
        apq3.toggle();
        System.out.println("Current state: " + apq3.state());
        System.out.println("Queue: " + apq3 + "\n");
        System.out.println("=====================================================");

        // Insert one more item and remove top to verify stability
        System.out.println("29. Inserting (99, Final) and then removing top element.");
        apq3.insert(99, "Final");
        System.out.println("Queue: " + apq3);
        System.out.println("Removed top: " + apq3.removeTop());
        System.out.println("Final state: " + apq3 + "\n");
        System.out.println("=====================================================");

        // Insert elements to exceed the default capacity
        System.out.println("30. Testing halfSize functionality.");

        for (int i = 0; i < 20; i++) {
            apq.insert(i, "Value" + i);
        }
        System.out.println("Queue after inserts: " + apq);

        // Remove elements to trigger halfSize
        for (int i = 0; i < 15; i++) {
            apq.removeTop();
        }
        System.out.println("Queue after removals: " + apq);

    }
}