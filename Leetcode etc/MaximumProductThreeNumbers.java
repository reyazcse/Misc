//https://leetcode.com/problems/maximum-product-of-three-numbers/
//Given an integer array nums, find three numbers whose product is maximum and return the maximum product.

/*
Solution:
	Here the three numbers need not be consecutive.
	M1:
		Brute force way is to use three loops and consider each triplet while keeping track of max
	M2:
		Another solution is to sort the elements
		IF all numbers are positive, then we can simply return prod of last three nums. But here there can be negatives also
		If the array is sorted, then:
		Let's say min1 = nums[0] and min2 = nums[1]
		max1 = nums[n-1], max2 = nums[n-2] and max3 = nums[n-3]
		
		The possibilities of the last three nums can be:
		- - -			=> return min1*min2*max1
		- - +			=> return min1*min2*max1
		- + +			=> return min1*min2*max3
		+ + + 			=> return max3*max2*max1
		
		Complexity: O(nlogn)
		
	M3:	
		The optimization we can do is: do NOT sort. Instead we find the above values in a single scan ; O (n)
		
 * */
package leetcode;

public class MaximumProductThreeNumbers {
	public int maximumProduct(int[] nums) {
		int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
		int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
		
		for(int num : nums) {
			//set min1 and min2
			if(num < min1) {
				min2 = min1;
				min1 = num;
			}else if(num < min2) {
				min2 = num;
			}
			
			//set max1, max2 and max3
			if(num > max1) {
				max3 = max2;
				max2 = max1;
				max1 = num;
			}else if(num > max2) {
				max3 = max2;
				max2 = num;
			}else if(num > max3){
				max3 = num;
			}
		}
		
		return Math.max(min1*min2*max1, max3*max2*max1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
