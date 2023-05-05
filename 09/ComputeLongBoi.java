import java.util.*;

/** ComputeLongBoi.java
 *  COSC 326 - Etude 09
 * 
 *  Uses characters of a string to generate the longest
 *  possible string containing no repeated sequences.
 *@author Liam Wilson
 *@author Eliot Luna
 *@author Zac Seales
 *@author Amaan Aamir
 */

public class ComputeLongBoi {

    /** Computes and returns the final string output using the 
     *  input string.
     *@param s - the alphabet being used.
     *@return - the longest string.
     */
    public String calculate(String s){
        String longboi;
        longboi = firstIteration(s);

        //exceptions that do not undergo the 2nd iteration.
        if (s.length() == 1){
            return longboi;
        } else if (s.length() == 2){
            return longboi + s.charAt(0);
        }
        
        //the second, recursive, iteration.
        longboi = secondIteration(s, 0, longboi);
        //string always ends with first character in the alphabet.
        return longboi += s.charAt(0);
    }

    /** Performs the first iteration of the alphabet.
     *  Adds 2 of each character to the output string.
     *@param s - the alphabet
     *@return - the resulting string.
     */
    private String firstIteration(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++){
            result += s.charAt(i);
            result += s.charAt(i);
        }
        return result;
    }

    /** Performs the second iteration. Recursively iterates the alphabet.
     *  Each recursive method call will loop the alphabet again, adding 
     *  the characters from both the inner and outer indices to the final
     *  result. The inner loop starts from the index point of the outer 
     *  recursive loop (defined by curr).
     *@param s - the alphabet.
     *@param curr - the current index of the outer recursive loop.
     *@param result - the final string.
     *@return - the longest possible string of the given alphabet.
     */
    private String secondIteration(String s, int curr, String result){
        if(curr == s.length() - 2){
            return result;
        }
        for (int i = curr + 1; i < s.length(); i++){
            result += s.charAt(i);
            result += s.charAt(curr);
        }
        //removes the final character for each recursive call.
        result = result.substring(0, result.length() - 1);
        return secondIteration(s, curr + 1, result);
    }

    /** Alphabetically sorts the characters of the String 
     *  input argument, and removes any duplicate characters.
     *  (Simplifies later computation).
     *@param s - the string being converted to an alphabet.
     *@return - the sorted string of unique characters.
     */
    public String sortAlph(String s){
        char[] arr = s.toCharArray();
        StringBuffer temp = new StringBuffer();
        Arrays.sort(arr);
        //adds unique characters to a StringBuffer (temp)
        for (int i = 0; i < arr.length; i++){
            if(i == 0 || arr[i] != arr[i - 1]){
                temp.append(arr[i]);
            }
        }
        //returns the unique characters.
        return new String(temp);
    }

}
