/*
https://leetcode.com/problems/basic-calculator-iii/
Similar to https://leetcode.com/problems/basic-calculator-ii/ except that braces are also there
Note this problem  is similar to CTCI 16.26
 * */


//Reference: https://buttercola.blogspot.com/2019/03/leetcode-772-basic-calculator-iii.html?showComment=1601959826157#c2445525862678943687
/*
Implement a basic calculator to evaluate a simple expression string.
The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
 * */
package misc;

import java.util.Stack;

public class BasicCalculatorIII {  
	public int calculate(String s) {
		if(s == null || s.length() == 0)
			return 0;
		s = s.replace(" ", "");									//remove spaces
		Stack<Integer> numStack  = new Stack<>();
		Stack<Character> opStack  = new Stack<>();
		int i=0;
		int num=0;
		while(i < s.length()) {
			char c = s.charAt(i);
			
			if(Character.isDigit(c)) {
				num = parseNextNumber(s, i);							//get the number
				i += Integer.toString(num).length();					//move pointer
				numStack.push(num);										//push number to stack
			}else {														//some character found
				if (c == ')') {
					char top = opStack.peek();
					while(top != '(') {
						calculate(numStack, opStack);
						top = opStack.peek();
					}
					opStack.pop();
					i++;
				}else if(opStack.isEmpty()) {
					opStack.push(c);
					i++;
				}
				else if(c == '(') {
					opStack.push(c);
					i++;
				}else if (c == '+' || c == '-') {
					if(opStack.peek() == '(') {
						opStack.push(c);i++;
					}else {
						calculate(numStack, opStack);
					}
						
				}else if (c == '*' || c == '/') {
					char top = opStack.peek();
					if(top == '*' || top == '/') {
						calculate(numStack, opStack);
					}else if(top == '+' || top == '-' || top == '(') {
						opStack.push(c);
						i++;
					}
					
				}
			}
		}
		while(!opStack.isEmpty()) {
			calculate(numStack, opStack);
		}
		return numStack.pop();
	}

	private void calculate(Stack<Integer> numStack, Stack<Character> opStack) {
		int num2 = numStack.pop();
		int num1 = numStack.pop();
		char op = opStack.pop();
		int res = 0;
		switch(op) {
			case '+':
				res = num1+num2;
				break;
			case '-':
				res = num1-num2;
				break;
			case '*':
				res = num1*num2;
				break;
			case '/':
				res = num1/num2;
				break;
		}
		numStack.push(res);
		
	}  
	
	//extract number starting at offset : 23*5 and offset = 0 then returns 23
	private int parseNextNumber(String seq, int offset) {
		StringBuilder sb = new StringBuilder();
		while(offset < seq.length() && Character.isDigit(seq.charAt(offset))) {
			sb.append(seq.charAt(offset));
			offset++;
		}
		return Integer.parseInt(sb.toString());
		
	}
	public static void main (String[] args) {
		BasicCalculatorIII obj = new BasicCalculatorIII();
		//System.out.println(obj.calculate("(1+(2+4*3)*2)"));
		//System.out.println(obj.calculate("1*2-3/4+5*6-7*8+9/10"));
		System.out.println(obj.calculate("(2*3+5)"));
	}

}
