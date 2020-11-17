//https://leetcode.com/problems/majority-element/
/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
 * */

//Solution: There are many ways to solve this(Refer leetcode solution). Two ways are given below.

package leetcode;

public class MajorityElement {
	
	/************************Divide and conquer method************************************************************/
	
	//O(n log n) time and O(log n) space for recursive stack
	public int majorityElement(int[] nums) {
		return utl(nums, 0, nums.length-1);
	}
	
	private int utl(int[] nums, int l, int r) {
		if(l == r) {
			return nums[l];
		}
		int mid = l + (r-l)/2;
		
		//get majority element in left and right halves
		int left = utl(nums, l, mid);
		int right = utl(nums, mid+1, r);
		
		//if majority element in both halves is same, then return that element
		if(left == right) {
			return left;
		}
		//get count of left and right elements from l to r
		int leftCount = getCountInRange(nums, left, l, r);
		int rightCount = getCountInRange(nums, right, l, r);
		
		//return majority element of this half: l to r
		return leftCount > rightCount ? left : right;
	}
	
	private int getCountInRange(int[]nums, int target, int start, int end) {
		int count = 0;
		for(int i=start; i<=end; i++) {
			if(target == nums[i]) {
				count++;
			}
		}
		return count;
	}
	
	
	/**************************Voting algorithm***********************************************************************/
	//O(n) time and O(1) space
	public int majorityElementVoting(int[] nums) {
		int candidate = nums[0];
		int count = 1;
		for(int i=1; i<nums.length; i++) {
			if(count == 0) {
				candidate = nums[i];
			}
			if(nums[i] == candidate) {
				count++;
			}else {
				count--;
			}
		}
		return candidate;
				
	}
	
	/**********************Voting algorithm another implementation***************************************************/
	
	//O(n) time and O(1) space
		public int majorityElementVoting2(int[] nums) {
			int candidate = -1;  //random random number can be assigned on initiation
			int count = 0;
			for(int i=0; i<nums.length; i++) {
				
				if(nums[i] == candidate) {
					count++;
				}else if (count == 0){
					count++;
					candidate = nums[i];
				}else {
					count--;
				}
			}
			return candidate;
					
		}
	
	public static void main(String[] args) {
		int[]nums = {3,2,3};
		MajorityElement ob = new MajorityElement();
		System.out.println(ob.majorityElement(nums));

	}

}
