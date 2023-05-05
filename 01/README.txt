README.txt

Dates program 
COSC326 Etude01
Zac Seales - 6687905 - seaza886

Instructions:
NOTE: '>>' is used to represent the command line window. 
      DO NOT type '>>'. Only type the text that follows.

 1. Compile all classes using,		
>>javac -Xlint *.java

 2. Either,
    a) run with a file (there in an attached files used for testing "dates.txt" 
>>java Dates dates.txt
    b) OR, run on it's own and type in a date (ctrl-C to exit)
>>java Dates
    c) OR, redirect a text file to the stdin
>>java Dates < dates.txt

Program info:
The Dates class is only used to import a file, check for validity,
and display the results, using the DateCheck class.
All error and validity checks/calculations are done in the DateCheck class.
Only the isValidDate(String input) method is made public, all checks are
called from this method.

Problems when altering code:
By seperating the main method into another class, removing global variables,
and redefining the access privilege of methods..
I caused a lot of errors and inefficiencies.
Having the day, month, and year as global variables made the code much easier
to manage. By removing them I've had to do the same parse and type conversions
in multiple methods because I may be comparing strings or numbers and the
way I've set up error checking, most methods need to return a boolean which 
makes it difficult to return other value types to the bottom of the call stack.
My entire code needs to be rewritten in order to fully optimise it to a decent
standard.
I had to keep an int month global field because it was the simplest
workaround. The other option is converting lettered months (ie. "Apr") to a 
int variable in a seperate method but again, my error checking prevents this 
possibility.
I also didn't remove the global constants because I want it to be easy to change
the validity range.
Hopefully by recognizing my errors I won't be made to rewrite everything XD <3

INPUT files:
dates.txt

RESULTS:
I've redirected the output of the program when run with dates.txt
into results.txt but you should be able to run the program yourself 
and get the same results.

Resources: Java API 
		https://docs.oracle.com/javase/7/docs/api/
	   Leap Year algorithm 
		https://en.wikipedia.org/wiki/Leap_year
