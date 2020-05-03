//src: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
/*
	Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
	Input: "23"
	Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumericKeypad {
	public static Map<Character, List<String>> keypad = new HashMap<>();
	
	
	 public List<String> letterCombinations(String digits) {
		 if(digits == null || digits.length() == 0) {
			 return new ArrayList<>();
		 }
		 List<String> ans = letterCombinationsUtl(digits, 0);
		 return ans;
	 }
	 
	 public List<String> letterCombinationsUtl(String digit, int pos) {
		List<String> currentCombs = new ArrayList<>();
		
		if (pos == digit.length()) {
			currentCombs.add("");		//base case : adding empty string
			return currentCombs;
		}
		List<String> nextCombs = new ArrayList<>();	
		nextCombs = letterCombinationsUtl(digit, pos+1);
		char ch = digit.charAt(pos);
		switch(ch) {
			case '2':
				for(String s : nextCombs) {
					currentCombs.add("a" + s);
					currentCombs.add("b" + s);
					currentCombs.add("c" + s);
				}
				break;
			case '3':
				for(String s : nextCombs) {
					currentCombs.add("d" + s);
					currentCombs.add("e" + s);
					currentCombs.add("f" + s);
				}
				break;
			case '4':
				for(String s : nextCombs) {
					currentCombs.add("g" + s);
					currentCombs.add("h" + s);
					currentCombs.add("i" + s);
				}
				break;
			case '5':
				for(String s : nextCombs) {
					currentCombs.add("j" + s);
					currentCombs.add("k" + s);
					currentCombs.add("l" + s);
				}
				break;
			case '6':
				for(String s : nextCombs) {
					currentCombs.add("m" + s);
					currentCombs.add("n" + s);
					currentCombs.add("o" + s);
				}
				break;
			case '7':
				for(String s : nextCombs) {
					currentCombs.add("p" + s);
					currentCombs.add("q" + s);
					currentCombs.add("r" + s);
					currentCombs.add("s" + s);
				}
				break;
			case '8':
				for(String s : nextCombs) {
					currentCombs.add("t" + s);
					currentCombs.add("u" + s);
					currentCombs.add("v" + s);
				}
				break;
			case '9':
				for(String s : nextCombs) {
					currentCombs.add("w" + s);
					currentCombs.add("x" + s);
					currentCombs.add("y" + s);
					currentCombs.add("z" + s);
				}
				break;
		}
		return currentCombs;
	 }

	public static void main(String[] args) {
		NumericKeypad obj = new NumericKeypad();
		List<String> result = obj.letterCombinations("23");
		System.out.println(result);
	}

}
