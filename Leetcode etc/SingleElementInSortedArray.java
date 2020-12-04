//https://leetcode.com/problems/single-element-in-a-sorted-array/
/*
Question:
	You are given a sorted array consisting of only integers where every element appears exactly twice, 
	except for one element which appears exactly once. 
	Find this single element that appears only once.
	
 * */

/*
Solution:
	Consider: 
	array : 1 2 2 3 3
	index : 0 1 2 3 4
	
	It can also be given like 
	1 1 2 3 3	: array	
	0 1 2 3 4   : index
	  
	1 1 2 2 3   : array
	0 1 2 3 4 	: index
	
	We observe that:
	Elements at right side of single element start at odd index and end at even index
	Elements at left side of single element start at even index and end at odd index
	Also size of array will be odd.
	
	So find the mid element. 
	If mid is odd, it means (mid-1) >= 0
	If mid is even, we should compare with mid+1 element as mid can be 0.
	
	If mid is even
		If nums[mid] == nums[mid+1]
		=> element at even index == element at odd index
		=> start of the element is at even index and end is at odd index
		=> hence go right: lo = mid+2
	   else go left: hi = mid;
	
	If mid is odd
		If nums[mid] == nums[mid-1]
		=> element at odd index == element at even index
		=> start of element is at even index and end is at odd index
		hence go right;
		else go left; 
	
 * */

package leetcode;

public class SingleElementInSortedArray {
	public int singleNonDuplicate(int[] nums) {
		int lo=0, hi=nums.length-1;
		while(lo < hi) {
			int mid = lo + (hi-lo)/2;
			if((mid & 1) == 0) {			//if mid is even
				if(nums[mid] == nums[mid+1]) {		//start at even and end at odd, hence go right
					lo = mid+2;
				}else {
					hi = mid;
				}
			}else {							//mid is odd
				if(nums[mid] == nums[mid-1]) {			//start at even and end at odd, hence go right
					lo = mid+1;
				}else {
					hi = mid-1;
				}
			}
		}
		return nums[lo];
	}

	public static void main(String[] args) {
		int []nums = {1,2,2,4,4};
		System.out.println(new SingleElementInSortedArray().singleNonDuplicate(nums));

	}

}
