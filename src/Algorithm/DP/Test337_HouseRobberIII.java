package Algorithm.DP;

import util.TreeNode;

public class Test337_HouseRobberIII {
	// TC: O(n^2), each node in recursion tree
	public int robI(TreeNode root) {
		return Math.max(helper(root, true), helper(root, false));
	}

	private int helper(TreeNode root, boolean skip) {
		if (root == null) {
			return 0;
		}

		if (skip) {
			return Math.max(helper(root.left, false), helper(root.left, true))
					+ Math.max(helper(root.right, false), helper(root.right, true));
		} else {
			return helper(root.left, true) + helper(root.right, true) + root.key;
		}
	}

	// improved TC: O(n), return skip/not skip together as int array
	public int robII(TreeNode root) {
		int[] res = findMax(root);
		return Math.max(res[0], res[1]);
	}

	// return int[] {max for skip root, max for rob root}
	private int[] findMax(TreeNode root) {
		if (root == null) return new int[2];
		int[] left = findMax(root.left);
		int[] right = findMax(root.right);
		int[] res = new int[2];
		// skip root, can skip/not skip child
		res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		// rob root, MUST skip child
		res[1] = root.key + left[0] + right[0];
		return res;
	}

}
