package Algorithm.Tree;

import util.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Test1110_DeleteNodesAndReturnForest {
	// TC: O(n)
	// SC: O(n) for hashset, O(height) for call stack
	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		HashSet<Integer> set = new HashSet<>();
		for (int i : to_delete) {
			set.add(i);
		}
		List<TreeNode> res = new ArrayList<>();
		if (!set.contains(root.key)) res.add(root);
		delNodes(root, set, res);
		return res;
	}

	private TreeNode delNodes(TreeNode root, HashSet<Integer> set, List<TreeNode> res) {
		// base case
		if (root == null) return null;

		root.left = delNodes(root.left, set, res);
		root.right = delNodes(root.right, set, res);

		// if root is the node to be deleted
		if (set.contains(root.key)) {
			if (root.left != null && !set.contains(root.left.key)) res.add(root.left);
			if (root.right != null && !set.contains(root.right.key)) res.add(root.right);

			// so that parent.left/right = null
			return null;
		}
		return root;
	}
}
