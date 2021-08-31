package Algorithm.Graph;

import java.util.*;

public class Test787_CheapestFlightsWithinKStops {
	// Method 1: DFS
	// TLE
	// TC: O(V + E)
	// SC: O(V + E)
	public int findCheapestPriceI(int n, int[][] flights, int src, int dst, int k) {
		int[] minPrice = {Integer.MAX_VALUE};
		HashMap<Integer, List<int[]>> flightsMap = new HashMap<>();
		// key = src
		// value = list of <dst, price>
		for (int[] f : flights) {
			flightsMap.putIfAbsent(f[0], new ArrayList<>());
			flightsMap.get(f[0]).add(new int[]{f[1], f[2]});
		}
		// k stops can go (1(start) + 1(end) + stops) locations
		dfs(src, dst, flightsMap, 0, minPrice, k + 2);
		return minPrice[0] == Integer.MAX_VALUE ? -1 : minPrice[0];
	}

	private void dfs(int curr, int dst,
					 HashMap<Integer, List<int[]>> flightsMap,
					 int currPrice, int[] minPrice, int remain) {
		if (remain <= 0) return;
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

	// Method 2: Dijkstra Algorithm
	public int findCheapestPriceII(int n, int[][] flights, int src, int dst, int k) {
		HashMap<Integer, List<int[]>> flightsMap = new HashMap<>();
		// key = src
		// value = list of <dst, price>
		for (int[] f : flights) {
			flightsMap.putIfAbsent(f[0], new ArrayList<>());
			flightsMap.get(f[0]).add(new int[]{f[1], f[2]});
		}

		int[] minPrices = new int[n];
		Arrays.fill(minPrices, Integer.MAX_VALUE);
		minPrices[src] = 0;
		// minHeap stores {some dst, price, stop amount}
		// priority w.r.t. price from src to dst
		Queue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>(){
			@Override
			public int compare(int[] route1, int[] route2) {
				return Integer.compare(route1[1], route2[1]);
			}
		});
		minHeap.offer(new int[]{src, 0, -1});

		while (!minHeap.isEmpty()) {
			int[] curr = minHeap.poll();
			int currNode = curr[0];
			int currPrice = curr[1];
			int currStop = curr[2];
			// minHeap always pops smallest price destination
			// if dst exist in minHeap, the smaller price one will be popped out first
			// and since it's in minHeap, it's in k stops range
			if (currNode == dst) return currPrice;
			// at most k stop, only enqueue valid destinations
			if (currStop < k) {
				List<int[]> neis = flightsMap.get(curr[0]);
				if (neis == null) continue;
				for (int[] nei : neis) {
					if (minPrices[nei[0]] > currPrice + nei[1]) {
						minHeap.offer(new int[]{nei[0], currPrice + nei[1], currStop + 1});
					}
				}
			}
		}
		return -1;
	}


	public static void main(String[] args) {
		Test787_CheapestFlightsWithinKStops t = new Test787_CheapestFlightsWithinKStops();
		System.out.println(
				t.findCheapestPriceII(5, new int[][]{{0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}}, 0 ,2, 2)
		);
	}
}
