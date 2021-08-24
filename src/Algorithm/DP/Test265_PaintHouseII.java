package Algorithm.DP;

public class Test265_PaintHouseII {
	// Method 1:
	// n house k colors
	// TC: O(n*k^2)
	// SC: O(k)
	public int minCostII_I(int[][] costs) {
		int n = costs.length, k = costs[0].length;
		// minCosts[i][c] represents the min cost of painting house n into color c
		// (n % 2) == i
		// minCosts[0][:] and minCosts[1][:] is updated in every two rounds
		int[][] minCosts = new int[2][k];
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				for (int j = 0; j < k; j++) minCosts[0][j] = costs[0][j];
			} else {
				// update minCosts[i % 2][x] for house i
				for (int x = 0; x < k; x++) {
					int min = Integer.MAX_VALUE;
					for (int y = 0; y < k; y++) {
						if (y == x) continue;   // can not use same color for adjacent house
						min = Math.min(min, minCosts[(i - 1) % 2][y]);
					}
					minCosts[i % 2][x] = min + costs[i][x];
				}
			}
		}

		// find the last round min cost
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < k; i++) {
			min = Math.min(min, minCosts[(n - 1) % 2][i]);
		}
		return min;
	}

	// Method 2: improve time/space complexity
	// no need to store all minCosts of previous round
	// only need to store previous round's <min, minColor a, secondMin>
	// for this round, round i,
	// minCost[a] = second min + costs[i][a]
	// minCosts[other colors] = min + costs[i][other colors]
	// update tuples <min, minColor, secondMin> for this round
	// TC: O(n*k)
	// SC: O(1)
	public int minCostII_II(int[][] costs) {
		int n = costs.length, k = costs[0].length;
		// init Tuple[0] : min, minColor, secondMin based on costs of first house
		// Tuple[0] for even house number mins info.
		// Tuple[1] for odd house number mins info.
		// update alternately
		Tuple[] mins = {new Tuple(), new Tuple()};
		for (int i = 0; i < k; i++) {
			int cost = costs[0][i];
			if (cost < mins[0].min) {
				// previous min become second min
				mins[0].secondMin = mins[0].min;
				mins[0].min = cost;
				mins[0].minColor = i;
			} else if (cost < mins[0].secondMin) {
				mins[0].secondMin = cost;
			}
		}

		// for each round, based on the min and second min of previous round
		for (int i = 1; i < n; i++) {
			Tuple prevMins = mins[(i - 1) % 2];
			Tuple currMins = mins[i % 2];
			currMins.min = Integer.MAX_VALUE;
			currMins.minColor = -1;
			currMins.secondMin = Integer.MAX_VALUE;
			for (int j = 0; j < k; j++) {
				// if j is previous round min color
				// can only use previous second min
				// other colors can use previous min cost
				int cost = costs[i][j] + (j == prevMins.minColor ? prevMins.secondMin : prevMins.min);
				// update this round Tuple: min, minColor, secondMin
				if (cost < currMins.min) {
					// previous min become second min
					currMins.secondMin = currMins.min;
					currMins.min = cost;
					currMins.minColor = j;
				} else if (cost < currMins.secondMin) {
					currMins.secondMin = cost;
				}
			}
		}

		return mins[(n - 1) % 2].min;
	}

	private static class Tuple {
		int min;
		int minColor;
		int secondMin;
		public Tuple() {
			min = Integer.MAX_VALUE;
			minColor = -1;
			secondMin = Integer.MAX_VALUE;
		}
	}

	public static void main(String[] args) {
		Test265_PaintHouseII t = new Test265_PaintHouseII();
		System.out.println(t.minCostII_II(new int[][]{
				{3,14,12,2,20,16,12,2},{9,6,9,8,2,9,20,18},{20,2,20,4,5,12,11,11},{16,3,7,5,15,2,2,4},{17,3,11,1,9,5,7,11}
		}));
	}

}