import java.util.Scanner;

/** Translator.java
 *  COSC326 - Etude 05
 * 
 *  Reads in a simple English sentence and translates to Maori.
 *  Sentences must be 2-4 words long and must begin with a 
 *  pronoun and end with a verb.
 *@author Zac Seales
 *@author Amaan Aamir
 */

 public class Translator {

    /** Reads in an English sentence from stdin, deconstructs the 
     *  sentence components, and outputs the Maori translation.
     *@param args - unused parameter.
     */
     public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Translate t = new Translate();
        String[] wordArray;
        String[] components;
        String sentence;

        while(scan.hasNextLine()){

            // initialize as null to simplify error checking
            components = new String[]{null, null, null};

            sentence = scan.nextLine();
            sentence = sentence.trim();
            sentence = sentence.toLowerCase();
    
            wordArray = sentence.split("\\s+"); //Sentence as a word array
    
            //Ensures sentence contains more than 1 word
            if(wordArray.length <= 1){
                System.out.println("Invalid Sentence, must be more than one word.");
                continue;
            }
            components = t.translate(wordArray, components);
            
            //Output Maori translation if no error
            if (components[0] != null){
                System.out.println(components[1] + " " + components[2] 
                        + " " + components[0] + "");
            }
        }
        scan.close();
    } //end main

}//end Translator class