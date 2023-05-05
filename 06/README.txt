README.txt

Counting Program
COSC 326 Etude 06

AUTHORS:
	Zac Seales
	Amaan Aamir

INSTRUCTIONS:
  1. Compile all java files.
>>javac -Xlint *.java

  2. Run the program by entering 2 long values through stdin.

	a) Either, run the program and type input into the command terminal
>>java Counter

	b) or, redirect a file of input to stdin
>>java Counter < validValues.txt
--------------------------------------------------------------------------------------
PROGRAM INFO:

This program takes 2 long values as input and returns the binomial coefficient,
by building the nth row of a pascals triangle up to the kth value.
The larger of the 2 values represents the row number = n of a pascals triangle.
The smaller value represents the column-number = k (index) of the row.

The (n-k) value is output to stdout.

k = 0 will always output 1, 
k = 1 will always output n,
k = 2 will always output (n * (n-1)) / 2.

All other k values are determined from a Pascalls triangle which is iteratively built.
Recursively building a pascalls triangle can not solve this problem as it will result 
in a call-stack StackOverflowError for larger values.

Our first solution involved converting longs to bitstrings and performing factorial mathematics
on the bitstrings, this way we could store results larger than 64-bits. The problem with this was
it was insanely slow, iterating huge bitstrings and manually performing binary maths resulted in huge 
wait-times.

After giving up on optimizing this extremely complex program, we attempted to create our own
BigInteger class. Luckily we didn't spend too much time on this before finding out Pascalls triangle
was the correct way to solve this problem. 
For a long time we were trying to find ways of representing numbers larger than 64-bits, we didn't realize
the factorial formula was possible to solve without multiplication.


WHO DID WHAT:
Zac Seales wrote the README file, the comments and the main method.
Amaan Aamir wrote the pascal triangle computations.

TESTING:
All testing was performed via the command line.
