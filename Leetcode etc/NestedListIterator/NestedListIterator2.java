//https://leetcode.com/problems/flatten-nested-list-iterator/submissions/
/*
Solution:
	Using arraylist. Similar to using queue in the previous solution
	O(n) time and O(n) space
 * */
package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedListIterator2 implements Iterator<Integer> {
    private List<Integer> list = new ArrayList<>();
    private int i=0;

    public NestedListIterator2(List<NestedInteger> nestedList) {
        helper(nestedList);
    }

    @Override
    public Integer next() {
        int result = list.get(i);
        i++;
        return result;
    }

    @Override
    public boolean hasNext() {
        return i < list.size();
    }
    
    private void helper(List<NestedInteger> nestedList) {
    	for(NestedInteger itm : nestedList) {
    		if(itm.isInteger()) {
    			list.add(itm.getInteger());
    		}else {
    			helper(itm.getList());
    		}
    	}
    }
}
