package Algorithm.Tree;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Test654_MaximumBinaryTree {
	// TC: avg: O(nlog n), worst : O(n^2)
	// SC: avg: O(nlog n), worst : O(n^2)
	public TreeNode constructMaximumBinaryTreeI(int[] nums) {
		if (nums.length == 0) {
			return null;
		}

		if (nums.length == 1) {
			return new TreeNode(nums[0]);
		}

		int maxIndex = 0;
		int max = nums[0];
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > max) {
				max = nums[i];
				maxIndex = i;
			}
		}
		TreeNode root = new TreeNode(nums[maxIndex]);
		root.left = constructMaximumBinaryTreeI(Arrays.copyOfRange(nums, 0, maxIndex));
		root.right = constructMaximumBinaryTreeI(Arrays.copyOfRange(nums, maxIndex + 1, nums.length));
		return root;
	}

	// use stack
	// TC : O(n)
	// SC : O(n)
	public TreeNode constructMaximumBinaryTreeII(int[] nums) {
		// stack always in descending order
		// [9 6 3 -1 ...
		// 9
		// \
		//  6
		//   \
		//    3
		//     \
		//     -1
		// the treeNode in stack are those nodes whose position are not settled yet, say here comes a node with 4
		// Then it will becomes
		// 9
		// \
		//  6
		//   \
		//    4
		//    /
		//   3
		//  /
		// -1
		// then 3 and -1 is settled, 3 and 1 can be removed from stack, but 4 needs to be pushed to stack
		Deque<TreeNode> stack = new ArrayDeque<>();

		for (int i : nums) {
			TreeNode curr = new TreeNode(i);
			// if curr is less than stack top
			// curr temporary should be child of top right
			if (!stack.isEmpty() && i < stack.peekFirst().key) {
				stack.peekFirst().right = curr;
			}

			// backward search in stack until found some node larger than curr
			// curr should be the temporary right child of that node
			// if all nodes are less than curr, curr should be the temporary root node
			TreeNode currLeftChild = null;
			while (!stack.isEmpty() && i > stack.peekFirst().key) {
				currLeftChild = stack.pollFirst();
			}
			curr.left = currLeftChild;
			if (!stack.isEmpty()) {
				stack.peekFirst().right = curr;
			}

			stack.offerFirst(curr);
		}
		return stack.pollFirst();
	}
}
