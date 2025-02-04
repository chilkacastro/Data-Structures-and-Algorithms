package recursion;

/**
 *
 * @author Chilka Castro and Christian D@avid
 */
public class LinearRecusion {
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] tetranacci = linearTetranacci(7);
        System.out.println("Linear Tetranacci: " + tetranacci[3]);       
        long end = System.currentTimeMillis();  
        System.out.printf("Elapsed Time in milliseconds: %d ms %n", (end - start));
    }
    
    /**
     * 
     * @param k
     * @return 
     */
    public static int[] linearTetranacci(int k) {
        int[] elements = {0, 0, 0, 0};
        
        if (k <= 2)
            return elements;
        else if (k == 3) {
            elements[k] = 1;
            return elements;
        }
        else {
            elements = linearTetranacci(k - 1);
            int total = elements[0] + elements[1] + elements[2] + elements[3];
            elements[0] = elements[1];
            elements[1] = elements[2];
            elements[2] = elements[3];
            elements[3] = total;
            
            return elements;
        }
    }
}
