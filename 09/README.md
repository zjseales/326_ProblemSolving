#### COSC 326 - Etude 09

# Substrings Program

### Authors
Zac Seales
Eliot Luna
Amaan Aamir
Liam Wilson

### Instructions
1. Compile all Java files.

>>javac -Xlint *.java

2. Run the program and redirect strings to stdin.
(or enter strings through the command line interface)

>>java LongBoi < alphabetStrings.txt

### Program information
First, the input string is converted to an alphabet using the sortAlph method.
This method sorts the characters of the input string alphabetically,
and removes any duplicate characters. Basically converts the input string to an alphabet.

The longest string creation (the calculate method) involves two parts.
1) Iterate the alphabet and add each character to the resulting string twice.
2) Recursively iterate the alphabet to determine the remainding characters.
Each recursive method call loops the alphabet starting from the index of the outer loop.

Our initial recursive method didn't work but was very close.
We removed certain characters from our incorrect results until we got a correct result, 
and then found that the removed characters followed a pattern.
We added this modification to our method and found it worked.

### Clarifications
Our program allows all characters (not just letters), it was unclear whether only letters would
be used but the result is no different, it would just require a check to ensure characters range 
from a-z.

Although we never computationally verified our results, we found multiple patterns regarding 
the length of the final string just by playing around with simple alphabets.
Final string Length is equal to the 'original alphabet size' squared plus one.
It's also equal to the total number of possible character pairs plus one.
Our results are consistent with these pattern and using human judgement, we have not found any
errors in the final string (ie. repeating sequences).

We don't have any test data. All testing was done from the CLI.
In theory, the program should work with any String.

### Who did what.
Amaan Aamir wrote an initial iterative method, which worked very similarly to our current solution.
Liam Wilson converted Amaan's method to a recursive solution (reduces O(n) by removing nested loops).
Zac Seales wrote the comments and README file.
Eliot Luna figured out and implemented the working solution.