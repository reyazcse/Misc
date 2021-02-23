//https://leetcode.com/problems/remove-invalid-parentheses/
//Reference: solution on leetcode
package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParenthesesDFS {
	int overallMinimum = Integer.MAX_VALUE;
	
	public  List<String> removeInvalidParentheses(String str) {
		StringBuilder expr = new StringBuilder();
		
		Set<String> result = new HashSet<>();
		
		recurse(str, expr, 0,0,0,0, result);
		return new ArrayList<>(result);
	}
	
	private void recurse(String s, StringBuilder expr, int leftCount, int rightCount, int pos, int currentRemoved, Set<String> result) {
		if(pos == s.length()) {
			if(leftCount == rightCount) {				//valid expression
				if(currentRemoved <= overallMinimum) {
					String possible = expr.toString();
					if(currentRemoved < overallMinimum) {		
						overallMinimum = currentRemoved;
						result.clear();						//remove previous answers as we got better
					}
					result.add(possible);
				}
			}
		}else {
			char c = s.charAt(pos);
			if(c != '(' && c != ')') {				//letter found
				expr.append(c);
				recurse(s, expr, leftCount, rightCount, pos+1, currentRemoved, result);
				expr.deleteCharAt(expr.length()-1);
			}else {
				recurse(s, expr, leftCount, rightCount, pos+1, currentRemoved+1, result);		//recurse by not considering current bracket
                expr.append(c);
				if(c == '(') {
					recurse(s, expr, leftCount+1, rightCount, pos+1, currentRemoved, result);
				}else if(rightCount < leftCount) {
					recurse(s, expr, leftCount, rightCount+1, pos+1, currentRemoved, result);
				}
				expr.deleteCharAt(expr.length()-1);
			}
		}
	}
	
	public static void main(String[] args) {
//		String s = "(ab))";
//		String s = ")(";
//		String s = "())()";
		String s = "";
		RemoveInvalidParenthesesDFS obj = new RemoveInvalidParenthesesDFS();
		System.out.println(obj.removeInvalidParentheses(s));

	}

}
