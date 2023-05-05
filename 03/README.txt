README.txt
COSC 326 Etude 03
Koch's Snowflake

Zac Seales, 6687905, seaza886

INSTRUCTIONS:

1. Compile program using the command,
>> javac *.java

2. Run the application with,
>> java FlakeApp

PROGRAM INFO:

User can either input a value between 1 and 9, into the text box. OR,
they can use the '+' and '-' buttons to increase the snowflake order value.
If the input is not an int value between 1 and 9 then an error message will
be output to System.err (stderr). This has no effect on the GUI.

The FlakeApp sets up the main frame container for display, from the main method.
The MainPanel, which contains all GUI components, is added to this frame.
All UI elements are set up in the Main Panel class and the snowflake image
is added below the UI.

METHOD:

I had written out a complicated implementation which used a circular queue 
of corner co-ordinate vectors, and from these corners it would recursively 
calculate and add the 3 new points for each line between consecutive corners 
in the queue. But when trying to relearn java graphics, I found a solution 
in my textbook which was far simpler.
I scrapped all my work and copied the textbook solution.

It's method is similar but the formula is far less complicated then the 
vector maths I was doing.

The part I struggled most with was getting the the UI layout the way I wanted.
I ended up just creating new panels for each row of the UI and adding them 
all in a column. If you resize the window you may notice that the current 
order value is not centered. This is because I didn't see the point in creating
a whole new panel for some text output.

I know you don't like global variables but they were necessary for components
that needed to be called from the inner event listener class.

ISSUES:
Current Order text is not centered properly,
If the window is made very wide or very tall then the points of a higher 
order snowflake may not fit the screen.

RESOURCES:
	Java Foundations 2nd ed. (page 857)
	Introduction to Program Design and Data Structures
	Lewis, DePasquale, and Chase
