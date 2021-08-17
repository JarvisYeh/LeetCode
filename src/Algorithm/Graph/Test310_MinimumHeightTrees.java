package Algorithm.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Test310_MinimumHeightTrees {
	// 从最外围的leaf node开始，每次剥离一层leaf node
	// 剥离到最后，剩下的1/2 nodes就是height最小的root/roots
	// topological sort
	// TC: O(V + E) one node will be traversed once, traverse a node has O(nei) (i.e. its edges) complexity
	// SC: O(V + E) for adjacency list representation of graph, O(V) for degree array
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		List<Integer> res = new ArrayList<>();
		// corner case
		if (n == 1) {
			res.add(0);
			return res;
		}

		// construct degree array and adjacency list representation of graph
		List<List<Integer>> graph = new ArrayList<>();
		int[] degree = new int[n];
		for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
			degree[edge[0]]++;
			degree[edge[1]]++;
		}


		// init queue with those degree = 1 (leaf node)
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			if (degree[i] == 1) q.offer(i);
		}

		int remainNode = n;
		while (remainNode > 2) {
			// remove all leaf node
			remainNode -= q.size();
			// traverse all the current leaf nodes
			for (int i = q.size(); i > 0; i--) {
				int leave = q.poll();
				for (int nei : graph.get(leave)) {
					degree[nei]--;
					if (degree[nei] == 1) q.offer(nei); // become new leaf node
				}
			}
		}
		// after the loop, the remainNode will be 1 or 2
		// those nodes will be in queue, and are final result roots

		while (!q.isEmpty()) {
			res.add(q.poll());
		}
		return res;
	}
}
