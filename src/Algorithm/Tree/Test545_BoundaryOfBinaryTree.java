package Algorithm.Tree;

import util.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test545_BoundaryOfBinaryTree {
	// leaf view is very hard to generate with level order traversal
	// need to associate every leaf with idx
	// therefore here all traversal use dfs

	// method 1: 2 pass - one for left, right boundary, one for leaf
	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		if (root == null) return new ArrayList<>();
		if (root.left == null && root.right == null) return Arrays.asList(root.key);
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		List<Integer> bottom = new ArrayList<>();
		findLeft(root.left, left);
		findRight(root.right, right);
		findBottom(root, bottom);

		List<Integer> res = new ArrayList<>();
		res.add(root.key);
		res.addAll(left);
		res.addAll(bottom);
		res.addAll(right);
		return res;
	}

	// find left boundary including root, excluding leaf node
	private void findLeft(TreeNode root, List<Integer> res) {
		if (root == null) return;
		// if it's not leaf node, add to result list
		if (root.left != null || root.right != null) {
			res.add(root.key);
		}
		// route left all along, if not left child, route right (only way down)
		if (root.left != null) {
			findLeft(root.left, res);
		} else {
			findLeft(root.right, res);
		}
	}

	// find reverse order right boundary including root, excluding leaf node
	private void findRight(TreeNode root, List<Integer> res) {
		if (root == null) return;
		// if it's not leaf node, add to head of result list
		if (root.left != null || root.right != null) {
			res.add(0, root.key);
		}
		// route right all along, if not right child, route left (only way down)
		if (root.right != null) {
			findRight(root.right, res);
		} else {
			findRight(root.left, res);
		}
	}

	// find all leaf nodes
	private void findBottom(TreeNode root, List<Integer> res) {
		if (root == null) return;
		// if it's a leaf node, add to res list
		if (root.left == null && root.right == null) res.add(root.key);
		// route left first then right
		findBottom(root.left, res);
		findBottom(root.right, res);
	}


	// Method 2: one pass
	public List<Integer> borderView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) return res;
		if (root.left == null && root.right == null) return Arrays.asList(root.key);
		helper(root, true, true, root, res);
		return res;
	}

	// leftMost means whether it's in left boundary
	// if both leftMost = rightMost = true
	// it is in left boundary
	private void helper(TreeNode curr, boolean leftMost, boolean rightMost, TreeNode root, List<Integer> res) {
		if (curr == null) return;

		// left - root - right
		// first meet the node, if it's in left boundary, or if it's leaf node, add to res
		// all left boundary nodes will come first than leaf
		if (leftMost || (curr.left == null && curr.right == null))
			res.add(curr.key);

		// first discuss if curr is root
		// all nodes in left subtree of root MUST NOT be right boundary candidate
		// all nodes in right subtree of root MUST NOT be left boundary candidate
		// if it's the root node
		if (curr == root) {
			// we are certain about all cases
			helper(curr.left, true, false, root, res);
			helper(curr.right, false, true, root, res);
		}
		// if it's not the root node
		else {
			// route left, leftMost extends previous level,
			// if no right child (one way left down), rightMost extends parent
			helper(curr.left, leftMost, curr.right == null ? rightMost : false, root, res);
			// route right, rightMost extends previous level,
			// if no left child (one way right down), leftMost extends parent
			helper(curr.right, curr.left == null ? leftMost : false, rightMost, root, res);
		}

		// when all nodes from left and right are traversed
		// add to list if they are in right boundary
		// !leftMost is to for when both leftMost == rightMost == true
		// by definition, it belongs to left boundary and is already added to result list
		// in right && not in left && not leaf
		if (rightMost && !leftMost && (curr.left != null || curr.right != null))
			res.add(curr.key);
	}


	public static void main(String[] args) {
		Test545_BoundaryOfBinaryTree t = new Test545_BoundaryOfBinaryTree();
		TreeNode root = TreeNode.formTreeByLayer(new Integer[]{1, 5, 3, 6, 7, null, 4, 8, 10, null, null, null, null, 11});
		TreeNode.printTree(root);
		System.out.println(t.borderView(root));
	}
}
