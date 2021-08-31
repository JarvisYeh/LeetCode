package Algorithm.List;

import java.util.HashMap;

class SkipListNode {
	public int value;
	public SkipListNode next;
	public SkipListNode forward;

	public SkipListNode(int value) {
		this.value = value;
	}
}

public class Test130_DeepCopySkipList {
	public class Solution {
		public SkipListNode copy(SkipListNode head) {
			HashMap<SkipListNode, SkipListNode> map = new HashMap<>();
			SkipListNode dummyHead = new SkipListNode(-1);
			SkipListNode curr = head, prev = dummyHead;
			while (curr != null) {
				map.putIfAbsent(curr, new SkipListNode(curr.value));
				SkipListNode copy = map.get(curr);
				prev.next = copy;

				if (curr.forward != null) {
					map.putIfAbsent(curr.forward, new SkipListNode(curr.forward.value));
					copy.forward = map.get(curr.forward);
				}
				curr = curr.next;
				prev = prev.next;
			}
			return dummyHead.next;
		}
	}
}