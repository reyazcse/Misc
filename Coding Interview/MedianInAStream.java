//https://leetcode.com/problems/find-median-from-data-stream/
/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 * */

/*
Solution:
	Using max heap to store smaller elements and min heap to store larger elements.
	O(logn) time and O(n) space
	
Note: 
1. One more log n soltuion using multiset and pointers is provided on leetcode page.
2. For follow up query, check leetcode solution.
 * */
package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianInAStream {
	PriorityQueue<Integer> maxHeap = null;
	PriorityQueue<Integer> minHeap = null;
	 public MedianInAStream() {
		 maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
		 minHeap = new PriorityQueue<Integer>();
	    }
	    
	    public void addNum(int num) {
	    	if(maxHeap.size() == 0 || num < maxHeap.peek()) {
	    		maxHeap.add(num);
	    	}else {
	    		minHeap.add(num);
	    	}
	    	rebalance();
	    }
	    
	    public double findMedian() {
	    	PriorityQueue<Integer> smaller = maxHeap.size() < minHeap.size() ? maxHeap : minHeap;
	    	PriorityQueue<Integer> bigger = maxHeap.size() < minHeap.size() ? minHeap : maxHeap;
	    	
	    	int size_diff = bigger.size() - smaller.size();
	    	if(size_diff == 1) {
	    		return bigger.peek();
	    	}else {
	    		double median = ((double)smaller.peek() + bigger.peek())/2;
	    		return median;
	    	}
	    }
	    
	    private void rebalance() {
	    	PriorityQueue<Integer> smaller = maxHeap.size() < minHeap.size() ? maxHeap : minHeap;
	    	PriorityQueue<Integer> bigger = maxHeap.size() < minHeap.size() ? minHeap : maxHeap;
	    	
	    	if(bigger.size() - smaller.size() > 1) {
	    		smaller.add(bigger.poll());
	    	}
	    }

}
