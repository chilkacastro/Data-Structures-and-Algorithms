/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylist;

/**
 *
 * @author Dennis Christian
 */
public interface List<E> {
    void add(int index, E e);
    Boolean add(E e);
    void clear();
    E remove(int index);
    Boolean remove(Object o);
    int size();
    String toString();
}
