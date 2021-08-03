package Algorithm.Tree;

import util.TreeNode;

public class Test404_SumOfLeftLeaves {
	// pre-order traverse
	// add value of left leaf node during traversal (in its parent node)
	public int sumOfLeftLeaves(TreeNode root) {
		int[] sum = new int[1];
		sumOfLeftLeaves(root, sum);
		return sum[0];
	}

	private void sumOfLeftLeaves(TreeNode root, int[] sum) {
		// base case
		if (root == null) {
			return;
		}

		// as a parent node, check left child is the leave node and add the value
		if (root.left != null && root.left.left == null && root.left.right == null) {
			sum[0] += root.left.key;
		}
		sumOfLeftLeaves(root.left, sum);
		sumOfLeftLeaves(root.right, sum);
	}
}
