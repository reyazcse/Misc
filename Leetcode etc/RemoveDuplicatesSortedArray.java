//https://leetcode.com/problems/remove-duplicates-from-sorted-array
//Given a sorted array nums, remove the duplicates in-place such that each element appears only once and returns the new length.
package leetcode;

public class RemoveDuplicatesSortedArray {
	public int removeDuplicates(int[] nums) {
		int i=0; 
		//idea is from partition algo of quicksort
		for(int j=1; j<nums.length; j++) {
			if(nums[j] != nums[i]) {
				i++;
				nums[i] = nums[j];
			}
		}
		return i+1;  //new length
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
