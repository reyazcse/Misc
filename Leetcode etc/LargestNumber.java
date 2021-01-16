//https://leetcode.com/problems/largest-number/

//For solution: refer leetcode solution
package leetcode;

import java.util.Arrays;

public class LargestNumber {
	public String largestNumber(int[] nums) {
		String[] strs = new String[nums.length];
		
		for(int i=0; i<nums.length; i++) {
			strs[i] = String.valueOf(nums[i]);
		}
		
		Arrays.sort(strs, (a, b) -> {
			String order1 = a+b;
			String order2 = b+a;
			return order2.compareTo(order1);
		});
		
		String result = "";
		for(String str : strs) {
			result += str;
		}
		if(result.startsWith("0")) {
			return "0";
		}
		return result;
	}
	
	public static void main(String[] args) {
		int [] nums = {01,2};								//this input not is invalid on leetcode
		LargestNumber ob = new LargestNumber();
		System.out.println(ob.largestNumber(nums));
	}

}
