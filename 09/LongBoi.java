import java.util.Scanner;

/** LongBoi.java
 *  COSC 326 - Etude 09
 * 
 *  Takes an alphabet as input and displays the longest possible string,
 *  using the alphabet, that contains no repeated sequences.
 *@author Liam Wilson
 *@author Eliot Luna
 *@author Zac Seales
 *@author Amaan Aamir
 */

public class LongBoi {

    /** Takes input strings from stdin, calls the calculate method of 
     *  the ComputeLongBoi class, and displays the result to stdout.
     *@param args - unused parameter.
     */
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        ComputeLongBoi stringCalc = new ComputeLongBoi();
        String alphabet;

        while(scan.hasNext()){

            alphabet = scan.next();
            alphabet.toLowerCase();
            alphabet.trim();

            //Ensures alphabet is not empty or null.
            if(alphabet == null || alphabet.isEmpty()){
                continue;
            }
            //converts input to an 'alphabet'
            alphabet = stringCalc.sortAlph(alphabet);
            //calculate and display longest possible string
            //with no repeating sequences
            String result = stringCalc.calculate(alphabet);
            System.out.println(result + " | Length: " + result.length());
        }
        scan.close();
    }//end main method

}//end class