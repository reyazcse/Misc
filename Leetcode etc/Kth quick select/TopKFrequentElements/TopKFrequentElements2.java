//https://leetcode.com/problems/top-k-frequent-elements/
/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * */

//Solution : Using min heap
//solution based on leetcode given solution
/*
Create a minHeap of size k of the unique elements.
Also have a map which stores element as key and count as value
Then while adding element in the minHeap, if its count is greater than count of top element, then remove top and 
add this element

Complexity Analysis

Time complexity : O(Nlogk) if k < N and O(NlogN) when N=k. 

Space complexity : O(N+k) to store the hash map with not more N elements and a heap with k elements.
 * */
package leetcode;

import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKFrequentElements2 {
	public int[] topKFrequent(int[] nums, int k) {
		HashMap<Integer, Integer> countMap = new HashMap<>();
		for(int num : nums) {
			int prevCount = countMap.getOrDefault(num, 0);
			countMap.put(num, prevCount+1);
		}
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((n1, n2) ->
			countMap.get(n1) - countMap.get(n2));
		
		for(int n : countMap.keySet()) {
			if(minHeap.isEmpty() || minHeap.size() < k) {			//first add elements till size is k
				minHeap.add(n);
			}else {
				if(countMap.get(n) > countMap.get(minHeap.peek())) {
					minHeap.poll();
					minHeap.add(n);
				}
			}
			
		}
		int[]result = new int[k];
		for(int i=k-1; i>=0; i--) {
			result[i] = minHeap.poll();
		}
		return result;
	}
	public static void main(String[] args) {
		int []nums = {1,1,1,2,2,3};
		int k = 2;
		TopKFrequentElements2 ob = new TopKFrequentElements2();
		ob.topKFrequent(nums, k);

	}

}
