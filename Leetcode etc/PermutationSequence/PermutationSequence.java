//https://leetcode.com/problems/permutation-sequence/
/*
The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
Given n and k, return the kth permutation sequence.

Example 1:

Input: n = 3, k = 3
Output: "213"

 * */

/*
Solution:
	Here we calculate the permutation till we get k permutations.
	We maintain a global count of permutations so far.
	This solution is not efficient as in worst case when k=n!, complexity will be n!
	
 * */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
	public String getPermutation(int n, int k) {
		int[] nums = new int[n];										//create array digits which will form the permutation
		for(int i=0; i<n; i++) {	
			nums[i] = i+1;
		}
		boolean[] used = new boolean[n];
		List<Integer> current = new ArrayList<>();						//permutation formed so far
		Counter cntr = new Counter();									//global counter across all recursive calls
		int ans = permutation(nums, k, used, current, cntr);
		return String.valueOf(ans);
	}
	
	/*
	 * Returns the kth permutation.
	 * used = boolean array to keep track of used digits in the 'nums' array
	 * current = current permutation in progress
	 * cntr = used to keep track of number of permutations globally
	 * 
	 * */
	
	private int permutation(int[] nums, int k, boolean[] used, List<Integer> current, Counter cntr) {
		if(current.size() == nums.length) {				//we got a permutation
			cntr.count++;								//increment global count
			if(cntr.count == k) {						
				return getNumber(current);				//return number form of digits in 'current' list
				
			}
		}
		for(int i = 0; i<nums.length; i++) {
			if(!used[i]) {
				current.add(nums[i]);
				used[i] = true;
				
				//recurse
				int curr_ans = permutation(nums, k, used, current, cntr);
				if(curr_ans != -1) {					//if we already got the required number of permutations, then no need 
					return curr_ans;					//to recurse further	
				}
				
				//backtrack
				current.remove(current.size()-1);
				used[i] = false;
			}
		}
		return -1;										//still we need to form more permutations
	}
	
	//returns the number represented by the digits in 'current' list
	private int getNumber(List<Integer> current) {
		int sum=0, multiplier=1;
		for(int i= current.size()-1; i>=0; i--) {
			int digit = current.get(i);
			sum += digit * multiplier;
			multiplier *= 10;
		}
		return sum;
	}
	
	private class Counter{
		public int count = 0;
	}
	public static void main(String[] args) {
		PermutationSequence ob = new PermutationSequence();
		System.out.println(ob.getPermutation(3, 3));;

	}

}
