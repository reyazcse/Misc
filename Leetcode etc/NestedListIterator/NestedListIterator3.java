//https://leetcode.com/problems/flatten-nested-list-iterator/

/*
Solution:
	Here stack is used.
	We put the list in the stack from end of list to the start.
	In the hasNext() method, we flatten if we get a list. So after we call hasNext(), we are sure that top of the stack
	holds an integer (nested integer to be exact).
	

Complexity: O(n) time and O(n) space
NestedListIterator3.java	
 * */
package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedListIterator3 implements Iterator<Integer> {
    Stack<NestedInteger> stack = new Stack<>();

    public NestedListIterator3(List<NestedInteger> nestedList) {
    	for(int i=nestedList.size()-1; i >= 0; i--) {
    		stack.add(nestedList.get(i));
    	}
    }

    @Override
    public Integer next() {
    	return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
    	
    	
    	while(!stack.isEmpty()) {
    		NestedInteger top = stack.peek();
    		if(top.isInteger()) {
    			return true;
    		}
    		
    		stack.pop();
    		flattenAndPut(top);
    	}
    	return false;
    	
    }
    
    private void flattenAndPut(NestedInteger top) {
    	List<NestedInteger> list = top.getList();
    	for(int i=list.size()-1; i>=0; i--) {
    		stack.add(list.get(i));
    	}
    }
    
}

