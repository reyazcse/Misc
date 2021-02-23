//https://leetcode.com/problems/remove-invalid-parentheses/
//References: leetcode solution
package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParenthesesDFS2 {
	private Set<String> solutions = new HashSet<String>();

	public List<String> removeInvalidParentheses(String s) {
		int left=0, right=0;
		for(int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			if(ch == '(') {
				left++;
			}else if(ch == ')') {
				if(left > 0) {
					left--;
				}else {
					right++;
				}
			}
		}
		recurse(s, 0, 0, 0, new StringBuilder(""), left, right);
		return new ArrayList<>(solutions);
	}

	private void recurse(String s, int pos, int leftCount, int rightCount, StringBuilder expression, int leftRem, int rightRem) {
		if(pos == s.length()) {
			if(leftRem == 0 && rightRem==0) {
				solutions.add(expression.toString());
			}
			
		} else {
			char ch = s.charAt(pos);
			int len = expression.length();
			if((ch == '(' && leftRem > 0) || (ch == ')' && rightRem > 0)) {
				recurse(s, pos+1, leftCount, rightCount, expression, leftRem - (ch == '(' ? 1 : 0), rightRem - (ch == ')' ? 1 : 0));
			}
			expression.append(ch);
			if(ch != '(' && ch != ')') {
				recurse(s, pos+1, leftCount, rightCount, expression, leftRem, rightRem);
			}else if(ch == '(') {
				recurse(s, pos+1, leftCount+1, rightCount, expression, leftRem, rightRem);
				
			}else if(rightCount < leftCount) {
				recurse(s, pos+1, leftCount, rightCount+1, expression, leftRem, rightRem);
			}
			expression.deleteCharAt(len);
		}
	}
	public static void main(String[] args) {
		String s = "()())()";
		RemoveInvalidParenthesesDFS2 obj = new RemoveInvalidParenthesesDFS2();
		List<String> solutions = obj.removeInvalidParentheses(s);
		System.out.println(solutions);
		

	}

}
