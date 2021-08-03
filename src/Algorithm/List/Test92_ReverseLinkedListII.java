package Algorithm.List;

import util.ListNode;

public class Test92_ReverseLinkedListII {
	public ListNode reverseBetween(ListNode head, int left, int right) {
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;

		ListNode leftPrev = dummyHead;
		ListNode leftNode = head;
		for (int i = 1; i < left; i++) {
			if (leftNode == null) return head;
			leftPrev = leftNode;
			leftNode = leftNode.next;
		}

		ListNode rightNode = leftNode;
		for (int i = left; i < right; i++) {
			if (rightNode == null) return head;
			rightNode = rightNode.next;
		}
		ListNode rightNext = rightNode.next;

		reverse(leftNode, rightNode);
		leftPrev.next = rightNode; // connect leftPrev.next      -> original right
		leftNode.next = rightNext; // connect original left.next -> rightNext
		return dummyHead.next;
	}

	private void reverse(ListNode left, ListNode right) {
		ListNode end = right.next;
		ListNode prev = null, curr = left;
		while (curr != end) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
	}

	public static void main(String[] args) {
		Test92_ReverseLinkedListII t = new Test92_ReverseLinkedListII();
		t.reverseBetween(util.ListNode.fromArray(new int[]{1, 2, 3, 4, 5}), 2, 4);
	}
}
