//https://leetcode.com/problems/min-stack/
package leetcode;

import java.util.Stack;

//One stack with a pair of values: first value is actual value while second value is the minimum value so far
public class MinStack2 {
	Stack<int[]> stack;	
	

	/** initialize your data structure here. */
	public MinStack2() {
		stack = new Stack<>();
	}

	public void push(int x) {
		if(stack.isEmpty()) {
			stack.add(new int[] {x,x});
		}
		else {
			int min = Math.min(x, stack.peek()[1]);		//get miniminum of (current value, min on stack top)
			stack.add(new int[] {x, min});
		}
	}

	public void pop() {
		stack.pop();
	}

	public int top() {
		return stack.peek()[0];
	}

	public int getMin() {
		return stack.peek()[1];
				
	}
}

//Note: Other solution is using one stak and a min variable:
//https://leetcode.com/problems/min-stack/discuss/961317/4-Solution-or-Node-or-One-Stack-or-Two-Stack-or-Easy-to-Understand
