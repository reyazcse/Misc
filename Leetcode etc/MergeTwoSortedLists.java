//https://leetcode.com/problems/merge-two-sorted-lists/
/*
Merge two sorted linked lists and return it as a new sorted list. 
The new list should be made by splicing together the nodes of the first two lists.
 * */

/*
Solution:
	We solve it using an approach similar to merging two sorted array in merge sort
	
 * */
package leetcode;

public class MergeTwoSortedLists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1 == null) {			//quick return
			return l2;
		}
		if(l2 == null) {			//quick return
			return l1;
		}
		ListNode head = new ListNode();
		ListNode dummy = head;
		while(l1 != null && l2 != null) {
			if(l1.val <= l2.val) {
				dummy.next = l1;
				l1 = l1.next;
				dummy = dummy.next;
			}else {
				dummy.next = l2;
				l2 = l2.next;
				dummy = dummy.next;
			}
		}
		//if l1 remaining, then just attach the remaining part of l1
		if(l1 != null) {
			dummy.next = l1;
		}
		
		//if l2 remaining, then just attach the remaining part of l2
		if(l2 != null) {
			dummy.next  = l2;
		}
		return head.next;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	private class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

}
