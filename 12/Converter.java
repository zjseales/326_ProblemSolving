import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/** Converter.java
 *  COSC 326 - Etude 12
 * 
 *  Converts IBM floats to IEEE standard by altering the 
 *  format of the binary string.
 *@author Zac Seales - 6687905
 */

public class Converter {
    // A hex to binary hashtable.
    private static Hashtable<Character, String> htab;

    /** Constructor - initializes the hash table. */
    public Converter(){
        initTab();
    }

    /** Initializes the hex to binary hashtable.
     */
    private void initTab(){
        htab = new Hashtable<>();
        htab.put('0', "0000");
        htab.put('1', "0001");
        htab.put('2', "0010");
        htab.put('3', "0011");
        htab.put('4', "0100");
        htab.put('5', "0101");
        htab.put('6', "0110");
        htab.put('7', "0111");
        htab.put('8', "1000");
        htab.put('9', "1001");
        htab.put('a', "1010");
        htab.put('b', "1011");
        htab.put('c', "1100");
        htab.put('d', "1101");
        htab.put('e', "1110");
        htab.put('f', "1111");
    }

    /** Converts the hexadecimal string to a binary string.
     *@param hexadec - the hex string.
     *@return - the binary string.
     */
    public String toBin(String hexadec){
        String bString = "";
        for (int i = 0; i < hexadec.length(); i++){
            bString += htab.get(hexadec.charAt(i));
        }
        return bString;
    }

    /** Converts the ibm formatted binaryString argument to 
     *  decimal format.
     *@param ibmString - binary string in IBM format.
     *@return - the decimal value of the ibm float.
     */
    public float toIEEE(String ibmString){
        //the sign bit as an integer
        int s;

        if (ibmString.charAt(0) == '0'){
            s = 1;
        } else {
            s = -1;
        }
        String e = "";
        String f = "";
        for (int i = 1; i < 8; i++){
            e += ibmString.charAt(i);
        }
        int exp = toDec(e);

        //System.out.println("exponent is " + exp);

        //get fraction part from binary string
        for (int i = 8; i < ibmString.length(); i++){
            f += ibmString.charAt(i);
        }
        int fract = toDec(f);
        
        //convert ibm to decimal value.
        float valD = s * (float)Math.pow(16,exp-64) * fract / (float)(Math.pow(16, 6));

        return valD;
    }

    /** Converts the given binary string to decimal.
     *@param b - the binary string.
     *@return - the decimal equivalent.
     */
    private int toDec(String bString){
        int total;
        if (bString.charAt(bString.length()-1) == '1'){
            total = 1;
        } else {
            total = 0;
        }
        for (int i = bString.length() - 2; i >= 0; i--){
            if (bString.charAt(i) == '1'){
                total += Math.pow(2,Math.abs(i - bString.length() + 1));
            }
        }
        return total;
    }

    /** Converts the input binary String to a hexadecimal
     *  String.
     *@param b - the binary string.
     *@return - the hex string.
     */
    public String toHex(String b){
        String newString = "";
        String temp = "";
        for (int i = 0; i < b.length(); i++){
            if (i % 4 == 0 && i != 0){
                newString += reverseTable(temp);
                temp = "";
            }
            temp += b.charAt(i);
        }
        newString += reverseTable(temp);
        return newString;
    }

    /** Iterates the hashtable to find the hex key for the given binary string.
     *@param c - the binary String.
     *@return - the hax value.
     */
    private char reverseTable(String c){
        char key = ' ';
        for(Map.Entry<Character, String> entry: htab.entrySet()){
            if(c.equals(entry.getValue())){
                key = (char)entry.getKey();
                break; //break once key found.
            }
        }
        return key;
    }

    /** Removes any text from the file to ensure no data from a previous 
     *  execution has remained.
     *@param f - the filename.
     */
    public void resetFile(String f){
        FileWriter writer;
        try {
            writer = new FileWriter(f, false);
            writer.append("");
            writer.close();
        } catch (IOException e){
            System.err.println("File '" + f + "' not found.");
        }
    }

}
