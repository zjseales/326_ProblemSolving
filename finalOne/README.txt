README.txt

ETUDE 08 - COSC 326
Integer class

Zac Seales - 6687905
---------------------------------------------------------------

INSTRUCTIONS:

1. Compile the C++ files
>>g++ *.cpp

2. Run the executable
>>./a.out

("testingSteffi.cpp" contains the main method call)
----------------------------------------------------------------

UPDATES:

---I fixed multiplication, one of my loops was using the wrong bound value...
I added a few comments, it's hard to describe what I'm doing.
It's supposed to be an implementation of the basic multiplication algorithm
that everyone performs when they write out large multiplication problems by hand.
Instead of multiplying and storing all results in an array to add later,
the results are added once each row is complete.

For example: 24 * 5 would have the first row
20      (5 * 4)
and the second row would be 100. (add a 0 because we're multiplying by the 10s column now)
100 = ((2 * 5) + a zero)
This simple example is clearly 120.

For more complicated problems, each row is added to the result of the previous rows addition, 
until the final answer is reached. Which is why I've calculated the first row before 
looping the rest of the equation (I've called the first row the 'base')

---Division now works, the method repeatedly adds the divisor until the result exceeds the numerator.
It then returns the number of times this addition occurred before the number was exceeded.

I had trouble accessing my other methods as well as retrieving the values stored in the arguments.
I think it's something to do with pointers, references, and addresses requiring different syntax.

So, I just kept rewriting what I had working, my addition loop occurs 3 times throughout this program.

If I wanted to get mod working I'd have to rewrite the division code as well as the subtraction code.
GCD would be even more complicated..
-----------------------------------------------------------------

PROGRAM DETAILS:

The Integer class holds a string of digits.
These digits represent a large Integer (reading from right to left).
The Integer overloads operators to perform basic arithmetic using
the string values.
The program only accounts for positive numbers, Also, the first value 
(for subtraction, modulus, and division) must be larger than the second number.

I've managed to get addition and subtraction working, as well as all 
comparison operators (<, >, <=, ==, etc.)

Modulus, division, and GCD don't work at all. 
I'm pretty sure they all require division to work and I can't figure it out.
I've managed to convert the divisor into an int value but am struggling to
implement long division.

I know what I'm supposed to do but I'm not capable of completing it in the time frame.
I keep spending hours trying to debug the smallest issues because I don't know C++ and
have very little experience with references and pointers and being able to alter parameters.
I've tried multiple implementations, in both division methods, to no avail.
I don't want points for these excuses, I just want you to know that I'm not lazy.

---------------------------------------------------------

RESOURCES:

https://www.geeksforgeeks.org/bigint-big-integers-in-c-with-example/

testingSteffi.cpp was provided by Stefanie Zollman