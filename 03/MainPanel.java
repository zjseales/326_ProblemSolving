import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** MainPanel.java
 *  COSC326 Etude 03
 * 
 *  The panel containing all graphical elements for the 
 *  Koch Snowflake program.
 *  This panel also contains UI elements and listeners.
 * 
 *@author Zac Seales - seaza886 - 6687905
 */

public class MainPanel extends JPanel implements ActionListener{

    /* The buttons to alter the snowflake order. */
    private JButton increase, decrease;
    /* The text labels. */
    private JLabel inputText, currOrder;
    /* The text-field allowing typed user input. */
    private JTextField input;
    /* The inner panels containing all UI elements. */
    private JPanel ui, textPanel, buttonPanel;
    /* The panel containing the snowflake graphic. */
    private ImagePanel image;

        /* The snowflake image panel.
         * Sets up all variables and adds them to the newly
         * created panel. Manages layout and event listeners.
         */
        public MainPanel(){
            //set up GUI components
            image = new ImagePanel(1);
            currOrder = new JLabel("Current Order Value = " + image.getOrder());
            inputText = new JLabel("Type order value here: ");
            input = new JTextField(5);
            increase = new JButton("+");
            decrease = new JButton("-");
            //add listeners
            increase.addActionListener(this);
            decrease.addActionListener(this);
            input.addActionListener(this);
            //set up an inner UI panel for text input
            textPanel = new JPanel();
            textPanel.setPreferredSize(new Dimension(600, 30));
            textPanel.setBackground(Color.cyan);
            textPanel.add(inputText);
            textPanel.add(input);
            //set up a button panel
            buttonPanel = new JPanel();
            buttonPanel.setPreferredSize(new Dimension(600, 40));
            buttonPanel.setBackground(Color.cyan);
            buttonPanel.add(decrease);
            buttonPanel.add(increase);
            //set up the ui panel
            ui = new JPanel();
            ui.setPreferredSize(new Dimension(600, 100));
            ui.setBackground(Color.cyan);
            ui.setLayout(new BoxLayout(ui, BoxLayout.Y_AXIS));
            ui.add(textPanel);
            ui.add(buttonPanel);
            ui.add(currOrder);

            //set up main panel
            setPreferredSize(new Dimension(600,700));
            setLayout(new BorderLayout());
            //add components
            add(ui, BorderLayout.NORTH);
            add(image, BorderLayout.CENTER);
  
        }//end MainPanel constructor

        /** Defines the Action listener used for all UI elements.
         *  Either a button was pressed or text was input.
         *@param e - The action event that occurred.
         */
        public void actionPerformed(ActionEvent e){
            int order = image.getOrder();

            if(e.getSource() == increase){
                order++;
            } else if (e.getSource() == decrease){
                order--;
            } else {
                try{
                    order = Integer.parseInt(input.getText());
                } catch(NumberFormatException ex){
                    System.err.println("Value must be an int between 1 and 9");
                    return;
                }
            }
            //Set a range for the snowflake order to prevent user's breaking
            //the program.
            if(order < 1 || order > 9){
                System.err.println("Value must be an int between 1 and 9.");
                return;
            }
            //redraws the new snowflake
            image.setOrder(order);
            currOrder.setText("Current Order Value = " + image.getOrder());
            image.setUpTriangle(image.getWidth(), image.getHeight());
            image.repaint();
            repaint();
        }//end actionPerformed method

}//end MainPanel class
