package Algorithm.DP;

public class Test121_BestTimeToBuyAndSellStock {
	// Method 1: DP
	// min[i] is the min prices of days [0, i], i included
	// array[i] - min[i] is the max profit one could make if sell on day i
	// actually only 1 variable is needed, SC can be reduced to O(1)
	public int maxProfitI(int[] array) {
		// min of [0, i], init at nums[0]
		int min = array[0];
		// max profit, init as 0
		int profit = 0;

		for (int i = 1; i < array.length; i++) {
			if (array[i] > min) {
				profit = Math.max(profit, array[i] - min);
			} else {
				min = array[i];
			}
		}
		return profit;
	}

	// Method 2: transformed to finding maximum subarray
	// prices = {7,1,5,3,6,4}, max profit 5
	// {prices[i] - prices[i - 1]} => array2
	// {0, -6, 4, -2, 3, -2}
	// actually the problem is transferred to finding the largest sum of subarray of array2
	// DP = {0, -6, 4, 2, 5, 3} => max profit = 5
	// DP[i] = max sum of subarray ended at i
	public int maxProfitII(int[] array) {
		int n = array.length;
		int[] gains = new int[n];
		gains[0] = 0;
		for (int i = 1; i < n; i++) {
			gains[i] = array[i] - array[i - 1];
		}

		// finding maximum sum of subarray of array gains
		int max = gains[0];
		int currMax = gains[0];
		for (int i = 1; i < n; i++) {
			if (currMax > 0) {
				currMax += gains[i];
			} else {
				currMax = gains[i];
			}
			max = Math.max(max, currMax);
		}
		return max;
	}

	public static void main(String[] args) {
		Test121_BestTimeToBuyAndSellStock t = new Test121_BestTimeToBuyAndSellStock();
		System.out.println(t.maxProfitII(new int[]{7,1,5,3,6,4}));
	}
}
