README.md

## Etude 12 - COSC 326
#### Zac Seales - 6687905

### Instructions
1. Compile all java files.
>>javac -Xlint *.java

2. Run the main program.
>>java IBMtoIEEE

3. Enter the input file path and the output filename, when prompted.
>>testing.bin
>>results.bin

#### Program Information

Input is processed as 32 bit values (single-to-single).
The program takes hex input and converts it to a binary string.
It then finds the sign, exponent, and mantissa of the binary string to convert the IBM value to it's Decimal equivalent.

The decimal value is then output to a binary file as a byte stream using the DataOutputStream class.

### Discussion

I've implemented the decimal to byte code that you mentioned and it allowed me to remove a lot of my computation code.
I think it works, I can now open the output file using "hexed.it".

I also tried again to implement the byte reading because you said you weren't happy with text input, but I failed after many attempts using BufferedReader, InputStreamReader, InputStream, BufferedInputStream, and scan.nextByte().

Basically, I can only get things working if you give me the answer.

### Previous submission notes

Again, I'm struggling to find resources. The most helpful thing I found was a MATLAB formula that converts IBM binary to decimal.
(https://www.mathworks.com/matlabcentral/answers/94074-is-it-possible-to-read-seismic-data-or-convert-ibm-float32-to-ieee-float-32-in-matlab-7-8-r2009a)

The problem then was converting the decimal value back to binary, I've found a site that has a few examples of IBM and IEEE equivalents, I've been using this table as my test values.

(https://www.crewes.org/Documents/ResearchReports/2017/CRR201725.pdf - Table2 Pg.6)


There's a lot more resources for decimal to ieee 754 format so I stuck with this method.
This (below) step by step process helped with that.

(https://binary-system.base-conversion.ro/real-number-converted-from-decimal-system-to-32bit-single-precision-IEEE754-binary-floating-point.php?decimal_number_base_ten=0.15625)

Once I finally got this working I then needed to account for decimal results which had already been converted to standard form
ie. 3.1415E38