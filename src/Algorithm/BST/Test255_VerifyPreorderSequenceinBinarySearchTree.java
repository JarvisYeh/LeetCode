package Algorithm.BST;

public class Test255_VerifyPreorderSequenceinBinarySearchTree {
	public boolean verifyPostorder(int[] postorder) {
		int[] idx = {postorder.length - 1};
		helper(postorder, Integer.MIN_VALUE, Integer.MAX_VALUE, idx);
		return idx[0] < 0;
	}

	private void helper(int[] post, int min, int max, int[] idx) {
		if (idx[0] < 0) return;
		if (post[idx[0]] < min) return;
		if (post[idx[0]] > max) return;

		int root = post[idx[0]];
		idx[0]--;
		// right subtree
		helper(post, root, max, idx);
		// left subtree
		helper(post, min, root, idx);
	}
}
