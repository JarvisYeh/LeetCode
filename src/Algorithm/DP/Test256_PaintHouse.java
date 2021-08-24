package Algorithm.DP;

public class Test256_PaintHouse {
	// n house k colors
	// TC: O(n*k)
	// SC: O(k)
	public int minCost(int[][] costs) {
		int n = costs.length;
		// minCosts[i][j] means the min cost of
		// painting [0, i] houses with i-th house have color j
		// this actually can be reduced to only 3 variables
		// because only previous round minCosts is needed
		int[] minCosts = new int[3];
		// since this round 3 costs need previous round 3 costs, need 3 intermediate variables
		int[] next = new int[3];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 0) {
					next[j] = costs[i][j];
				} else {
					next[j] = costs[i][j] + Math.min(minCosts[(j + 1) % 3], minCosts[(j + 2) % 3]);
				}
			}
			int[] tmp = next;
			next = minCosts;
			minCosts = tmp;
		}
		return Math.min(minCosts[0], Math.min(minCosts[1], minCosts[2]));
	}
}
