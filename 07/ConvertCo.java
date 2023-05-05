import java.util.regex.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** ConvertCo.java
 *  COSC 326 - Etude 07
 * 
 *  Performs validity checks to ensure a string is a valid
 *  co-ordinate on earth, and converts the valid 
 *  co-ordinate to standard form.
 *@author Zac Seales - 6687905
 */

public class ConvertCo {

    /** Performs the first iteration of the input list in order to determine 3 
     *  important factors which are represented as an int array.
     *  arr[0] indicates Latitude co-ordinate first or Longitude first (0 or 1)
     *  arr[1] holds the total number of numbers in the array.
     *  arr[2] stores the index of the end of the first co-ordinate. (0 if not found)
     *  arr[3] holds the sign of the first co-ordinate cardinality.
     *  This initial iteration simplifies the later conversion processing.
     *@param arr - the input string as a String ArrayList.
     *@return - the initial findings with regards to input formatting.
     */
    public int[] firstIterationBasicChecks(ArrayList<String> arr){
        //initialize all findings as 0
        int[] info = new int[]{0, 0, 0, 1};
        Double temp;

        //pattern representing end of a co-ordinate.
        Pattern end = Pattern.compile("\"||,");
        Pattern nsCards = Pattern.compile("n||north||s||south");
        Pattern ewCards = Pattern.compile("e||east||w||west");
        //first value must be a number
        if(!isVal(arr.get(0))){
            return null;
        }
        info[1]++;
        temp = getVal(arr.get(0));
        //invalid value.
        if (Math.abs(temp) > 180) {
            return null;
        }
        //check for longitude
        if (Math.abs(temp) == 180){
            info[0] = 1;
            info[2] = 1;
        //check 90 is a lat for now
        } else if (Math.abs(temp) > 90){
            info[0] = 1;
            
        }

        for (int i = 1; i < arr.size(); i++){
            //prevents weird error
            if (arr.get(i).equals("-")){
                arr.remove(i);
            }
            //Number Processing.
            if (isVal(arr.get(i))){
                info[1]++;
                //retrieve value for processing
                temp = getVal(arr.get(i));
                if (info[2] == 0 && temp >= 60){
                    info[2] = i + 1;
                }

                //determine end of co-ordinate one by non-whole number
                if (info[2] == 0 && temp % 1 != 0){
                    info[2] = i + 1;
                }
            //other ways of determining end of first co-ordinate.
            } else if (info[2] == 0 && info[1] <= 3){
                //determine end of first co-ordinate by punctuation. (either " or ,)
                if (end.matcher(arr.get(i)).matches()){
                    info[2] = i;
                //determine end of first co-ordinate by lat cardinality.
                } else if (nsCards.matcher(arr.get(i)).matches()){
                    info[2] = i - 1;
                    //determines sign of first co-ordinate.
                    info[3] = determineSign(arr.get(i));                    
                } else if (ewCards.matcher(arr.get(i)).matches()){
                    info[2] = i - 1;
                    info[0] = 1;
                    info[3] = determineSign(arr.get(i));
                }
            } else if (info[2] < arr.size()-1 && i == (info[2] + 1) && isCard(arr.get(i))){
                info[3] = determineSign(arr.get(i));
            }
        }
        //invalid if only 1 number found
        if (info[1] == 1){
            return null;
        }

/** Test the info array method works.
        if (info[0] == 0){
            System.out.println("First co-ordinate is a latitude.");
        } else {
            System.out.println("First co-ordinate is a longitude.");
        }
        System.out.println("There are " + info[1] + " numbers in this input string.");
        if (info[2] == 0){
            System.out.println("End of first co-ordinate is unknown.");
        } else {
            System.out.println("End of first co-ordinate at index " + info[2]);
        }
*/
        return info;
    }

    /** Returns true if this string is a valid number, else false
     *@param s - the string being analyzed.
     *@return - true if number value, else false.
     */
    private boolean isVal(String s){
        //regex representing positive and negative numbers
        Pattern n = Pattern.compile("^-?\\d*\\.?\\d*$");

        if (n.matcher(s).matches()){
            return true;
        }
        return false;
    }

    /** Returns either negative 1 or positive 1 depending on the cardinality input
     *  argument.
     *@param cardinality - the cardinality being checked.
     *@return - the sign that the cardinality represents.
     */
    private int determineSign(String cardinality){
        Pattern posCards = Pattern.compile("n||north||e||east");
        Pattern negCards = Pattern.compile("w||west||s||south");
        if (posCards.matcher(cardinality).matches()){
            return 1;
        } else if (negCards.matcher(cardinality).matches()){
            return -1;
        }
        return 1;
    }

    /** Determines input co-ordinates from the inArray argument
     *  and returns them in standard form, (as a double array of size 2)
     *      result[0] = Latitude 
     *      result[1] = Longitude
     *@param inArray - The initial input as an array list.
     *@param format - the previously determined format of the first co-ordinate
     *@param result - the initial results array, holds out of range co-ordinates to simplify error checking. 
     *@return - the co-ordinates in standard form.
     */
    public Double[] getCoords(ArrayList<String> inArray, Double[] result, int[] info){

        if (info[0] == 0) {
            result[0] = getVal(inArray.get(0));
        } else {
            result[1] = getVal(inArray.get(0));
        }
        //Standard form, 2 numbers only
        if (info[1] == 2){
            result = getOther(inArray, result, info);
            return result;
        }
        //first co-ord already found
        if (info[2] == 1){
            result = findFromRest(inArray, result, 1);
        //check if end of first co-ord is known
        //and find other co-ordinate.
        } else if (info[2] != 0){
            result = findAllKnown(inArray, result, info, true);
        //iterate whole string and estimate co-ordinates based on validity.
        } else {
            result = findAllKnown(inArray, result, info, false);
        }
        return result;
    }

    /** Determines both co-ordinates using the end of the first 
     *  co-ordinate which was determined earlier and returns them 
     *  in standard form.
     *@param arr - the list of input.
     *@param r - the results so far.
     *@param info - the information array values.
     *@param check - true if end of co-ordinate 1 is known, else false.
     *@return - the results after processing, or null.
     */
    private Double[] findAllKnown(ArrayList<String> arr, Double[] r, int[] info, boolean check){
        int num = 1;
        Double f;
        Double temp;
        int end = info[2];
        int it1Size;
        //bool argument determines end of first iteration
        if (check){
            it1Size = info[2];
        } else {
            it1Size = arr.size() - 2;
        }

        if (r[0] > 180){
            f = r[1];
        } else {
            f = r[0];
        }
        /**Iterate up to the known final index value. */
        for (int i = 1; i <= it1Size; i++){
            if (isVal(arr.get(i))){
                num++;
                temp = getVal(arr.get(i));
                if (temp >= 60){
                    end = i;
                    break;
                }
                if (num == 2 && temp < 60){
                    f += temp / (double)60;
                } else if (num == 3 && temp < 60){
                    f += temp / (double)3600;
                }
                if (temp % 1 != 0 || num == 3 || num == info[1] - 1){
                    end = i + 1;
                    break;
                }
            }
        }
        //multiplies by the sign found earlier.
        f *= info [3];
        //insert final value back into results
        if (r[0] > 180){
            r[1] = f;
        } else {
            r[0] = f;
        }
        //determine other co-ordinate by the end of the current co-ordinate.
        r = findFromRest(arr, r, end);

        return r;
    }

    /** Finds the remaining co-ordinate value by iterating the list of input
     *  from wherever the first co-ordinate ended (i).
     *@param inArray - the array of input string values.
     *@param r - the resulting co-ordinates.
     *@param end - the point in the list to begin iteration from.
     *@return - the results.
     */
    private Double[] findFromRest(ArrayList<String> inArray, Double[] r, int end){
        Double temp = null;
        Double f = 0.0;
        //represents the number of values found so far.
        int n = 0;

        //if end of list, return that value.
        if (end == inArray.size() - 1){
            temp = getVal(inArray.get(end));
            r = putInUnknown(temp, r);
            return r;
        }
        //otherwise iterate to determine the co-ordinate.
        for (int i = end; i < inArray.size(); i++){
            if (n >= 3){
                break;
            }
            //process numbers
            if (isVal(inArray.get(i))){
                temp = getVal(inArray.get(i));
                n++;
                //first value is start of unknown result co-ordinate.
                if (n == 1){
                    f = temp;
                } else if (n == 2 && temp < 60){
                    f += temp / (double)60;
                } else if (n == 3 && temp < 60){
                    f += temp / (double)3600;
                    break;
                }
                //end of co-ord if current val contains fractional part
                if (temp % 1 != 0){
                    break;
                }
            } else if (f != 0.0 && isCard(inArray.get(i))){
                break;
            }
        }
        n = -1;
        //try find cardinality of co-ord from last values
        if (isCard(inArray.get(inArray.size() - 1))){
            n = inArray.size() - 1;
            f *= determineSign(inArray.get(n));
        } else if (isCard(inArray.get(inArray.size() - 2)) 
                && !isVal(inArray.get(inArray.size() - 1))){   
            n = inArray.size() - 2;
            f *= determineSign(inArray.get(n));
        }
        //ensures final lat cardinality defines a lat co-ord
        if (n != -1){
            r = manageFinalCard(inArray, n, r);
        }
        //put result into the unknown result slot.
        r = putInUnknown(f, r);
        //ensure data is valid
        r = finalChecks(r);

        return r;
    }

    /** Initializes the file that will contain the output geojson co-ordinates.
     *@param filename - the name of the file.
     */
    public void initFile(String filename){
        FileWriter writer;
        try {
            File newFile = new File(filename);
            newFile.createNewFile();
            writer = new FileWriter(filename, false);
            //The file header
            writer.write("{ \"type\": \"FeatureCollection\", \n\"features\": [");
            writer.close();
        } catch (IOException e){
            System.err.println("File '" + filename + "' could not be created.");
            return;
        }
    }

    /** Add the tail of the geojson file. To be added after all input is processed.
     *@param filename - the name of the file.
     */
    public void finFile(String filename){
        FileWriter writer;
        try {
            writer = new FileWriter(filename, true);
            //The file tail contains empty point to fix 'comma bug'
            writer.write("\n]\n}");
            writer.close();
        } catch (IOException e){
            System.err.println("File '" + filename + "' not found.");
            return;
        }
    }

    /** Outputs the standard form input co-ordinates to
     *  GeoJSON co-ordinates in the specified filename.
     *@param lat - the latitude.
     *@param lon - the longitude.
     *@param filename - the file being written to.
     */
    public void outputGeoJSON(String lat, String lon, String filename){
        FileWriter writer;
        try {
            writer = new FileWriter(filename, true);  
            writer.append("\n{ \"type\": \"Feature\",\n\"geometry\": {");
            writer.append("\n\"type\": \"Point\",\n");
            writer.append("\"coordinates\": [" + lon + ",\n" + lat + "\n]\n},");
            writer.append("\n\"properties\": {\n\"label\":");
            writer.append(" \"\"\n}\n}");
            writer.close();
        } catch(IOException e){
            System.err.println("File '" + filename + "' not found.");
            return;
        }
    }

    /** Fixes bug where final co-ordinate included a 
     *  comma and made file invalid.
     *@param the filename.
     */
    public void addComma(String filename){
        FileWriter writer;
        try {
            writer = new FileWriter(filename, true);  
            writer.append(",");
            writer.close();
        } catch(IOException e){
            System.err.println("File '" + filename + "' not found.");
            return;
        }
    }

    /** Manages a bug where lat cardinalities do not define a 
     *  co-ordinate as a a latitude. 
     *@param a - the input array.
     *@param n - defines the position of the cardinality.
     *@param r - the results before processing.
     *@return - the results array after processing.
     */
    private Double[] manageFinalCard(ArrayList<String> a, int n, Double[] r){
        //used to ensure final lat cardinality defines a lat
        Pattern latCards = Pattern.compile("n||north||s||south");

        //if is lat cardinality and lat already found then swap.
        if (latCards.matcher(a.get(n)).matches() && r[1] > 180) {
            r = swapVals(r);
        }
        return r;
    }

    /** Swaps the 2 values in the results array, this is called whenever
     *  an exception occurs regarding which co-ordinate is a lat or longitude.
     *@param r - the results array.
     *@return - the results array with the values swapped.
     */
    private Double[] swapVals(Double[] r){
        Double temp;
        temp = r[0];
        r[0] = r[1];
        r[1] = temp;
        return r;
    }

    /** Get the other co-ordinate for strings containing 2 numbers.
     *  (ie. standard form input)
     *@param a - the input string as an array.
     *@param r - the results so far.
     *@return - the final results for a standard form input string.
    */
    private Double[] getOther(ArrayList<String> a, Double[] r, int[] info){

        Double temp = null;
        int check = -1;

        if(r[0] > 180){
            r[1] *= info[3];
        } else {
            r[0] *= info[3];
        }

        // Iterate to find the next number.
        for (int i = 1; i < a.size(); i++){
            //check for number
            if (isVal(a.get(i))){
                temp = getVal(a.get(i));
            //check for cardinality of 2nd value
            } else if (temp != null){
                if (isCard(a.get(i))){
                    check = i;
                } else if (a.size() > 3 && isCard(a.get(i-1))){
                    check = i - 1;
                }
            }
        }
        //try find cardinality of co-ord from last values
        if (isCard(a.get(a.size() - 1))){
            check = a.size() - 1;
            temp *= determineSign(a.get(a.size() - 1));
        } else if (isCard(a.get(a.size() - 2)) && !isVal(a.get(a.size() - 1))){
            check = a.size() - 2;
            temp *= determineSign(a.get(a.size() - 2));
        }
        //manages bug where lat cardinalities do not define a lat co-ord
        if (check != -1) {
            r = manageFinalCard(a, check, r);
        }
        //assigns the second value to the result that has not yet been assigned
        r = putInUnknown(temp, r);
        //ensure valid result
        r = finalChecks(r);
        return r;
    }

    /** Inserts the double argument into the array slot that has not 
     *  yet been assigned. Simplifies later code by allowing reusability
     *  of this common operation.
     *@param val - The value being inserted.
     *@param r - the array being altered.
     *@param - the altered results array.
     */
    private Double[] putInUnknown(Double val, Double[] r){
        if(r[0] > 180){
            r[0] = val;
        } else {
            r[1] = val;
        }
        return r;
    }

    /** Return true if the string argument is a valid cardinality
     *  otherwise returns false.
     *@param c - the string being checked.
     *@return - true if input is a cardinality value, else false.
     */
    private boolean isCard(String c){
        Pattern cards = Pattern.compile("w||west||s||south||n||north||e||east");

        if (cards.matcher(c).matches()){
            return true;
        }
        return false;
    }

    /** Performs the final checks to ensure our results array is correct.
     * @param r - the 2 co-ordinate values being checked, standard form.
     * @return - valid co-ordinates, or null.
     */
    private Double[] finalChecks(Double[] r){
        //invalid if no lat value detected.
        if (Math.abs(r[0]) > 90 && Math.abs(r[1]) > 90){
            return null;
        //if lat > 90, assumes long and lat are swapped.
        } else if (Math.abs(r[0]) > 90){
            r = swapVals(r);
        }
        //invalid if long out of range.
        if (Math.abs(r[1]) > 180){
            return null;
        }
        return r;
    }

    /** Parses the string argument into a double (if possible)
     *  and returns the result.
     *  This method will return 'null' if no double is found.
     *@param v - the string being analyzed.
     *@param d - the double from the string (or null).
     */
    private Double getVal(String v){
        Double d;
        try {
            d = Double.parseDouble(v);
        } catch (NumberFormatException e){
            //expected exception
            return null;
        }
        return d;
    }

    /** Removes all punctuation, that is not necessary to defining 
     *  the co-ordinate type (ie. DD, DDM, DMS), from the string argument.
     *  An attempt to remove all typos early, to ease later error
     *  checking.
     *@param s - the string having the punctuation removed.
     *@return - the input string without punctuation.
     */
    public String removePunctuation(String s){
        Pattern pattern = Pattern.compile("(?![.,'°\"-])[!#$%&()*+/:;<=>?@\\[\\]^_`{|}~]");
        return pattern.matcher(s).replaceAll(" ");
    }

    /** Used to seperate numbers from co-ordinate formatting punctuation.
     *  Simplifies later iteration checks.
     * @param inString - the string having it's punctuation modified.
     * @return - the modified string as an ArrayList.
     */
    public ArrayList<String> splitData(String inString){
        ArrayList<String> newList = new ArrayList<String>();
        String[] temp;
        //split by valid punctuation
        Pattern pattern = Pattern.compile("'");
        inString = pattern.matcher(inString).replaceAll(" ' ");
        inString.replaceAll("'", " ' ");
        pattern = Pattern.compile("\"");
        inString = pattern.matcher(inString).replaceAll(" \" ");
        pattern = Pattern.compile("°");
        inString = pattern.matcher(inString).replaceAll(" ° ");
        pattern = Pattern.compile(",");
        inString = pattern.matcher(inString).replaceAll(" , ");

        //split off attached cardinality values
        //when adding String values to list
        temp = inString.split("\\s+");
        for (int i = 0; i < temp.length; i++){
            if (temp[i].length() == 1){
                newList.add(temp[i]);
            } else if(temp[i].endsWith("n") || temp[i].endsWith("s")
                     || temp[i].endsWith("e") || temp[i].endsWith("w")){
                String card = temp[i].substring(temp[i].length()-1);
                newList.add(temp[i].substring(0, temp[i].length() - 1));
                newList.add(card);
            } else {
                newList.add(temp[i]);
            }
        }
        return newList;
    }

}
//unused in order to simplify
/** Returns the double value that represents the detected circle of
     *  latitude.
     *@param s - the string being checked.
     *@return - the latitude as a double (if detected).
     
    public Double isValidLatString(String s, Double result){
        if (s.contains("eq")){
            result = 0.0;
        } else if (s.contains("canc")){
            result = 23.43638;
        } else if (s.contains("capric")){
            result = -23.43638;
        } else if (s.contains("antarct")){
            result = -66.3;
        } else if (s.contains("arct")){
            result = 66.3;
        }
        return result;
    }
*/
//unused
 /** Returns the double which represents the Longitude that is
     *  determined by the longitude-string detected within the string arg.
     *@param s - the string being analyzed.
     *@return  - the double representing the longitude string value.
     
    public Double isValidLongString(String s){
        Double result = 181.0;
        if(s.contains("greenwich") || s.contains("prime") || s.contains("meridian")){
            result = 0.0;
        }
        return result;
    }
*/