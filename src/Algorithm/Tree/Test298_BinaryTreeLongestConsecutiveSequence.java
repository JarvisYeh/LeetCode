package Algorithm.Tree;

import util.TreeNode;

public class Test298_BinaryTreeLongestConsecutiveSequence {
	// Method 1: 从上往下传值
	public int longestConsecutiveI(TreeNode root) {
		if (root == null) return 0;
		int[] max = {0};
		helper(root, root.key - 1, 0, max);
		return max[0];
	}

	public void helper(TreeNode root, int prev, int currLen, int[] max) {
		if (root == null) {
			return;
		}

		if (root.key == prev + 1) {
			max[0] = Math.max(currLen + 1, max[0]);
			helper(root.left, root.key, currLen + 1, max);
			helper(root.right, root.key, currLen + 1, max);
		} else {
			helper(root.left, root.key, 1, max);
			helper(root.right, root.key, 1, max);
		}
	}


	// Method 2: 从下往上传值
	public int longestConsecutiveII(TreeNode root) {
		int[] max = {0};
		helper(root, max);
		return max[0];
	}

	// return the longest consecutive length start from root
	private int helper(TreeNode root, int[] max) {
		if (root == null) return 0;
		int len = 1;
		int left = helper(root.left, max);
		int right = helper(root.right, max);
		if (root.left != null && root.left.key == root.key + 1) {
			len = Math.max(left + 1, len);
		}
		if (root.right != null && root.right.key == root.key + 1) {
			len = Math.max(right + 1, len);
		}
		// update max
		max[0] = Math.max(max[0], len);
		return len;
	}
}
