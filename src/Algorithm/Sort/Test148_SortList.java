package Algorithm.Sort;

import util.ListNode;

public class Test148_SortList {
	// quickSort list
	// TC: O(nlogn)
	// SC: O(log n)
	public ListNode quickSort(ListNode head) {
		if (head == null) return null;

		ListNode tail = head;
		while(tail.next != null) {
			tail = tail.next;
		}

		return quickSort(head, tail)[0];
	}

	private ListNode[] quickSort(ListNode head, ListNode tail) {
		// base cases
		if (head == tail) return new ListNode[]{head, tail};
		if (head == null) return null;

		// head -> [....]
		// put [....] nodes into linked list before/after head
		ListNode dummyHeadBefore = new ListNode(-1);
		ListNode dummyHeadAfter = new ListNode(-1);
		ListNode prevBefore = dummyHeadBefore;
		ListNode prevAfter = dummyHeadAfter;

		ListNode curr = head.next;
		while (curr != null) {
			if (curr.value <= head.value) {
				prevBefore.next = curr;
				prevBefore = prevBefore.next;
			} else {
				prevAfter.next = curr;
				prevAfter = prevAfter.next;
			}
			curr = curr.next;
		}
		prevBefore.next = null;
		prevAfter.next = null;

		// retrieve left half {head, tail}
		ListNode[] befNodes = quickSort(dummyHeadBefore.next, prevBefore);
		// retrieve right half {head, tail}
		ListNode[] aftNodes = quickSort(dummyHeadAfter.next, prevAfter);

		ListNode newHead, newTail;
		if (befNodes != null) {
			newHead = befNodes[0];
			befNodes[1].next = head;
		} else {
			newHead = head;
		}

		if (aftNodes != null) {
			head.next = aftNodes[0];
			newTail = aftNodes[1];
		} else {
			head.next = null;
			newTail = head;
		}
		return new ListNode[]{newHead, newTail};
	}


	// mergeSort list
	// TC: O(nlog n)
	// SC: O(log n)
	public ListNode sortList(ListNode head) {
		return mergeSort(head);
	}

	private ListNode mergeSort(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode mid = findMiddle(head);
		ListNode two = mid.next;
		// split the two list from middle
		mid.next = null;

		return merge(mergeSort(head), mergeSort(two));
	}

	private ListNode findMiddle(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	private ListNode merge(ListNode one, ListNode two) {
		if (one == null) {
			return two;
		}

		if (two == null) {
			return one;
		}

		if (one.value < two.value) {
			one.next = merge(one.next, two);
			return one;
		} else {
			two.next = merge(one, two.next);
			return two;
		}
	}
}
