package Algorithm.Tree;

import util.TreeNode;

public class Test129_SumRoottoLeafNumbers {
	public int sumNumbers(TreeNode root) {
		int[] sum = {0};
		helper(root, 0, sum);
		return sum[0];
	}

	private void helper(TreeNode root, int currSum, int[] sum) {
		if (root.left == null && root.right == null) {
			sum[0] += (currSum*10 + root.key);
		}

		if (root.left != null)
			helper(root.left, currSum*10 + root.key, sum);
		if (root.right != null)
			helper(root.right, currSum*10 + root.key, sum);
	}
}
