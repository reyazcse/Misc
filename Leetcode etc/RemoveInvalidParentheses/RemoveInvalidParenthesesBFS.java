/*
https://leetcode.com/problems/remove-invalid-parentheses/
Question:
	Given a string of parenthesis and alphabets, digits (a(0)) or (#($)*). Remove the minimum number of parenthesis to get balanced expression.
For example:
	Input: "(a)())()"
	Output: ["(a)()()", "(a())()"]

Solution:
	We solve this using BFS.
	In the queue, we add strings by removing parenthesis at each position.
	If we find one balanced expression, then we look for other expressions stored currently on the queue, rather than going to store other expressions on queue
	This is because we have to remove minimum  number of parentheses

Check 2 leetcode solutions using DFS 
 * */
package misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class RemoveInvalidParenthesesBFS {
	public static List<String> minRemovalOfParentheis(String str) {
		Set<String> result = new HashSet<>();
		Set<String> visited = new HashSet<>();
		Queue<String> q = new LinkedList<>();
		q.add(str);
		boolean found = false;
		String expr = null;
		
		//BFS algo
		while (!q.isEmpty()) {
			expr = q.remove();
			if(visited.contains(expr))
				continue;
			//mark this expression as visited
			visited.add(expr);
			
			//if balanced expression is found, add to result and continue removing from queue
			if (isBalanced(expr)) {
				found = true;
				result.add(expr);
			} 
				
			if(found)					//VERY IMPORTANT: If we found a balanced one at this level, then we need not add 
				continue;				//nodes at next level. We have to search at this level only
			
			//below is not executed if we find a balanced expression above
			//storing strings by removing parenthesis at ith position.
			for(int i=0; i<expr.length(); i++) {
				if (expr.charAt(i) == '(' || expr.charAt(i) == ')') {
					String subExpr = expr.substring(0,i) + expr.substring(i+1, expr.length());
					//if(!visited.contains(subExpr)) {													//if we check here, it gives TLE
						q.add(subExpr);
					//}
					
				}
				
			}
		}
		return new ArrayList<>(result);
	}
	public static boolean isBalanced(String expr) {
		int count = 0;
		for(int i=0; i<expr.length(); i++) {
			if (expr.charAt(i) == '(') count++;
			else if (expr.charAt(i) == ')') {
				count--;
				if (count < 0)
					return false;
			}
		}
		return count == 0;
	}
	
	public static void main(String [] args) {
		//String expr = "(a)())()";
		String expr = "()(((((((()";
		List<String> balancedExpressions = minRemovalOfParentheis(expr);
		for (String str: balancedExpressions) {
			System.out.println(str);
		}
	}
}


//Below gives TLE error (expr = "()(((((((()")
//The only difference is while adding string to the queue, we have checked if it is not visited before

/*
public static List<String> minRemovalOfParentheis(String str) {
	Set<String> result = new HashSet<>();
	Set<String> visited = new HashSet<>();
	Queue<String> q = new LinkedList<>();
	q.add(str);
	boolean found = false;
	String expr = null;
	
	//BFS algo
	while (!q.isEmpty()) {
		expr = q.remove();
//		if(visited.containsKey(expr) && visited.get(expr))
//			continue;
		//mark this expression as visited
		visited.add(expr);
		
		//if balanced expression is found, add to result and continue removing from queue
		if (isBalanced(expr)) {
			found = true;
			result.add(expr);
		} 
			
		if(found)					//VERY IMPORTANT: If we found a balanced one at this level, then we need not add 
			continue;				//nodes at next level. We have to search at this level only
		
		//below is not executed if we find a balanced expression above
		//storing strings by removing parenthesis at ith position.
		for(int i=0; i<expr.length(); i++) {
			if (expr.charAt(i) == '(' || expr.charAt(i) == ')') {
				String subExpr = expr.substring(0,i) + expr.substring(i+1, expr.length());
				if(!visited.contains(subExpr)) {
					q.add(subExpr);
				}
				
			}
			
		}
	}
	return new ArrayList<>(result);
}
*/