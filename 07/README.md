README.md

# COSC 326 - Etude 07
### Zac Seales - 6687905

#### Execution details

    1. Compile the java files.
>>javac -Xlint *.java

    2. Run the class containing the main method, and input values via stdin.
Either redirect a file of input to stdin.
>>java Co < validCo.txt 
or, run the program and type input into the command terminal.
>>java Co

### Program Information

This program reads lines of input from stdin and attempts to find a horizontal-coordinate value.
Valid input data includes: Standard Form co-ordinates, Degrees Decimal Minutes, or Degrees Minutes Seconds.
Results are output to stdout in standard form.

First, an input string is 'tidied'.
Unecessary punctuation characters are removed, cardinalities, and valid punctuation symbols, that are attached to numbers get seperated.

The tidied string is then split by whitespace into an ArrayList of Strings. This "should" contain all numbers, all valid symbols, and all cardinalities.

The list of input is then iterated twice.
The first iteration returns an 'info' array. Which contains 4 values that drastically simplify later checking.
The info array values are as follows:
info[0] - if this value is 0 then the first co-ordinate is a latitude, otherwise it's a longitude.
info[1] - this value is the total number of numbers in the input list.
info[2] - this value represents the final index of the first co-ordinate. (0 if unknown)
info[3] - Either -1 or 1, represents the cardinality of the first co-ordinate.

By determining these values we can accept input of a huge variety (With or without punctuation, multiple formats, etc.)
The actual co-ordinates are then determined and the methods they use are dependent on these values determined from the pre-processing.
The finalChecks method is then called to ensure final results are a valid horizontal co-ordinate in standard form.

### Things done "well"

I've taken a very rule-based approach.
I'm not fluent with regex expressions but I've tried to include as many as I can.
I've also tried to create reusable methods wherever possible because dependent on the information gathered from the first iteration, the input will undergo different co-ordinate retrieval methods. I noticed a lot of the same operations were being used for many of these methods and even managed to simplify earlier methods with these small reusable methods (isVal, getVal, isCard, determineSign, putInUnknown, swapVals).

### Problems

Even with these optimizations, I know this code is a mess and difficult to understand. 
I was overwhelmed by how much possible input is allowed. I've tried to generalize operations to allow for 'bad' input but also appropriately compute valid input.
I decided to comment out my valid Strings methods as this overcomplicated checking.
Eventually I took an incremental approach instead of trying to include all valid data at once.
I've done my best to handle the 6 possible scenarios in the etude and I believe they're working.

I can't figure out Map extensions. I've tried Leaflet and Mapbox to no avail. 
They seem to require an HTML page but I don't have a website nor do I know how to set one up.