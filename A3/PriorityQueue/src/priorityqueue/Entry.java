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
public class Entry {
    String value;
    int key;
    int index;

    public Entry(String value, int key) {
        this.value = value;
        this.key = key;
    }
    
    public void changeIndex(int i) {
        this.index = i;
    }
}
