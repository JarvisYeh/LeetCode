package Algorithm.BST;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Test95_UniqueBinarySearchTreesII {
	// Method 1: DFS
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

	// Method 2: DP
	// [1...i - 1] i [i + 1, ..., n]
	public List<TreeNode> generateBSTs(int n) {
		if (n == 0) {
			List<TreeNode> res = new ArrayList<>();
			res.add(null);
			return res;
		}
		List<TreeNode>[][] DP = new List[n + 1][n + 1];
		// base case
		for (int i = 1; i <= n; i++) {
			DP[i][i] = new ArrayList<>();
			DP[i][i].add(new TreeNode(i));
		}
		List<TreeNode> nullList = new ArrayList<>();
		nullList.add(null);

		// [i, j]
		for (int j = 1; j <= n; j++) {
			for (int i = j - 1; i >= 1; i--) {
				List<TreeNode> roots = new ArrayList<>();
				for (int rootKey = i; rootKey <= j; rootKey++) {
					List<TreeNode> left = i <= rootKey - 1 ? DP[i][rootKey - 1] : nullList;
					List<TreeNode> right = rootKey + 1 <= j ? DP[rootKey + 1][j] : nullList;
					for (TreeNode lChild : left) {
						for (TreeNode rChild : right) {
							TreeNode root = new TreeNode(rootKey);
							root.left = lChild;
							root.right = rChild;
							roots.add(root);
						}
					}
				}
				DP[i][j] = roots;
			}
		}
		return DP[1][n];
	}

	public static void main(String[] args) {
		Test95_UniqueBinarySearchTreesII t = new Test95_UniqueBinarySearchTreesII();
		t.generateBSTs(3);
	}

}
