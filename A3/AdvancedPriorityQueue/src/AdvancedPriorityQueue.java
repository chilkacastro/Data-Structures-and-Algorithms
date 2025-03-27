/**
 * Advanced Priority Queue class that can toggle between min and max heaps
 * @author Chilka Castro and Christian David
 */
public class AdvancedPriorityQueue {
    Boolean state = true;
    Entry[] elements;
    int size;  // how many elements are in the array Entry

    /**
     * Switches the queue between min-heap and max-heap modes.
     */
    public void toggle() {
        if (this.state) {  // Switches from min and max. Min is true and max is false.
            this.state = false;
        }
        else {
            this.state = true;
        }
        fixTree();
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
            int child1 = 2 * i + 1;
            int child2 = 2 * i + 2;
            int smallest = i;

            // Check if left child exists and is smaller
            if (child1 < size && elements[child1].key < elements[smallest].key) {
                smallest = child1;
            }

            // Check if right child exists and is even smaller
            if (child2 < size && elements[child2].key < elements[smallest].key) {
                smallest = child2;
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
     * Helper method to bubble down the array for max heap
     */
    private void bubbleDownMaxHeap() {
        int i = 0;

        while (true) {
            int child1 = 2 * i + 1;
            int child2 = 2 * i + 2;
            int biggest = i;

            // Check if left child exists and is bigger
            if (child1 > size && elements[child1].key > elements[biggest].key) {
                biggest = child1;
            }

            // Check if right child exists and is even bigger
            if (child2 > size && elements[child2].key > elements[biggest].key) {
                biggest = child2;
            }

            // If i is smallest, heap property is satisfied
            if (biggest == i) break;

            // Swap with the smallest child
            swap(i, biggest);
            i = biggest;
        }
    }

    // Adds to the end of the array
    public void insert(int k, String value) {
        Entry temp = new Entry(value, k);

        // adds at the very last position.
        elements[size] = temp;
        int i = size;
        this.size++;

        //A method that bubbles up and down the array

        //For min heap
        if(this.state) {
            while (i > 0) {
                int parent = (i - 1) / 2;

                if (elements[i].key >= elements[parent].key) {
                    break;
                }

                swap(i, parent);

                i = parent;
            }
        }

        //For max Heap
        else {
        }
        while (i > 0) {
            int parent = (i - 1) / 2;

            if (elements[i].key <= elements[parent].key) {
                break;
            }

            swap(i, parent);

            i = parent;
        }

        //Dynamic sizing.
        if (elements.length == size) {
            doubleSize();
        }
    }

    public Entry top() {
        return elements[0];
    }

    public Entry remove(Entry e) {
        swap(search(e.key), size);
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
        Entry[] temp2 = new Entry[size];
        temp1 = elements;
        elements = temp2;

        // Puts the datas back in the new array.
        for(int i = 0; i < elements.length; i++){
            insert(temp1[i].key, temp1[i].value);
        }
    }
}

