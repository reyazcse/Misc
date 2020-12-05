//https://leetcode.com/problems/valid-parentheses/
/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

	Open brackets must be closed by the same type of brackets.
	Open brackets must be closed in the correct order.

Example 2:

Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

 * */

//Solution
//for opening bracket, push. for closing bracket, check if current match on stack top.


package leetcode;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {
	public boolean isValid(String s) {
		char[] char_arr = s.toCharArray();
		Stack<Character> stack = new Stack<>();
		for(int i=0; i<char_arr.length; i++) {
			char bracket = char_arr[i];
			if(bracket == '(' || bracket == '{' || bracket == '[') {
				stack.add(bracket);
			}else {
				
				if(stack.isEmpty()) {
					return false;
				}
				char peek = stack.peek();
				if((bracket == ')' && peek != '(' ) || (bracket == '}' && peek != '{') || (bracket == ']' && peek != '[')) {
					return false;
				}
				stack.pop();
			}
		}
		return stack.isEmpty();
	}
	
	//A cleaner method using mappings
	public boolean isValid_2(String s) {
		HashMap<Character, Character> mappings = new HashMap<>();
		mappings.put(')','(');
		mappings.put('}','{');
		mappings.put(']','[');
		
		Stack<Character> stack = new Stack<>();
		for(int i=0; i<s.length(); i++) {
			char bracket = s.charAt(i);
			if(mappings.containsKey(bracket)) {		//if closing bracket
				char popped = stack.isEmpty() ? '#' : stack.pop();
				if(popped != mappings.get(bracket)) {		//no match between popped and current bracket
					return false;
				}
			}
			else {
				stack.add(bracket);
			}
		}
		return stack.isEmpty();
	}
}
