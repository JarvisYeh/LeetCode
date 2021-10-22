package Algorithm.List;

import util.ListNode;

public class Test82_RemoveDuplicatesfromSortedListII {
	// TC: O(n)
	// SC: O(1)
	public ListNode deleteDuplicates(ListNode head) {
		ListNode dummyHead = new ListNode(-1);
		ListNode prev = dummyHead;
		prev.next = head;

		while (prev.next != null) {
			ListNode start = prev.next;
			ListNode end = start;
			// [start, end] is the duplicate range
			while (end.next != null && end.next.value == end.value) end = end.next;

			// now end.next == null || end.next.val != end.val
			ListNode next = end.next;
			// if there is only one node in duplicate range, keep that node
			if (end == start) {
				prev = prev.next;
			}
			// else remove this duplicate range
			else {
				prev.next = next;
			}
		}
		return dummyHead.next;
	}
}
