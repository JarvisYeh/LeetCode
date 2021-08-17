package Algorithm.BST;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test99_RecoverBinarySearchTree {
	// method 1: recursion
	public void recoverTree(TreeNode root) {
		TreeNode[] misNodes = new TreeNode[2];
		TreeNode[] prev = {new TreeNode(Integer.MIN_VALUE)};

		inOrder(root, prev, misNodes);
		// swap twp misplaced nodes values
		int tmp = misNodes[0].key;
		misNodes[0].key = misNodes[1].key;
		misNodes[1].key = tmp;
	}

	private void inOrder(TreeNode root, TreeNode[] prev, TreeNode[] misNodes) {
		if (root == null) return;

		inOrder(root.left, prev, misNodes);
		// current level, check relationship with previous nodes
		// case 1:
		// haven't found the first misplaced node
		// the first misplaced node will be larger than the next node
		// large value moves to front: 1 2 3 '10' 4 ...
		if (misNodes[0] == null && root.key < prev[0].key) {
			misNodes[0] = prev[0];
		}

		// case 2:
		// found first misplaced node
		// the second misplaced node will be less than previous node
		// small value move to back: 7 8 9 '5' 11
		if (misNodes[0] != null && root.key < prev[0].key) {
			misNodes[1] = root;
		}
		prev[0] = root;
		inOrder(root.right, prev, misNodes);
	}


	// method 2: iteration
	public void recover(TreeNode root) {
		Deque<TreeNode> stack = new ArrayDeque<>();
		while (root != null) {
			stack.offerFirst(root);
			root = root.left;
		}

		TreeNode first = null, second = null;
		TreeNode prev = new TreeNode(Integer.MIN_VALUE);
		while (!stack.isEmpty()) {
			TreeNode curr = stack.pollFirst();
			// process curr
			// curr.key is supposed to be > prev
			// first misplaced node < next node, large node move forward
			// second misplaced node > prev node, small node move backward
			// 1 2 {7} 4 5 6 {3} 8 9
			if (first == null && curr.key < prev.key) {
				first = prev;
			}
			if (first != null && curr.key < prev.key) {
				second = curr;
			}

			TreeNode tmp = curr.right;
			while (tmp != null) {
				stack.offerFirst(tmp);
				tmp = tmp.left;
			}
			// update prev
			prev = curr;
		}

		int tmp = first.key;
		first.key = second.key;
		second.key = tmp;
	}

	public static void main(String[] args) {
		Test99_RecoverBinarySearchTree t = new Test99_RecoverBinarySearchTree();
		TreeNode root = TreeNode.formTreeByLayer(new Integer[]{5,7,9,null,4,3});
		TreeNode.printTree(root);
		t.recover(root);
		TreeNode.printTree(root);

	}
}

