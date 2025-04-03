import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Advanced Priority Queue class that can toggle between min and max heaps
 * @author Chilka Castro and Christian David
 */
public class AdvancedPriorityQueue {
    private boolean state;
    private Entry[] elements;  // array of Entry objects
    private int size;  // how many elements are in the array Entry
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructor for AdvancedPriorityQueue
     */
    public AdvancedPriorityQueue() {
        this.state = true;  // starts in min heap mode
        this.elements = new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Switches the queue between min-heap and max-heap modes.
     */
    public void toggle() {
        state = !state;
        if (size > 0)
            buildHeap();
    }

    /**
     *  Removes and returns the entry (key-value pair) with the smallest or largest
     *  key, depending on the current mode (Min or Max).
     *  return the entry with the smallest or largest key
     */
    public Entry removeTop() {  //
        if (size == 0)
            throw new NoSuchElementException("Priority Queue is empty");

        Entry removedElement = elements[0];
        elements[0] = elements[size - 1]; 
        elements[size - 1] = null;
        size--;

        if (size > DEFAULT_CAPACITY && size < elements.length / 4)
            halfSize();

        if (size > 0)
            bubbleDown(0);

        return removedElement;
    }


    /** Adds a new entry with key k and value v to the queue,
     * returning the resulting entry object.
     * return the entry object that was added to the queue
     */
    public Entry insert(int k, String v) {
        if (size == elements.length)
            doubleSize();

        Entry newElement = new Entry(k, v);
        elements[size] = newElement;
        int i = size;
        size++;
        bubbleUp(i);
        return newElement;
    }

    /**
     * Retrieves the entry with the smallest or largest key
     * (depending on the current mode) without removing it.
     *
     * return the entry with the smallest or largest key
     */
    public Entry top() {
        return elements[0];
    }

    /**
     * Removes a specific entry e from the queue and returns it.
     *
     * @param e the entry to be removed
     * @return the entry that was removed
     */
    public Entry remove(Entry e) {
        if (e == null)
            throw new IllegalArgumentException("Entry is null");
        if (size == 0)
            throw new NoSuchElementException("Priority Queue is empty.");

        // Find the index of the entry to be removed.
        int indexToRemove = search(e.getKey());
        Entry removedElement = elements[indexToRemove];

        // Swap the element to be removed with the last element.
        swap(indexToRemove, size - 1);
        elements[size - 1] = null;  // Clear the last element.
        size--;

        // If there are still elements and the removal did not happen at the very end
        if (size > 0 && indexToRemove < size) {
            if (indexToRemove > 0) {  // if the element is not at the root, compare it with its parent.
                int parentIndex = getParentIndex(indexToRemove);
                // For a min-heap, if the moved element is smaller than its parent, it should bubble up.
                if (state) { // min-heap
                    if (elements[indexToRemove].getKey() < elements[parentIndex].getKey()) {
                        bubbleUp(indexToRemove);
                    } else {
                        bubbleDown(indexToRemove);
                    }
                }
                // For a max-heap, if it is larger than its parent, it should bubble up.

                else { // max-heap
                    if (elements[indexToRemove].getKey() > elements[parentIndex].getKey()) {
                        bubbleUp(indexToRemove);
                    } else {
                        bubbleDown(indexToRemove);
                    }
                }
            }
            else {
                bubbleDown(0); // if the element is now at the root (index 0), only bubble down is needed
            }
        }

        // Optionally shrink the underlying array if usage is low.
        if (size > DEFAULT_CAPACITY && size < elements.length / 4) {
            halfSize();
        }

        return removedElement;
    }

    /**
     * Updates the key of entry e to k and returns the old key.
     *
     * @param e the entry to be updated
     * @param newKey the new key
     * @return the old key
     */
    public int replaceKey(Entry e, int newKey) {
        int index = search(e.getKey());
        int oldKey = elements[index].getKey();
        elements[index].setKey(newKey);

        if (state) {                // for min-heap
            if (newKey < oldKey) {
                bubbleUp(index);    // if the new key is smaller, bubble up
            }
            if (newKey > oldKey) {
                bubbleDown(index);  // if the new key is larger, bubble down
            }
        }
        else {  // for max-heap
            if (newKey > oldKey) {
                bubbleUp(index);    // if the new key is larger, bubble up
            }
            if (newKey < oldKey) {
                bubbleDown(index);  // if the new key is smaller, bubble down
            }
        }

        return oldKey;
    }

    /**
     * Updates the value of entry e to v and returns the old value.
     * @param e the entry to be updated
     * @param value the new value to update
     * @return the old value of the entry
     */
    public String replaceValue(Entry e, String value) {
        int index = search(e.getKey());
        String oldValue = elements[index].getValue();
        elements[index].setValue(value);
        return oldValue;
    }

    /**
     * Returns the current mode of the queue (Min or Max).
     * @return the current mode of the queue
     */
    public String state() {
        return state ? "Min heap" : "Max heap";
    }

    /**
     * Checks if the queue is empty, returning true if it is.
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the total number of entries in the queue.
     * @return the number of entries in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns the n-th entry in the priority queue (e.g., the n-th smallest key in a min-
     * heap or the n-th largest key in a max-heap) without removing it. Throws an error if n is out of
     * bounds.
     * @param n the nth entry to peek at
     * @return the n-th entry in the priority queue
     */
    public Entry peekAt(int n) {
        if (n < 0 || n >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");

        return elements[n];
    }

    /**
     * Merges the current priority queue with another APQ, combining all
     * entries into a single APQ. The result should maintain the current state (Min or Max) of the
     * primary APQ.
     * @param otherAPQ the other APQ to merge
     */
    public void merge(AdvancedPriorityQueue otherAPQ) {
        for (int i = 0; i < otherAPQ.size; i++) {
            insert(otherAPQ.elements[i].getKey(), otherAPQ.elements[i].getValue());
        }
    }

    // --------------------------- Helper methods ---------------------------
    /**
     * Helper method to get the parent index
     * @param i the index of the child
     * @return the index of the parent
     */
    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    /**
     * Helper method to get the left child index
     * @param i the index of the parent
     * @return the index of the left child
     */
    // Helper method to get the left child index
    private int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    /**
     * Helper method to get the right child index
     * @param i the index of the parent
     * @return the index of the right child
     */
    private int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    /**
     * Helper method to bubble down the array
     * This method is used to maintain the heap property after removing the top element
     */
    private void bubbleDown(int index) {
        while (true) {
            int left = getLeftChildIndex(index);
            int right = getRightChildIndex(index);
            int target = index; // This will be the index to potentially swap with

            if (state) { // Min-Heap
                if (left < size && elements[left].getKey() < elements[target].getKey()) {
                    target = left;
                }
                if (right < size && elements[right].getKey() < elements[target].getKey()) {
                    target = right;
                }
            } else { // Max-Heap
                if (left < size && elements[left].getKey() > elements[target].getKey()) {
                    target = left;
                }
                if (right < size && elements[right].getKey() > elements[target].getKey()) {
                    target = right;
                }
            }

            if (target == index) {
                break; // if no child is better, stop the loop
            }

            swap(index, target); // swap with the better child
            index = target;      // move to the child position and continue
        }
    }

    /**
     * Helper method to bubble up the array
     * This method is used to maintain the heap property after inserting a new element
     */
    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = getParentIndex(index);
            if ((state && elements[index].getKey() < elements[parentIndex].getKey()) ||
                    (!state && elements[index].getKey() > elements[parentIndex].getKey())) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    /**
     * Helper method for searching a key in the array.
     * Throws an exception if the key is not found.
     */
    private int search(int key) {
        for (int index = 0; index < size; index++) {
            if (elements[index].getKey() == key) {
                return index;
            }
        }
        throw new IllegalArgumentException("Key " + key + " not found in the priority queue.");
    }

    /**
     * Helper method to swap two elements in the array.
     * @param i the index of the first entry
     * @param j the index of the second entry
     */
    private void swap(int i, int j) {
        Entry temp = elements[j];
        elements[j] = elements[i];
        elements[i] = temp;
    }

    /**
     * Helper method to double the size of the array.
     */
    private void doubleSize() {
        Entry[] temp = new Entry[elements.length * 2];
        for(int i = 0; i < elements.length; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    /**
     * Helper method to half the size of the array.
     */
    private void halfSize() {
        Entry[] temp = new Entry[elements.length / 2];
        for(int i = 0; i < temp.length; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }
    /**
     * Helper method to fix the tree after toggling the state.
     * This method is used to maintain the heap property after toggling the state
     */
    private void buildHeap() {
        for (int i = getParentIndex(size - 1); i >= 0; i--) {
            bubbleDown(i);;
        }
    }

    /**
     * Returns a string representation of the priority queue.
     * @return a string representation of the priority queue
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Priority Queue: State: ").append(state ? "Min heap" : "Max heap")
                .append(", Size: ").append(size).append(", Entries: [");
        boolean first = true;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append("{key=").append(elements[i].getKey())
                        .append(", value=").append(elements[i].getValue()).append("}");
                first = false;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Prints the entries in the priority queue.
     */
    public void printEntries() {
        System.out.print("State: " + (state ? "Min heap" : "Max heap") + ", Size: " + size + ", Entries: [");
        boolean first = true;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(elements[i].getKey() + "=" + elements[i].getValue());
                first = false;
            }
        }
        System.out.println("]");
    }
}

