//https://leetcode.com/problems/continuous-subarray-sum/
/*
Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.

Example 2:

Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.

 * */


package leetcode;

import java.util.HashMap;

public class ContinuousSubarraySum {
	/*
	 * 
	 * The idea is similar to the problem SubarraySumsDivisibleByK
	 * sum(i,j) is divisible by k when sum(0,j) and sum(0,i-1) have same remainder
	 * 
	 * */
	public boolean checkSubarraySum2 (int[] nums, int k) {

		int sum = 0;
		HashMap<Integer, Integer> map = new HashMap <>();
		map.put (0, -1);									//takes care when sum from index 0 till current is divisible by k and size >=2

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			int rem = k != 0 ? sum % k : sum;
			if(map.containsKey(rem) && (i - map.get(rem)) >= 2) {  //sum of elements from index ( map.get(rem) + 1 ) till i is divisible by k
				return true;
			}
			map.put(rem, map.getOrDefault(rem, i));
		}

		return false;
	}


	/*
	 * We can separately handle case when k==0
	 * 
	 * */

	public boolean checkSubarraySum3 (int[] nums, int k) {
		if(k == 0) {
			for(int i=1; i<nums.length; i++) {
				if(nums[i] == 0 && nums[i-1] == 0) {	//sum of two adjacent elements will be 0
					return true;
				}
			}
			return false;
		}

		int sum = 0;
		HashMap<Integer, Integer> map = new HashMap <>();
		map.put (0, -1);									//takes care when sum from index 0 till current is divisible by k and size >=2

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			int rem = sum % k;
			if(map.containsKey(rem) && (i - map.get(rem)) >= 2) {  //sum of elements from index ( map.get(rem) + 1 ) till i is divisible by k
				return true;
			}
			map.put(rem, map.getOrDefault(rem, i));
		}

		return false;
	}



	//Instead of putting remainder in map and the calculating cumulative sum each time,
	//We calculate cumulative sum using remainder as shown below:
	//This code is from leetcode discuss

	public boolean checkSubarraySum4(int[] nums, int k) {
		int sum = 0;
		if(nums == null || nums.length==0) return false;
		HashMap<Integer, Integer> map = new HashMap<>();

		for(int i=0; i<nums.length; i++){
			sum+=nums[i];
			if(k!=0) sum%=k;

			if(sum == 0 && i>0) return true;

			if(map.containsKey(sum)){
				if(map.get(sum) < (i-1)) return true;
			}
			else {
				map.put(sum, i);
			}
		}
		return false;
	}



	//O(n^2) time
	//ACCEPTED
	public boolean checkSubarraySum(int[] nums, int k) {
		for(int i=0; i<nums.length; i++) {
			int sum = nums[i];
			for(int j=i+1; j<nums.length; j++) {
				sum += nums[j];
				if(sum == 0) {								//sum of at least two element is 0, then return true
					return true;					
				}
				if(k != 0 && sum % k == 0) {
					return true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
		ContinuousSubarraySum ob  = new ContinuousSubarraySum();
		int [] nums = {5,5};
		int k = 5;
		System.out.println(ob.checkSubarraySum2(nums, k));
	}

}
