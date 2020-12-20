//https://leetcode.com/problems/kth-largest-element-in-a-stream/

//Solution: Use min heap. If size of heap is k, return peek element. Else pop elements till size become k and then return the peek element
package leetcode;

import java.util.PriorityQueue;

public class KthLargestElementStream {
	
	int k;
	PriorityQueue<Integer> pq;

	public KthLargestElementStream(int k, int[] nums) {
		this.k = k;
		pq = new PriorityQueue<Integer>(); //minHeap
		for(int num : nums) {
			pq.add(num);
		}
				
	}

	public int add(int val) {
		pq.add(val);
		if(pq.size() == k) {
			return pq.peek();
		}
		int diff = pq.size() - k;
		while(diff > 0) {
			pq.poll();
			diff--;
		}
		return pq.peek();
	}
	

}
