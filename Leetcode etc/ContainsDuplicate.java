//src: https://leetcode.com/problems/contains-duplicate-ii/
/*
	Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array 
	such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
	
	Input: nums = [1,2,3,1], k = 3
	Output: true
	
Solution:
	We have a map and in the map we keep the nearest index of an element encountered.
	When we find that current element is already in map, we compute diff of indices. IF we get ans we return else we update the index to current index 
 * */
package leetcode;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate {
	
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, Integer> mp = new HashMap<>();		//stores a value and its nearest index
		for(int i=0; i<nums.length; i++) {
			if(mp.containsKey(nums[i])) {			//if this value is already in map, then compute diff of the two indices
				int prevIndex = mp.get(nums[i]);
				if(Math.abs(i-prevIndex) <= k)		
					return true;					//return ans
				mp.put(nums[i], i);					//else store nearest index
			}
			else {
				mp.put(nums[i], i);					//store this num and its index
			}
		}
		return false;
    }

	public static void main(String[] args) {
		int [] nums = {1,2,3,1,2,3};
		int k = 2;
		ContainsDuplicate obj = new ContainsDuplicate();
		System.out.println(obj.containsNearbyDuplicate(nums, k));

	}

}
