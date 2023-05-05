/** OneWayCross.java
 *  COSC326 Etude04
 *  
 *  Calculates the minimum number of fuel cans needed to cross the desert.
 *  Problem Scenario taken from Etude 4 Part2.
 *@author Liam Wilson
 *@author Eliot Luna
 *@author Zac Seales
 *@author Amaan Aamir
 */

public class OneWayCross {
    
    /* Solves part 2 of etude 4 and displays the result to stdout.
     */
    public void solve(){
        /* The amount of fuel available to drop */
        double fuelDrop = 80.0; 
        /* The total distance travelled */
        double dTravelled = 0;
        /* The number of trips taken */
        int numTrips = 40;

        for (int i = numTrips; i > 0; i--){
            dTravelled += ((double)1 / (2 * i)) * fuelDrop * 12;
            displayOut(dTravelled);
        }
    }

    public void displayOut(double dTravelled){
        System.out.println("Distance Travelled: " + dTravelled);
    }
}
/**
n = input("Number of trips: ")
n = int(n)
fuel = 80

distance_reached = 0
i = 1
while i <= n:
   print((80*12)/(2*i), i)
   distance_reached += ((80*12)/(2*i))
   i+=1
 */