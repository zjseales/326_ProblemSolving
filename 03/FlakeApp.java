import javax.swing.*;


/** FlakeApp.java
 *  COSC326 Etude 03
 * 
 *  Creates a JFrame to contain all elements of the Koch
 *  Snowflake program. (UI and Image Panels) 
 *@author Zac Seales - seaza886 - 6687905
 */

public class FlakeApp {

    /** Creates a frame, adds the panel components and displays 
     *  to the screen.
     *@param args - unused argument.
     */
    public static void main(String[] args){
        JFrame frame = new JFrame("Koch's Snowflake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MainPanel());
        frame.pack(); //sizes frame to fit the panel components
        frame.setVisible(true);
    }//end main

}//end FlakeApp class