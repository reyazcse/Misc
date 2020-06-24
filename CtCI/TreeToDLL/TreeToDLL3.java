//ref: https://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
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

Solution :
	Do inorder traversal of the tree while keeping track of the last node visited (a node in inorder traversal is visited after its left subtree)
	After left subtree is visited, attach this current node after the prev node
	
We use array to keep track of prev node. We could have used static variable too or private class object so as to maintain the value across all method calls
  
Complexity: O(N) time as inorder traversal
 
 * */

package ctci;


public class TreeToDLL3 {
	Node head = null;
	
	public Node convertToDLL(Node root) {
		Node[] prev = new Node[1]; 		//we use array so that the value in prev[0] is available across all method calls. We could have use a static prev too
		utl(root, prev);
		return head;
	}
	
	public void utl(Node root, Node[] prev) {
		if (root == null)
			return;
		//traverse left subtree
		utl(root.node1, prev);
		
		//visit current node
		if(prev[0] == null) {			//executed once only
			head = root;
		} else {
			prev[0].node2 = root;
			root.node1 = prev[0];
		}
		prev[0] = root;					//mark this node as prev node
		
		//traverse right subtree
		utl(root.node2, prev);
		
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
		System.out.println("hiiii");
		TreeToDLL3 obj = new TreeToDLL3();
		obj.printList(obj.convertToDLL(root));

	}

}
