import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Advanced Priority Queue class that can toggle between min and max heaps
 *
 * @author Chilka Castro and Christian David
 */
public class AdvancedPriorityQueue {
    private boolean state;
    private Entry[] elements; // array of Entry objects
    private int size; // how many elements are in the array Entry
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructor for AdvancedPriorityQueue
     */
    public AdvancedPriorityQueue() {
        this.state = true; // starts in min heap mode
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
     * key, depending on the current mode (Min or Max).
     * return the entry with the smallest or largest key
     */
    public Entry removeTop() { //
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

    /**
     * Adds a new entry with key k and value v to the queue,
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
     * <p>
     * return the entry with the smallest or largest key
     */
    public Entry top() {
        return elements[0];
    }

    //    /**
//     * Removes a specific entry e from the queue and returns it.
//     *
//     * @param e the entry to be removed
//     * @return the entry that was removed
//     */
//    public Entry remove(Entry e) {
//        if (e == null)
//            throw new IllegalArgumentException("Entry is null");
//        if (size == 0)
//            throw new NoSuchElementException("Priority Queue is empty.");
//
//        // Find the index of the entry to be removed.
//        int indexToRemove = search(e.getKey());
//        Entry removedElement = elements[indexToRemove];
//
//        // Swap the element to be removed with the last element.
//        swap(indexToRemove, size - 1);
//        elements[size - 1] = null; // Clear the last element.
//        size--;
//
//        // If there are still elements and the removal did not happen at the very end
//        if (size > 0 && indexToRemove < size) {
//            if (indexToRemove > 0) { // if the element is not at the root, compare it with its parent.
//                int parentIndex = getParentIndex(indexToRemove);
//                // For a min-heap, if the moved element is smaller than its parent, it should
//                // bubble up.
//                if (state) { // min-heap
//                    if (elements[indexToRemove].getKey() < elements[parentIndex].getKey()) {
//                        bubbleUp(indexToRemove);
//                    } else {
//                        bubbleDown(indexToRemove);
//                    }
//                }
//                // For a max-heap, if it is larger than its parent, it should bubble up.
//
//                else { // max-heap
//                    if (elements[indexToRemove].getKey() > elements[parentIndex].getKey()) {
//                        bubbleUp(indexToRemove);
//                    } else {
//                        bubbleDown(indexToRemove);
//                    }
//                }
//            } else {
//                bubbleDown(0); // if the element is now at the root (index 0), only bubble down is needed
//            }
//        }
//
//        // Optionally shrink the underlying array if usage is low.
//        if (size > DEFAULT_CAPACITY && size < elements.length / 4) {
//            halfSize();
//        }
//
//        return removedElement;
//    }
    public Entry remove(Entry e) {
        if (e == null)
            throw new IllegalArgumentException("Entry is null");
        if (size == 0)
            throw new NoSuchElementException("Priority Queue is empty.");

        // Find the first element that equals the given entry.
        int indexToRemove = -1;
        for (int i = 0; i < size; i++) {
            if (e.equals(elements[i])) { // Uses your Entry.equals() method
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove == -1)
            throw new IllegalArgumentException("Entry not found in the priority queue.");

        // Remove the element at indexToRemove.
        Entry removedElement = elements[indexToRemove];
        swap(indexToRemove, size - 1);
        elements[size - 1] = null;
        size--;

        // Restore heap order.
        if (size > 0 && indexToRemove < size) {
            if (indexToRemove > 0) {
                int parentIndex = getParentIndex(indexToRemove);
                if (state) { // min-heap
                    if (elements[indexToRemove].getKey() < elements[parentIndex].getKey())
                        bubbleUp(indexToRemove);
                    else
                        bubbleDown(indexToRemove);
                } else { // max-heap
                    if (elements[indexToRemove].getKey() > elements[parentIndex].getKey())
                        bubbleUp(indexToRemove);
                    else
                        bubbleDown(indexToRemove);
                }
            } else {
                bubbleDown(0);
            }
        }

        // Optionally shrink the array if needed.
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

        // For a min-heap: if new key is smaller, bubble up; if larger, bubble down.
        // For a max-heap: if new key is larger, bubble up; if smaller, bubble down.
        if (state) { // min-heap
            if (newKey < oldKey)
                bubbleUp(index);
            else if (newKey > oldKey)
                bubbleDown(index);
        } else { // max-heap
            if (newKey > oldKey)
                bubbleUp(index);
            else if (newKey < oldKey)
                bubbleDown(index);
        }
        return oldKey;
    }
//    /**
//     * Updates the key of entry e to k and returns the old key.
//     *
//     * @param e      the entry to be updated
//     * @param newKey the new key
//     * @return the old key
//     */
//    public int replaceKey(Entry e, int newKey) {
//        int index = search(e.getKey());
//        int oldKey = elements[index].getKey();
//        elements[index].setKey(newKey);
//
//        if (state) { // for min-heap
//            if (newKey < oldKey) {
//                bubbleUp(index); // if the new key is smaller, bubble up
//            }
//            if (newKey > oldKey) {
//                bubbleDown(index); // if the new key is larger, bubble down
//            }
//        } else { // for max-heap
//            if (newKey > oldKey) {
//                bubbleUp(index); // if the new key is larger, bubble up
//            }
//            if (newKey < oldKey) {
//                bubbleDown(index); // if the new key is smaller, bubble down
//            }
//        }
//
//        return oldKey;
//    }

//    /**
//     * Updates the value of entry e to v and returns the old value.
//     *
//     * @param e     the entry to be updated
//     * @param value the new value to update
//     * @return the old value of the entry
//     */
//    public String replaceValue(Entry e, String value) {
//        int index = search(e.getKey());
//        String oldValue = elements[index].getValue();
//        elements[index].setValue(value);
//        return oldValue;
//    }

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
        // No reheapification is needed because the key remains unchanged.
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
     * Merges the current priority queue with another APQ, combining all
     * entries into a single APQ. The result should maintain the current state (Min
     * or Max) of the
     * primary APQ.
     *
     * @param otherAPQ the other APQ to merge
     */
    public void merge(AdvancedPriorityQueue otherAPQ) {
        int newSize = this.size + otherAPQ.size;

        if (newSize > elements.length)
            elements = Arrays.copyOf(elements, newSize);

        System.arraycopy(otherAPQ.elements, 0, elements, this.size, otherAPQ.size);

        this.size = newSize;

        buildHeap();
    }


    /**
     * Helper method to get the parent zindex
     *
     * @param i the index of the child
     * @return the index of the parent
     */
    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    /**
     * Helper method to get the left child index
     *
     * @param i the index of the parent
     * @return the index of the left child
     */
    // Helper method to get the left child index
    private int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    /**
     * Helper method to get the right child index
     *
     * @param i the index of the parent
     * @return the index of the right child
     */
    private int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    /**
     * Helper method to bubble down the array
     * This method is used to maintain the heap property after removing the top
     * element
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
            index = target; // move to the child position and continue
        }
    }

    /**
     * Helper method to bubble up the array
     *
     * @param index the index of the element to bubble up
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
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if the key is not found
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
     * Helper method to swap two elements in the elements array.
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
     * Helper method to double the size of the array.
     */
    private void doubleSize() {
        Entry[] temp = new Entry[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    /**
     * Helper method to half the size of the array.
     */
    private void halfSize() {
        Entry[] temp = new Entry[elements.length / 2];
        for (int i = 0; i < temp.length; i++) {
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
     * Returns the elements in the priority queue.
     *
     * @return the elements in the priority queue
     */
    public Entry[] getElements() {
        return elements;
    }

    /**
     * Returns the nth element (order statistic) in the queue according to the current ordering.
     * Uses a worst-case O(n) median-of-medians quickselect algorithm.
     */
    public Entry peekAt(int n) {
        if (n < 0 || n >= size)
            throw new IndexOutOfBoundsException("Index out of bounds for peekAt: " + n + ", size: " + size);

        // Manually copy the active part of the elements array.
        Entry[] tempElements = new Entry[size];
        for (int i = 0; i < size; i++) {
            tempElements[i] = elements[i];
        }

        return quickSelect(tempElements, 0, size - 1, n);
    }

    /**
     * Quickselect routine: returns the element at index n (in the sorted order)
     * from the subarray arr[left..right].
     */
    private Entry quickSelect(Entry[] arr, int left, int right, int n) {
        int index = quickSelectIndex(arr, left, right, n);
        return arr[index];
    }

    /**
     * Quickselect helper: returns the index of the nth element in arr (within [left, right]).
     */
    private int quickSelectIndex(Entry[] arr, int left, int right, int indexToFind) {
        if (left == right)
            return left;

        while (left <= right) {
            int pivotIndex = medianOfMedians(arr, left, right);
            pivotIndex = partition(arr, left, right, pivotIndex);

            if (pivotIndex == indexToFind)
                return pivotIndex;
            else if (indexToFind < pivotIndex)
                right = pivotIndex - 1;
            else
                left = pivotIndex + 1;
        }
        return -1; // This should not happen if the input is valid.
    }

    /**
     * Chooses a pivot index using the median-of-medians algorithm.
     */
    private int medianOfMedians(Entry[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5)
            return findMedianIndex(arr, left, right);

        int numMedians = (n + 4) / 5; // number of groups of 5
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            int medianIndex = findMedianIndex(arr, subLeft, subRight);
            swap(arr, left + i, medianIndex);
        }
        int mid = left + (numMedians - 1) / 2;
        return quickSelectIndex(arr, left, left + numMedians - 1, mid);
    }

    /**
     * partition5: sorts the subarray arr[left..right] using insertion sort and
     * returns the index of its median element.
     */
    private int findMedianIndex(Entry[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int j = i;
            // Use the current ordering: if state is true, lower keys come first (min-heap)
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
     */
    private int partition(Entry[] arr, int left, int right, int pivotIndex) {
        Entry pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);  // Move pivot to end.
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (state ? arr[i].getKey() < pivot.getKey() : arr[i].getKey() > pivot.getKey()) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);  // Move pivot to its final place.
        return storeIndex;
    }

    /**
     * Swaps two elements in the given array.
     */
    private void swap(Entry[] arr, int i, int j) {
        Entry temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
