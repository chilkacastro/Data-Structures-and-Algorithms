import java.util.NoSuchElementException;

/**
 * Main class to test the AdvancedPriorityQueue implementation.
 *
 * @author Chilka Castro and Christian David
 */
public class Main {
    public static void main(String[] args) {
        System.out.print("===================================================\n");
        // ----- Test 1: Initial state of an empty queue -----
        AdvancedPriorityQueue apq = new AdvancedPriorityQueue();
        System.out.println("Test 1: Initial state");
        System.out.println("State: " + apq.state());
        System.out.println("isEmpty: " + apq.isEmpty());
        System.out.println("Size: " + apq.size());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 2: Insert a few elements -----
        System.out.println("Test 2: Inserting elements");
        Entry e1 = apq.insert(10, "Ten");
        Entry e2 = apq.insert(20, "Twenty");
        Entry e3 = apq.insert(5, "Five");
        Entry e4 = apq.insert(15, "Fifteen");
        System.out.println("Queue: " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 3: Check top() in min-heap mode (should be smallest) -----
        System.out.println("Test 3: Testing top() method");
        System.out.println("Top element: " + apq.top());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 4: Use peekAt(n) for all valid indices -----
        System.out.println("Test 4: Testing peekAt(n) for valid indices");
        for (int i = 0; i < apq.size(); i++) {
            System.out.println("peekAt(" + i + "): " + apq.peekAt(i));
        }
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 5: Remove top element using removeTop() -----
        System.out.println("Test 5: Removing top element using removeTop()");
        Entry removed = apq.removeTop();
        System.out.println("Removed element: " + removed);
        System.out.println("Queue after removal: " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 6: replaceKey() causing bubbleUp -----
        System.out.println("Test 6: Testing replaceKey() to cause bubbleUp");
        Entry e5 = apq.insert(30, "Thirty");
        System.out.println("Queue before replaceKey: " + apq);
        apq.replaceKey(e5, 1);  // Lowering the key to trigger bubbleUp.
        System.out.println("Queue after replaceKey (30 -> 1): " + apq);
        System.out.println("New top: " + apq.top());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 7: replaceKey() causing bubbleDown -----
        System.out.println("Test 7: Testing replaceKey() to cause bubbleDown");
        Entry e6 = apq.insert(2, "Two");
        System.out.println("Queue before replaceKey: " + apq);
        apq.replaceKey(e6, 50);  // Increasing the key to trigger bubbleDown.
        System.out.println("Queue after replaceKey (2 -> 50): " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 8: replaceValue() -----
        System.out.println("Test 8: Testing replaceValue()");
        System.out.println("Original value of e2: " + e2.getValue());
        apq.replaceValue(e2, "TwentyUpdated");
        System.out.println("Updated value of e2: " + e2.getValue());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 9: remove(e) for an element in the middle -----
        System.out.println("Test 9: Testing remove(e) for an element in the middle");
        System.out.println("Queue before removal: " + apq);
        // Instead of e3 (which was removed in Test 5), remove e4 ("Fifteen")
        apq.remove(e4);
        System.out.println("Queue after removing element 'Fifteen': " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 10: Toggle heap mode from min-heap to max-heap -----
        System.out.println("Test 10: Toggling heap mode (min-heap -> max-heap)");
        apq.toggle();
        System.out.println("State after toggle: " + apq.state());
        System.out.println("Queue after toggle: " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 11: Toggle back to min-heap mode -----
        System.out.println("Test 11: Toggling heap mode (max-heap -> min-heap)");
        apq.toggle();
        System.out.println("State after toggle: " + apq.state());
        System.out.println("Queue after toggle: " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 12: Insert many elements to force automatic array extension -----
        System.out.println("Test 12: Inserting multiple elements to trigger array extension");
        for (int i = 100; i < 120; i++) {
            apq.insert(i, "Num" + i);
        }
        System.out.println("Queue after bulk insertion: " + apq);
        System.out.println("Size: " + apq.size());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 13: Remove several elements to potentially trigger array shrinking -----
        System.out.println("Test 13: Removing several elements to test automatic array shrinkage");
        for (int i = 0; i < 10; i++) {
            apq.removeTop();
        }
        System.out.println("Queue after removals: " + apq);
        System.out.println("Size: " + apq.size());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 14: Mixed insertions and removals -----
        System.out.println("Test 14: Inserting and then removing a mix of elements");
        Entry e7 = apq.insert(45, "FortyFive");
        Entry e8 = apq.insert(35, "ThirtyFive");
        System.out.println("Queue after insertion: " + apq);
        apq.remove(e7);
        System.out.println("Queue after removing 'FortyFive': " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 15: Insert in max-heap mode -----
        System.out.println("Test 15: Testing insert in max-heap mode");
        apq.toggle(); // Switch to max-heap mode
        Entry e9 = apq.insert(99, "NinetyNine");
        System.out.println("Queue in max-heap mode: " + apq);
        System.out.println("Top element (max): " + apq.top());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 16: peekAt(n) with negative index (expect exception) -----
        System.out.println("Test 16: Testing peekAt(n) with negative index (expect exception)");
        try {
            apq.peekAt(-1);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Caught expected exception: " + ex.getMessage());
        }
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 17: peekAt(n) with index >= size (expect exception) -----
        System.out.println("Test 17: Testing peekAt(n) with index >= size (expect exception)");
        try {
            apq.peekAt(apq.size());
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Caught expected exception: " + ex.getMessage());
        }
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 18: remove(e) for a non-existent entry (expect exception) -----
        System.out.println("Test 18: Testing remove(e) for a non-existent entry (expect exception)");
        try {
            Entry fakeEntry = new Entry(999, "NonExistent");
            apq.remove(fakeEntry);
        } catch (IllegalArgumentException ex) {
            System.out.println("Caught expected exception: " + ex.getMessage());
        }
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 19: removeTop() on an empty queue (expect exception) -----
        System.out.println("Test 19: Clearing queue and testing removeTop() on empty queue (expect exception)");
        while (!apq.isEmpty()) {
            apq.removeTop();
        }
        try {
            apq.removeTop();
        } catch (NoSuchElementException ex) {
            System.out.println("Caught expected exception: " + ex.getMessage());
        }
        System.out.println();

        // Reinsert some elements for further tests.
        for (int i = 1; i <= 5; i++) {
            apq.insert(i * 10, "Val" + (i * 10));
        }
        System.out.print("===================================================\n");

        // ----- Test 20: replaceKey() with same value (no effective change) -----
        System.out.println("Test 20: Testing replaceKey() with same value");
        Entry tempEntry = apq.peekAt(2);
        int originalKey = tempEntry.getKey();
        apq.replaceKey(tempEntry, originalKey);
        System.out.println("Entry after replaceKey with same value: " + tempEntry);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 21: replaceValue() with an empty string -----
        System.out.println("Test 21: Testing replaceValue() with an empty string");
        Entry tempEntry2 = apq.peekAt(1);
        System.out.println("Before: " + tempEntry2);
        apq.replaceValue(tempEntry2, "");
        System.out.println("After: " + tempEntry2);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 22: Use peekAt(n) on several positions -----
        System.out.println("Test 22: Testing peekAt(n) on various positions");
        for (int i = 0; i < apq.size(); i++) {
            System.out.println("peekAt(" + i + "): " + apq.peekAt(i));
        }
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 23: Validate size() method -----
        System.out.println("Test 23: Testing size() method");
        System.out.println("Current size: " + apq.size());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 24: Validate isEmpty() when queue is not empty -----
        System.out.println("Test 24: Testing isEmpty() method (should be false)");
        System.out.println("isEmpty: " + apq.isEmpty());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 25: Insert duplicate keys -----
        System.out.println("Test 25: Inserting duplicate keys");
        apq.insert(30, "Duplicate1");
        apq.insert(30, "Duplicate2");
        System.out.println("Queue after inserting duplicates: " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 26: Toggle to min-heap mode and then removeTop repeatedly -----
        System.out.println("Test 26: Toggling to min-heap mode and removing elements one by one");
        apq.toggle(); // Switch to min-heap mode
        while (!apq.isEmpty()) {
            System.out.println("removeTop(): " + apq.removeTop());
        }
        System.out.println("Queue after removals: " + apq);
        System.out.println();

        // Reinsert elements for further testing.
        for (int i = 50; i < 60; i++) {
            apq.insert(i, "Num" + i);
        }
        System.out.print("===================================================\n");

        // ----- Test 27: Insert negative keys -----
        System.out.println("Test 27: Inserting negative keys");
        apq.insert(-10, "MinusTen");
        apq.insert(-20, "MinusTwenty");
        System.out.println("Queue after inserting negatives: " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 28: Insert keys in descending order -----
        System.out.println("Test 28: Inserting keys in descending order");
        for (int i = 100; i > 90; i--) {
            apq.insert(i, "Desc" + i);
        }
        System.out.println("Queue after descending insertions: " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 29: Insert keys in ascending order -----
        System.out.println("Test 29: Inserting keys in ascending order");
        for (int i = 200; i < 205; i++) {
            apq.insert(i, "Asc" + i);
        }
        System.out.println("Queue after ascending insertions: " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 30: peekAt(last index) and state -----
        System.out.println("Test 30: peekAt(last index) and state");
        int lastIndex = apq.size() - 1;
        System.out.println("peekAt(" + lastIndex + "): " + apq.peekAt(lastIndex));
        System.out.println("Queue state: " + apq);
        System.out.println("Size: " + apq.size());
        System.out.println("isEmpty: " + apq.isEmpty());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 31: Multiple toggles in a row -----
        System.out.println("Test 31: Toggling heap mode several times in a row");
        for (int i = 0; i < 5; i++) {
            apq.toggle();
            System.out.println("Toggle " + (i + 1) + " state: " + apq.state());
        }
        System.out.println("Queue after multiple toggles: " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 32: Remove all elements using remove(e) instead of removeTop() -----
        System.out.println("Test 32: Removing all elements using remove(e)");
        while (!apq.isEmpty()) {
            Entry temp = apq.peekAt(0);
            apq.remove(temp);
            System.out.println("Removed: " + temp + " | Queue now: " + apq);
        }
        System.out.println("Queue should now be empty. isEmpty: " + apq.isEmpty());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 33: Multiple replaceKey() operations to shuffle the heap -----
        System.out.println("Test 33: Inserting several elements then performing multiple replaceKey() operations");
        Entry r1 = apq.insert(40, "Forty");
        Entry r2 = apq.insert(35, "ThirtyFive");
        Entry r3 = apq.insert(25, "TwentyFive");
        Entry r4 = apq.insert(45, "FortyFive");
        System.out.println("Queue before multiple replaceKey: " + apq);
        apq.replaceKey(r1, 15);
        apq.replaceKey(r2, 55);
        apq.replaceKey(r3, 5);
        apq.replaceKey(r4, 50);
        System.out.println("Queue after multiple replaceKey operations: " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 34: Stress test - Insert 1000 elements and remove 500 elements -----
        System.out.println("Test 34: Stress test - Inserting 1000 elements and then removing 500 elements");
        for (int i = 0; i < 1000; i++) {
            apq.insert(i, "Num" + i);
        }
        System.out.println("Size after 1000 insertions: " + apq.size());
        for (int i = 0; i < 500; i++) {
            apq.removeTop();
        }
        System.out.println("Size after 500 removals: " + apq.size());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 35: Random peekAt(n) checks on a large queue -----
        System.out.println("Test 35: Random peekAt(n) checks on the current large queue");
        int currentSize = apq.size();
        for (int i = 0; i < 10; i++) {
            int randomIndex = (int) (Math.random() * currentSize);
            System.out.println("peekAt(" + randomIndex + "): " + apq.peekAt(randomIndex));
        }
        System.out.println("Final state of the queue: " + apq);
        System.out.println("Final size: " + apq.size());
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 36: remove(e) with null parameter (expect exception) -----
        System.out.println("Test 36: Testing remove(e) with null (expect exception)");
        try {
            apq.remove(null);
        } catch (IllegalArgumentException ex) {
            System.out.println("Caught expected exception for null removal: " + ex.getMessage());
        }
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 37: Remove duplicate keys -----
        System.out.println("Test 37: Testing remove(e) for duplicate keys");
        // Insert two entries with the same key but different values.
        Entry dup1 = apq.insert(500, "Dup500-A");
        Entry dup2 = apq.insert(500, "Dup500-B");
        System.out.println("Queue before duplicate removal: " + apq);
        // Remove dup1.
        Entry removedDup = apq.remove(dup1);
        System.out.println("Removed entry: " + removedDup);
        // Verify that one duplicate remains.
        boolean found = false;
        for (int i = 0; i < apq.size(); i++) {
            if (apq.peekAt(i).getKey() == 500) {
                found = true;
                break;
            }
        }
        System.out.println("Duplicate entry with key 500 still exists: " + found);
        System.out.println("Queue after duplicate removal: " + apq);
        System.out.println();
        System.out.print("===================================================\n");

        // ----- Test 38: Remove an entry that was already removed (expect exception) -----
        System.out.println("Test 38: Testing remove(e) on an already removed entry (expect exception)");
        try {
            apq.remove(dup1);
        } catch (IllegalArgumentException ex) {
            System.out.println("Caught expected exception when removing an already removed entry: " + ex.getMessage());
        }
        System.out.println();
        System.out.print("===================================================\n");
    }
}