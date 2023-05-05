README.txt

Syllable Counting program
COSC326 Etude 02

AUTHORS:
	Eliot Luna
	Liam Wilson
	Zac Seales
	Amaan Aamir

INSTRUCTIONS:
  1. Compile all java files.
>>javac -Xlint *.java

  2. There are 3 options for running the program; (testing file is attached 'words.txt')

	a) Run Syll class with a filename argument
>>java Syll words.txt

	b) Redirect a file to stdin
>>java Syll < words.txt

	c) Run the program on it's own and type input into the command line (Ctrl-C to exit)
>>java Syll

WHO DID WHAT:
This was a collaborative effort. We all contributed to the code and communicated well whenever
we wanted to change anything. It's difficult to to split up the roles as we didn't have any clearly defined roles.

Just in case this humble explanation isn't to your satisfaction..
Zac Seales wrote the original vowel detection code and the final README file.
Liam Wilson created rules for valid words and made the main method detect valid input from text files.
Eliot Luna and Amaan Aamir coded the rules, created test data, and optimized the original code.

PROGRAM INFO:
The Syll class only takes input, calls methods of the SyllCounter class, and displays the results.
All computations and checks are done in the SyllCounter class.

The first method to be called (isValidWord()) ensures the line of input read in was a single String of 
english letter characters with no whitespace.
If these conditions are not met then an error message will be output to stderr and the input line
will not be analyzed.

If the input is valid then the syllables will be counted. We began with a simple vowel counter and have 
kept adding conditions and exceptions to more accurately predict syllables.
The word is iterated and each character is analyzed independently.

If the current character is an a, e, i, o, or u, then the analyzeVowel method is called which will 
check surrounding characters to determine whether the vowel is likely to be pronounced as a seperate 
syllable or not.
analyzeVowel returns 1 if it is deemed a new syllable, otherwise returns 0.

The analyzeY method is similar, it will return 1 if the y is deemed a vowel depending on surrounding characters.

Once the entire word is iterated and an estimate is computed, the main method will output the results.

ISSUES:
No matter how you algorithmically estimate syllables, there will always be certain words that create exceptions.
eg. 'coin' is 1 syllable but the same sequence in 'coincide' is pronounced as 2 syllables.
    'fruit' and 'fruition', 'boing' and 'doing', 'ion' and 'friction', 'fear' and 'react'. 

We've tried to only include rules which contain no exceptions, or very few exceptions.

TESTING/RESULTS:
We've been using words.txt for testing and the results have been redirected to results.txt
It's just a list of random words we thought of that created exceptions to our initial rules,
and we've tried to address these exceptions as best we can.

