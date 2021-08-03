package Algorithm.Tree;

import util.TreeNode;

public class Test235_LCAofBinarySearchTree {
	// iteration
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode res = root;
		// keep looking if p and q both belong to left or right subtree of root
		while ((root.key > p.key && root.key > q.key) || (root.key < p.key && root.key < q.key)) {
			if (p.key < root.key) {
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return root;
	}

	// recursion
	public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}

		// if p or q is the LCA, return
		if (root.key == p.key || root.key == q.key) {
			return root.key == p.key ? p : q;
		}

		// if both p and q belongs to the same subtree of root, continue searching
		if (root.key < p.key && root.key < q.key) {
			return lowestCommonAncestorII(root.right, p, q);
		} else if (root.key > p.key && root.key > q.key) {
			return lowestCommonAncestorII(root.left, p, q);
		} else {
			return root;
		}
	}

	public static void main(String[] args) {
		Test235_LCAofBinarySearchTree t = new Test235_LCAofBinarySearchTree();
		TreeNode root = TreeNode.formTreeByLayer(new Integer[]{6,2,8,0,4,7,9,null,null,3,5});
		System.out.println(t.lowestCommonAncestorII(root, root.left, root.left.right).key);
	}

}
