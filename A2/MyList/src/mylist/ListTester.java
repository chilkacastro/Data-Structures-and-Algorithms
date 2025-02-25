package mylist;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * ListTester class to test the performance of custom and standard lists
 * for insertions and removals
 * @author Chilka Castro and Christian David
 */
public class ListTester {
    private static final Random random = new Random();
    private static PrintStream output;

    public static void main(String[] args) {
        try {
            output = new PrintStream(new FileOutputStream("testrun.txt", true)); // append to file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

//        int[] sizes = {10, 100, 1000}; // different N values
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};

        printOutput("=".repeat(50));
        printOutput("            Performance Test Results");
        printOutput("=".repeat(50));

        for (int N : sizes) {
            printOutput("\n\nN = " + N);

            // Generate test data once (values between 0 to 2N)
            List<Integer> numbers = generateRandomNumbers(N, 2 * N);
            List<Integer> positions = generateRandomPositions(N);

            // Initialize custom lists
            MyList<Integer> myArrayList = new MyArrayList<>();
            MyList<Integer> myLinkedList = new MyLinkedList<>();

            // Initialize Java lists
            List<Integer> javaArrayList = new ArrayList<>();
            List<Integer> javaLinkedList = new LinkedList<>();

            // a) Test Insertions
            printOutput("\nInsertion Times (ms):");
            printOutput(String.format("%-15s %-15s %-15s %-15s", "List", "Insert@Start", "Insert@End", "Insert@Random"));

            testCustomInsertions(myArrayList, numbers, positions, "MyArrayList", N);
            testCustomInsertions(myLinkedList, numbers, positions, "MyLinkedList", N);
            testStandardInsertions(javaArrayList, numbers, positions, "ArrayList", N);
            testStandardInsertions(javaLinkedList, numbers, positions, "LinkedList", N);

            // b) Test Removals
            printOutput("\nRemoval Times (ms):");
            printOutput(String.format("%-15s %-15s %-15s %-15s", "List", "Remove@Start", "Remove@End", "Remove@Random"));

            testCustomRemovals(myArrayList, numbers, positions, "MyArrayList", N);
            testCustomRemovals(myLinkedList, numbers, positions, "MyLinkedList", N);
            testStandardRemovals(javaArrayList, numbers, positions, "ArrayList", N);
            testStandardRemovals(javaLinkedList, numbers, positions, "LinkedList", N);

            // c) Test Removing Random Values
            printOutput("\nRandom Value Removal Times (ms):");
            printOutput(String.format("%-15s %-15s", "List", "RemoveByValue"));

            testCustomRandomRemovals(myArrayList, numbers, "MyArrayList", N);
            testCustomRandomRemovals(myLinkedList, numbers, "MyLinkedList", N);
            testStandardRandomRemovals(javaArrayList, numbers, "ArrayList", N);
            testStandardRandomRemovals(javaLinkedList, numbers, "LinkedList", N);
        }

        output.close();
    }

    /**
     * Generates N random numbers in the range [0, maxValue]
     * @param count the number of numbers to generate
     * @param maxValue the maximum value of the numbers
     * @return the list of random numbers
     */
    private static List<Integer> generateRandomNumbers(int count, int maxValue) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(random.nextInt(maxValue + 1));
        }
        return numbers;
    }

    /**
     * Generates N random positions in the range [0, N]
     * @param count the number of positions to generate
     * @return the list of random positions
     */
    private static List<Integer> generateRandomPositions(int count) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            positions.add(random.nextInt(count));
        }
        return positions;
    }

    /**
     * Measures the start time of an operation
     * @return  the start time of the operation
     */
    private static long timeOperationStart() {
        return System.currentTimeMillis();
    }


    /** Measures execution time
     * @param startTime the start time of the operation
     */
    private static long timeOperationEnd(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Tests insertions for Custom Lists
     * @param list the list to test
     * @param numbers the list of numbers to insert
     * @param positions the positions to insert
     * @param listName the name of the list
     * @param N the size of the list
     */
    private static void testCustomInsertions(MyList<Integer> list, List<Integer> numbers, List<Integer> positions, String listName, int N) {
        list.clear();
        System.out.println(listName + " before Insert@Start: " + list);
        long start = timeOperationStart();
        for (Integer number : numbers)
            list.add(0, number);
        long timeStart = timeOperationEnd(start);
        System.out.println(listName + " after Insert@Start: " + list);

        list.clear();
        System.out.println(listName + " before Insert@End: " + list);
        start = timeOperationStart();
        for (Integer number : numbers)
            list.add(number);
        long timeEnd = timeOperationEnd(start);
        System.out.println(listName + " after Insert@End: " + list);

        list.clear();
        System.out.println(listName + " before Insert@Random: " + list);
        start = timeOperationStart();
        for (int i = 0; i < numbers.size(); i++)
            list.add(positions.get(i) % (list.size() + 1), numbers.get(i));
        long timeRandom = timeOperationEnd(start);
        System.out.println(listName + " after Insert@Random: " + list);

        printOutput(String.format("%-15s %-15d %-15d %-15d", listName, timeStart, timeEnd, timeRandom));
    }

    /**
     * Tests insertions for Standard Lists
     * @param list the list to test
     * @param numbers the list of numbers to insert
     * @param positions the positions to insert
     * @param listName the name of the list
     * @param N the size of the list
     */
    private static void testStandardInsertions(List<Integer> list, List<Integer> numbers, List<Integer> positions, String listName, int N) {
        list.clear();
        System.out.println(listName + " before Insert@Start: " + list);
        long start = timeOperationStart();
        for (Integer number : numbers)
            list.add(0, number);
        long timeStart = timeOperationEnd(start);
        System.out.println(listName + " after Insert@Start: " + list);

        list.clear();
        System.out.println(listName + " before Insert@End: " + list);
        start = timeOperationStart();
        list.addAll(numbers);
        long timeEnd = timeOperationEnd(start);
        System.out.println(listName + " after Insert@End: " + list);

        list.clear();
        System.out.println(listName + " before Insert@Random: " + list);
        start = timeOperationStart();
        for (int i = 0; i < numbers.size(); i++)
            list.add(positions.get(i) % (list.size() + 1), numbers.get(i));
        long timeRandom = timeOperationEnd(start);
        System.out.println(listName + " after Insert@Random: " + list);

        printOutput(String.format("%-15s %-15d %-15d %-15d", listName, timeStart, timeEnd, timeRandom));
    }

    /**
     * Tests removals for Custom Lists
     * @param list the list to test
     * @param numbers  the list of numbers to remove
     * @param positions the positions to remove
     * @param listName the name of the list
     * @param N the size of the list
     */
    private static void testCustomRemovals(MyList<Integer> list, List<Integer> numbers, List<Integer> positions, String listName, int N) {
        list.clear();
        for (Integer num : numbers)
            list.add(num);
        System.out.println(listName + " before Remove@Start: " + list);
        long start = timeOperationStart();
        while (list.size() > 0)
            list.remove(0);
        long timeStart = timeOperationEnd(start);
        System.out.println(listName + " after Remove@Start: " + list);

        list.clear();
        for (Integer num : numbers)
            list.add(num);
        System.out.println(listName + " before Remove@End: " + list);
        start = timeOperationStart();
        while (list.size() > 0)
            list.remove(list.size() - 1);
        long timeEnd = timeOperationEnd(start);
        System.out.println(listName + " after Remove@End: " + list);

        list.clear();
        for (Integer num : numbers)
            list.add(num);
        System.out.println(listName + " before Remove@Random: " + list);
        start = timeOperationStart();
        for (int pos : positions) {
            if (list.size() > 0)
                list.remove(pos % list.size());
        }
        long timeRandom = timeOperationEnd(start);
        System.out.println(listName + " after Remove@Random: " + list);

        printOutput(String.format("%-15s %-15d %-15d %-15d", listName, timeStart, timeEnd, timeRandom));
    }

    /**
     * Tests removals for Standard Lists
     * @param list the list to test
     * @param numbers  the list of numbers to remove
     * @param positions the positions to remove
     * @param listName the name of the list
     * @param N the size of the list
     */
    private static void testStandardRemovals(List<Integer> list, List<Integer> numbers, List<Integer> positions, String listName, int N) {
        list.clear();
        list.addAll(numbers);
        System.out.println(listName + " before Remove@Start: " + list);
        long start = timeOperationStart();
        while (!list.isEmpty())
            list.remove(0);
        long timeStart = timeOperationEnd(start);
        System.out.println(listName + " after Remove@Start: " + list);

        list.clear();
        list.addAll(numbers);
        System.out.println(listName + " before Remove@End: " + list);
        start = timeOperationStart();
        while (!list.isEmpty())
            list.remove(list.size() - 1);
        long timeEnd = timeOperationEnd(start);
        System.out.println(listName + " after Remove@End: " + list);

        list.clear();
        list.addAll(numbers);
        System.out.println(listName + " before Remove@Random: " + list);
        start = timeOperationStart();
        for (int pos : positions) {
            if (!list.isEmpty())
                list.remove(pos % list.size());
        }
        long timeRandom = timeOperationEnd(start);
        System.out.println(listName + " after Remove@Random: " + list);

        printOutput(String.format("%-15s %-15d %-15d %-15d", listName, timeStart, timeEnd, timeRandom));
    }

    /**
     * Tests removals of random values for Custom Lists
     * @param list the list to test
     * @param numbers  the list of numbers to remove
     * @param listName the name of the list
     * @param N the size of the list
     */
    private static void testCustomRandomRemovals(MyList<Integer> list, List<Integer> numbers, String listName, int N) {
        list.clear();
        for (Integer num : numbers)
            list.add(num);
        System.out.println(listName + " before RemoveByValue: " + list);
        long start = timeOperationStart();
        for (Integer value : numbers)
            list.remove(value);
        long timeRemoveByValue = timeOperationEnd(start);
        System.out.println(listName + " after RemoveByValue: " + list);

        printOutput(String.format("%-15s %-15d", listName, timeRemoveByValue));
    }

    /**
     * Tests removals of random values for Standard Lists
     * @param list the list to test
     * @param numbers the list of numbers to remove
     * @param listName the name of the list
     * @param N the size of the list
     */
    private static void testStandardRandomRemovals(List<Integer> list, List<Integer> numbers, String listName, int N) {
        list.clear();
        list.addAll(numbers);
        System.out.println(listName + " before RemoveByValue: " + list);
        long start = timeOperationStart();
        for (Integer value : numbers)
            list.remove(value);
        long timeRemoveByValue = timeOperationEnd(start);
        System.out.println(listName + " after RemoveByValue: " + list);

        printOutput(String.format("%-15s %-15d", listName, timeRemoveByValue));
    }

    /**
     * Prints the message to the console and the output file
     * @param message the message to print
     */
    private static void printOutput(String message) {
        System.out.println(message);
        output.println(message);
    }
}