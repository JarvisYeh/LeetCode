package Algorithm.Tree;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Test111_MinimumDepthofBinaryTree {
	// dfs
	public int minDepthI(TreeNode root) {
		if (root == null) return 0;
		if (root.left == null) return minDepthI(root.right) + 1;
		if (root.right == null) return minDepthI(root.left) + 1;
		return Math.min(minDepthI(root.left), minDepthI(root.right)) + 1;
	}

	// bfs
	public int minDepth(TreeNode root) {
		if (root == null) return 0;
		Queue<TreeNode> q = new ArrayDeque<>();
		q.offer(root);
		int depth = 0;
		while (!q.isEmpty()) {
			depth += 1;
			for (int i = q.size(); i > 0; i--) {
				TreeNode curr = q.poll();
				if (curr.left == null && curr.right == null) {
					return depth;
				}
				if (curr.left != null) q.offer(curr.left);
				if (curr.right != null) q.offer(curr.right);
			}
		}
		return 0;
	}
}
