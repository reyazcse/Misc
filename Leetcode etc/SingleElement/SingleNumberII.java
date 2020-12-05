//https://leetcode.com/problems/single-number-ii/
/*
Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.

Example 1:

Input: nums = [2,2,3,2]
Output: 3

 * */

//O(n) time complexity
//Note there is another O(n) optimized solution but that is not intuitive
package leetcode;

public class SingleNumberII {
	public int singleNumber(int[] nums) {
		int[] bits_count = new int[32];
		int mask = 1;
		
		//calculate number of bits for each number
		for(int num : nums) {
			for(int i=0; i<32; i++) {
				if(((mask << i) & num) != 0 ) {
					bits_count[i]++;
				}
			}
		}
		
		//find out the single number using the bits count
		int result = 0;
		int multiplier = 1;
		for(int i=0; i<32; i++) {
			bits_count[i] %= 3;
			if(bits_count[i] == 1) 	{
				result = result +  multiplier;
			}
			multiplier = multiplier *2;
			
		}		
		
		
		
		return result;
	}
	
//Another way to find the single number:
/*
	int result = 0;
	for(int i=31; i>=0; i--) {
		bits_count[i] %= 3;
		if(bits_count[i] == 1) {
			result = result+1;
		}
		if(i == 0) {
			break;
		}
		result = (result << 1);
	}
	
	return result;	

*/
	
	public static void main(String []args) {
		//int [] nums = {1,3,3,3};
		int [] nums = {-2,-2,1,1,4,1,4,4,-4,-2};
		SingleNumberII ob = new SingleNumberII();
		System.out.println(ob.singleNumber(nums));
	}
	

}
