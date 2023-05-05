import java.util.InputMismatchException;
import java.util.Scanner;

/** Counter.java
 *  Etude 06 - COSC 326
 * 
 *  Reads in two 64-bit integer values (longs).
 *  One represents a total and the other represents a subset of the total.
 *  The 'CalculateNumPossibilites' class is then called to compute and
 *  return the number of possible subsets for the given total.
 *@author Zac Seales
 *@author Amaan Aamir
 */

public class Counter {

    /** Reads in 2 long values via stdin, computes their "n/k" value,
     *  and displays the result to stdout.
     *@param args - unused parameter.
     */
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        CalculateNumPossibilities calc = new CalculateNumPossibilities();
        long totalSet, subset, totalValue;

        while(scan.hasNextLong()){

            try {
                totalSet = scan.nextLong();
                subset = scan.nextLong();
            } catch (InputMismatchException e){
                System.out.println("Invalid Input. Must be 64-bit integer (long)");
                continue;
            }

            if (totalSet < 0 || subset < 0){
                System.out.println("Invalid Input: Numbers must be positive.");
                continue;
            }
            
            totalValue = calc.retrieveBinomialCoefficient(
                            Math.max(totalSet,subset), Math.min(totalSet,subset));
            //Output the calculated answer, if successful
            if (totalValue > 0){
                System.out.println(totalValue + "");
            }
        }
        scan.close();
    } //end main

}