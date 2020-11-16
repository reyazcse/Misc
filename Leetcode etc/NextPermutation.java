//https://leetcode.com/problems/next-permutation/
/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
 * */

/*
Solution:
Algorithm:
	Start from right and find the first element which is less than its right adjacent neighbour.
	Let the index be 'ind'
	We need to swap it with some number towards its right.Again start from right end of array
	and find the first element which is larger then the current found element.
	Swap this element with element at position 'ind'.
	After this we have to reverse the array from 'ind'+1 till end and we are done.
	
Let's see how it works::
nums[] = {1 3 5 4 2}
So we see an increasing sequence from right: 5 4 3 2
We find 3 which is first decreasing element and swap it with the element just more than 3 from right end:
{1 4 5 3 2}  : after swapping 3 with 4.

Now we observe that we can decrease this part: 5 3 2.
Since this part is increasing, the least we will get is by reversing : 2 3 5
So our final answer is 1 4 2 3 5 

Complexity: O(n) time and O(1) space
	
 * */
package leetcode;

public class NextPermutation {
	public void nextPermutation(int[] nums) {
		if(nums == null || nums.length == 0) {
			return;
		}
		//index of first decreasing number
		int ind = -1;
		for(int i=nums.length-2; i>=0; i--) {
			if(nums[i] < nums[i+1]) {
				ind = i;
				break;
			}
		}
		//if a decreasing number is found, then we need to swap again
		if(ind != -1) {
			for(int i=nums.length-1; i>ind; i--) {
				if(nums[i] > nums[ind]) {		//element found just larger than nums[ind]
					int tmp = nums[ind];
					nums[ind] = nums[i];
					nums[i] = tmp;
					break;
				}
			}
			swapAll(nums, ind+1);
			
		}else {		//no decreasing number is found. for example if we have {4 3 2 1}, then reverse the arrays to get least number
			swapAll(nums, 0);
		}
		
	}

	private void swapAll(int [] nums, int start) {
		int i=start, j = nums.length-1;
		while(i < j) {
			int tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
			i++;j--;
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
