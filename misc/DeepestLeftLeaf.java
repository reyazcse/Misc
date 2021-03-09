//https://www.geeksforgeeks.org/deepest-left-leaf-node-in-a-binary-tree/
//Solution: Keep a global variable to track the deepest left leaf. At each left leaf node, update it
//if a more deep node is found
package misc;

public class DeepestLeftLeaf {
	public TreeNode deepestLeftLeaf(TreeNode root) {
		Result result = new Result(null, -1);
		utl(root, false, 0, result);
		return result.deepestLeftLeaf;
	}
	
	private void utl(TreeNode node, boolean isLeftNode, int currLevel, Result result) {
		if(node == null) {
			return;
		}
		if(isLeftNode && isLeaf(node) && currLevel > result.lvl) {	//check and update
			result.deepestLeftLeaf = node;
			result.lvl = currLevel;
		}
		
		utl(node.left, true, currLevel+1, result);			//recurse left
		utl(node.right, false, currLevel+1, result);		//recurse right	
	}
	
	public static void main(String[] args) {
		{ 
			
			TreeNode root = new TreeNode(1);  
			root.left = new TreeNode(2); 
			root.right = new TreeNode(3); 
			root.left.right = new TreeNode(4); 
			root.right.left = new TreeNode(5); 
			root.right.right = new TreeNode(6); 
			root.right.left.right = new TreeNode(7);
			
			root.right.right.right = new TreeNode(8); 
			root.right.left.right.left = new TreeNode(9); 
			root.right.right.right.right = new TreeNode(10); 

			DeepestLeftLeaf ob = new DeepestLeftLeaf();
			TreeNode result = ob.deepestLeftLeaf(root);
			if(result != null) {
				System.out.println(result.data);
			}else {
				System.out.println("No node");
			}
					
			 
		}

	}
	
	private static class Result {
		TreeNode deepestLeftLeaf;
		int lvl;
		
		public Result(TreeNode node, int lvl) {
			this.deepestLeftLeaf = node;
			this.lvl = lvl;
			
		}
	}
	
	
	
	
	
	private boolean isLeaf(TreeNode node) {
		return node != null && node.left == null && node.right == null;
	}
	
	private static class TreeNode 
	{ 
		int data; 
		TreeNode left, right; 

		// Constructor 
		public TreeNode(int data) 
		{ 
			this.data = data; 
			left = right = null; 
		} 
	} 
}
