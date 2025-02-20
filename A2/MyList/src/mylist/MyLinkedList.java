package mylist;

/**
 *
 * @author Chilka Castro and Christian David
 */
public class MyLinkedList<E> implements List<E> {
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

  }

  // Implement the entire doubly linked list first

  public static class Node {
    Object data;
    Node next;
    Node prev;

    // Constructor
    public Node(Object data) {
      this.data = data;
      this.next = null;
      this.prev = null;
    }
  }

  public Node head;
  public Node tail;

  public Boolean add(E e) {
    Node newNode = new Node(e);
    if (head == null) {
      head = newNode;
      tail = newNode;
      return true;
    }
    newNode.next = head;
    head.prev = newNode;
    head = newNode;
    return true;
  }

  public void add(int index, E e) {
    Node newNode = new Node(e);
    Node temp = head;
    int counter = 0;

    // If index is 0
    if (index == 0) {
      if (head == null) {
        head = newNode;
        tail = newNode;
      }
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
      return;
    }

    // Finds the index
    while (counter != index || temp != null) {
      counter++;
      temp = temp.next;
    }

    // Add by the index
    if (counter == index) {
      newNode.next = temp;
      temp.prev = newNode;
      temp = newNode;
      return;
    }

    // if (head == null) {
    // head = newNode;
    // tail = newNode;
    // }
    // newNode.next = head;
    // head.prev = newNode;
    // head = newNode;
  }

  public int size() {
    int counter = 0;
    Node temp = head;
    while (temp != null) {
      counter++;
      temp = temp.next;
    }
    return counter;
  }

  public void clear() {
    head = null;
    tail = null;
  }

  public Boolean remove(Object o) {
    Node temp = head;
    while (temp != null && !temp.data.equals(o)) {
      temp = temp.next;
    }

    if (temp.equals(head)) {
      head = temp.next;
      if (head != null) {
        head.prev = null;
      }
      return true;
    }

    if (temp.equals(tail)) {
      tail = temp.prev;
      if (tail != null) {
        tail.next = null;
      }
      return true;
    }

    if (temp.data.equals(o)) {
      Node temp1 = temp.next;
      Node temp2 = temp.prev;

      temp1.prev = temp2;
      temp2.next = temp1;

      return true;
    }

    return false;
  }

  public E remove(int index) {
    Node temp = head;
    Node temp1;
    Node temp2;

    int counter = 0;

    // If index is on head.
    if (index == 0) {
      E removed = (E) head.data;
      head = temp.next;
      if (head != null) {
        head.prev = null;
      }
      return removed;
    }

    // Finds the index
    while (counter != index || temp != null) {
      counter++;
      temp = temp.next;
    }

    if (temp != null) {
      E removed = (E) temp.data;

      temp1 = temp.next;
      temp2 = temp.prev;

      temp1.prev = temp2;
      temp2.next = temp1;

      return removed;
    }

    // If index is on tail.
    if (temp.equals(tail)) {
      E removed = (E) tail.data;
      tail = temp.prev;
      if (tail != null) {
        tail.next = null;
      }
      return removed;
    }

    return null;
  }
}
