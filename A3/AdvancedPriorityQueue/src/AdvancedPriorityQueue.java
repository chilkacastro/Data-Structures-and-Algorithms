import java.util.Arrays;

/**
 * Advanced Priority Queue class that can toggle between min and max heaps
 * @author Chilka Castro and Christian David
 */
public class AdvancedPriorityQueue {
    private boolean state = true;
    private Entry[] elements;  // array of Entry objects
    private int size;  // how many elements are in the array Entry
    private static int DEFAULT_CAPACITY = 10;


    /**
     * Constructor for AdvancedPriorityQueue
     */
    public AdvancedPriorityQueue() {
        this.elements = new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Switches the queue between min-heap and max-heap modes.
     */
    public void toggle() {
        if (this.state)  // Switches from min and max. Min is true and max is false.
            this.state = false;
        else
            this.state = true;

        if (size > 0) {
            fixTree();
        }
    }

    /**
     *  Removes and returns the entry (key-value pair) with the smallest or largest
     *  key, depending on the current mode (Min or Max).
     *  return the entry with the smallest or largest key
     */
    public Entry removeTop() {  //
        // if no elements in the array, throw an exception
        if (size == 0) {
            throw new RuntimeException("Array is empty");
        }

        Entry removedElement = elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        size--;
        bubbleDown();
        return removedElement;
    }

    /**
     * Helper method to bubble down the array
     * This method is used to maintain the heap property after removing the top element
     */
    private void bubbleDown() {
        if (this.state)
            bubbleDownMinHeap();
        else
            bubbleDownMaxHeap();
    }

    /**
     * Helper method to bubble down the array for min heap
     */
    private void bubbleDownMinHeap() {
        int i = 0;
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            // Check if left child exists and is smaller
            if (left < size && elements[left].key < elements[smallest].key) {
                smallest = left;
            }

            // Check if right child exists and is even smaller
            if (right < size && elements[right].key < elements[smallest].key) {
                smallest = right;
            }

            // If i is smallest, heap property is satisfied
            if (smallest == i)
                break;

            // Swap with the smallest child
            swap(i, smallest);
            i = smallest;
        }
    }

    /**
     * Helper method to bubble down the array for max heap -> so the largest element is at the top
     */
    private void bubbleDownMaxHeap() {
        int i = 0;

        while (true) {
            int left = getLeftChildIndex(i);
            int right = getRightChildIndex(i);
            int biggest = i;

            // Check if left child exists and is bigger
            if (getLeftChildIndex(i) < size && elements[left].key > elements[biggest].key) {
                biggest = left;
            }

            // Check if right child exists and is even bigger
            if (right < size && elements[right].key > elements[biggest].key) {
                biggest = right;
            }

            // If i is smallest, heap property is satisfied
            if (biggest == i)
                break;

            // Swap with the smallest child
            swap(i, biggest);
            i = biggest;
        }
    }

    /** Adds a new entry with key k and value v to the queue,
     * returning the resulting entry object.
     * return the entry object that was added to the queue
     */
    public Entry insert(int k, String v) {
        if (size == elements.length) {
            doubleSize();
        }

        Entry newElement = new Entry(k, v);
        elements[size] = newElement;
        int i = size;
        size++;

        // For min heap: bubble up until parent's key is less than or equal to child's key.
        if (this.state) {
            while (i > 0) {
                int parent = getParentIndex(i);
                if (elements[i].key >= elements[parent].key) {
                    break;
                }
                swap(i, parent);
                i = parent;
            }
        }
        // For max heap: bubble up until parent's key is greater than or equal to child's key.
        else {
            while (i > 0) {
                int parent = (i - 1) / 2;
                if (elements[i].key <= elements[parent].key) {
                    break;
                }
                swap(i, parent);
                i = parent;
            }
        }

        return newElement;
    }

    //Helper method to get the parent index
    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    // Helper method to get the left child index
    private int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    // Helper method to get the right child index
    private int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    /** Retrieves the entry with the smallest or largest key
     *  (depending on the current mode) without removing it.
     *
     *  return the entry with the smallest or largest key
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
        swap(search(e.key), size); // swap the element to be removed with the last element
        elements[size] = null;
        size--;
        bubbleDown();
        return e;
    }

    public int replaceKey(int i, int j) {
        int index = search(i);
        elements[index].key = j;
        fixTree();
        return i;
    }

    public String replaceValue(int i, String value) {
        int index = search(i);
        String oldValue = elements[index].value;
        elements[index].value = value;

        return oldValue;
    }


    public String state() {
        if (state) {
            return "Min heap";
        }
        else {
            return "Max heap";
        }
    }


    /**
     * Checks if the queue is empty, returning true if it is.
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public Entry peekAt(int i) {
        int index = search(i);
        return elements[index];
    }

    public void merge(AdvancedPriorityQueue temp) {
        int i = 0;
        while (i < temp.size) {
            insert(temp.elements[i].key, temp.elements[i].value);
        }
    }

    //Helper method for searching key and values.
    private int search(int i){
        int index = 0;

        while(i != elements[index].key){
            index++;
        }

        return index;
    }

    // Helper method
    private void swap(int i, int j) {
        Entry temp = elements[j];
        elements[j] = elements[i];
        elements[i] = temp;
    }


    //This is for dynamic sizing of the PQueue
    private void doubleSize() {
        Entry[] temp = new Entry[elements.length * 2];

        for(int i = 0; i < elements.length; i++) {
            temp[i] = elements[i];
        }

        elements = temp;
    }

    //A helper method to help in toggling the array from min and max.
    private void fixTree() {
        Entry[] temp1 = new Entry[size];
        for (int i = 0; i < size; i++) {
            temp1[i] = elements[i];
        }
        elements = new Entry[size]; // Keep the same size
        size = 0; // Reset size to 0

        // Insert the elements back into the array
        for (int i = 0; i < temp1.length; i++) {
            insert(temp1[i].key, temp1[i].value);
        }

    }
// TO VIEW BOTH VALUES AND KEYS
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("AdvancedPriorityQueue{");
//        sb.append("state=").append(state ? "Min heap" : "Max heap");
//        sb.append(", entries=[");
//        for (int i = 0; i < size; i++) {
//            sb.append("{key=").append(elements[i].key).append(", value=").append(elements[i].value).append("}");
//            if (i < size - 1) {
//                sb.append(", ");
//            }
//        }
//        sb.append("], size=").append(size);
//        sb.append('}');
//        return sb.toString();
//    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdvancedPriorityQueue{");
        sb.append("state=").append(state ? "Min heap" : "Max heap");
        sb.append(", keys=[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i].key);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("], size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}

