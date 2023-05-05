import java.util.regex.*;
import java.util.*;

/** Translate.java
 *  COSC326 Etude05
 * 
 *  Contains the formulae required to translate basic English 
 *  sentences to Maori.
 *@author Zac Seales
 *@author Amaan Aamir
 */

public class Translate {

    private Hashtable<String, String> pronounHash;
    private Hashtable<String, String> connectorTenseHash;

    /** Constructor - initializes the pronoun hash table to optimize
     *  searching/filtering.
     */
    public Translate(){
        initPronounHash();
        initTenseHash();
    }

    /** Initializes the pronoun hash table which maps English pronouns to 
     *  their equivalent Maori translation.
     */
    private void initPronounHash(){
        pronounHash = new Hashtable<String, String>(11);

        pronounHash.put("i", "au");
        pronounHash.put("we1ex", "au");
        pronounHash.put("you1inc", "koe");
        pronounHash.put("he", "ia");
        pronounHash.put("she", "ia");
        pronounHash.put("they1ex", "ia");
        pronounHash.put("we2ex", "māua");
        pronounHash.put("we3ex", "mātou");
        pronounHash.put("we2inc", "tāua");
        pronounHash.put("we", "tātou");
        pronounHash.put("we3inc", "tātou");
        pronounHash.put("we", "tātou");
        pronounHash.put("you", "koe");
        pronounHash.put("you1ex", "koe");
        pronounHash.put("you2inc", "kōrua");
        pronounHash.put("you3inc", "koutou");
        pronounHash.put("they2ex", "rāua");
        pronounHash.put("they3ex", "rātou");
        pronounHash.put("they", "rātou");
    }

    /** Initializes the hash table which maps connecting words to the
     *  tense that they correspond to.
     */
    private void initTenseHash(){
        connectorTenseHash = new Hashtable<String, String>(3);

        connectorTenseHash.put("was", "I");
        connectorTenseHash.put("did", "I");
        connectorTenseHash.put("has", "I");
        connectorTenseHash.put("have", "I");
        connectorTenseHash.put("had", "I");
        connectorTenseHash.put("been", "I");
        connectorTenseHash.put("is", "Kei te");
        connectorTenseHash.put("am", "Kei te");
        connectorTenseHash.put("does", "Kei te");
        connectorTenseHash.put("are", "Kei te");
        connectorTenseHash.put("will", "Ka");
        connectorTenseHash.put("would", "Ka");
        connectorTenseHash.put("should", "Ka");
        connectorTenseHash.put("shall", "Ka");
        connectorTenseHash.put("be", "Ka");

    }

    /** Deconstructs the English sentence and returns 3 components.
     *  The pronoun, the verb, and the tense.
     *Note: if an error occurs then the first component is set to null so
     *that the main method will ignore invalid sentences with a simple check.
     *@param s - The sentence being analyzed.
     *@param components - The initial, empty array of components.
     *@return - The final components array.
     */
    public String[] translate(String[] w, String[] components){
        //Retrieve English Pronoun
        components = getPronoun(w, components);
        if (components[0] == null){
            return components;
        }
        //attempts to retrieve the tense of the sentence using the words
        //that connect the pronoun and verb.
        if (components[1] == null && w.length > 2 && !w[w.length-2].contains(")")){
            components[1] = connectorTenseHash.get(w[w.length - 2]);
            if (w[w.length-3].equals("will")){
                components[1] = "Ka";
            }
        }
        // the if statements look messy but they're actually high IQ bugchecks 
        // which consider obscure grammatical possibilities which we're expecting
        // you to test.

        components[0] = pronounHash.get(components[0]);//get pronoun translation

        if ((components = translateVerb(w, components))[0] == null){
            return components;
        }
        //if tense was not determined by connecting words 
        //then it is defined via the verb.
        if (components[1] == null){
            components[1] = determineTenseFromVerb(w[w.length-1]);
        }
        return components;
    }

    /** Contains simple 'tense-detection' rules which apply to valid verbs.
     *@param s - the verb being analyzed.
     *@return - the tense implied by the verb.
     */
    private String determineTenseFromVerb(String s){
        if(s.endsWith("d") || s.endsWith("t") || s.endsWith("w") || s.equals("made")){
            return "I";
        }
        return "Kei te"; 
    }

    /** Analyzes the first word of the sentence to ensure it is a valid
     *  English pronoun. If necessary, decomposes the contraction (apostrophe words)
     *  and determines the sentence tense.
     *@param s - The first word of the English sentence.
     *@param components - The array of sentence components.
     *@return - the updated components array.
     */
    private String[] getPronoun(String[] wordArray, String[] components){
        String aposAdd = null;
        String s = wordArray[0];
        if(s.contains("'")){
            aposAdd = s.substring(s.indexOf("'") + 1);
            s = s.substring(0, s.indexOf("'"));
            aposAdd = convertConnector(aposAdd, wordArray);
            if (aposAdd == null){
                return errorOccur(components, "Invalid pronoun");
            }
            components[1] = connectorTenseHash.get(aposAdd);
        }
        if (s.equals("i") || s.equals("he") || s.equals("she")){
            components[0] = s;
        } else if (s.equals("they") || s.equals("them") || s.equals("we") || s.equals("you")){
            components[0] = analyzePronoun(s, wordArray);
            if(components[0].startsWith("I")){
                return errorOccur(components, components[0]);
            }
        } else if(components[0] == null){
            return errorOccur(components, "Unknown pronoun '" + s + "'");
        }
        return components;
    }

    /** If pronouns have used an apostrophe, then this method will convert the 
     *  added characters to the full word that they represent. 
     *  This simplifies later checks.
     *@param s - the characters being checked.
     *@param wordArray - the original input, the connecting word may depend on the verb.
     *@param - the word (or null if not detected).
     */
    private String convertConnector(String s, String[] wordArray){
        String validWord = null;
        String verb;
        if (s.equals("ve")){
            validWord = "have";
        } else if (s.equals("re")){
            validWord = "are";
        } else if (s.equals("ll")){
            validWord = "will";
        } else if (s.equals("m")){
            validWord = "am";
        } else if (s.equals("s")){
            verb = wordArray[wordArray.length - 1];
            if(verb.endsWith("ing")){
                validWord = "is";
            } else {
                validWord = "has";
            }
        } else if (s.equals("d")){
            verb = wordArray[wordArray.length - 1];
            if(verb.endsWith("een") || verb.endsWith("ed") || verb.endsWith("ing")
                    || verb.equals("learnt") || verb.equals("made")){
                validWord = "had";
            } else {
                validWord = "would";
            }
        } else {
            return null;
        }
        return validWord;
    }

    /** English pronouns which have multiple meanings require additional checks
     *  to see if the specific meaning is defined within the string.
     *@param pronoun - the pronoun.
     *@param wordArray - the input array.
     *@return - the newly defined pronoun.
     */
    private String analyzePronoun(String pronoun, String[] wordArray) 
                throws IndexOutOfBoundsException {
        int num = 0;
        if (!wordArray[1].startsWith("(")){
            return pronoun;
        }
        try {
            wordArray[1] = wordArray[1].substring(1);
            num = Integer.parseInt(wordArray[1]);
            if (num == 1){
                pronoun += "1";
            } else if (num == 2){
                pronoun += "2";
            } else if (num > 2){
                pronoun += "3";
            } else {
                return "Invalid number: " + num;
            }
            if (wordArray[2].equals("excl)")){
                pronoun += "ex";
            } else if (wordArray[2].equals("incl)")){
                if (num == 1 && (pronoun.equals("they1") || pronoun.equals("we1"))){
                    pronoun = pronoun.substring(0, pronoun.length() - 1);
                    return "Improbable pronoun '" + pronoun + " (1 incl)'";
                }
                pronoun += "inc";
            } else {
                return "Invalid argument '" + wordArray[2] + "'";
            }
        } catch (NumberFormatException exception){
            return "Integer not found - '" + wordArray[1] + "' is not a valid argument";
        } catch (IndexOutOfBoundsException e){
            return "Incomplete pronoun arguments '" + wordArray[0] + " (" +wordArray[1] + "'";
        }
        return pronoun;
    }

    /** Retrieves the English verb from the wordArray and
     *  returns the Maori translation. 
     *@param s - The wordArray containing the original sentence.
     *@param components - The current sentence components.
     *@return - the components array after having the verb translated.
     */
    private String[] translateVerb(String[] wordArray, String[] components){
        String s = wordArray[wordArray.length - 1];
        Pattern pattern = null;
        //Removes any punctuation marks
        if(s.charAt(s.length()-1) < 'a' || s.charAt(s.length()-1) > 'z'){
            s = s.substring(0, s.length() - 1);
        }
        //performs a quick check before testing the appropriate regex
        if (s.contains("go") || s.equals("went")){
            pattern = Pattern.compile("went||go||go(es||ing)");
            components[2] = "haere";
        } else if (s.contains("ma")){
            pattern = Pattern.compile("ma(de||kes||king||ke)");
            components[2] = "hanga";
        } else if (s.contains("see") || s.equals("saw")){
            pattern = Pattern.compile("saw||see(s||ing||n)");
            components[2] = "kite";
        } else if(s.contains("want")) {
            pattern = Pattern.compile("want||want(s||ed||ing)");
            components[2] = "hiahia";
        } else if (s.contains("call")){
            pattern = Pattern.compile("call||call(s||ed||ing)");
            components[2] = "karanga";
        } else if (s.startsWith("ask")){
            pattern = Pattern.compile("ask||ask(s||ing||ed)");
            components[2] = "pātai";
        } else if (s.contains("read")){
            pattern = Pattern.compile("read||read(s||ing)");
            components[2] = "pānui";
        } else if (s.contains("learn")){
            pattern = Pattern.compile("learn||learn(s||ing||t||ed)");
            components[2] = "ako";
        }
        //if conditions met then no error occurred
        if(pattern != null && pattern.matcher(s).matches()){
            return components;
        }
        return errorOccur(components, "Invalid verb '" + s + "'");
    }

    /** Displays a custom error message and sets the components array to
     *  indicate an error occurred. (components[0] = null)
     *@param components - the array of sentence elements.
     *@param message - the custom error message.
     *@return - the new array indicating an error has occurred.
     */
    private String[] errorOccur(String[] components, String message){
        components[0] = null;
        System.out.println("Invalid Sentence: " + message + ".");
        return components;
    }

}