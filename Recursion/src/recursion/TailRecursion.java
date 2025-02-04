
package recursion;

/**
 *
 * @author Chilka Castro and Christian David
 */
public class TailRecursion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {   
        long start = System.currentTimeMillis();
        System.out.println("Tail Tetranacci: " +  startTetranacci(7));
        long end = System.currentTimeMillis();  
        System.out.println("Elapsed Time in milliseconds: " + (end - start));
    }
    
    /**
     * 
     * @param k
     * @param a
     * @param b
     * @param c
     * @param d
     * @return 
     */
    public static int tailTetranacci(int k, int a, int b, int c, int d) {
        if (k == 3)
            return d;
        else
            return tailTetranacci(k - 1, b, c, d, a + b + c + d);
    }
     
    /**
     * 
     * @param k
     * @return 
     */
    public static int startTetranacci(int k) {
        if (k < 3)
            return 0;
        else if (k == 3) 
            return 1;
        else 
            return tailTetranacci(k, 0, 0, 0, 1);
    }  
};