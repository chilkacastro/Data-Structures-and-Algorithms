package mylist;

/**
 * TestCustomMethods
 * @author Chilka Castro and Christian David
 */
public class TestCustomMethods {
    public static void main(String[] args) {
        // MYARRAYLIST
        System.out.println("===============Testing MyArrayList===============");
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
        System.out.println("\n===============Testing MyLinkedList===============");
        MyLinkedList<Integer> list2 = new MyLinkedList<>();
        list2.add(8);
        list2.add(12);
        list2.add(83);
        list2.add(31);
        list2.add(9);
        list2.add(12);
        list2.add(67);
        System.out.println(list2.toString() + " size: " + list2.size());
        System.out.println("Remove index 2 value");
        list2.remove(2);
        System.out.println(list2.toString() + " size: " + list2.size());
        System.out.println("Add to index 3 value 23");
        list2.add(3, 23);
        System.out.println(list2.toString() + " size: " + list2.size());
        System.out.println("First value 12 removed? " + list2.remove((Integer) 12));
        System.out.println(list2.toString() + " size: " + list2.size());
        System.out.println("Index 4 value removed");
        list2.remove(4);
        System.out.println(list2.toString() + " size: " + list2.size());
        System.out.println("Clear list");
        list2.clear();
        System.out.println(list2.toString() + " size: " + list2.size());
    }
}
