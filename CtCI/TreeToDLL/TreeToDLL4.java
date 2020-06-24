//ref: https://www.geeksforgeeks.org/convert-a-given-binary-tree-to-doubly-linked-list-set-4/
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
	We do reverse inorder traversal. We keep track of the 'head' of the list and while visiting every node, we attach it to head and make current node as head

Algorithm:
		Visit right subtree
		Attach this node before head node
		Update head: head = current node
		Visit left subtree
	
  
Complexity: O(N) time as inorder
 
 * */
package ctci;

public class TreeToDLL4 {
	Node head;
	
	public Node convertToDLL(Node root) {
		utl(root);
		return head;
	}

	public void utl(Node root) {
		if(root == null) return;
		utl(root.node2);
		
		if(head != null) {
			root.node2 = head;
			head.node1 = root;
		}
		head = root;
		utl(root.node1);
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
		TreeToDLL4 obj = new TreeToDLL4();
		obj.printList(obj.convertToDLL(root));

	}

}
