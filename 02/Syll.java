import java.io.*;
import java.util.Scanner;
import java.io.FileInputStream;

/** Syll.java
 * COSC326 Etude 2
 *
 * Reads a word from System.in and outputs the estimated number of syllables.
 * Either read from a file (one word per line) OR input by the user at
 * run time. 
 * This class holds the main method and only:
 * - takes input 
 * - executes/calls methods of the SyllCounter class 
 * - Displays output
 *
 *@author Liam Wilson
 *@author Amaan Aamir
 *@author Eliot Luna
 *@author Zac Seales
 */

public class Syll{

    /** Reads individual words and outputs the number of syllables.
     *@param args - possible file being read.
     */
    public static void main(String[] args) throws FileNotFoundException {
	Scanner scan;		//the Scanner object
	String current; 	//current word being analyzed
	int currentSylls;	//the number of syllables in 'current'

	SyllCounter sc = new SyllCounter();
	
	if (args.length < 1){
	    scan = new Scanner(System.in);
	} else {
	    scan = new Scanner(new FileInputStream(args[0]));
	}
	//iterates input and counts the syllables
	while(scan.hasNextLine()){
	    current = scan.nextLine();
	    current = current.trim();
	    
	    //if the input is valid, counts syllables and displays result
	    if(sc.isValidWord(current)){
		currentSylls = sc.countSylls(current);
		System.out.println("-----The word " + current + " has "
				   + currentSylls + " syllables-----\n");
	    }
	}
	scan.close();
	System.exit(0);
    }//end main
    
}//end Syll class
