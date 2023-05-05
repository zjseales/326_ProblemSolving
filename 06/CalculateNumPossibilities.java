/** CalculateNumPossibilites.java
 *  Etude 06 - COSC 326
 * 
 *  Calculates the number of possible subsets from a complete set
 *  of unique items and returns the answer.
 *@author Amaan Aamir
 *@author Zac Seales
 */

public class CalculateNumPossibilities {

    /** Retrieves the binomial coefficient.
     *@param n - the number of rows.
     *@param k - the row element number.
     *@return - the answer.
     */
    public long retrieveBinomialCoefficient(long n, long k) {
        //alters k to it's lower counterpart-equivalent
        if ((n % 2 == 0 && k >= n / 2)
                || n % 2 != 0 && k > ((n / 2) + 0.5)){
            k = n - k;
        }
        //test proper k value is calculated.
        //System.out.println(k + "");
        
        //early detection of exceptional results
        if (n < 2 || k == 0){
            return 1;
        } else if (k == 1){
            return n;
        } else if (n == 2){
            return 2;
        } else if (k == 2){
            if (n > Long.parseLong("4294967296")){
                System.err.println("Out of range");
                return 0;
            }
            long result = (long)(((double)n / 2) * (n - 1));
            return result;
        }

        // initializes a max size array
        long[][] sadboi  = new long[(int)n + 2][(int)k + 1];

        sadboi[0][0] = 0;

        try {
            for (long row = 1; row <= n + 1; row++){
                for (int i = 1; i <= k ; i++){
                    sadboi[(int)row][i] 
                        = sadboi[(int)row - 1][i] + sadboi[(int)row - 1][i - 1];
                    if(sadboi[(int)row][i] < 0){
                        System.err.println("Out of range");
                        return 0;
                    }
                }
                sadboi[(int)row][0]++;
            }
            return sadboi[(int)n + 1][(int)k];
        } catch (IndexOutOfBoundsException | NumberFormatException | OutOfMemoryError e){
            System.err.println("Output larger than 64-bit");
        }
        return 0;
        
    }

}