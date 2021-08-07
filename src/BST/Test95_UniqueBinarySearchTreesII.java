package BST;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Test95_UniqueBinarySearchTreesII {
	public List<TreeNode> generateTrees(int n) {
		return generate(1, n);
	}

	private List<TreeNode> generate(int l, int r) {
		List<TreeNode> res = new ArrayList<>();

		if (l > r) {
			res.add(null);
			return res;
		}

		for (int i = l; i <= r; i++) {
			List<TreeNode> left = generate(l, i - 1);
			List<TreeNode> right = generate(i + 1, r);
			for (TreeNode lnode : left) {
				for (TreeNode rnode : right) {
					TreeNode root = new TreeNode(i);
					root.left = lnode;
					root.right = rnode;
					res.add(root);
				}
			}
		}
		return res;
	}
}
