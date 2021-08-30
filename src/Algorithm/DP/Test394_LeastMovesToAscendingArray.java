package Algorithm.DP;

public class Test394_LeastMovesToAscendingArray {
	// find the least move to make a array into ascending order
	// transform the problem into finding the longest increasing subsequence
	// answer = n - max
	// TC: O(n)
	// SC: O(n)
	public int leastMoves(int[] array) {
		int n = array.length;
		int[] DP = new int[n];
		DP[0] = 1;
		int max = 1;
		for (int i = 1; i < n; i++) {
			DP[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (array[j] < array[i]) {
					DP[i] = Math.max(DP[j] + 1, DP[i]);
				}
			}
			max = Math.max(max, DP[i]);
		}
		return n - max;
	}
}
