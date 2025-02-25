package mylist;

import java.util.Objects;

/**
 * MyLinkedList class
 * @author Chilka Castro and Christian David
 */
public class MyLinkedList<E> implements List<E> {
  /**
   * Inner Node class
   *
   */
  private static class Node<E> {
    private E data;
    private Node<E> next;
    private Node<E> prev;

    /**
     * Constructor
     *
     * @param data the data to be stored in the node
     */
    private Node(E data) {
      this.data = data;
      this.next = null;
      this.prev = null;
    }
  }

  private Node<E> head;
  private Node<E> tail;
  private int size;

  /**
   * Constructor
   */
  public MyLinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * Appends the specified element to the end of this list
   *
   * @param e element to be appended to this list
   * @return True or False if the element was added
   */
  public Boolean add(E e) {
    Node<E> newNode = new Node<>(e);
    if (head == null) {
      head = tail = newNode;
    } else {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
    size++;
    return true;
  }

  /**
   * Inserts the specified element at the specified position in this list
   *
   * @param index index at which the specified element is to be inserted
   * @param e     element to be inserted
   */
  public void add(int index, E e) {
    if (index > size || index < 0) { // if the index is out of bounds, throw an exception
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }

    Node<E> newNode = new Node<>(e);
    if (index == 0) {   // if the index is 0, add to the head
      if (head == null) { // no nodes in the list so set the new node as the head and tail
        head = tail = newNode;
      }
      else { // if there are nodes in the list, set the new node as the head
        newNode.next = head; // set the new node's next to the current head
        head.prev = newNode; // set the current head's previous to the new node
        head = newNode;  // set the new node as the head
      }
    }
    else if (index == size) {  // if the index is the size, add to the end
        tail.next = newNode; // set the tail's next to the new node
        newNode.prev = tail; // set the new node's previous to the tail
        tail = newNode; // set the new node as the tail
    }
    else { // if the index is in the middle
        Node<E> current = head; // set the current node to the head
        for (int i = 0; i < index; i++) { // loop through the list before index
          current = current.next; // set the current node to the next node -> will stop at the specified index because of current = current.next
        }
        newNode.next = current;  // set the new node's next to the current node's next
        newNode.prev = current.prev; // set the new node's previous to the current node's previous -- so copy the current node's previous
        current.prev.next = newNode; //  the new node is the current node's previous next now after insertion
        current.prev = newNode; // the new node is the current node's previous now after insertion
    }
    size++;
  }

  /**
   * Removes all elements from this list
   */
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Removes the element at the specified position in this list
   *
   * @param index index of the element to be removed
   * @return the element that was removed from the list
   */
  public E remove(int index) {
    if (index < 0 || index >= size) {  // if the index is out of bounds, throw an exception
       throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
    Node<E> current = head;           // set the current node to the head
    for (int i = 0; i < index; i++) { // loop through the list until the index
      current = current.next;          // set the temp node to the next node, in the end points to the node to be removed
    }

    E removedElement = current.data;   // store the element to return
    // HEAD
    if (current.prev != null) {        // if the current node's previous is not null
       current.prev.next = current.next; // set the current node's previous next to the current node's next
    }
    else {   // if the current node's previous is null -- this means the current node is the head
      head = current.next;  // the current node's next is the new head
      if (head != null) { // since the current node is the head, the new head's previous is null -- so set the new head's previous to null
          head.prev = null;
      }
    }
    // MIDDLE
    if (current.next != null) { // if the current node's next is not null -- this means the current node is in the middle
      current.next.prev = current.prev; // set the current node's next previous to the current node's previous
    } // TAIL
    else {   // if the current node's next is null -- this means the current node is the tail
      tail = current.prev; // the current node's previous is the new tail
        if (tail != null) {
            tail.next = null;
        }
    }
    size--; // decrement the size
    return removedElement; // return the removed element
  }

  /**
   * Removes the first occurrence of the specified element from this list, if it is present
   *
   * @param o element to be removed from this list, if present
   * @return True or False if the element was removed
   */
  public Boolean remove(Object o) {
    Node<E> current = head;  // set the current node to the head to start the search with the first node

    while (current != null) { // loop through the list until the current node is null
      if ((o == null && current.data == null) || (o != null && o.equals(current.data))) { // if the current node's data is equal to the object - either null or equal to the object
        if (current.prev != null) {   // if the current node's previous is not null -- this means the current node is not the head
          current.prev.next = current.next; // set the current node's previous next to the current node's next
        } else {   // if the current node's previous is null -- this means the current node is the head
          head = current.next;  // the current node's next is the new head
            if (head != null) {   // since the current node is the head, the new head's previous is null -- so set the new head's previous to null
                head.prev = null;  // set the new head's previous to null
            }
        }
        if (current.next != null) { // if the current node's next is not null -- this means the current node is not the tail
          current.next.prev = current.prev;
        } else { // if the current node's next is null -- this means the current node is the tail
          tail = current.prev; // the current node's previous is the new tail
          if (tail != null) {   // since the current node is the tail, the new tail's next should be null -- so set the new tail's next to null
            tail.next = null;  // set the new tail's next to null
          }
        }
        size--;
        return true; // return true if the element was found and removed
      }
      current = current.next;  // moves to the next node to check -- while loop will stop when the current node is null
    }
    return false; // if the element was not found, return false
  }

  /**
   * Returns the number of elements in this list
   *
   * @return the number of elements in this list
   */
  public int size() {
    return size;
  }

  /**
   * Returns a string representation of the object
   *
   * @return String representation of the object
   */
  public String toString() {
    String result = "";
    Node temp = head;
    while (temp != null) {
      result += temp.data;
      if (temp.next != null) {
        result += ", ";
      }
      temp = temp.next;
    }
    return String.format("[%s]", result);
  }
}