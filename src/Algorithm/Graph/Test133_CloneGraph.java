package Algorithm.Graph;

import java.util.*;
import java.util.stream.Collectors;

public class Test133_CloneGraph {
	static class Node {
		int val;
		List<Node> neighbors;
		public Node(int val) {
			this.val = val;
			this.neighbors = new ArrayList<>();
		}
	}

	// Method 1: BFS
	public Node cloneGraphI(Node node) {
		if (node == null) return null;
		HashMap<Node, Node> generated = new HashMap<>();
		Queue<Node> q = new ArrayDeque<>();
		q.offer(node);
		generated.put(node, new Node(node.val));
		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (Node nei : curr.neighbors) {
				Node neiCopy = generated.getOrDefault(nei, null);
				if (neiCopy == null) {
					neiCopy = new Node(nei.val);
					generated.put(nei, neiCopy);
					q.offer(nei);
				}
				generated.get(curr).neighbors.add(neiCopy);
			}
		}
		return generated.get(node);
	}

	// Method 2: DFS
	public Node cloneGraph(Node node) {
		if (node == null) return null;
		HashMap<Node, Node> nodeMap =  new HashMap<>();
		return helper(node, nodeMap);
	}

	private Node helper(Node node, HashMap<Node, Node> nodeMap) {
		Node nodeCopy = new Node(node.val);
		nodeMap.put(node, nodeCopy);

		for (Node nei : node.neighbors) {
			Node neiCopy = nodeMap.getOrDefault(nei, null);
			if (neiCopy == null) {
				neiCopy = helper(nei, nodeMap);
			}
			nodeCopy.neighbors.add(neiCopy);
		}
		return nodeCopy;
	}

}
