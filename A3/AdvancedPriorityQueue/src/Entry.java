
/**
 * Entry class for PriorityQueue
 * @author Chilka Castro and Christian David
 */
public class Entry {
    private String value;
    private int key;

    /**
     * Constructor for Entry
     * @param value the value to be stored
     * @param key  the key to be stored
     */
    public Entry(int key, String value) {
        this.value = value;
        this.key = key;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Entry other = (Entry) obj;
        return key == other.key && (value == null ? other.value == null : value.equals(other.value));
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(key);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{key=" + key + ", value=" + value + "}";
    }
    }