/*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
For example, given nums = [-2, 0, 1, 3], and target = 2.
Return 2. Because there are two triplets which sums are less than 2:
[-2, 0, 1]
[-2, 0, 3]
 * */

//O(n^2) time complexity.
package leetcode;

import java.util.Arrays;

public class ThreeSumSmaller {
	public int threeSumSmaller(int []nums, int target) {
		int totalCount = 0;
		Arrays.sort(nums);
		for(int i=0; i<nums.length-2; i++) {
			int start = i+1;
			int end = nums.length-1;
			while(start < end) {
				int currSum = nums[i] + nums[start] + nums[end];
				if(currSum < target) {
					totalCount += (end-start);    //all values of end from end till start+1 will give less sum only
					start++;
				}else {
					end--;
				}
			}
		}
		return totalCount;
	}
	public static void main(String[] args) {
		int []nums = {-2, 0, 1, 3};
		int target = 2;
		ThreeSumSmaller obj = new ThreeSumSmaller();
		System.out.println(obj.threeSumSmaller(nums, target));

	}

}
