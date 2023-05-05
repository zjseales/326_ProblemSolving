#include "Integer.h"
#include <iterator>
#include <algorithm>

/** Integer.cpp
 *  COSC326 - Etude 08
 * 
 *  Class representing large integers.
 *@author Zac Seales (Altered skeleton code from blackboard)
 */

namespace cosc326 {

	/** Constructor - initializes a default Integer 
	 *  with the value 0.
	 */
	Integer::Integer() {
		sVal = 0 + "";
		positive = true;
	}
	/** Copy Constructor - initializes an Integer 
	 *  with the Integer value specified in the argument.
	 *@param i - The Integer being copied.
	 */
	Integer::Integer(const Integer& i) {
		positive = i.positive;
		sVal = i.sVal;
	}

	/** Constructor (String) - initializes a new Integer
	 *  with the specified String argument.
	 *@param s - the string being 'converted' to an Integer object.
	 */
	Integer::Integer(const std::string& s) {
		//check for sign.
		if (s.at(0) == (char)"-"){
			positive = false;
		} else {
			positive = true;
		}
		sVal = s;
	}

	/** Destructor
	 */
	Integer::~Integer() {
		
	}

	/** Assignment operator - Assigns a new Integer value to 
	 *  this Integer.
	 *@param i - the Integer object that defines the new value.
	 */
	Integer& Integer::operator=(const Integer& i) {
		sVal = i.sVal;
		return *this;
	}

	/** Converts this Integer to a negative Integer.
	 *@return - the negative Integer.
	 */
	Integer Integer::operator-() const {
		Integer(*this).positive = false;
		return Integer(*this);
	}

	/** Converts this Integer to positive (if not already).
	 *@return - the positive Integer.
	 */
	Integer Integer::operator+() const {
		Integer(*this).positive = true;
		return Integer(*this);
	}

	Integer& Integer::operator+=(const Integer& i) {
		return *this;
	}

	Integer& Integer::operator-=(const Integer& i) {
		return *this;
	}

	Integer& Integer::operator*=(const Integer& i) {
		return *this;
	}

	Integer& Integer::operator/=(const Integer& i) {
		return *this;
	}

	Integer& Integer::operator%=(const Integer& i) {
		return *this;
	}

	/** Adds the 2 Integer values together and returns the result.
	 *@param lhs - the first Integer.
	 *@param rhs - the second Integer.
	 */
	Integer operator+(const Integer& lhs, const Integer& rhs) {
		char c1 = (char)lhs.sVal.at(lhs.sVal.length()-1);
		int v1 = getDigit(c1);
		char c2 = (char)rhs.sVal.at(rhs.sVal.length()-1);
		int v2 = getDigit(c2);

		int remainder;

		int curr = v1 + v2;
		lhs.sVal.substr(0, lhs.sVal.length()-1);
		if (curr < 10){
			lhs.sVal = lhs.sVal + curr;
			remainder = 0;
		} else {
			lhs.sVal += (char)9;
			remainder = curr - 9;
		}
		return lhs;
	}

	Integer operator-(const Integer& lhs, const Integer& rhs) {

		return lhs;
	}

	Integer operator*(const Integer& lhs, const Integer& rhs) {
		return lhs;
	}

	Integer operator/(const Integer& lhs, const Integer& rhs) {
		return lhs;
	}

	Integer operator%(const Integer& lhs, const Integer& rhs) {
		return lhs;
	}


	std::ostream& operator<<(std::ostream& os, const Integer& i) {
		return os;
	}

	std::istream& operator>>(std::istream& is, Integer& i) {
		return is;
	}

	bool operator<(const Integer& lhs, const Integer& rhs) {
		return true;
	}

	bool operator> (const Integer& lhs, const Integer& rhs) {
		return true;
	}

	bool operator<=(const Integer& lhs, const Integer& rhs) {
		return true;
	}

	bool operator>=(const Integer& lhs, const Integer& rhs) {
		return true;
	}

	bool operator==(const Integer& lhs, const Integer& rhs) {
		return true;
	}

	bool operator!=(const Integer& lhs, const Integer& rhs) {
		return true;
	}

	Integer gcd(const Integer& a, const Integer& b) {
		return a;
	}

	/** Returns the current character as the int digit that it represents.
	 *@param c - the character being converted.
	 *@return - the int value parsed from the argument.
	 */
	int getDigit(char c){
		int i = c - 48;
		return i;
	}

	/** Returns the sign of this integer */
	bool getSign(const Integer& i){
		return Integer(i).positive;
	}

}
