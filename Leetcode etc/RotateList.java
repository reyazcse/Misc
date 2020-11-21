//https://leetcode.com/problems/rotate-list/
/*
Given the head of a linked list, rotate the list to the right by k places.

Examples:
Input: head = [0,1,2], k = 4
Output: [2,0,1]
 
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
 * */

/*
Solution:
	We first move a pointer to the (k+1)th node : temp
	Then we keep another pointer at head : temp2
	Then move both pointers till temp reaches the last node.
	After that make temp.next = head
	New head will be temp2.next.
	Also make temp2.next as null
	
	For example: 1-2-3-4-5 and k=2
	We move to 3 and then we keep pointer at 1
	We move both pointers till the first pointer is at 5.
	After that we change the links.
	
	In the given problem, we can have k >= size of the list
	So we have to handle such cases
	
Complexity: O(n) time and O(1) space.
 * */
package leetcode;


public class RotateList {
	public ListNode rotateRight(ListNode head, int k) {
		if(head == null) {
			return null;
		}
		int n = getSize(head);		//size of the list
		k = k % n;					//k can be greater than or equal to n
		if(k == 0) {				//if k is multiple of n then dont do anything
			return head;
		}
		ListNode temp = head;
		
		//move to the k+1 th node
		for(int i=1; i<=k; i++) {
			temp = temp.next;
		}
		
		ListNode temp2 = head;
		while(temp.next != null) {
			temp2 = temp2.next;
			temp = temp.next;
		}
		//change links
		temp.next = head;
		ListNode newHead = temp2.next;
		temp2.next = null;
		return newHead;
		
	}
	
	private int getSize(ListNode head) {
		int size = 0;
		while(head != null) {
			head = head.next;
			size++;
		}
		return size;
	}
	public static void main(String[] args) {
		RotateList ob = new RotateList();
		ListNode head = ob.new ListNode(1);
		head.next = ob.new ListNode(2);
		head.next.next  = ob.new ListNode(3);
		head.next.next.next = ob.new ListNode(4);
		head.next.next.next.next = ob.new ListNode(5);

	}
	
	private void print(ListNode root) {
		while(root != null) {
			System.out.println(root.val );
			root = root.next;
		}
	}
	
	private class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

}
