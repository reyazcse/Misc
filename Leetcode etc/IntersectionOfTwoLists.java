//https://leetcode.com/problems/intersection-of-two-linked-lists/
/*

Notes:
	If the two linked lists have no intersection at all, return null.
	The linked lists must retain their original structure after the function returns.
	You may assume there are no cycles anywhere in the entire linked structure.
	Each value on each linked list is in the range [1, 10^9].
	Your code should preferably run in O(n) time and use only O(1) memory.
 * */

/*
Solution:
There are many methods to solve it like using set to put the nodes, counting length of two lists and then start after moving forward
on the larger list

Here is another method whose implementation is provided:

	The idea is take two  pointers ptr1 and ptr2 at first and second list respectively
	If one of the pointers is null, then assign start of other list
	Finally we return when we have ptr1 == ptr2
	
	Complexity:
	O(m + n) time and O(1) space
 * */
package leetcode;


public class IntersectionOfTwoLists {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if(headA == null || headB == null) {
			return null;
		}
		ListNode ptr1 = headA, ptr2 = headB;
		while(ptr1 != ptr2) {
			ptr1 = ptr1.next;
			ptr2 = ptr2.next;
			if(ptr1 == ptr2) {
				return ptr1;
			}
			if(ptr1 == null) {
				ptr1 = headB;
			}
			if(ptr2 == null) {
				ptr2 = headA;
			}
		}
		return ptr1;
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
