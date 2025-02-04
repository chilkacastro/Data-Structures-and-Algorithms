
package recursion;

/**
 *
 * @author Chilka Castro and Christian David
 */
public class MultipleRecursion {
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("Multiple Tetranacci: " + multipleTetranacci(7)); 
        long end = System.currentTimeMillis();  
        System.out.println("Elapsed Time in milliseconds: " + (end - start));
    }
    
    /**
     * 
     * @param k
     * @return 
     */
    public static int multipleTetranacci(int k) { 
        if (k == 0 || k == 1 || k == 2) 
          return 0;
        
        else if (k == 3) 
          return 1;

        else 
            return multipleTetranacci(k-1) + multipleTetranacci(k-2) + 
                    multipleTetranacci(k-3) + multipleTetranacci(k-4);
    }
    
}
