//src: https://leetcode.com/problems/find-peak-element/
/*
	A peak element is an element that is greater than its neighbors.
	Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
	The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
	You may imagine that nums[-1] = nums[n] = -∞

Solution explanation: https://www.youtube.com/watch?v=L7gNay1c4ak  | errichto
	When nums[mid-1] is greater than nums[mid], it means there will be a peak in left part as we have -inf at nums[-1]
	When nums[mid-1] is less than nums[mid], it means nums[mid] can be peak since we have -inf at nums[n] OR another value larger than
	nums[mid] can be peak.
 * */

package leetcode;

public class FindPeakElement {
	//O(logN)
	public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int L = 0, R = nums.length-1;
        while(L < R) {
        	int mid = L + 1 +  (R-L-1)/2;  //(mid = L+R+1 basically as when we have two elements, mid is the rightmost one)
        	if(nums[mid-1] > nums[mid]) {
        		R = mid-1;
        	}else 
        		L = mid;
        }
        //L==R
        return L;
        
        
    }

	public static void main(String[] args) {
		int[] nums = {1,2,3,1};
		FindPeakElement obj = new FindPeakElement();
		System.out.println(obj.findPeakElement(nums));
	}

}
