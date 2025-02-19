/*
 * The MIT License
 *
 * Copyright 2025 chilcj.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package mylist;

/**
 *
 * @author Chilka Castro
 */
public class MyArrayList {
 
     public static int[] myList = new int[5];


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//  
    }
    
    public static Boolean add(int n) {
        for (int i = 0; i < myList.length; i++) {
            if (myList[i] != 0) {
                myList[i] = n;
                return true;
            }
        }
        return false;
    }
    
    public static void add(int index, int n) {
        for (int i = 0; i < myList.length; i++) {
            if (i == index) {
                myList[i] = n;
            }
        }
    }
    
    public static void clear() {
        int[] temp = new int[myList.length];
        myList = temp;
    }
    
    public static int size() {
        return myList.length;
    }
    
    public String toString() {
        StringBuilder temp = new StringBuilder("Numbers are: ");
        for (int i = 0; i < myList.length; i++) {
            temp.append(myList[i] + ", ");
        }
        return temp.toString();
    }
    
    public static void doubleSize() {
        int[] temp = new int[myList.length * 2];
        for (int i = 0; i < myList.length; i++) {
            temp[i] = myList[i];
        }
        myList = temp;
    }
    
    public static void halfSize() {
        int[] temp = new int[myList.length / 2];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = myList[i];
        }
        myList = temp;
    }

}
