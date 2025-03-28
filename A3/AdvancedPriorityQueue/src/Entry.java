
/**
 * Entry class for PriorityQueue
 * @author Chilka Castro and Christian David
 */
public class Entry {
    private String value;
    private int key;
    private int index;

    /**
     * Constructor for Entry
     * @param value the value to be stored
     * @param key  the key to be stored
     */
    public Entry(int key, String value) {
        this.value = value;
        this.key = key;
    }
//
//    /**
//     * Change the index of the Entry
//     * @param i
//     */
//    public void changeIndex(int i) {
//        this.index = i;
//    }
    // Getters and Setters
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }
}
