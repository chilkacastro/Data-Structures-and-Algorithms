import java.util.NoSuchElementException;

/**
 * Advanced Priority Queue class that can toggle between min and max heaps
 *
 * @author Chilka Castro and Christian David
 */
public class AdvancedPriorityQueue {
    private boolean state;
    private Entry[] elements; // Array of Entry objects
    private int size;         // Number of elements in the APQ
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructor for AdvancedPriorityQueue
     */
    public AdvancedPriorityQueue() {
        this.state = true; // Starts in min heap mode which is true and max heap mode is false
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
     * Removes and returns the entry (key-value pair) with the smallest or largest
     * key, depending on the current mode (Min or Max)
     *
     * @return the removed entry
     * @throws NoSuchElementException if the queue is empty
     */
    public Entry removeTop() { //
        if (size == 0)
            throw new NoSuchElementException("Priority Queue is empty");

        Entry removedEntry = elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        size--;

        if (size > DEFAULT_CAPACITY && size < elements.length / 4)
            halfSize();

        if (size > 0)
            bubbleDown(0);

        return removedEntry;
    }

    /**
     * Adds a new entry with key k and value v to the queue,
     * returning the resulting entry object.
     *
     * @param k the key of the entry
     * @param v the value of the entry
     * @return the newly created entry
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
     * @return the entry with the smallest or largest key
     */
    public Entry top() {
        return elements[0];
    }

    /**
     * Removes a specific entry e from the queue and returns it.
     * @param e the entry to be removed
     * @return the removed entry
     * @throws IllegalArgumentException if e is null or not found in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public Entry remove(Entry e) {
        if (e == null)
            throw new IllegalArgumentException("Entry is null");
        if (size == 0)
            throw new NoSuchElementException("Priority Queue is empty.");

        int indexToRemove = -1;
        for (int i = 0; i < size; i++) {
            if (e.equals(elements[i])) {        // Uses Entry.equals() method
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove == -1)
            throw new IllegalArgumentException("Entry not found in the priority queue.");

        // Remove the element at indexToRemove
        Entry removedElement = elements[indexToRemove];
        swap(indexToRemove, size - 1);
        elements[size - 1] = null;
        size--;

        // Restore heap order.
        if (size > 0 && indexToRemove < size) {
            if (indexToRemove > 0) {
                int parentIndex = getParentIndex(indexToRemove);
                if (state) {                // Min-heap
                    if (elements[indexToRemove].getKey() < elements[parentIndex].getKey())
                        bubbleUp(indexToRemove);
                    else
                        bubbleDown(indexToRemove);
                } else {                    // Max-heap
                    if (elements[indexToRemove].getKey() > elements[parentIndex].getKey())
                        bubbleUp(indexToRemove);
                    else
                        bubbleDown(indexToRemove);
                }
            } else {
                bubbleDown(0);
            }
        }

        if (size > DEFAULT_CAPACITY && size < elements.length / 4)
            halfSize();

        return removedElement;
    }

    /**
     * Updates the key of the entry that equals e to newKey and returns the old key.
     * This version finds the entry using equals() rather than a key-only search.
     *
     * @param e      the entry to be updated (must be found in the queue)
     * @param newKey the new key value to set
     * @return the old key of the found entry
     * @throws IllegalArgumentException if e is null or not found in the queue.
     */
    public int replaceKey(Entry e, int newKey) {
        if (e == null)
            throw new IllegalArgumentException("Entry is null");

        int index = -1;
        for (int i = 0; i < size; i++) {
            if (e.equals(elements[i])) {
                index = i;
                break;
            }
        }
        if (index == -1)
            throw new IllegalArgumentException("Entry not found in the priority queue.");

        int oldKey = elements[index].getKey();
        elements[index].setKey(newKey);

        if (state) {            // Min-heap - if new key is smaller, bubble up; if larger, bubble down
            if (newKey < oldKey)
                bubbleUp(index);
            else if (newKey > oldKey)
                bubbleDown(index);
        } else {                // Max-heap - if new key is larger, bubble up; if smaller, bubble down.
            if (newKey > oldKey)
                bubbleUp(index);
            else if (newKey < oldKey)
                bubbleDown(index);
        }
        return oldKey;
    }

    /**
     * Updates the value of the entry that equals e to newValue and returns the old value.
     * This version finds the entry using equals() rather than a key-only search.
     *
     * @param e the entry to be updated (must be found in the queue)
     * @param newValue the new value to set
     * @return the old value of the found entry
     * @throws IllegalArgumentException if e is null or not found in the queue.
     */
    public String replaceValue(Entry e, String newValue) {
        if (e == null)
            throw new IllegalArgumentException("Entry is null");

        int index = -1;
        for (int i = 0; i < size; i++) {
            if (e.equals(elements[i])) {
                index = i;
                break;
            }
        }
        if (index == -1)
            throw new IllegalArgumentException("Entry not found in the priority queue.");

        String oldValue = elements[index].getValue();
        elements[index].setValue(newValue);

        return oldValue;
    }

    /**
     * Returns the current mode of the queue (Min or Max).
     *
     * @return the current mode of the queue
     */
    public String state() {
        return state ? "Min heap" : "Max heap";
    }

    /**
     * Checks if the queue is empty, returning true if it is.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the total number of entries in the queue.
     *
     * @return the number of entries in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns the n-th entry in the priority queue (e.g., the n-th smallest key in a min-heap or the n-th largest key
     * in a max-heap) without removing it.
     * @param n the index of the element to find
     * @return the nth element/Entry in the queue
     * @throws IndexOutOfBoundsException if n is out of bounds
     */
    public Entry peekAt(int n) {
        if (n < 0 || n >= size)
            throw new IndexOutOfBoundsException("Index out of bounds for peekAt: " + n + ", size: " + size);

        Entry[] tempElements = new Entry[size];
        for (int i = 0; i < size; i++) {
            tempElements[i] = elements[i];
        }

        return quickSelect(tempElements, 0, size - 1, n);
    }

    /**
     * Merges the current priority queue with another APQ, combining all
     * entries into a single APQ. The result maintains the current state (Min or Max)
     * of the primary APQ.
     *
     * @param otherAPQ the other APQ to merge
     */
    public void merge(AdvancedPriorityQueue otherAPQ) {
        int newSize = this.size + otherAPQ.size;

        // If the new size exceeds the current array's capacity, create a new array and copy elements manually.
        if (newSize > elements.length) {
            Entry[] newElements = new Entry[newSize];
            for (int i = 0; i < this.size; i++) {  // Copy elements from the current APQ
                newElements[i] = this.elements[i];
            }
            this.elements = newElements;
        }

        for (int i = 0; i < otherAPQ.size; i++) {
            this.elements[this.size + i] = otherAPQ.elements[i];
        }

        // Update size and rebuild the heap.
        this.size = newSize;
        buildHeap();
    }

    // === Helper methods ===

    /**
     * Gets the parent index of a child node
     *
     * @param i the index of the child
     * @return the index of the parent
     */
    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    /**
     * Gets the left child index of a parent node
     *
     * @param i the index of the parent
     * @return the index of the left child
     */
    // Helper method to get the left child index
    private int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    /**
     * Gets the right child index of a parent node
     *
     * @param i the index of the parent
     * @return the index of the right child
     */
    private int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    /**
     * Bubbles down the array to maintain the heap property after removal.
     * @param index the index of the element to bubble down
     */
    private void bubbleDown(int index) {
        while (true) {
            int left = getLeftChildIndex(index);
            int right = getRightChildIndex(index);
            int target = index; // index to potentially swap with

            if (state) { // Min-Heap
                // If the left child is smaller than the current element, swap
                if (left < size && elements[left].getKey() < elements[target].getKey()) {
                    target = left;
                }
                // If the right child is smaller than the current element, swap
                if (right < size && elements[right].getKey() < elements[target].getKey()) {
                    target = right;
                }
            } else { // Max-Heap
                // If the left child is larger than the current element, swap
                if (left < size && elements[left].getKey() > elements[target].getKey()) {
                    target = left;
                }
                // If the right child is larger than the current element, swap
                if (right < size && elements[right].getKey() > elements[target].getKey()) {
                    target = right;
                }
            }

            if (target == index) {
                break;           // If no child is better, stop the loop
            }

            swap(index, target); // Swap with the better child
            index = target;      // Move to the child position and continue
        }
    }

    /**
     * Bubbles up the array to maintain the heap property after inserting a new element
     *
     * @param index the index of the element to bubble up
     */
    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = getParentIndex(index);
            // Compare the current element with its parent - if the current element is smaller (or larger) then swap
            if ((state && elements[index].getKey() < elements[parentIndex].getKey()) ||
                    (!state && elements[index].getKey() > elements[parentIndex].getKey())) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;  // If the parent is smaller (or larger), stop the loop
            }
        }
    }

    /**
     * Swaps two elements in the elements array.
     * This is used by bubbleDown and bubbleUp methods
     *
     * @param i the index of the first entry
     * @param j the index of the second entry
     */
    private void swap(int i, int j) {
        Entry temp = elements[j];
        elements[j] = elements[i];
        elements[i] = temp;
    }

    /**
     * Doubles the size of the array.
     */
    private void doubleSize() {
        Entry[] temp = new Entry[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    /**
     * Halves the size of the array.
     */
    private void halfSize() {
        Entry[] temp = new Entry[elements.length / 2];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    /**
     * Fixes the tree after toggling the state.
     */
    private void buildHeap() {
        for (int i = getParentIndex(size - 1); i >= 0; i--) {
            bubbleDown(i);
        }
    }

    /**
     * Returns a string representation of the priority queue.
     *
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

    /**
     * Quickselect method to find the nth smallest/largest element in the array.
     * @param arr the array to search
     * @param left the left index of the subarray
     * @param right the right index of the subarray
     * @param n the index of the element to find
     * @return the nth smallest/largest element
     */
    private Entry quickSelect(Entry[] arr, int left, int right, int n) {
        int index = quickSelectIndex(arr, left, right, n);
        return arr[index];
    }

    /**
     * Performs the quickselect algorithm to find the nth smallest/largest element.
     * @param arr the array to search
     * @param left the left index of the subarray
     * @param right the right index of the subarray
     * @param indexToFind the index of the element to find
     * @return the index of the found element
     */
    private int quickSelectIndex(Entry[] arr, int left, int right, int indexToFind) {
        if (left == right)                                       // If the list contains only one element
            return left;

        while (left <= right) {
            int pivotIndex = medianOfMedians(arr, left, right);   // Choose a pivot index using the median-of-medians algorithm
            pivotIndex = partition(arr, left, right, pivotIndex); // Partition the array around the pivot

            if (pivotIndex == indexToFind)
                return pivotIndex;
            else if (indexToFind < pivotIndex)
                right = pivotIndex - 1;
            else
                left = pivotIndex + 1;
        }
        return -1;                                                 // If the input is invalid
    }

    /**
     * Chooses a pivot index using the median-of-medians algorithm.
     * @param arr the array to find the pivot in
     * @param left the left index of the subarray
     * @param right the right index of the subarray
     * @return the index of the pivot element
     */
    private int medianOfMedians(Entry[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5)
            return findMedianIndex(arr, left, right);

        // Divide arr into groups of 5 and find the median of each group
        int numMedians = (n + 4) / 5;                                   // Number of groups
        for (int i = 0; i < numMedians; i++) {                          // Iterate through each group
            int subLeft = left + i * 5;                                 // Start of the subarray
            int subRight = Math.min(subLeft + 4, right);                // End of the subarray
            int medianIndex = findMedianIndex(arr, subLeft, subRight);  // Find the median of the subarray
            swap(arr, left + i, medianIndex);
        }
        int mid = left + (numMedians - 1) / 2;                          // Find the median of the medians
        return quickSelectIndex(arr, left, left + numMedians - 1, mid);
    }

    /**
     * Finds the median index of the subarray arr[left..right]
     * @param arr the array to find the median in
     * @param left the left index of the subarray
     * @param right the right index of the subarray
     * @return the index of the median element
     */
    private int findMedianIndex(Entry[] arr, int left, int right) {
       // Use insertion sort to find the median of the subarray
        for (int i = left + 1; i <= right; i++) {
            int j = i;
            while (j > left && (state ? arr[j].getKey() < arr[j - 1].getKey()
                    : arr[j].getKey() > arr[j - 1].getKey())) {
                swap(arr, j, j - 1);
                j--;
            }
        }
        return (left + right) / 2;
    }

    /**
     * Partitions the subarray arr[left..right] around the element at pivotIndex.
     * Elements less than the pivot (or greater, in max-heap mode) are moved left.
     * Returns the final index of the pivot element.
     *
     * @param arr the array to partition
     * @param left the left index of the subarray
     * @param right the right index of the subarray
     * @param pivotIndex the index of the pivot element
     * @return the final index of the pivot element
     *
     */
    private int partition(Entry[] arr, int left, int right, int pivotIndex) {
        Entry pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            // Compare the current element with the pivot - if the current element is smaller (or larger) than the pivot, swap
            if (state ? arr[i].getKey() < pivot.getKey() : arr[i].getKey() > pivot.getKey()) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    /**
     * Swaps two elements in the given array.
     * This is used by the partition method.
     */
    private void swap(Entry[] arr, int i, int j) {
        Entry temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
