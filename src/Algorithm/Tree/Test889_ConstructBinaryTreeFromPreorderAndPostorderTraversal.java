package Algorithm.Tree;

import util.TreeNode;

import java.util.HashMap;

public class Test889_ConstructBinaryTreeFromPreorderAndPostorderTraversal {
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		HashMap<Integer, Integer> indexMap = new HashMap<>();
		for (int i = 0; i < post.length; i++) {
			indexMap.put(post[i], i);
		}
		return construct(pre, 0, pre.length - 1, post, 0, post.length - 1, indexMap);
	}

	private TreeNode construct(int[] pre, int preL, int preR,
							   int[] post, int postL, int postR,
							   HashMap<Integer, Integer> indexMap) {
		if (preL > preR) {
			return null;
		}
		if (preL == preR) {
			return new TreeNode(pre[preL]);
		}

		TreeNode root = new TreeNode(pre[preL]);
		int index = indexMap.get(pre[preL + 1]);
		int lSize = index - postL + 1;
		root.left = construct(pre, preL + 1, preL + lSize,
				post, postL, index,
				indexMap);
		root.right = construct(pre, preL + lSize + 1, preR,
				post, index + 1, postR - 1,
				indexMap);
		return root;
	}
}
