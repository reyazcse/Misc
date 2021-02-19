package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class CountSmallerAfterSelf3 {
	

	public List<Integer> countSmaller(int[] nums) {
		Integer[] result = new Integer[nums.length];
		//base case
		if(nums == null || nums.length == 0) {
			return new ArrayList<Integer>();
		}
		int n = nums.length;
		TreeNode root = null;
		for(int i=n-1; i>=0; i--) {
			root = insert(root, nums[i], i, 0, result);
		}
		return Arrays.asList(result);
	}
	//sum is the number of elements smaller than elements at nums[index] 
	private TreeNode insert(TreeNode root, int value, int index, int sum, Integer[] result) {
		if(root == null) {
			result[index] = sum;
			return new TreeNode(value, 0); //lessThan = 0 since no node less than this node currently
		}
		if(value == root.value) {
			root.dup++;
			result[index] = root.lessThan + sum;
			return root;
		}
		//current element is less than root element, hence we increment lessThan of root and recurse left
		if(value < root.value) {
			root.lessThan++;
			root.left =  insert(root.left, value, index, sum, result);
		}else {
			
			root.right = insert(root.right, value, index, sum + root.lessThan + root.dup, result); 
			
		}
		return root;
		
	}
	public static void main(String[] args) {
		//int[] nums = {5,2,6,1,1};
		int[] nums = {5,2,1,6,1};
		//int[] nums = {5,5};
		CountSmallerAfterSelf3 obj = new CountSmallerAfterSelf3();
		List<Integer> result = obj.countSmaller(nums);
		for(int elt : result) {
			System.out.println(elt);
		}

	}
	private class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public int value;
		public int lessThan;  //number of nodes whose value is less than value of this node
		public int dup;		  //number of duplicate nodes	
		public TreeNode(int val, int lessThan) {
			this.value = val;
			this.lessThan = lessThan;
			this.dup = 1;
		}
	}


}
	
