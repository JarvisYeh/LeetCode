package Algorithm.Graph;

import java.util.*;

public class Test323_NumberofConnectedComponentsinanUndirectedGraph {
	// method 1:
	// union find
	// TC: O(E*1)
	// SC: O(V)
	public int countComponentsI(int n, int[][] edges) {
		// init n clusters
		int[] clusters = new int[n];
		int countOfClusters = n;
		for (int i = 0; i < n; i++) {
			clusters[i] = i;
		}

		for (int[] edge : edges) {
			int rootA = find(edge[0], clusters);
			int rootB = find(edge[1], clusters);
			// merge two clusters connected by an edge
			if (rootA != rootB) {
				clusters[rootA] = rootB;
				countOfClusters--;
			}
		}
		return countOfClusters;
	}

	// return the root of the cluster that node belongs to
	private int find(int node, int[] clusters) {
		if (clusters[node] == node) return node;
		clusters[node] = find(clusters[node], clusters);
		return clusters[node];
	}

	// method 2:
	// DFS
	// TC: O(V + E)
	// SC: O(V + E) for hashMap represent graph
 	public int countComponentsII(int n, int[][] edges) {
		HashMap<Integer, List<Integer>> graph = new HashMap<>();
		for (int[] edge : edges) {
			List<Integer> l1 = graph.getOrDefault(edge[0], new ArrayList<>());
			List<Integer> l2 = graph.getOrDefault(edge[1], new ArrayList<>());
			l1.add(edge[1]);
			l2.add(edge[0]);
			graph.put(edge[0], l1);
			graph.put(edge[1], l2);
		}

		boolean[] visited = new boolean[n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				count++;
				dfs(i, graph, visited);
			}
		}
		return count;
	}

	private void dfs(int node, HashMap<Integer, List<Integer>> graph, boolean[] visited) {
		if (visited[node]) return;
		visited[node] = true;
		if (!graph.containsKey(node)) return;
		for (int nei : graph.get(node)) {
			dfs(nei, graph, visited);
		}
	}


	// method 3:
	// BFS
	// TC: O(V + E)
	// SC: O(V + E) for hashMap represent graph
	public int countComponentsIII(int n, int[][] edges) {
		HashMap<Integer, List<Integer>> graph = new HashMap<>();
		for (int[] edge : edges) {
			List<Integer> l1 = graph.getOrDefault(edge[0], new ArrayList<>());
			List<Integer> l2 = graph.getOrDefault(edge[1], new ArrayList<>());
			l1.add(edge[1]);
			l2.add(edge[0]);
			graph.put(edge[0], l1);
			graph.put(edge[1], l2);
		}

		boolean[] visited = new boolean[n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				count++;
				Queue<Integer> q = new ArrayDeque<>();
				q.offer(i);
				while (!q.isEmpty()) {
					int curr = q.poll();
					visited[curr] = true;
					if (!graph.containsKey(curr)) continue;
					for (int nei : graph.get(curr)) {
						if (!visited[nei]) {
							q.offer(nei);
						}
					}
				}
			}
		}
		return count;
	}
}
