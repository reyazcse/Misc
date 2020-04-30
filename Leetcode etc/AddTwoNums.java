// https://leetcode.com/problems/add-two-numbers-ii/

/*
	Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 8 -> 0 -> 7
	
	Solution:
		We go the end of both the lists and start adding from the end nodes.
		for the longer list, we first traverse few nodes so that after that we need to traverse equal nodes for both the lists
 * */
package misc;



class ListNode {
	 int val;
	 ListNode next;
	 ListNode() {}
	 ListNode(int val) { this.val = val; }
	 ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class AddTwoNums {
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		//l1 is larger list
		int x = getListSize(l1);
		int y = getListSize(l2);
		ListNode temp = null;
		//swap if second list is longer
		if ( y > x) {
			temp = l1;
			l1 = l2;
			l2 = temp;
		}
		int lenDiff = Math.abs(x-y);		//diff between the lengths of two lists
		ListNode head1 = getHeadAfterDiffNodes(l1,lenDiff);		//head1 points to the node from where both the lists are equal
		ListNode head2 = l2;
		ListNode head = null;
		
		//for storing carry
		int []carry = new int[1];
		
		//add two lists which are of same size
		ListNode target = addTwoEqualLists(head1, head2, carry);
		if (head1 == l1) {                          //both lists are equal
			if(carry[0] == 0)
				return target;
			head = new ListNode(carry[0], target);	//if carry is there we need extra node
			return head;
		}
		//we need to add carry to the nodes from l1 till head1(exclusive)
		head = addCarryToList(l1, head1, carry);
		if(carry[0] != 0) {
			temp = head;
			head = new ListNode(carry[0], temp);		//if carry is there we need extra node
		}
		temp = head;
		//join the two parts to get the desired result
		while (temp.next != null)
			temp = temp.next;
		temp.next = target;
		return head;
		
    }
	public int getListSize(ListNode root) {
		if(root == null)	return 0;
		int count = 0;
		while(root != null) {
			count++;
			root = root.next;
		}
		return count;
	}
	
	public ListNode getHeadAfterDiffNodes(ListNode root, int lenDiff ) {
		while (lenDiff > 0) {
			root = root.next;
			lenDiff--;
		}
		return root;
	}
	
	//(2 -> 4 -> 3) + (5 -> 6 -> 4)
	public ListNode addTwoEqualLists(ListNode head1, ListNode head2, int[]carry) {
		if(head1 == null) return null;
		ListNode node = new ListNode();
		node.next = addTwoEqualLists(head1.next, head2.next, carry);
		int val = head1.val + head2.val + carry[0];
		int sum = val % 10;
		carry[0] = val/10;
		node.val = sum;
		return node;
	}
	//add carry to a list starting from node l1 till head1(exclusive)
	public ListNode addCarryToList(ListNode l1, ListNode head1, int[]carry) {
		if (l1 == head1)	return null;
		ListNode node = new ListNode();
		node.next = addCarryToList(l1.next, head1, carry);
		int val = l1.val + carry[0];
		int sum = val % 10;
		carry[0] = val/10;
		node.val = sum;
		return node;
	}

	public static void main(String[] args) {
		AddTwoNums obj = new AddTwoNums();
		ListNode l1 = new ListNode(9);
		l1.next = new ListNode(9);
		//l1.next.next = new ListNode(4);
		//l1.next.next.next = new ListNode(3);
		
		ListNode l2 = new ListNode(1);
		//l2.next = new ListNode(6);
		//l2.next.next = new ListNode(4);
		
		ListNode result = obj.addTwoNumbers(l1, l2);
		printResult(result);
		//printResult(l2);

	}
	public static void printResult(ListNode node) {
		if(node == null)	return;
		while(node.next != null) {
			System.out.print(node.val + " -> ");
			node = node.next;
		}
		
		System.out.print(node.val);
	}
}








