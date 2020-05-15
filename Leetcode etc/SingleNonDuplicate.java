//src: https://leetcode.com/problems/single-element-in-a-sorted-array/
/*
Question:
	You are given a sorted array consisting of only integers where every element appears exactly twice, 
	except for one element which appears exactly once. 
	Find this single element that appears only once.
	
Solution:
	We use binary search.
	When mid divides the array into two equal parts, we need to go to that part whose element adjacent to mid is equal to mid element
	So basically if mid is odd, then it divides array into two equal parts and since adjacent element is equal, we have let's say
	4 elements in a part of size 4, but one element is already matched to mid element. So the unique element is in this part as 
	elements occur exactly twice
 * */

package leetcode;

public class SingleNonDuplicate {
	 public int singleNonDuplicate(int[] nums) {
	        int n = nums.length;
	        int lo = 0;
	        int hi = n-1;
	        while(lo < hi) {
	            int mid = lo + (hi-lo)/2;
	            if (mid %2 == 0) {  //mid divides array into two equal parts
	            	if((mid ==0 || nums[mid-1] != nums[mid]) && (mid == n-1 || nums[mid] != nums[mid+1])) {
	            		return nums[mid];
	            	}
	            	else if(mid == 0 || nums[mid-1]==nums[mid]) //go left 
	            		hi = mid-1;
	            	else 
	            		lo = mid+1;
	            } else {
	            	if((mid ==0 || nums[mid-1] != nums[mid]) && (mid == n-1 || nums[mid] != nums[mid+1])) {
	            		return nums[mid];
	            	}
	            	else if(mid == 0 || nums[mid-1]!=nums[mid])
	            		hi = mid-1;
	            	else 
	            		lo = mid+1;
	            }
	              
	        }
	        return nums[lo];
	    }

	public static void main(String[] args) {
		int []nums = {1,1,2,3,3,4,4,8,8};
		System.out.println(new SingleNonDuplicate().singleNonDuplicate(nums));

	}

}
