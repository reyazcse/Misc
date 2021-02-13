//https://leetcode.com/problems/sliding-window-maximum/
/*
Question:
	You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
	You can only see the k numbers in the window. Each time the sliding window moves right by one position.
	Return the max sliding window.
	
	Example:
	
	Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
	Output: [3,3,5,5,6,7]
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

 * */

/*
Solution 1: O(nk) time and O(1) space
Solution 2: O(n) time and O(k) space 
	This is done by dequeue. 
	Reference: https://www.ideserve.co.in/learn/maximum-element-from-each-subarray-of-size-k-set-2

Other solutions:
	We can also solve it by keeping k elements in some data structure so that we can pick up the max 
	element very fast.
	One way is to use self balancing bst such as avl tree for the k elements.
	
	Another way is to use a max heap. We pickup the max  element, then remove element and add new element each time the window moves. 
	Complexity: O(nlogk) time and O(k) space.
 * */
package leetcode;

import java.util.LinkedList;

public class SlidingWindowMax {
	/*
	 * Here we first calculate the max value in the first window.
	 * Then we move the window. While moving, we check if we lose the max value.
	 * If we lose it, then we need to find the max from the new window
	 * If we do not lose it, then new max is Math.max(max, nums[end])
	 * 
	 * Complexity: O(nk) time and O(1) space
	 * */
	public static int[] maxSlidingWindow(int[] nums, int k) {
		int start=0, end=k-1;
		int max = getMax(nums,start,end);
		//stores max for each window
		int[]maxes = new int[nums.length-k+1];
		int i=0;
		maxes[i]=max;
		while(end < nums.length) {
			start++;end++;i++;
			if(end < nums.length) {
				if(nums[start-1] == max) {		//if we lose max on moving window start
					max = getMax(nums, start, end);
				}else {							//else we need to find for this window
					max = Math.max(max, nums[end]);
				}
				maxes[i] = max;
			}
		}
		return maxes;
    }
	
	//////////////////////////////////////////////////////////////////////////
	//Efficient method using deque:
	/*
	 * Here we use a deque of size k. The idea is to keep indices of elements which are in decreasing order
	 * When we are at index i, at first we remove indices from deque which are not part of window. Removal will of course be 
	 * from the start of the list
	 * After that we remove indices whose elements are < current element. We remove from the end of the list.
	 * The max value is located at the head of the dequeue.
	 * 
	 * Complexity: O(n) time and O(k) space
	 * */
	public static int[] maxSlidingWindowEfficient(int[] nums, int k) {
		int n = nums.length;
		int []maxes = new int[n-k+1];
		int pos = 0;
		LinkedList<Integer> list = new LinkedList();
		//populate for first window
		for(int i=0; i<k; i++) {
			while(!list.isEmpty() && nums[list.getLast()] < nums[i]) {
				list.removeLast();
			}
			list.addLast(i);
		}
		maxes[pos] = nums[list.getFirst()];
		for(int i=k; i< n; i++) {
			//remove indices from head which are not part of current window
			while(!list.isEmpty() && list.getFirst() < (i-k+1)) {
				list.removeFirst();
			}
			//remove indices whose elements are < current element
			while(!list.isEmpty() && nums[list.getLast()] < nums[i]) {
				list.removeLast();
			}
			//add index of current element
			list.addLast(i);
			//pick up max element
			maxes[++pos] = nums[list.getFirst()];
		}
		return maxes;
	}
	private static int getMax(int[]nums, int start, int end) {
		int max = Integer.MIN_VALUE;
		for(int i=start; i<=end; i++) {
			max  = Math.max(max, nums[i]);
		}
		return max;
	}
	public static void main(String[] args) {
		int[] nums = {1,3,-1,-3,5,3,6,7};
		//int[] nums = {9,6,11,8,10,5,14,13,93,14};
		int k=3;
		//int []res = maxSlidingWindow(nums, k);
		int []res = maxSlidingWindowEfficient(nums, k);
		for(int num : res) {
			System.out.println(num);
		}

	}

}
