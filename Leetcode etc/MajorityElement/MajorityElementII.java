//https://leetcode.com/problems/majority-element-ii/
/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Follow-up: Could you solve the problem in linear time and in O(1) space?

Example 1:

Input: nums = [3,2,3]
Output: [3]
 * */

/*
Solution:
	We use voting algorithm for finding two candidates with most appearances.
	After finding the candidates we need to find their count and check if their count is more than n/3
	
	We need to check the count since we can have following types of inputs which fails if we do not count actual appearances:
	1 1 2 2 3 3 4: no majority element
	
	Complexity: O(n) time and O(1) space
 * */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {
	public List<Integer> majorityElement(int[] nums) {
		int candidate1 = -1, candidate2 = -1, count1 = 0, count2 = 0;
		for(int i=0; i<nums.length; i++) {
			if(nums[i] == candidate1) {
				count1++;
			}else if (nums[i] == candidate2) {
				count2++;
			}else if (count1 == 0) {
				candidate1 = nums[i];
				count1 = 1;
			}else if (count2 == 0) {
				candidate2 = nums[i];
				count2 = 1;
			}else {
				count1--;
				count2--;
			}
		}
		count1 = 0; count2=0;
		for(int i=0; i<nums.length; i++) {
			if(nums[i] == candidate1) {
				count1++;
			}else if (nums[i] == candidate2) {
				count2++;
			}
		}
		List<Integer> result = new ArrayList<>();
		if(count1 > nums.length/3) {
			result.add(candidate1);
		}
		if(count2 > nums.length/3) {
			result.add(candidate2);
		}
		return result;
	}
	public static void main(String[] args) {
		int []nums = {1,1,2,2,3,3,4};
		MajorityElementII ob = new MajorityElementII();
		ob.majorityElement(nums);
		
	}

}
