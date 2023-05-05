import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

/** IBMtoIEEE.java
 *  COSC 326 - Etude 12
 *
 * The executable class for etude 12.
 * - Prompts user for an input file and an output filename,
 * - Converts all IBM floats from the file to IEEE format.
 * - Outputs the results to a file with the user-specified name.
 *
 *@author - Zac Seales - 6687905
 */

public class IBMtoIEEE {

    /** Reads files of Hexadecimal values that represent IBM floats,
     *  converts them to IEEE, and outputs the results to the 
     *  filename specified by the user.
     *@param args - unused.
     */
    public static void main(String[] args) throws IOException {
	    Scanner scan = new Scanner(System.in);		//the Scanner object.
        String currString;
        float decimalVal;
        String fileIn;  //the input file name.
        String fileOut; //the output file name.
        Converter convert = new Converter();
        //Pattern representinhg a hex value.
        Pattern hexi = Pattern.compile("[0-9a-fA-F]+");

        OutputStream os;
        DataOutputStream dos;

        //prompt for user input.
        System.out.println("Enter the path of the file you wish to convert");
        fileIn = scan.nextLine().trim();
        System.out.println("Enter the name of the output file.");
        fileOut = scan.nextLine().trim();

        os = new FileOutputStream(fileOut);
        dos = new DataOutputStream(os);

        //removes any current txt in the file.
        convert.resetFile(fileOut);

        scan = new Scanner(new FileInputStream(fileIn));

        //iterates hex values.
    	while(scan.hasNext(hexi)){
            int i = 0;
            currString = "";
            //set up current hex string.
            while(scan.hasNext(hexi) && i != 4){
                currString += scan.next(hexi);
                i++;
            }
            currString = currString.toLowerCase();
            //convert hex input to binary
            currString = convert.toBin(currString);
            //convert binary string to IEEE format.
            decimalVal = convert.toIEEE(currString);
            dos.writeFloat(decimalVal);

    	}

        dos.close();
    	scan.close();
    	System.exit(0);
    }//end main

}//end IBMtoIEEE class