package mylist;

/**
 *  MyList interface
 * @author Chilka Castro and Christian David
 */
public interface MyList<E> {
    void add(int index, E e);
    Boolean add(E e);
    void clear();
    E remove(int index);
    Boolean remove(Object o);
    int size();
    String toString();
}
