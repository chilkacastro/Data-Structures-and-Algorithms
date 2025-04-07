/**
 * Entry class for PriorityQueue
 *
 * @author Chilka Castro and Christian David
 */
public class Entry {
    private String value;
    private int key;

    /**
     * Constructor for Entry
     *
     * @param value the value to be stored
     * @param key   the key to be stored
     */
    public Entry(int key, String value) {
        this.value = value;
        this.key = key;
    }

    /**
     * Get the value of Entry
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value of Entry
     * @param value the value to be set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Get the key of Entry
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * Set the key of Entry
     * @param key the key to be set
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Compare two Entry objects
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Entry other = (Entry) obj;
        return key == other.key &&
                (value == null ? other.value == null : value.equals(other.value));
    }

    /**
     * Hash code for Entry
     *
     * @return hash code for Entry
     */
    @Override
    public int hashCode() {
        // Use the key and value to generate a hash code
        int result = Integer.hashCode(key);
        // Use a prime number to reduce collisions
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    /**
     * String representation of Entry
     *
     * @return string representation of Entry
     */
    @Override
    public String toString() {
        return "{key=" + key + ", value=" + value + "}";
    }
}