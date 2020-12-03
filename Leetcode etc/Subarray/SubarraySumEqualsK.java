//https://leetcode.com/problems/subarray-sum-equals-k/
/*
Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
 * */
package leetcode;

import java.util.HashMap;

public class SubarraySumEqualsK {
	/*
	 * Method 1: calculate sum[i...j]  = sum [0...j] - sum [0...i] + nums[i]
	 * O(n^2) time and O(n) space
	 * 
	 * */
	
	//gives TLE
	public int subarraySum_1(int[] nums, int k) {
		int sum=0;
		int count=0;
		HashMap<Pair, Integer> map = new HashMap<>();
		
		//store sum[0...j] where j iterates till end of array.
		//also check that if we get any sum as k, then increment count 
		int i=0;
		for(int j=0; j<nums.length; j++) {
			sum += nums[j];
			if(sum == k) {
				count++;
			}
			Pair p = new Pair(i, j);
			map.put(p, sum);
		}
		
		//we start from 1 since sums like sum[0...j] is already taken care of above while populating the map
		for(i=1; i<nums.length; i++) {
			for(int j=i; j<nums.length; j++) {
				Pair p1 = new Pair(0, i);
				Pair p2 = new Pair(0, j);
				sum = map.get(p2) - map.get(p1) + nums[i];  //sum[i...j] = sum[0...j] - sum[0...i] + nums[i]
				if(sum == k) {
					count++;
				}
			}
		}
		return count;

	}
	

	//stores a pair of indices
	private class Pair{
		public int start;
		public int end;
		public Pair(int s, int e) {
			this.start = s;
			this.end = e;
		}

		public int hashCode() {
			return Integer.valueOf(start).hashCode() + Integer.valueOf(end).hashCode();
		}

		public boolean equals(Object o) {
			if(o == null || !(o instanceof Pair)) {
				return false;
			}
			Pair other = (Pair)o;
			if(start == other.start && end == other.end) {
				return true;
			}
			return false;
		}
	}
	
	
	/*
	 * Method 2: Using cumulative sum. This is similar as above.
	 * 		sum[i] stores sum of elements from 0 till i
	 * 		Then sum[i...j] = sum[j] - sum[i] + nums[i]
	 * 
	 * O(n^2) time and O(n) time
	 * */
	
	//Accepted but not good time
	public int subarraySum_2(int[] nums, int k) {
		int[]sum = new int[nums.length];
		
		//store cumulative sum
		sum[0] = nums[0];
		for(int i=1; i<nums.length; i++) {
			sum[i] = sum[i-1] + nums[i]; 
		}
		
		int count=0;
		//calculate sum for each subarray
		for(int i=0; i<nums.length; i++) {
			for(int j=i; j<nums.length; j++) {
				int subarray_sum = sum[j] - sum[i] + nums[i];
				if(subarray_sum == k) {
					count++;
				}
			}
		}
		return count;
	}
	
	
	/*
	 * Method 3: Using cumulative sum. This is similar as above but we use constant space
	 * 		Here calculate cumulative sum inside the first loop for each sub array
	 * 
	 * 		O(n^2) time and O(1) time
	 * */
	
	
	//Accepted but not good time
	public int subarraySum_3(int[] nums, int k) {
		int count=0;
		
		//calculate sum for each sub array in cumulative way
		for(int i=0; i<nums.length; i++) {
			int sum=0;
			for(int j=i; j<nums.length; j++) {
				sum += nums[j];
				if(sum == k) {
					count++;
				}
			}
		}
		return count;
	}
	
	
	
	/*
	 * BEST METHOD
	 * Method 4: Using cumulative sum and map.
	 * 		Let's say we have two indices: ...i...j... where i < j
	 * 		Then if cumulative sum at j == cumulative sum at i, it means sum of elements from i+1 till j will be 0
	 * 		We use similar technique. If difference is k, then it means sum of elements from i+1 till j will be k
	 * 
	 * 		So we store cumulative sum and count of its occurrences in a map.
	 * 		Initially we store (0,1) in map. Let's say 3,4,... and k=7. So when we are at 4, cumulative sum is 7
	 * 		and we get 7-k = 0. So we have 1 count
	 * 		
	 * 		
	 * 
	 * 		O(n) time and O(n) time
	 * */
	public int subarraySum(int[] nums, int k) {
		int count=0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0,1);
		
		int sum=0;
		//calculate sum for each sub array in cumulative way
		for(int i=0; i<nums.length; i++) {
			sum += nums[i];
			
			if(map.containsKey(sum - k)) {
				count += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}
	
	
	
	public static void main(String[] args) {
		int [] nums = {-1,-1,1};
		int k=0;
		SubarraySumEqualsK ob = new SubarraySumEqualsK();
		System.out.println(ob.subarraySum_1(nums, k));

	}
	

}
