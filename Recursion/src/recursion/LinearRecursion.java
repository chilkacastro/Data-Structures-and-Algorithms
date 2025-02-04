package recursion;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Linear Recursion Tetranacci Calculator
 * This class computes the Tetranacci sequence using linear recursion.
 * It measures execution time and writes the output to a shared file.
 *
 * @author Chilka Castro and Christian David
 */
public class LinearRecursion {
    private static final String OUTPUT_FILE = "LinearRecursion_TetraOut.txt";  
    private static final String CALCULATOR_TYPE = "Linear Recursion";
    
    public static void main(String[] args) {
        int n = getUserInput();                 // comment this if user input isnt needed
//        int n = 7;  
        long executionTime = measureExecutionTime(n);
        String output = generateOutput(n, executionTime);
        
        System.out.println(output);
        writeOutputToFile(output);
    }
    
    /**
     * Gets the user input for the Tetranacci sequence.
     * @return the user entered integer
     */
    public static int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        scanner.close();
        return n;
    }
    
    /**
     * Measures the execution time of the Tetranacci calculation.
     * @param n the number for which Tetranacci is calculated
     * @return execution time in milliseconds
     */
    public static long measureExecutionTime(int n) {
        long start = System.currentTimeMillis();
        BigInteger result = linearTetranacci(n)[3];
        long end = System.currentTimeMillis();
        return end - start;
    }
    
    /**
     * Generates the formatted output string for console and file output.
     * @param n the input number
     * @param executionTime the execution time in milliseconds
     * @return formatted output string
     */
    public static String generateOutput(int n, long executionTime) {
        return String.format("[%s] Tetranacci of %d: %s\nExecution Time in milliseconds: %d ms\n", 
            CALCULATOR_TYPE, n, linearTetranacci(n)[3].toString(), executionTime);
    }
    
    /**
     * Writes the Tetranacci output and execution time.
     * @param output the formatted output string
     */
    public static void writeOutputToFile(String output) {
        try (FileWriter writer = new FileWriter(OUTPUT_FILE, true)) { 
            writer.write(output + "\n"); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Computes the Tetranacci sequence using linear recursion.
     * The function maintains an array of the last four computed values, 
     * shifting values forward at each step and computing the next value 
     * recursively.
     * 
     * @param k the index of the Tetranacci sequence
     * @return an array containing the computed Tetranacci values
     */
    public static BigInteger[] linearTetranacci(int k) {
        BigInteger[] elements = {BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO};   // {0,0,0,0}
        
        if (k <= 2)
            return elements;
        else if (k == 3) {
            elements[3] = BigInteger.ONE;     //{ 0, 0, 0, 1}
            return elements;
        }
        else {
            elements = linearTetranacci(k - 1);
            BigInteger total = elements[0].add(elements[1]).add(elements[2]).add(elements[3]);
            elements[0] = elements[1];
            elements[1] = elements[2];
            elements[2] = elements[3];
            elements[3] = total;
            
            return elements;
        }
    }
}
