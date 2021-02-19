//https://leetcode.com/problems/count-of-smaller-numbers-after-self/
/*
Solution: Using mergesort
 While merging when we find that the element in the left part is  <= element in the right part, then we increment
 a variable which indicates that this element in the left part need a swap when we are sorting
 
 Complexity: O(nlogn)time in worst case
 			 and O(n) space
 * */

package leetcode;

import java.util.ArrayList;
import java.util.List;


public class CountSmallerAfterSelf4 {
	public List<Integer> countSmaller(int[] nums) {
		if(nums == null || nums.length == 0) {
			return new ArrayList<>();
		}
		int n = nums.length;
		int[]originalIndex = new int[n];
		for(int i=0; i<n; i++) {
			originalIndex[i] = i;
		}
		int[]count = new int[n];
		sort(nums, originalIndex, 0, nums.length-1, count);
		List<Integer> result = new ArrayList<Integer>();
		for(int i=0; i<n; i++) {
			result.add(count[i]);
		}
		return result;
		
	}
	private void sort(int[]nums, int[] originalIndex, int l, int r, int[] count) {
		if(l < r) {
			int m = l + (r-l)/2;
			sort(nums, originalIndex, l, m, count);
			sort(nums, originalIndex, m+1, r, count);
			merge(nums, originalIndex, l, m, r, count);
		}
	}
	
	//merges two sorted array. We use the index of element while merging.
	//example: merge {5,8} and {1,3,6} => rightCount for 5 is 2 and for 8 it will be 3
	private void merge(int[] nums, int[] originalIndex, int l, int m, int r, int[] count) {
		int lArrSize = m-l+1;
		int rArrSize = r-m;
		int[] lArr = new int[lArrSize];		//left temporary subarray
		int[] rArr = new int[rArrSize];     //right temporary subarray
		
		//copy the left part
		for(int i=0; i<lArrSize; i++) {
			lArr[i] = originalIndex[l+i];
		}
		
		//copy the right part
		for(int i=0; i<rArrSize; i++) {
			rArr[i] = originalIndex[m+1+i];
		}
		
		int k=l, rightCount=0, i=0, j=0;
		
		while(i<lArrSize && j< rArrSize) {
			//element in left part is <= element in right part
			if(nums[lArr[i]] <= nums[rArr[j]]) {
				originalIndex[k] = lArr[i];				//merging
				count[lArr[i]] += rightCount;
				i++;
			}else {
				originalIndex[k] = rArr[j];				//merging
				rightCount++;							//increment count of smaller element for current
				j++;
			}
			k++;
		}
		//process remaining elements in left subarray
		while(i < lArrSize) {
			originalIndex[k] = lArr[i];
			count[lArr[i]] += rightCount;
			i++;
			k++;
		}
		//process remaining elements in right subarray
		while(j < rArrSize) {
			originalIndex[k] = rArr[j];
			j++;
			k++;
		}
	}
	public static void main(String[] args) {
		int[] nums = {5,2,1,6,1};
		//int[] nums = {5,5};
		CountSmallerAfterSelf4 obj = new CountSmallerAfterSelf4();
		List<Integer> result = obj.countSmaller(nums);
		for(int elt : result) {
			System.out.println(elt);
		}

	}

}
