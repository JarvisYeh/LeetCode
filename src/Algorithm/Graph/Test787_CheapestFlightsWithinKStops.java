package Algorithm.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test787_CheapestFlightsWithinKStops {
	// Method 1: TLE
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		int[] minPrice = {Integer.MAX_VALUE};
		HashMap<Integer, List<int[]>> flightsMap = new HashMap<>();
		// key = src
		// value = list of <dst, price>
		for (int[] f : flights) {
			flightsMap.putIfAbsent(f[0], new ArrayList<>());
			flightsMap.get(f[0]).add(new int[]{f[1], f[2]});
		}
		dfs(src, dst, flightsMap, 0, minPrice, k);
		return minPrice[0] == Integer.MAX_VALUE ? -1 : minPrice[0];
	}

	private void dfs(int curr, int dst,
					 HashMap<Integer, List<int[]>> flightsMap,
					 int currPrice, int[] minPrice, int remain) {
		if (remain < 0) return;
		if (curr == dst) {
			minPrice[0] = Math.min(minPrice[0], currPrice);
			return;
		}

		if (flightsMap.get(curr) == null) return;
		for (int[] next : flightsMap.get(curr)) {
			// if fly to next costs more than curr minPrice, skip this route
			if (currPrice + next[1] > minPrice[0]) continue;
			dfs(next[0], dst, flightsMap, currPrice + next[1], minPrice, remain - 1);
		}
	}

	public static void main(String[] args) {
		Test787_CheapestFlightsWithinKStops t = new Test787_CheapestFlightsWithinKStops();
		System.out.println(
				t.findCheapestPrice(3, new int[][]{{0,1,100},{1,2,100},{0,2,500}},0,2,1)
		);
	}
}
