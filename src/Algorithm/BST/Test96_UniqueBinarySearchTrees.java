package Algorithm.BST;

public class Test96_UniqueBinarySearchTrees {
	// DP
	// TC: O(n^2)
	// SC: O(n)
	public int numTrees(int n) {
		if (n == 1) return 1;
		int[] count = new int[n + 1];
		count[0] = 1;

		for (int i = 1; i <= n; i++) {
			if (i == 1) {
				count[i] = 1;
			} else {
				// root value = 1 ... i
				for (int root = 1; root <= i; root++) {
					// amount of nodes to left of root = root - 1
					// amount of nodes to right of root = i - root
					count[i] += count[root - 1]*count[i - root];
				}
			}
		}
		return count[n];
	}
}
