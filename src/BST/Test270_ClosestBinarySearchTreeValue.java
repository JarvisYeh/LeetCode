package BST;

import util.ListNode;
import util.TreeNode;

public class Test270_ClosestBinarySearchTreeValue {
	public TreeNode sortedListToBST(ListNode head) {
		return constructBST(head);
	}

	private TreeNode constructBST(ListNode head) {
		if (head == null) return null;
		if (head.next == null) return new TreeNode(head.value);

		// now head is the head of list with size >= 2
		// divide list into two equal size sub list
		ListNode left = head;
		ListNode mid = findMid(left);
		ListNode rootNode = mid.next;
		// if rootNode is not null, can access rootNode.next
		ListNode right = rootNode.next;
		// cut between left and mid
		mid.next = null;

		TreeNode root = new TreeNode(rootNode.value);
		root.left = constructBST(left);
		root.right = constructBST(right);
		return root;
	}

	// 1 2 3 4
	// return 1
	// 1 2 3
	// return 1
	private ListNode findMid(ListNode head) {
		ListNode s = head, f = head;
		while (f.next != null && f.next.next != null && f.next.next.next != null) {
			s = s.next;
			f = f.next.next;
		}
		return s;
	}

	public static void main(String[] args) {
		ListNode head = ListNode.fromArray(new int[]{-10,-3,0,5,9});
		Test270_ClosestBinarySearchTreeValue t = new Test270_ClosestBinarySearchTreeValue();
		t.constructBST(head);
	}
}
