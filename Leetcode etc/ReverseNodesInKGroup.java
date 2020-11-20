//https://leetcode.com/problems/reverse-nodes-in-k-group/
/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
k is a positive integer and is less than or equal to the length of the linked list. 
If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * */

/*
 * O (n) time and O(1) space solution
 * */
package leetcode;


public class ReverseNodesInKGroup {
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummy = new ListNode();
		dummy.next = utl(head, k);
		return dummy.next;
	}
	
	private ListNode utl(ListNode head, int k) {
		if(head == null) {
			return null;
		}
		ListNode tmp = head;
		ListNode prev = null, curr = head, next = null;
		int i=0;
		for(i=0; i<k && curr != null; i++) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		//if less than k nodes, then we should not reverse! hence revert back
		if(i != k) {
			curr = prev;
			prev = null;
			while(curr != null) {
				next = curr.next;
				curr.next = prev;
				prev = curr;
				curr = next;
			}
			return prev;
					
		}
		//if more nodes remain to be processed
		if(curr != null) {
			tmp.next = utl(curr, k);
		}
		return prev;
	}
	public static void main(String[] args) {
		

	}
	
	private class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

}
