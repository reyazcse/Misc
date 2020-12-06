//https://leetcode.com/problems/min-stack/
/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
 * */



package leetcode;

import java.util.Stack;
//Using two stacks: clean
class MinStack {
	Stack<Integer> stack;
	Stack<Integer> min_vals;
    
    /** initialize your data structure here. */
    public MinStack() {
           stack = new Stack<Integer>();
           min_vals = new Stack<Integer>();
    }
    
    public void push(int x) {
        if(min_vals.isEmpty() || x <= min_vals.peek()) {
        	min_vals.push(x);
        }
        stack.push(x);
    }
    
    public void pop() {
        if(stack.peek().equals(min_vals.peek())) {		// == returns wrong result. So use equals method
        	min_vals.pop();
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min_vals.peek();
    }
}
