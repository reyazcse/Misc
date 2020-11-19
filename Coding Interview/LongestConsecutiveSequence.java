//https://leetcode.com/problems/longest-consecutive-sequence/
/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * */

/*
Solution 1:
	O(N^2) time and O(N) space:
	--------------------------
	Put all the numbers in a set.
	Then iterate on the array and for each num in the array, we keep checking while (num+1) is
	also present. While doing, keep incrementing num and current streak by 1.
	After we are done for a num, update the longest streak found so far.
	We do this for all the elements. 
	In the worst case, time is O(N^2) for example for array = {1,2,3,4,5,6}
	
Solution 2:
	O(N) time and O(N) space:
	-------------------------
	We do similar approach as above. But when we come at a number in the array, we first check
	if (number-1) is not there in the set. This ensures that we do not repeat when we are at 2,3,4,5,6 in the above example
	
	Below code is the implementation.
	
 * */
package leetcode;

import java.util.HashSet;

public class LongestConsecutiveSequence {
	public static int longestConsecutive(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for(int num : nums) {
			set.add(num);
		}
		int maxStreak = 0;
		for(int num : nums) {
			if(!set.contains(num-1)) {			//since num has been counted while dealing with num-1. This step is very IMPORTANT
				int currentStreak = 1;
				int currentNum = num;
				while(set.contains(currentNum+1)) {
					currentStreak++;
					currentNum++;
				}
				maxStreak = Math.max(currentStreak, maxStreak);
			}
			
		}
		return maxStreak;
	}

	public static void main(String[] args) {
		int[] nums = {100, 3, 1, 2, 40};
		System.out.println(longestConsecutive(nums));
		

	}

}
