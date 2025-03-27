
/**
 * Entry class for PriorityQueue
 * @author Chilka Castro and Christian Davic
 */
public class Entry {
    String value;
    int key;
    int index;

    /**
     * Constructor for Entry
     * @param value the value to be stored
     * @param key  the key to be stored
     */
    public Entry(String value, int key) {
        this.value = value;
        this.key = key;
    }

    /**
     * Change the index of the Entry
     * @param i
     */
    public void changeIndex(int i) {
        this.index = i;
    }
}
