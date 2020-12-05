//https://leetcode.com/problems/single-number-iii/
/*
Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
Find the two elements that appear only once. You can return the answer in any order.
 * */

/*
Solution: Using bits
		  Let the two numbers be a and b.
		  Xor of all elements will be (a xor b) since twice occurring elements will cancel out.
		  In this xor, find any set bit. We find the last set bit.
		  Then divide the elements into two groups: group1 has elements which has bit set and group2 has elements having bit not set
		  Finally a or b will be in each of the groups
		  
		  Complexity: O(n) time and O(1) space
 * */
package leetcode;

public class SingleNumberIII {
	public int[] singleNumber(int[] nums) {
		if(nums.length <= 2) {
			return nums;
		}

		int sum = 0;
		for(int i=0; i<nums.length; i++) {
			sum ^= nums[i];
		}

		//last set bit of xor
		int last_set_bit = sum & -sum;

		//put into two groups on the basis of last_set_bit set and not set
		int sum1 = 0, sum2=0;

		for(int i=0; i<nums.length; i++) {
			if((nums[i] & last_set_bit) > 0) {
				sum1 ^= nums[i];
			}else {
				sum2 ^= nums[i];
			}
		}

		int[] result = new int[2];
		result[0] = sum1;
		result[1] = sum2;
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
