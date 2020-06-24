//reference: CtCI | 17.12 BiNode
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
We return a node pair which contains head and tail of the list
	Get the left node pair
	Get the right node pair
	Concatenate left with current root
	Concatenate right with current root
	Return the node pair with current head and current tail
	

Note: Instead of returning the node pair, we can just return node (BiNode here) itself and then we can get the tail by traversing from the node head. But then
its complexity would be O(N^2).
  
  
 
 * */

package ctci;

//node of a list or tree
class BiNode {
	public int data;
	public BiNode node1;
	public BiNode node2;
	public BiNode (int d) {
		data = d;
		node1 = null;
		node2 = null;
	}
}

//this contains head and tail of a BiNode
class BiNodePair{
	public int data;
	public BiNode head;
	public BiNode tail;
	public BiNodePair(BiNode h, BiNode t) {
		head = h;
		tail = t;
	}
}
public class TreeToDLL1 {
	
	public BiNode treeToDLL (BiNode root) {
		BiNodePair front = utl(root);
		if (front == null)
			return null;
		else
			return front.head;		//return head of the list
	}
	
	public BiNodePair utl(BiNode node) {
		if (node == null) return null;
		//get left part
		BiNodePair left = utl(node.node1);
		
		//get right part
		BiNodePair right = utl(node.node2);
		
		if (left != null)
			concat(left.tail, node);		//concatenate left part with this node
		if(right != null)
			concat(node,right.head);		//concatenate right part with this node
		
		
		BiNodePair result = new BiNodePair(left == null ? node : left.head, right == null? node : right.tail);
		return result;
	}
	
	public void concat(BiNode x, BiNode y) {
		x.node2 = y;
		y.node1 = x;
	}
	

	public static void main(String[] args) {
		BiNode root = new BiNode(4);
		root.node1 = new BiNode(2);
		root.node2 = new BiNode(5);
		root.node1.node1 = new BiNode(1);
		root.node1.node2 = new BiNode(3);
		root.node1.node1.node1 = new BiNode(0);
		root.node2.node2 = new BiNode(6);
		
		TreeToDLL1 obj = new TreeToDLL1();
		BiNode head = obj.treeToDLL(root);
		obj.printList(head);
		

	}
	
	public void printList(BiNode head) {
		while(head != null) {
			System.out.print(head.data + " -> ");
			head = head.node2;
		}
	}

}
