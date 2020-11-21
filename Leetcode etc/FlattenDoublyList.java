//https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
/*
You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.

 

Example 1:

Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]

 * */

/*
Solution:
	Traverse the list from head.
	When we get a node which has child node, flattern the child list recursively.
	After getting child list, attach current node to the front of the child list.
	Then go to the tail of the child list and attach the tail of the child list to the node in the current level.
	
	Complexity: O(2n) = O(n) time and O(1) space
 * */
package leetcode;

public class FlattenDoublyList {
	public Node flatten(Node head) {
		Node curr = head;
		while(curr != null) {
			if(curr.child != null) {
				Node next = curr.next;
				//recurse on the child list to flatten list. 'below' is head of the flattened list 
				Node below = flatten(curr.child);
				
				//join current node with the flattened list
				curr.next = below;
				below.prev = curr;
				
				//go to the tail of the flattened list
				while(below.next != null) {
					below = below.next;
				}
				
				//attach the flattened list to the next node in the current level
				below.next = next;
				if(next != null) {
					next.prev = below;
				}
				//no more child for current node
				curr.child = null;
				
				//move to the next node in the level
				curr = next;
			}else {
				curr = curr.next;		//move to the next node in the level
			}
			
		}
		return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class Node {
		public int val;
		public Node prev;
		public Node next;
		public Node child;
	};
}
