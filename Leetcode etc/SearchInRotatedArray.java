//https://leetcode.com/problems/search-in-rotated-sorted-array/
/*
Solution:
	Using binary search.
	Handle duplicates also (though as per problem no duplicates).
	
	Idea:
		Once we get middle element, compare it with leftmost and rightmost element.
		Only one of left or right part will be normally sorted, the other will have inflection point.
 * */
package leetcode;

public class SearchInRotatedArray {
	public int search(int[] nums, int target) {
		return bin_search(nums, 0, nums.length-1, target);
	}
	public int bin_search(int[] nums, int lo, int hi, int target) {
		if(lo > hi) {
			return -1;
		}       
		int ans = -1;
		int mid = lo + (hi-lo)/2;
		if(nums[mid] == target) {
			return mid;
		}else if(nums[lo] < nums[mid]){								//left part is normally sorted
			if(target >= nums[lo] && target < nums[mid]){
				ans = bin_search(nums, lo, mid-1, target);
			}else{
				ans = bin_search(nums, mid+1, hi, target);
			}
		}else if(nums[hi] > nums[mid]) {							//right part is normally sorted
			if(target <= nums[hi] && target > nums[mid]){
				ans = bin_search(nums, mid+1, hi, target);
			}else {
				ans = bin_search(nums, lo, mid-1, target);
			}

		}else {																		//handling duplicates
			if(nums[lo] == nums[mid] && nums[hi] == nums[mid]) {					//search both sides

				int x = bin_search(nums, lo, mid-1, target);
				if(x == -1){
					x = bin_search(nums, mid+1, hi, target);
				}
				ans = x;

			}else if(nums[lo] == nums[mid]) {					
				ans = bin_search(nums, mid+1, hi, target);			//search right side
			}else{
				ans = bin_search(nums, lo, mid-1, target);			//search left side	
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		SearchInRotatedArray ob = new SearchInRotatedArray();
		int [] nums = {3,1};
		int target = 1;
		System.out.println(ob.search(nums, target));

	}

}
