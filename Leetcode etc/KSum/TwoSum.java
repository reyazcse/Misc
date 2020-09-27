//https://leetcode.com/problems/two-sum/
package leetcode;

import java.util.HashMap;

public class TwoSum {
	 public int[] twoSum(int[] nums, int target) {
	        int[]result = new int[2];
	        HashMap<Integer, Integer> map = new HashMap<>();
	        for(int i=0; i<nums.length; i++) {
	            if(map.containsKey(nums[i])) {
	                result[0] = map.get(nums[i]);
	                result[1] = i;
	                return result;
	            }
	            map.put(target-nums[i], i);
	        }
	        return result;
	        
	    }

	public static void main(String[] args) {
		int []nums = {2,7,11,15};
		TwoSum obj = new TwoSum();
		int[] res = obj.twoSum(nums, 9);
		System.out.println(res[0] + "  " + res[1]);
		

	}

}
