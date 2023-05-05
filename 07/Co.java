import java.text.DecimalFormat;
import java.util.*;


/** Co.java
 *  COSC326 - Etude 07
 * 
 *  Takes lines of input from stdin.
 *  Ensures they are valid co-ordinates of any form.
 *  Converts the input co-ordinates to Standard Form,
 *  and displays the result.
 *@author Zac Seales - 6687905
 */

public class Co {

    public static void main(String[] args){
        final DecimalFormat df = new DecimalFormat("0.000000");
        Scanner scan = new Scanner(System.in);
        ConvertCo converter = new ConvertCo();
        String currCo;
        ArrayList<String> inArray;
        Double[] result;
        String filename = "results.geojson";
        converter.initFile(filename);

        while(scan.hasNextLine()){
            //initialize the new results as invalid
            result = new Double[]{181.0, 181.0};
            //read and tidy input
            currCo = scan.nextLine();
            currCo = currCo.toLowerCase();
            currCo = currCo.trim();
            currCo = converter.removePunctuation(currCo);
            //Ensures input is not empty or null.
            if(currCo == null || currCo.isEmpty()){
                continue;
            }
            inArray = converter.splitData(currCo);
            //Ensures input is at least 2 'words'
            if (inArray.size() < 2){
                System.out.println("Unable to process: " + currCo);
                continue;
            }

            /** //Test data is split correctly
            for (int i = 0; i < inArray.size(); i++){
                System.out.println(inArray.get(i));
            }
            */

            int[] info = converter.firstIterationBasicChecks(inArray);
            //initial error checking failed
            if (info == null){
                System.out.println("Unable to process: " + currCo);
                continue;
            }
            //estimate results
            result = converter.getCoords(inArray, result, info);

            if (result == null){
                System.out.println("Unable to process: " + currCo);
            } else {
                converter.outputGeoJSON(df.format(result[0]), df.format(result[1]), filename);
            }
            if (scan.hasNextLine()){
                converter.addComma(filename);
            }
        }
        converter.finFile(filename);
        scan.close();
    }//end main

}