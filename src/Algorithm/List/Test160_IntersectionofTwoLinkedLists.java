package Algorithm.List;

import util.ListNode;

import java.util.HashSet;

public class Test160_IntersectionofTwoLinkedLists {
	// method 1: hashmap
	// TC: O(n + m)
	// SC: O(m or n)
	public ListNode getIntersectionNodeI(ListNode headA, ListNode headB) {
		HashSet<ListNode> set = new HashSet<>();
		// put all node in list A in set
		while (headA != null) {
			set.add(headA);
			headA = headA.next;
		}

		// iterate through list b, the first node appears in set is the interset node
		while (headB != null) {
			if (set.contains(headB)) {
				return headB;
			}
			headB = headB.next;
		}
		return null;
	}

	// method 2: two pointers
	// TC: O(m + n)
	// SC: O(1)
	public ListNode getIntersectionNodeII(ListNode headA, ListNode headB) {
		ListNode a = headA, b = headB;
		// if list a and list b doesn't intersect
			// a goes m + n
			// b goes n + m
			// together goes to null, exit the loop, return null
		// if list a and list b has common range = x, list a = m + x, list b = n + x
			// a goes m + x + n
			// b goes n + x + m
			// together at intersection, exit loop, return intersect node
		while (a != b) {
			a = a == null ? headB : a.next;
			b = b == null ? headA : b.next;
		}
		return a;
	}


}
