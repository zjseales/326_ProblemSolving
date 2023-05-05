import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Dates.java
 *  COSC326 etude01
 *
 * Reads a line of input, either from the user, or from a file
 * and determines whether the input String is a valid date, or not.
 *
 *@author Zac Seales - seaza886 - 6687905
 */

public class Dates{

    /** Reads a line of input from the user (or a file) and determines
     *  whether or not the input is a valid date.
     *@param args - possible file to be read.
     *@throws FileNotFoundException - catches file exception.
     */
    public static void main(String[] args) throws FileNotFoundException {
	String input;
	Scanner scan;
	DateCheck dc = new DateCheck();
	//checks for file, stdin if no file is read
	if (args.length < 1){
	    scan = new Scanner(System.in);
	} else {
	    //reads file as input 
	    scan = new Scanner(new FileInputStream(args[0]));
	}
	//iterates file
	while (scan.hasNextLine()){
	    input = scan.nextLine();
	    dc.isValidDate(input);
	}
	scan.close();
    }//end main method

}//end Dates class
