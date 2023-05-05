/** SyllCounter.java
 * COSC326 etude 2
 *
 * Contains validity checks and calculations needed to estimate the
 * number of syllables in a single word.
 *
 *@author Liam Wilson
 *@author Amaan Aamir
 *@author Eliot Luna
 *@author Zac Seales
 */

public class SyllCounter {

    /** Ensures input is a valid word. ie. does not encapsulate any white 
     *  space, only uses letter characters, and also is not an empty String. 
     *@param word - the current word being analyzed.
     *@return bool - true if string arg is a valid word, else false.
     */
    public boolean isValidWord(String word){
	String[] temp = word.split("\\s+");
	//checks word for validity
	if (word.length() < 1){
	    System.err.println("\nEmpty String can not have syllables counted");
	    return false;
	} else if (temp.length > 1){
	    System.err.println("\n" + word + " is more than one word.");
	    return false;
	}
	//recursive letter check to impress via a display of elegance
	if (letterCheck(word.toLowerCase(), word.length() - 1) == -1){
	    System.err.println("\nWord must contain english letter chars only");
	    return false;
	}
	//returns true if all checks have been passed
	return true;
    }//end isValidword method

    /** Recursive method to analyze each character of the input, to ensure
     *   that only english letter characters have been used.
     *@param word - the word being iterated.
     *@param i - the current character index being analyzed.
     *@return - Either: 0 - if all chars have been checked.
     *                 -1 - if any chars are not english letters
     *                  or, the next char index to check.
     */
    private int letterCheck(String word, int i){
	//base case
	if(i <= -1){
	    return 0;
	}
	//checks if character is out of range
	if(word.charAt(i) < 97 || word.charAt(i) > 122){
	    return -1;
	}
	return letterCheck(word, --i);
    }//end letterCheck method

    /** Returns the number of syllables estimated in the word argument, 
     *  Consecutive and equal vowels count as one syllable.
     *  Consecutive non-equals vowels are analyzed to determine if they
     *  are 1 or 2 syllables, 'y' characters are analyzed to determine whether
     *  they are likely to be a vowel or not. 
     *
     *@param word - the word being iterated.
     *@return count - the number of syllables.
     */
    public int countSylls(String word){
	//simplifies comparisons
	word = word.toLowerCase(); 
	//initializes total syllable count
	int count = 0;
	boolean currVowel;

	//just in case XD
	if(word.equals("hawaii")){
	    return 3;
	}
	
	//iterates the word
	for (int i = 0; i < word.length(); i++){
	    currVowel = false;
	    //checks for vowel and analyzes surrounding characters
	    if(isVowel(word.charAt(i))) {
		//possibly skips characters if conditions are met
		if(analyzeVowel(word, i) == -1){
		    count--;
		} else {
		    i += analyzeVowel(word, i);
		}
		//not a syllable if last letter is e 
		if(i == word.length() - 1 && word.charAt(i) == 'e'
		   && count > 0){
		    return count;
		}
		count++;
		currVowel = true;
	    }
	    
	    try{
		//analyzeY returns 1 if the y is deemed a vowel,
		//otherwise returns 0
		if(word.charAt(i) == 'y'){
		    count += analyzeY(word, i);
		    currVowel = true;
		}
		//if last letter is m and is not preceded by a vowel
		//then an extra syllable is added
		if(i + 1 == word.length() && word.charAt(i) == 'm'
		   && !isVowel(word.charAt(i - 1))){
		       count++;
		   }
		//skips consecutive equal vowels
		while (currVowel && word.charAt(i) == word.charAt(i + 1)){
		    i++;
		}
	    } catch (IndexOutOfBoundsException e){
		//expected error
	    }
	}//end for loop
	
	return count;
    }//end calculateSylls method

    /** Analyzes the characters surrounding a vowel to determine
     *  a more accurate syllable estimate. 
     *  This method contains common exceptions to the rules I have defined.
     *@param word - the word being analyzed.
     *@param i - the index of the current vowel.
     *@return - the number of characters to ignore, if any.
     */
    private int analyzeVowel(String word, int i){
	//ignores OutOfBoundsExceptions
	try{
	    if(word.charAt(i) == 'i'){
		if(word.charAt(i + 1) == 'e' && !word.equals("tier")){
		    return 2;
		}
		if (word.charAt(i + 1) == 'o' && word.charAt(i + 2) == 'n'
		  && (word.charAt(i - 1) == 's' || word.charAt(i - 1) == 't')){
		    if(word.equals("fruition")){
			return 0;
		    }
		    return 1;
		}
	    }
	    if(word.charAt(i) == 'a'){
		if (word.charAt(i + 1) == 'i' || word.charAt(i + 1) == 'u'){
		    return 1;
		}
		if(word.charAt(i + 1) == 'w' && word.charAt(i + 2) == 'e'){
		    return 2;
		}
	    }
	    if(word.charAt(i) == 'o' && word.charAt(i + 1) == 'u'){
		return 1;
	    }
	    if(word.charAt(i) == 'e'){	
		if(word.charAt(i - 1) == 'r' && i == 1){
		    return 0;
		}
		if(word.charAt(i + 1) == 's' && (i + 1) == word.length() - 1){
		    if(word.charAt(i - 1) == 'l' || word.charAt(i - 1) == 'm'){
			return -1;
		    }
		}
		if (word.charAt(i + 1) == 'i' && word.charAt(i + 2) == 'r'){
		    return 2;
		}
		if (word.charAt(i + 1) == 'u'){
		    return 1;
		}
		if (word.charAt(i + 1) == 'a'){
		    if (word.charAt(i + 2) == 'u'){
			return 2;
		    }
		    if(word.charAt(i + 2) == 'l'){
			return 0;
		    }
		    return 1;
		}
	    }
	    if(word.charAt(i - 1) == 'q' && word.charAt(i) == 'u'
	       && isVowel(word.charAt(i + 1))){
		if (word.equals("queue") || word.equals("queues")){
		    return 3;
		}
		return 1;
	    }
	    if(word.charAt(i) == 'u'
	       && (word.charAt(i + 1) == 'e' || word.charAt(i + 1) == 'i')){
		return 1;
	    }
	} catch(IndexOutOfBoundsException e){
	    //Expected Exception
	}	
	//default return value	
	return 0;
    }//end analyzeVowel method

    /** Analyzes the characters surrounding the y character to predict 
     *  whether the y character is a vowel in the current word.
     *@param word - the word being analyzed.
     *@param i - the index of the y character.
     *@return - returns 1 if the y is deemed a vowel, else 0.
     */
    private int analyzeY(String word, int i){
	//returns from method if character is not a y (just in case)
	if(word.charAt(i) != 'y'){
	    return 0;
	}
	//ignores outofbounds exception when checking surrounding indices
	try{
	    //y at the end of a word is a vowel if previous index is not vowel
	    if(i == word.length() - 1 && !isVowel(word.charAt(i - 1))){
		return 1;
	    } else if (i == word.length() - 1){
		return 0;
	    }

	    //y at the start of word and not followed by a vowel
	    //is deemed a vowel.
	    if(i == 0 && !isVowel(word.charAt(i + 1))){
		return 1;
	    } else if (i == 0){
		return 0;
	    }
	    //NOTE: at this point we know the y is not the last or first letter

	    //y is not a vowel if encapsulated by vowels
	    if(isVowel(word.charAt(i - 1)) && isVowel(word.charAt(i + 1))){
		return 0;
	    }
	    //y is a vowel if preceded by a non vowel
	    if(!isVowel(word.charAt(i - 1))){
		return 1;
	    }
	} catch(IndexOutOfBoundsException e){
	}
	//default conclusion, y is not a vowel
	return 0;	
    }//end analyzeY method

    /** Simple check, returns true if the argument is a vowel.
     *@param c - the character being checked.
     *@return bool - true if c is a vowel, else false.
     */
    private boolean isVowel(char c){
	if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
	    return true;
	}
	return false;
    }//end isVowel method
    
}//end SyllCounter class
	
	
	    
	

    
