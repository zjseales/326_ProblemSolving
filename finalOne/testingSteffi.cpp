/*
	Test main for Rational and Integer
*/

#include <iostream>
using namespace std;

#include "Integer.h"

using namespace cosc326;

int main() {

	Integer a("922337");
	Integer b("42949");

	//smaller values to simplify testing
	Integer c("27");
	Integer d("9");

	//cout << c * d << endl;

	cout << a + b << endl;
	cout << a - b << endl;
	cout << a * b << endl;
	cout << a / b << endl;
	cout << c / d << endl;
	//cout << a % b << endl;

	//test if values are equal
	if (a >= b){
		cout << "true" << endl;
	} else if (a < b){
		cout << "false" << endl;
	}

	return 0;
}
