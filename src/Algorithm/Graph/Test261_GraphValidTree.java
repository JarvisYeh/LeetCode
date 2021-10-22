package Algorithm.Graph;

import java.util.*;

public class Test261_GraphValidTree {
	// Method 1: DFS
	// TC: O(V + E)
	// SC: O(V)
	public boolean validTreeI(int n, int[][] edges) {
		// construct graph
		List<List<Integer>> graph = new ArrayList<>();
		// node from 0 to n - 1
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}

		// dfs the graph, start from node 0
		boolean[] visited = new boolean[n];
		int[] countVisit = {0};
		// check if there is cycle
		if (!hasCycle(0, -1, graph, visited, countVisit)) {
			// check if all n nodes are visited
			return countVisit[0] == n;
		}

		return false;
	}

	private boolean hasCycle(int node, int parent, List<List<Integer>> graph, boolean[] visited, int[] count) {
		// visit a node that is visited before, return true directly
		if (visited[node]) return true;

		// traverse that node
		visited[node] = true;
		count[0]++;
		for (int nei : graph.get(node)) {
			// do not traverse back to parent
			if (nei != parent) {
				if (hasCycle(nei, node, graph, visited, count)) return true;
			}
		}
		return false;
	}


	// Method 2: BFS
	// TC: O(V + E)
	// SC: O(2*V)
	public boolean validTreeII(int n, int[][] edges) {
		// construct graph
		HashMap<Integer, HashSet<Integer>> edgeMap = new HashMap<>();
		for (int[] edge : edges) {
			HashSet<Integer> set0 = edgeMap.getOrDefault(edge[0], new HashSet<>());
			HashSet<Integer> set1 = edgeMap.getOrDefault(edge[1], new HashSet<>());
			set0.add(edge[1]);
			set1.add(edge[0]);
			edgeMap.put(edge[0], set0);
			edgeMap.put(edge[1], set1);
		}


		// init queue with node 0
		boolean[] generated = new boolean[n];
		Queue<Integer> q = new ArrayDeque<>();
		generated[0] = true;
		int generateCount = 1;
		q.offer(0);

		// bfs
		while (!q.isEmpty()) {
			int curr = q.poll();
			HashSet<Integer> neighbors = edgeMap.get(curr);
			if (neighbors == null) continue;
			for (int nei : neighbors) {
				if (generated[nei]) return false;
				// remove itself from nei's neighbors set, so that it won't traverse back
				edgeMap.get(nei).remove(curr);
				q.offer(nei);
				generated[nei] = true;
				generateCount++;
			}
		}
		return generateCount == n;
	}

	// Method 3: union find
	// find(i) has amortized O(\alpha(n)) time complexity
	// one call of find(node x), all ascendant node of x are resolved
	// union(a, b) is O(\alpha(n))
	// TC: O(E + V)
	// SC: O(V)
	public boolean validTreeIII(int n, int[][] edges) {
		// init n isolated cluster
		// each node is the root of its own cluster
		int[] clusters = new int[n];
		for (int i = 0; i < n; i++) {
			clusters[i] = i;
		}

		// union two vertex w.r.t edge
		for (int[] edge : edges) {
			int rootA = find(clusters, edge[0]);
			int rootB = find(clusters, edge[1]);

			// if edge connects two nodes belongs to a same cluster
			// meaning there is a cycle in graph
			// return false
			if (rootA == rootB) return false;

			// union rootA and rootB
			// i.e. assign rootB as rootA's root
			clusters[rootA] = rootB;
		}

		// if there is no cycle, still need to check if edges connects all node
		// i.e. after union, there is only 1 cluster
		int root = find(clusters, 0);
		for (int i : clusters) {
			if (root != find(clusters, i)) return false;
		}
		return true;
	}

	// recursively find
	// the root of cluster that node i belongs to
	private int find(int[] clusters, int i) {
		if (clusters[i] == i) return i;
		clusters[i] = find(clusters, clusters[i]);
		return clusters[i];
	}
}
