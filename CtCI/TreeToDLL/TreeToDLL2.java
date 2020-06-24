//ref: https://www.geeksforgeeks.org/in-place-convert-a-given-binary-tree-to-doubly-linked-list/
/*
Question: Given a tree with node called BiNode, convert it to doubly linked list
Input: 
  				4
  			  /   \
  			 2     5
  		   /  \     \
  		  1   3      6
  		 /
  		0
  		
Output:  0 - 1 - 2 - 3 - 4 - 5 - 6

Solution 1:
	Convert the left subtree into list
	Convert the right subtree into list
	Get the inorder predecessor node pred.
		pred node will be before current root node.
	Get the inorder successor node succ.
		succ node will be after current root node.
	return the current node
  
Complexity: O(N^2) since we find the inorder pre and succ at each node
 
 * */

package ctci;

//Node
class Node {
	int data;
	Node node1;
	Node node2;
	public Node (int data ) {
		this.data = data;
	}
	public Node() {}
}

public class TreeToDLL2 {
	
	public Node convertToDLL2(Node root) {
		if (root == null)
			return root;
		Node head = utl(root);
		//since we get the root of the tree (which has now been converted to a list), we need to move backwards till we get the head node
		while(head.node1 != null)
			head = head.node1;
		return head;				//return head of the list
	}
	
	//converts a tree to list
	public Node utl(Node root) {
		if(root == null)
			return root;
		
		//process left subtree
		Node left = utl(root.node1);
		//process right subtree
		Node right = utl(root.node2);
		
		//find inorder predecessor
		while(left != null && left.node2 != null) {
			left = left.node2;
		}
		//attach root
		if(left != null) {
			left .node2 = root;
			root.node1 = left;
		}
		
		//find inorder successor
		while(right != null && right.node1 != null) {
			right = right.node1;
		}
		//attach root
		if(right != null) {
			root.node2 = right;
			right.node1 = root;
		}
		//return current node
		return root;
	}
	
	public void printList(Node head) {
		while(head != null) {
			System.out.print(head.data + " -> ");
			head = head.node2;
		}
	}
	
	
	public static void main(String[] args) {
		Node root = new Node(4);
		root.node1 = new Node(2);
		root.node2 = new Node(5);
		root.node1.node1 = new Node(1);
		root.node1.node2 = new Node(3);
		root.node1.node1.node1 = new Node(0);
		root.node2.node2 = new Node(6);
		
		TreeToDLL2 obj = new TreeToDLL2();
		Node head = obj.convertToDLL2(root);
		obj.printList(head);

	}

}
