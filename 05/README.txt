README.txt

Maori Translation Program
COSC 326 Etude 05

AUTHORS:
	Zac Seales
	Amaan Aamir

INSTRUCTIONS:
  1. Compile all java files.
>>javac -Xlint *.java

  2. Run the program by entering English sentences through stdin.
--
NOTE: Input sentences must begin with a pronoun and end with a verb.
--
	a) Either, run the program and type input into the command terminal
>>java Translator

	b) or, redirect a file of input to stdin
>>java Translator < validSentences.txt
--------------------------------------------------------------------------------------
SUBMISSION 2 FIX-UPS:
- Added macrons.
- Error messages now print to stdout, instead of stderr.
- Added "made" to the past tense verbs.
- Made "you (1 incl)" a valid input
- Fixed bug that was preventing 'saw' from being considered a valid verb.
ADDED HASHTABLES:
- Translate constructor now initializes 2 hashtables;
	'pronounHash' which maps English pronouns to their Maori translation.
	'connectorTenseHash' which maps 'connecting words' to the tense that they represent
	(in Maori).

These hash tables allow us to retrieve the Maori translation with O(1) efficiency and
also allowed us to remove a few methods containing large extended if statements.

A hash table for verbs would make verb translation much faster but we don't want
to have to type out every single nominalization as a 'key', for each verb.
We're happy with our regex solution.

There's still a lot of extended if statement bugchecks but it's difficult to 
simplify/remove them without rewriting the entire program. Most of the checks are 
necessary for detecting specific input possibilities.

Also, Stefanie doesn't like global variables but we figured it was better to make the
hash-tables global instead of reinitializing them for every iteration.
--------------------------------------------------------------------------------------
PROGRAM INFO:
The Translator class reads lines of input, calls the translate method from
the Translate class, and outputs the results (if no error occurred).

There are 3 components which determine the final Maori translation;
Pronoun, tense, and verb. This program attempts to determine these values
which are represented in the 'components' array.
The main method will ignore any components array which is not complete.

The translate method checks the first word of the input to ensure it is
a valid pronoun.
It then attempts to determine the tense from any words that are not the 
pronoun or the verb.
The verb is then analyzed and translated.
If the tense has not yet been determined then the valid verb will determine 
the tense.

KNOWN PROBLEMS:
As long as a sentence begins with a valid pronoun and ends with a valid verb 
it will be considered a valid sentence.
(Provided it has passed the excl/incl bugchecks)
There's likely some 'connecting words' that we haven't considered so any
unknown connecting words are just ignored and tense is determined from the verb.

WHO DID WHAT:
Zac Seales wrote the README file, the comments and the initial code,
also worked largely on error and bug-checking.
Amaan Aamir wrote test data and ensured test data lined up with correct results.
Amaan also worked on the rules and bug-checking.

TESTING:
Our test data consists of 2 text files:
validSentences.txt
bugcheckSentences.txt
