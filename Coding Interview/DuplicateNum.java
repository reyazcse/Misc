//https://leetcode.com/problems/find-the-duplicate-number/
/*
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one duplicate number in nums, return this duplicate number.

Example 1:

Input: nums = [1,3,4,2,2]
Output: 2
Example 2:

Input: nums = [3,1,3,4,2]
Output: 3
 * */

/*
Suppose we have 1-based array indexing.
	We place nums[i] to an index nums[i].
	We continue swapping till we find that the current position has been fixed i.e. nums[i] is same as index of cell
	While doing swaps, we check to see if the number we swapped is same as the replacement. If it is, then it means this number is the duplicate.
	Note that there is only one number which is repeating.
	
	For example:
	array values: 3  1  2  2 
	array index:  1  2  3  4
	array values: 2  1  3  2  //after swapping 3 and 2
	          	  1  2  3  2  //after swapping 2 and 1
	          	  		  	  //now 1 is fixed, we move to 2 which is also fixed.We move to 3 which is also fixed
	          	  		  	  When we are at the end, we swap 2 and place it at index 2. We then find the new 2 at end
	          	  		  	  is same. Hence 2 is the duplicate number.

O(N) time and O(1) space complexity.	          	  		  
 * */
package leetcode;

public class DuplicateNum {
	
	public static int findDuplicate(int[] nums) {
		for(int i=0; i<nums.length; i++) {
			int correctPos  = nums[i]-1;  //since array is 0 based indexing, we do -1
			
			while(nums[i] != i+1) {			//while this position is not fixed. i.e. num[i] = i+1
				int temp = nums[correctPos];
				if(temp == nums[i])
					return temp;            //if after swap, we find that we get same number, then this num is repeating.
				nums[correctPos] = nums[i];
				nums[i] = temp;
				correctPos = nums[i]-1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int [] nums = {2,2,2,2,2};
		System.out.println(findDuplicate(nums));
		

	}

}
//Note that this problem cannot be solved using xor method as intput array can be like this: [2 2 2 2 2]
//Another solution based on tortoise hare model. See solution on leetcode for logic.Here is solutin:
/*

class Solution {
  public int findDuplicate(int[] nums) {
    // Find the intersection point of the two runners.
    int tortoise = nums[0];
    int hare = nums[0];
    do {
      tortoise = nums[tortoise];
      hare = nums[nums[hare]];
    } while (tortoise != hare);

    // Find the "entrance" to the cycle.
    tortoise = nums[0];
    while (tortoise != hare) {
      tortoise = nums[tortoise];
      hare = nums[hare];
    }

    return hare;
  }
}
 * */
