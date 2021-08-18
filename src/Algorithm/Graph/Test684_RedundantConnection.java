package Algorithm.Graph;

public class Test684_RedundantConnection {
	// TC: O(E)
	public int[] findRedundantConnection(int[][] edges) {
		if (edges == null || edges.length == 0) return new int[0];
		int n = 1;
		for (int[] edge : edges) {
			n = Math.max(n, edge[0]);
			n = Math.max(n, edge[1]);
		}
		// all vertex is an individual cluster initially
		int[] clusters = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			clusters[i] = i;
		}
		int[] redundantEdge = null;

		for (int[] edge : edges) {
			int rootA = find(edge[0], clusters), rootB = find(edge[1], clusters);
			// if two vertex are already in a same cluster
			// meaning there is a path between them already
			// the new edge is a redundant edge
			// the question only asked for last redundant edge
			if (rootA == rootB) {
				redundantEdge = edge;
			} else {
				// union rootA to rootB if they are not in a same clusters
				clusters[rootA] = rootB;
			}
		}
		return redundantEdge;
	}

	private int find(int i, int[] clusters) {
		if (clusters[i] == i) return i;
		clusters[i] = find(clusters[i], clusters);
		return clusters[i];
	}
}
