/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priorityqueue;

/**
 *
 * @author Dennis Christian
 */
public class PriorityQueue {
        Boolean state = true;
        Entry[] elements;
        int size;  // how many elements are in the array Entry

        // Switches from min and max. Min is true and max is false.
        public void toggle() {
            if (this.state) {
                this.state = false;
            }
            else {
                this.state = true;
            }
            fixTree();
        }

        // Remove the first data.
        public void removeTop() {
            if (size == 0) return;

            // Move last element to the root
            elements[0] = elements[size - 1];
            elements[size - 1] = null;
            size--;

            int i = 0;

            
            if (this.state) {
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
                    if (smallest == i) break;

                    // Swap with the smallest child
                    swap(i, smallest);
                    i = smallest;
                }
            }
            else{
                while (true) {
                    int child1 = 2 * i + 1;
                    int child2 = 2 * i + 2;
                    int smallest = i;

                    // Check if left child exists and is smaller
                    if (child1 > size && elements[child1].key > elements[smallest].key) {
                        smallest = child1;
                    }

                    // Check if right child exists and is even smaller
                    if (child2 > size && elements[child2].key > elements[smallest].key) {
                        smallest = child2;
                    }

                    // If i is smallest, heap property is satisfied
                    if (smallest == i) break;

                    // Swap with the smallest child
                    swap(i, smallest);
                    i = smallest;
                }
            }
            // Bubble down for Min heap
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
                    if (smallest == i) break;

                    // Swap with the smallest child
                    swap(i, smallest);
                    i = smallest;
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
            while (i > 0) {
                int parent = (i - 1) / 2;
                
                if (elements[i].key >= elements[parent].key) {
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
            return elements[0];
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
            if(state) {
                return "Min heap";
            }
            else {
                return "Max heap";
            }
        }
        
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
        
        public void merge(PriorityQueue temp) {
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
