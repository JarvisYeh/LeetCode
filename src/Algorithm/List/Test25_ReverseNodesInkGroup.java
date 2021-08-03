package Algorithm.List;

import util.ListNode;

public class Test25_ReverseNodesInkGroup {
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummyHead = new ListNode(-1);
		ListNode prev = dummyHead;
		prev.next = head;
		ListNode next = null;

		// prev -> head -> ... ->tail -> next

		ListNode gHead, gTail;
		while (true) {
			gHead = prev.next;
			gTail = prev;
			for (int i = 0; i < k; i++) {
				if (gTail.next != null) {
					gTail = gTail.next;
				} else {
					return dummyHead.next;
				}
			}
			// record the next pointer after group
			next = gTail.next;

			reverse(gHead, gTail);
			// after that newHead = gTail, newTail = gHead
			prev.next = gTail;       // prev.next = newHead
			gHead.next = next;       // newTail.next = next

			// update prev pointer to the one before the next group
			// which is the tail of current group
			prev = gHead;            // prev = newTail
		}
	}


	private void reverse(ListNode head, ListNode tail) {
		ListNode prev = null, curr = head, endNode = tail.next;
		while (curr != endNode) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
	}

	/**
	 * Recursion method
	 * reverse current group (k nodes)
	 * call recursive function to complete all following groups
	 * the recursive function returns the node that current group tail.next should point to
	 * current recursion function returns current group new head, which is where the previous group tail.next should point to
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroupII(ListNode head, int k) {
		// check if there are still k nodes remains
		ListNode tail = head;
		for (int i = 0; i < k ; i++) {
			if (tail == null) {
				return head;
			}
			tail = tail.next;
		}
		// now tail is the start of the next segment;

		// reverse current segment
		// reverse all the following nodes first
		// and the function will return the tail node of current segment
		ListNode prev = reverseKGroupII(tail, k), curr = head;
		// reverse current segment
		for (int i = 0; i < k; i++) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		// prev is the tail of the previous segment, return it
		return prev;
	}

	public static void main(String[] args) {
		Test25_ReverseNodesInkGroup t = new Test25_ReverseNodesInkGroup();
		int[] arr = {1,2,3,4,5,6,7};
		ListNode dummyHead = new ListNode(-1);
		ListNode curr = dummyHead;
		for (int i : arr) {
			curr.next = new ListNode(i);
			curr = curr.next;
		}

		ListNode head = t.reverseKGroup(dummyHead.next, 3);
		while (head != null) {
			System.out.print(head.value + "->");
			head = head.next;
		}
	}
}
