package Algorithm.List;

import util.ListNode;

public class Test142_LinkedListCycleII {
	public ListNode detectCycle(ListNode head) {
		ListNode s = head, f = head;
		while (f != null && f.next != null) {
			s = s.next;
			f = f.next.next;
			// two pointers meet
			if (s == f) {
				// reset two nodes, both go 'a' steps
				s = head;
				while (s != f) {
					s = s.next;
					f = f.next;
				}
				return s;
			}
		}
		return null;
	}
}
