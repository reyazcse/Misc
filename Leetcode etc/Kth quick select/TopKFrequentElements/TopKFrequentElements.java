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


//SOLUTION: based on leetcode solution given in the link
/*
We will solve it using the algorithm of finding the kth smallest element (quickselect)
We have to find the top k elements.
	We can think of it as find the the kth largest and all other larger elements than the kth largest.
	So we can find (n-k) smaller elements. We will group the array such that we get the rightmost index of smaller elements
	Then from the next index till last, we have the top k elements!
Algorithm:
	Create a map of elements and their counts.
	The create an array of unique elements and put the unique elements in it.
	Now partition it in such way that we have smaller elements on left side and greater or equal elements at right side
	When we have (n-k) elements to the left, we return the index.
	Our solution is elements from index+1 till last element in the unique array
	
Complexity: 
	O(n) expected time
	O(n&^2) worst case but this is rare.

	O(n) space complexity.

 * */
package leetcode;

import java.util.HashMap;

public class TopKFrequentElements {
	HashMap<Integer, Integer> countMap = new HashMap<>();		//contains element as key and count as value
	public int[] topKFrequent(int[] nums, int k) {
		for(int num : nums) {
			int prevCount = countMap.getOrDefault(num, 0);
			countMap.put(num, prevCount+1);
		}

		int [] unique = new int[countMap.size()];
		int n = unique.length;
		int i=0;
		
		//put unique elements
		for(int num : countMap.keySet()) {
			unique[i++] = num;
		}
		n  = unique.length;
		//find n-k smaller elements
		quickSelect(unique, 0, n-1, n-k);
		int [] result = new int[k];			//to store result
		i=0;
		//we have top k elements fron n-k ...n-1
		for(int pos = n-k; pos<unique.length; pos++) {
			result[i++] = unique[pos];
		}
		return result;
	}

	private void quickSelect(int []unique, int l, int r, int k) {
		if(l >= r) {			//needed else we get out of bounds exception
			return;
		}
		int pivot = randomPartition(unique, l, r);
		
		if(pivot - l + 1 == k) {
			return;
		}else if (pivot - l +1 > k) {
			quickSelect(unique, l, pivot-1, k);
		}else {
			quickSelect(unique, pivot+1, r, k- (pivot-l+1));
		}
	}

	//partition is done on the basis of count of the element.
	//put elements which have smaller count than count of pivot element to the left
	//elements whose count are greater or equal are on the right of pivot
	private int randomPartition(int[]unique, int l, int r) {
		//pivot index
		int pivot = l + (int) (Math.random()*(r-l+1));
		int x = countMap.get(unique[pivot]);
		System.out.println(" l " + l + " r " + r + " x " + x);
		swap(unique, pivot, r);
		int curr = l-1;
		for(int i=l; i<r; i++) {
			if(countMap.get(unique[i]) < x) {		//if count is less, swap to the left
				curr++;
				swap(unique, curr, i);
				
			}
		}
		
		swap(unique, curr+1, r);
		System.out.println("array after partition: ");printArray(unique);
		return curr+1;		//return index of the pivot
	}
	
	private void printArray(int []nums) {
		for(int elt : nums) {
			System.out.print("  " + elt);
		}
	}
	private void swap(int []nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	public static void main(String[] args) {
		//int [] nums = {1,1,1,3,3,4,5,5,5,5,6};
		//int [] nums = {4,4,4,4,2,1,1,5,5,5,5,3};
		int []nums = {1,1,1,2,2,3};
		int k = 2;
		TopKFrequentElements ob = new TopKFrequentElements();
		ob.topKFrequent(nums, k);

	}

}
