#include "Integer.h"
#include <iterator>
#include <algorithm>

#include <cmath>
#include <vector>
#include <string>       // std::string
#include <iostream>     // std::cout
#include <sstream>      // std::stringstream

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
		sVal = "0";
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
		if (s.find("-") != std::string::npos) {
			positive = false;
		} else {
			positive = true;
		}
		for (int i = s.size() - 1; i >= 0; i--){
			if(!isdigit(s[i])){
            	throw("ERROR");
			}
        	sVal.push_back(s[i] - '0');
		}
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
		Integer newInt = (*this);
		newInt.positive = false;
		return newInt;
	}

	/** Converts this Integer to positive (if not already).
	 *@return - the positive Integer.
	 */
	Integer Integer::operator+() const {
		Integer newInt = (*this);
		newInt.positive = true;
		return newInt;
	}

	Integer& Integer::operator+=(const Integer& i) {
		*this = *this + i;
		return *this;
	}

	Integer& Integer::operator-=(const Integer& i) {
		*this = *this - i;
		return *this;
	}

	Integer& Integer::operator*=(const Integer& i) {
		*this = *this * i;
		return *this;
	}

	/** Divides the first Integer argument by the second 
	 * 
	 * (Trying to decrease the current Integer by the argument)
	*/
	Integer& Integer::operator/=(const Integer& i) {
		Integer temp = i;
		Integer temp2 = Integer();
		//temp2.sVal = "0";
		int num = 1;
		while (*this > i){
			*this = *this - i;
			num++;
		}
		std::cout << num << std::endl;
		*this = temp;
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
		Integer finalResult;
		std::string tempResult = lhs.sVal;
		int remainder = 0;
		int s;

		tempResult = lhs.sVal;
		for (int i = 0; i < tempResult.size(); i++){
			if (i < rhs.sVal.size()){
				s = (tempResult[i] + rhs.sVal[i]) + remainder;
			} else {
				s = tempResult[i] + remainder;
			}
			remainder = s / 10;
			s = s % 10;

			std::string tem = std::to_string(s);
			tempResult[i] = tem[0];

		}
		if (remainder != 0){
			std::string t = std::to_string(remainder);
			finalResult.sVal = std::string(tempResult + t);
		} else {
			finalResult.sVal = tempResult;
		}
		return finalResult;
	}

	/** The binary minus operation for the Integer class.
	 */
	Integer operator-(const Integer& lhs, const Integer& rhs) {

		Integer finalResult;
		std::string tempResult = lhs.sVal;
		int remainder = 0;
		int s;

		for (int i = 0; i < lhs.sVal.size(); i++){
			if (i < rhs.sVal.size()){
				s = (lhs.sVal[i] - rhs.sVal[i]) + remainder;
			} else {
				s = lhs.sVal[i] + remainder;
			}
			if (s < 0){
				s += 10;
				remainder = -1;
			} else {
				remainder = 0;
			}
			std::string tem = std::to_string(s);
			tempResult[i] = tem[0];
			//std::cout << tempResult << std::endl;
		}
		s = lhs.sVal.size();
		while (s > 1 && tempResult[s - 1] == 0){
			tempResult.pop_back(),
			s--;
		}
		finalResult.sVal = tempResult;
		return finalResult;
	}

	/** Multiplies the 2 Integer arguments together and returns the 
	 *  resulting Integer.
	 */
	Integer operator*(const Integer& lhs, const Integer& rhs) {
		Integer finalResult;
		Integer finalResult2;

		if ((lhs.sVal == "0") || (rhs.sVal == "0")){
			return Integer();
		}
		std::string tempResult = "";
		Integer tempInt1;
		Integer tempInt2;
		int remainder = 0;
		int s;

		//perform first multiplication iteration to obtain 'base'
		//multiply first digit in lhs value by all digits in rhs value
		for (int i = 0; i < 1; i++){
			for (int j = 0; j < rhs.sVal.size(); j++){
				s = (lhs.sVal[i]) * (rhs.sVal[j]) + remainder;
				remainder = s / 10;
				s = s % 10;
				std::string tem = std::to_string(s);
				tempResult = tempResult + tem[0];
			}
			if (remainder != 0){
				tempResult += std::to_string(remainder);
				remainder = 0;
			}
		}
		tempInt1.sVal = tempResult;
		tempResult = "";

		//perform subsequent iterations to obtain the
		//"list of multiplication results that need to be added"
		for (int i = 1; i < lhs.sVal.size(); i++){
			if (tempResult != ""){
				tempInt1.sVal = tempResult;
			}

			tempResult = "";
			for (int k = 0; k < i; k++){
				tempResult.append("0");
			}
			for (int j = 0; j < rhs.sVal.size(); j++){
				s = ((lhs.sVal[i]) * (rhs.sVal[j])) + remainder;
				remainder = s / 10;
				s = s % 10;
				tempResult += std::to_string(s);
			}
			if (remainder != 0){
				tempResult += std::to_string(remainder);
				remainder = 0;
			}
			tempInt2.sVal = tempResult;

			int rem2 = 0;
			int v1 = 0;
			int v2 = 0;

			//adds the current result with the result so far
			//performs addition while mulitplication iteration is 
			//happening because I don't know how to manage array memory
			for (int u = 0; u < tempInt2.sVal.size(); u++){

				if (u < tempInt1.sVal.size()){
					v1 = (int)tempInt1.sVal[u] - 48;
					v2 = (int)tempInt2.sVal[u] - 48;
					s = v1 + v2 + rem2;
				} else {
					v1 = (int)tempInt2.sVal[u] - 48;
					s = v1 + rem2;
				}
				rem2 = s / 10;
				s = s % 10;
				std::string tem = std::to_string(s);
				tempResult[u] = tem[0];
			}
			
			if (rem2 != 0){
				std::string t = tempResult + std::to_string(rem2);
				finalResult2.sVal = t;
			} else {
				finalResult2.sVal = tempResult;
			}
			tempResult = finalResult2.sVal;
		}

		return finalResult2;
	}

	/** Performs division using the 2 Integer arguments.
	 */
	Integer operator/(const Integer& lhs, const Integer& rhs) {
		if (rhs.sVal == "0"){
			throw ("Error: Division by 0");
		}
		//this doesn't return an Integer with 
		//value 1 and i don't know why
		if (lhs == rhs){
			return Integer("1");
		}

		Integer numerator;
		Integer denominator;
		Integer tempResult;
		Integer finalResult;
		std::string temporary = "";
		numerator.sVal = "";
		denominator.sVal = "";
		int num;
		int v1;
		int v2;
		int s;
		int rem = 0;

		//retrieve the integer arguments 1 digit at a time.
		//not sure why i can't retrieve the whole value.
		for (int i = 0; i < lhs.sVal.size(); i++){
			if (i < rhs.sVal.size()){
				denominator.sVal += std::to_string(rhs.sVal[i]);
			}
			numerator.sVal += std::to_string(lhs.sVal[i]);
		}
		//std::cout << numerator << std::endl;
		//std::cout << denominator << std::endl;

		tempResult.sVal = std::to_string(0);
		//std::cout << tempResult << std::endl;

		num = 0;
		//keep adding the smaller value and keep track of how many 
		//times it was added before the value exceeds the numerator
		while (tempResult < numerator){
			//if first addition, temp = denominator
			if (tempResult.sVal == "0"){
				tempResult = denominator;
			} else {
			//otherwise add denominator to temp
				for (int u = 0; u < tempResult.sVal.size(); u++){

					if (u < denominator.sVal.size()){
						v1 = (int)tempResult.sVal[u] - 48;
						v2 = (int)denominator.sVal[u] - 48;
						//std::cout << v1 << std::endl;
						//std::cout << v2 << std::endl;
						s = v1 + v2 + rem;
						//std::cout << s << std::endl;
					} else {
						v1 = (int)tempResult.sVal[u] - 48;
						s = v1 + rem;
					}
					rem = s / 10;
					//std::cout << rem << std::endl;
					s = s % 10;
					//std::cout << s << std::endl;
					std::string tem = std::to_string(s);
					temporary += tem[0];
					//std::cout << temporary << std::endl;
				}
			
				if (rem != 0){
					std::string t = temporary + std::to_string(rem);
					finalResult.sVal = t;
					rem = 0;
				} else {
					finalResult.sVal = temporary;
				}
				temporary = "";
				tempResult.sVal = finalResult.sVal;
			}
			//display the new result and increase the number of
			// times the denominator has been used
			//std::cout << tempResult << std::endl;
			//std::cout << num << std::endl;
			num++;
		}
			
		//decrease by 1 if result exceeded the numerator
		if (tempResult.sVal != numerator.sVal){
			num--;
			//std::cout << num << std::endl;
		}
		numerator.sVal = std::to_string(num);
		finalResult.sVal = "";
		for (int u = numerator.sVal.size() - 1; u >= 0; u--){
			finalResult.sVal += numerator.sVal[u];
		}
		return finalResult;
	}

	/** Performs the modulus operation using the 2 arguments 
	 *  and returns an Integer object that holds the value of 
	 *  the remainder.
	 */
	Integer operator%(const Integer& lhs, const Integer& rhs){
		return lhs;
	}

	/** Output stream */
	std::ostream& operator<<(std::ostream& os, const Integer& i) {
		for (int index = i.sVal.size() - 1; index >= 0; index--){
			std::cout << i.sVal[index];
		}
		return std::cout;
	}

	/** Input Stream */
	std::istream& operator>>(std::istream& is, Integer& i) {
		std::string s;
    	is >> s;
    	int n = s.size();
		
    	for (int j = n - 1; j >= 0;j--){
        	if(!isdigit(s[j])){
        	    throw("INVALID NUMBER");
			}
			i.sVal[n - j - 1] = s[j];
    	}
		return is;
	}

	/** Returns a boolean indicating whether or not the lhs arg
	 *  is less than rhs.
	 */
	bool operator<(const Integer& lhs, const Integer& rhs) {
		int n = lhs.sVal.size(), m = rhs.sVal.size();
    	if(n != m)
    	    return n < m;
    	while(n--){
    	    if(lhs.sVal[n] != rhs.sVal[n]){
    	        return lhs.sVal[n] < rhs.sVal[n];
			}
		}
    	return false;
	}

	/** Returns a boolean indicating whether or not the lhs arg
	 *  is greater than rhs.
	 */
	bool operator>(const Integer& lhs, const Integer& rhs) {
		return rhs < lhs;
	}

	/** Returns a boolean indicating whether or not the lhs arg
	 *  is less than, or equal to, rhs.
	 */
	bool operator<=(const Integer& lhs, const Integer& rhs) {
		return !(lhs > rhs);
	}

	/** Returns a boolean indicating whether or not the lhs arg
	 *  is greater than, or equal to, rhs.
	 */
	bool operator>=(const Integer& lhs, const Integer& rhs) {
		return !(lhs < rhs);
	}

	/** Returns a boolean indicating whether or not the Integer
	 *  arguments hold the same value.
	 */
	bool operator==(const Integer& lhs, const Integer& rhs) {
		if (lhs.positive == rhs.positive){
			return lhs.sVal == rhs.sVal;
		}
		return false;
	}

	/** Returns a boolean indicating whether or not the Integer
	 *  arguments hold different values.
	 */
	bool operator!=(const Integer& lhs, const Integer& rhs) {
		if (lhs.positive == rhs.positive){
			return !(lhs.sVal == rhs.sVal);
		}
		return true;
	}

	Integer gcd(const Integer& a, const Integer& b) {
		return a;
	}

	/** Returns the sign of this integer */
	bool getSign(const Integer& i){
		return Integer(i).positive;
	}

}
