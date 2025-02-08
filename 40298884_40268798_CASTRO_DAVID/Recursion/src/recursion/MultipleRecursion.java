package recursion;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Multiple Recursion Tetranacci Calculator
 * This class computes the Tetranacci sequence using multiple recursion.
 * It measures execution time and writes the output to a shared file.
 *
 * @author Chilka Castro and Christian David
 */
public class MultipleRecursion {
    private static final String OUTPUT_FILE = "MultipleRecursion_TetraOut.txt";     
    private static final String CALCULATOR_TYPE = "Multiple Recursion";
    
    public static void main(String[] args) {
        int n = getUserInput();               // comment this if user input isnt needed
//        int n = 7
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
        BigInteger result = multipleTetranacci(n);
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
            CALCULATOR_TYPE, n, multipleTetranacci(n).toString(), executionTime);
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
     * Computes the Tetranacci number using multiple recursion.
     * @param k the index of the Tetranacci sequence
     * @return the computed Tetranacci value
     */
    public static BigInteger multipleTetranacci(int k) { 
        if (k <= 2) 
            return BigInteger.ZERO;
        else if (k == 3) 
            return BigInteger.ONE;
        else 
            return multipleTetranacci(k - 1).add(multipleTetranacci(k - 2))
                    .add(multipleTetranacci(k - 3)).add(multipleTetranacci(k - 4));
    }
}
