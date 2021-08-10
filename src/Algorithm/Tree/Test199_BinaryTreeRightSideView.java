package Algorithm.Tree;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Test199_BinaryTreeRightSideView {
	// method 1:
	// level order traversal and store the last node in each level
	// TC: O(n)
	// SC: O(n)
	public List<Integer> rightSideViewI(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) return res;
		Queue<TreeNode> q = new ArrayDeque<>();
		q.offer(root);
		while (!q.isEmpty()) {
			for (int i = q.size(); i > 0; i--) {
				TreeNode curr = q.poll();
				if (i == 1) res.add(curr.key);
				if (curr.left != null) q.offer(curr.left);
				if (curr.right != null) q.offer(curr.right);
			}
		}
		return res;
	}

	// method 2:
	// dfs
	// TC: O(n)
	// SC: O(h)
	public List<Integer> rightSideViewII(TreeNode root) {
		List<Integer> rightView = new ArrayList<>();
		dfs(root, 0, rightView);
		return rightView;
	}

	// dfs with anti-pre-order
	// curr - right - left
	private void dfs(TreeNode root, int depth, List<Integer> rightView) {
		if (root == null) return;
		if (depth == rightView.size()) {
			rightView.add(root.key);
		}
		dfs(root.right, depth + 1, rightView);
		dfs(root.left, depth + 1, rightView);
	}
}
