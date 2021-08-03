package Algorithm.Offer;

import util.TreeNode;

import java.util.HashMap;

// TC: O(n)
// SC: O(n)
public class Test07_RebuildBinaryTreeWithInPreOrder {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		// key = inorder array value
		// val = inorder array index
		HashMap<Integer, Integer> idxMap = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			idxMap.put(inorder[i], i);
		}
		return helper(
				preorder, 0, preorder.length - 1,
				idxMap, 0, inorder.length - 1);
	}


	private TreeNode helper(int[] pre, int pL, int pR,
							HashMap<Integer, Integer> inMap, int iL, int iR) {
		// base case
		if (pL > pR) {
			return null;
		}

		TreeNode root = new TreeNode(pre[pL]);
		int rootIdx = inMap.get(pre[pL]);
		int leftSize = rootIdx - iL;

		root.left = helper(pre, pL + 1, pL + leftSize,
				inMap, iL, rootIdx - 1);
		root.right = helper(pre, pL + leftSize + 1, pR,
				inMap, rootIdx + 1, iR);
		return root;
	}
}
