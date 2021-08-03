package Algorithm.DP;

import java.util.Arrays;

public class Test1388_PizzaWith3nSlices {
	// TC: O(n^2)
	// SC: O(n^2)
	public int maxSizeSlices(int[] slices) {
		// split slice into [0, len - 2] and [1: len - 1]
		// so that will not pick both 0 and len - 1 at the same time
		int[] one = Arrays.copyOfRange(slices, 0, slices.length - 1);
		int[] two = Arrays.copyOfRange(slices, 1, slices.length);
		return Math.max(findMaxSize(one), findMaxSize(two));
	}

	private int findMaxSize(int[] slices) {
		// sizes[i][j] represetns the max sizes of choosing j slices among first i slices
		int[][] sizes = new int[slices.length + 1][(slices.length + 1)/3 + 1];
		for (int i = 1; i < sizes.length; i++) {
			for (int j = 1; j < sizes[0].length; j++) {
				if (i == 1) {
					sizes[i][j] = slices[0];
				} else {
					// pick i-th slice
					int pick = slices[i - 1] + sizes[i - 2][j - 1];
					// do not pick i-th slice
					int notPick = sizes[i - 1][j];
					sizes[i][j] = Math.max(pick, notPick);
				}
			}
		}
		return sizes[slices.length][(slices.length + 1)/3];
	}
}
