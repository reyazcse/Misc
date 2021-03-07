//https://leetcode.com/problems/product-of-array-except-self/
package leetcode;

public class ProductExceptSelf {
	
	/*
	 * L[i] is product of all elements from i till 0
	 * 
	 * Here we just create the L array and return it as result.
	 * O(n) time and O(1) space since the result array is not considered
	 * 
	 * */
	public static int[] productExceptSelf_SpaceOptimized(int[] nums) {
        if(nums == null || nums.length == 0) {
            return nums;
        }
        int n = nums.length;
        int[] L = new int[n];
        
        //fill L array
        L[0] = nums[0];
        for(int i=1; i<n; i++) {
            L[i] = nums[i] * L[i-1];
        }
        
        int R = nums[n-1];
        L[n-1] = L[n-2];						//last cell value
        for(int i=n-2; i>=1; i--) {
            L[i] = L[i-1] * R;
            R = R * nums[i];
        }
        L[0] = R;
        return L;
    }
	
	
	/*
	 * O(n) time and O(n) space as we create L and R arrays
	 * L[i] stores product of all elements from i till 0
	 * R[i] stores product of all elements from i end of array
	 * */
	public static int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0) {
            return nums;
        }
        int n = nums.length;
        int[] L = new int[n];
        int[] R = new int[n];
        
        L[0] = nums[0];
        for(int i=1; i<n; i++) {
            L[i] = nums[i] * L[i-1];
        }
        
        R[n-1] = nums[n-1];
        for(int i=n-2; i>=0; i--) {
            R[i] = nums[i] * R[i+1];
        }
        
        int[] result = new int[n];
        result[0] = R[1];
        result[n-1] = L[n-2];
        for(int i=1; i<n-1; i++) {
            result[i] = L[i-1] * R[i+1];
        }
        return result;
    }
	public static void main(String[] args) {
		int[] nums = {1,2,3,4};
		int[] res = productExceptSelf(nums);
	}

}
