import java.awt.*;

import javax.swing.*;

/** ImagePanel.java
 *  COSC326 Etude 03
 * 
 *  The panel containing the snowflake image for the
 *  Koch Snowflake program.
 *  This class also contains the recursive calculations.
 *  Recursive algorithm taken from Java Foundations 2nd ed. 
 *  (Lewis, DePasquale, Chase)
 * 
 *@author Zac Seales - seaza886 - 6687095 
 */

public class ImagePanel extends JPanel{

    /* The order 1 triangle dimensions */
    private int[] initTri;
    /*The current order value. */
    private int current;
    /* Holds the previous size of the window */
    private Dimension prevSize;

    /** Constructor - Sets the snowflake to the specified arg value.
     *@param val - the snowflake order.
     */
    public ImagePanel(int val){
        current = val;
        prevSize = new Dimension(WIDTH, HEIGHT);
        setUpTriangle(600,600);
        setBackground(Color.black);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }//end ImagePanel constructor

    /** Sets up the triangle co ordinates based on the Width and Height
     *  arguments.
     *@param W - width of image.
     *@param H - height of image.
     */
    public void setUpTriangle(int W, int H){
        initTri = new int[6];

        initTri[0] = W / 2;
        initTri[1] = 40;
        initTri[2] = W / 8;
        initTri[3] = (int)(H * 0.7);
        initTri[4] = (int)(W * 7/8);
        initTri[5] = (int)(H * 0.7);
    }

    /** Draws a Koch snowflake recursively.
     *  Base case draws a straight line (order = 1).
     *  Otherwise 3 intermediate points are calculated.
     *@param current - the current order.
     *@param x1, y1, x5, y5 - The outer 2 points of the fractal.
     *@param page - the Graphics object.
     */
    public void drawFractal(int order, int x1, int y1, int x5, int y5, Graphics page){
        // The 3 intermediate points.
        int x2, y2, x3, y3, x4, y4;
        // The distance between the outer points.
        int dx, dy;
        /* Variable used for recursive calculation */
        double CONST = Math.sqrt(3.0) / 6;

        //base case
        if(order == 1){
            page.drawLine(x1, y1, x5, y5);
        } else {
            dx = x5 - x1; 
            dy = y5 - y1;

            x2 = x1 + dx / 3;
            y2 = y1 + dy / 3;

            x3 = (int)((x1 + x5) / 2 + CONST * (y1 - y5));
            y3 = (int)((y1 + y5) / 2 + CONST * (x5 - x1));

            x4 = x1 + dx * 2/3;
            y4 = y1 + dy * 2/3;

            drawFractal(order-1, x1, y1, x2, y2, page);
            drawFractal(order-1, x2, y2, x3, y3, page);
            drawFractal(order-1, x3, y3, x4, y4, page);
            drawFractal(order-1, x4, y4, x5, y5, page);
        }
    }//end drawFractal method

    /** Allows the order value to be changed from outside this class.
     * @param order - the new order value.
     */
    public void setOrder(int order) {
        current = order;
    }//end setOrder mutator

    /** Allows the order value to be received from outside this class. 
     *@return current - the current order value.
     */
    public int getOrder(){
        return current;
    }//end getOrder accessor

    /** Calls the drawFractal method. This overwrites the 
     *  paintComponent method from JPanel.
     *@param page - the Graphics object.
     */
    public void paintComponent(Graphics page){

        //detects changes in panel size and recalculates snowflake
        if(!prevSize.equals(getSize())){
            prevSize = getSize();
            setUpTriangle(prevSize.width, prevSize.height);
        }

        super.paintComponent(page);

        page.setColor(Color.white);
        drawFractal(current, initTri[0], initTri[1], initTri[2], initTri[3], page);
        drawFractal(current, initTri[2], initTri[3], initTri[4], initTri[5], page);
        drawFractal(current, initTri[4], initTri[5], initTri[0], initTri[1], page);
    }//end paintComponent method
    
}//end ImagePanel class