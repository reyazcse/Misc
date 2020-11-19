//https://leetcode.com/problems/remove-nth-node-from-end-of-list/

/*
Solution:
	Take two pointers.
	Move fast pointer to nth node from start.
	Then move both slow and fast pointers one step till fast is at the last node
	Then remove the node next to slow pointer.
	Return dummy.next (dummy node.next = head initially)
 * */
package leetcode;

public class RemoveNthNode {
	//O(n) and single pass
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode();
		dummy.next = head;
		ListNode slow = dummy, fast = dummy;
		for(int i=1; i<=n; i++) {
			fast = fast.next;
		}
		while(fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
		slow.next = slow.next.next;
		return dummy.next;
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
