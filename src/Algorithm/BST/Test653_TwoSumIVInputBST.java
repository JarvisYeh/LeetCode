package Algorithm.BST;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class Test653_TwoSumIVInputBST {
	// Method 1: recursion
	// two sum with hashset
	// return ture/false if bst contains two nodes
	// sum(n1.key, n2.key) == k
	// traverse bst and use hashmap
	// TC: O(n)
	// SC: O(2*height), two O(height) stacks
	public boolean findTargetI(TreeNode root, int k) {
		HashSet<Integer> set = new HashSet<>();
		return inOrder(root, set, k);
	}

	private boolean inOrder(TreeNode root, HashSet<Integer> set, int k) {
		if (root == null) return false;
		if (set.contains(k - root.key)) return true;

		set.add(root.key);
		return inOrder(root.left, set, k) || inOrder(root.right, set, k);
	}

	// Method 2: iteration
	// two sum with two pointers
	// TC: O(n)
	// SC: O(n)
	public boolean findTargetII(TreeNode root, int k) {
		// stack 1 for in-order traversal
		// left - root - right
		Deque<TreeNode> s1 = new ArrayDeque<>();
		pushLeftNodes(s1, root);
		// stack 2 for reverse in-order traversal
		// right - root - left
		Deque<TreeNode> s2 = new ArrayDeque<>();
		pushRightNodes(s2, root);

		// same as two pointers while (i < j)
		while (!s1.isEmpty() && !s2.isEmpty() && s1.peekFirst() != s2.peekFirst()) {
			if (s1.peekFirst().key + s2.peekFirst().key > k) {
				TreeNode curr = s2.pollFirst();
				pushRightNodes(s2, curr.left);
			} else if (s1.peekFirst().key + s2.peekFirst().key < k) {
				TreeNode curr = s1.pollFirst();
				pushLeftNodes(s1, curr.right);
			} else {
				return true;
			}
		}

		return false;

	}

	private void pushLeftNodes(Deque<TreeNode> s, TreeNode root) {
		while (root != null) {
			s.offerFirst(root);
			root = root.left;
		}
	}

	private void pushRightNodes(Deque<TreeNode> s, TreeNode root) {
		while (root != null) {
			s.offerFirst(root);
			root = root.right;
		}
	}
}
