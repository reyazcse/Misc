/*
This is similar to CountSmallerAfterSelf3.java
Only difference is initial duplicate count at a node is 0 instead of 1.

Complexity: O(n^2) time and O(n) space
 * */
package leetcode;

public class CountSmallerAfterSelf3A {
	int[] countSmaller (int [] nums) {
		int n = nums.length;
		int[] result = new int[n];
		
		TreeNode root = null;
		for(int i=n-1; i>=0; i--) {
			root = insert(root, nums, i, result, 0);
		}
		return result;
	}
	
	//total is total number of values less than current node value
	private TreeNode insert (TreeNode root, int[] nums, int index, int [] result, int total) {
		if(root == null) {
			result[index] = total;
			return new TreeNode(nums[index], total);
		}
		if(root.val == nums[index]) {
			root.dup++;								//increment duplicate count
			result[index] = root.lessThan;
			return root;
		}else if (nums[index] < root.val) {			//go left
			root.lessThan++;
			root.left = insert(root.left, nums, index, result, total);
		}else {																	//go right
			root.right = insert(root.right, nums, index, result, root.lessThan + root.dup + 1);
		}
		return root;
	}
	
	
	private static class TreeNode {
		int val;
		int dup;
		int lessThan;
		TreeNode left;
		TreeNode right;
		
		public TreeNode () {
		}
		
		public TreeNode (int val, int lessThan) {
			this.val = val;
			this.lessThan = lessThan;
		}
		
		public TreeNode (int val, int dup, int lessThan) {
			this.val = val;
			this.dup = dup;
			this.lessThan = lessThan;
		}
	}
	
	public static void main(String[] args) {
		CountSmallerAfterSelf3A ob = new CountSmallerAfterSelf3A();
		int[] nums = {1,6,5,5,3,4};
		int[] res = ob.countSmaller(nums);
		
		for(int x : res) {
			System.out.println(x);
		}
		
	}
}
