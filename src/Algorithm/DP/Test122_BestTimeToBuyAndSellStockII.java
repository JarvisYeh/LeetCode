package Algorithm.DP;

public class Test122_BestTimeToBuyAndSellStockII {
	// peak-vally approach
	// 将所有股价的上升区间求和即为可以得到的最大profit
	// TC: O(n)
	// SC: O(1)
	public int maxProfit(int[] array) {
		int profit = 0;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i + 1] > array[i]) {
				profit += array[i + 1] - array[i];
			}
		}
		return profit;
	}
}