package mylist;

public class TestCustomMethods {
    public static void main(String[] args) {
        // MYARRAYLIST
        System.out.println("Testing MyArrayList");
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        list.add(5);
        System.out.println(list.toString() + " size: " + list.size());
        System.out.println("Remove index 2 value");
        list.remove(2);
        System.out.println(list.toString() + " size: " + list.size());
        System.out.println("Add to index 3 value 8");
        list.add(3, 8);
        System.out.println(list.toString() + " size: " + list.size());
        System.out.println("First value 2 removed? " + list.remove((Integer) 2));
        System.out.println(list.toString() + " size: " + list.size());
        System.out.println("Index 4 value removed");
        list.remove(4);
        System.out.println(list.toString() + " size: " + list.size());
        System.out.println("Clear list");
        list.clear();
        System.out.println(list.toString() + " size: " + list.size());

        // MYLINKEDLIST
        System.out.println("\nTesting MyLinkedList");
        MyLinkedList<Integer> list2 = new MyLinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(2);
        list2.add(1);
        list2.add(5);
        System.out.println(list2.toString() + " size: " + list2.size());
        System.out.println("Remove index 2 value");
        list2.remove(2);
        System.out.println(list2.toString() + " size: " + list2.size());
        System.out.println("Add to index 3 value 8");
        list2.add(3, 8);
        System.out.println(list2.toString() + " size: " + list2.size());
        System.out.println("First value 2 removed? " + list2.remove((Integer) 2));
        System.out.println(list2.toString() + " size: " + list2.size());
        System.out.println("Index 4 value removed");
        list2.remove(4);
        System.out.println(list2.toString() + " size: " + list2.size());
        System.out.println("Clear list");
        list2.clear();
        System.out.println(list2.toString() + " size: " + list2.size());
    }
}
