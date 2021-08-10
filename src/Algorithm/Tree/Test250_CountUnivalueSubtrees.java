package Algorithm.Tree;

import util.TreeNode;

public class Test250_CountUnivalueSubtrees {
	public int countUnivalSubtrees(TreeNode root) {
		int[] count = {0};
		helper(root, count);
		return count[0];
	}

	// return if root is a universal tree
	private boolean helper(TreeNode root, int[] count) {
		if (root == null) {
			return true;
		}
		boolean left = helper(root.left, count);
		boolean right = helper(root.right, count);
		if (left && right) {
			if (root.left != null && root.left.key != root.key) return false;
			if (root.right != null && root.right.key != root.key) return false;
			count[0] += 1;
			return true;
		}
		return false;
	}
}
