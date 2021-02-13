//https://leetcode.com/problems/first-missing-positive/
//Given an unsorted integer array, find the smallest missing positive integer.

package misc;

import java.util.HashSet;

public class FirstMissingPositive {
	//O(N) time and O(N)space
	public int findMissing(int[] nums) {
		HashSet<Integer> set  = new HashSet<>();
		//put all the elements in set
		for(int num : nums) {
			set.add(num);
		}
		int i=1;
		//check which value is not there in the set.
		for(i=1; i<=nums.length; i++) {  // we go till nums.length since we can have nums[] = {1,2,3}
			if(!set.contains(i)) {
				return i;
			}
		}
		return i;
	}
	
	//Here we swap the element with another one whose index = element
	/*
	 index: 1  2  3  4
	 value: 3  4  7  1    //swap 3 with 7 as val 3 != index 1
	        7  4  3  1    //swap 4 with 1 as val 4 != index 2 
	        7  1  3  4    //swap 1 with 7 ......
	        1  7  3  4    //no swaps after this
	 Then we iterate and return the first index which is not equal to value.
	 
	 In our actual solution, we use 0 based indexing and hence for each element, its correct pos
	 is given by element-1
	 * */
	public int findMissingOptimized(int [] nums) {
		for(int i=0; i<nums.length; i++) {
			int correcPos = nums[i]-1;         //position where this element has to swapped
			while(nums[i]>=1 && nums[i] <= nums.length && nums[i] != nums[correcPos]) { //if(nums[i] == 1 then it has to put at index 0, and nums[i] == arr.length has to be put at arr.length-1 index
				int temp = nums[i];
				nums[i] = nums[correcPos];
				nums[correcPos] = temp;
				correcPos = nums[i] - 1;   //update correcPos for the new element now at i
			}
		}
		
		for(int i=0; i<nums.length; i++) {
			if(nums[i] != i+1) {
				return i+1;					//i+1 as we do 0 based indexing
			}
		}
		return nums.length +1;							//works for arrays like {1,2,3,4}
	}
	public static void main(String[] args) {
		int[] nums = {1,2,3,4};
		//int[] nums = {-3,-4,-7,-1};
		//int[] nums = {3,4,7,1};
		//int [] nums = {1,2,0};
		
		FirstMissingPositive obj = new FirstMissingPositive();
		System.out.println(obj.findMissingOptimized(nums));

	}

}
