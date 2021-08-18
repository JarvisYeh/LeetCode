package Algorithm.Tree;

import util.TreeNode;

import java.util.*;

public class Test987_VerticalOrderTraversalOfABinaryTree {
	// Method 1: DFS
	public List<List<Integer>> verticalTraversalI(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		// TreeSet here is worked here as an ordered list
		TreeMap<Integer, List<Tuple>> tupleListMap = new TreeMap<>();
		dfs(new Tuple(0, 0, root), tupleListMap);

		// for each tupleList (all tuple in same list has same idx)
		// sort w.r.t level first, then value
		for (List<Tuple> tupleList  : tupleListMap.values()) {
			tupleList.sort((t1, t2) -> {
				if (Integer.compare(t1.level, t2.level) == 0)
					return Integer.compare(t1.node.key, t2.node.key);
				return Integer.compare(t1.level, t2.level);
			});
			// extract treeNode value from tupleList
			List<Integer> list = new ArrayList<>();
			for (Tuple t : tupleList) {
				list.add(t.node.key);
			}
			res.add(list);
		}
		return res;
	}

	private void dfs(Tuple tuple, TreeMap<Integer, List<Tuple>> tupleListMap) {
		if (tuple.node == null) return;
		// tupleListMap[idx] is the list w.r.t. idx
		List<Tuple> list = tupleListMap.getOrDefault(tuple.idx, new ArrayList<Tuple>());
		list.add(tuple);
		tupleListMap.put(tuple.idx, list);

		dfs(new Tuple(tuple.level + 1, tuple.idx - 1, tuple.node.left), tupleListMap);
		dfs(new Tuple(tuple.level + 1, tuple.idx + 1, tuple.node.right), tupleListMap);
	}


	// Method 2: BFS
	public List<List<Integer>> verticalTraversalII(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		Queue<Tuple> q = new ArrayDeque<>();
		TreeMap<Integer, List<Tuple>> listMap = new TreeMap<>();
		q.offer(new Tuple(0, 0, root));
		while (!q.isEmpty()) {
			Tuple curr = q.poll();
			List<Tuple> list = listMap.getOrDefault(curr.idx, new ArrayList<>());
			list.add(curr);
			listMap.put(curr.idx, list);
			if (curr.node.left != null) q.offer(new Tuple(curr.level + 1, curr.idx - 1, curr.node.left));
			if (curr.node.right != null) q.offer(new Tuple(curr.level + 1, curr.idx + 1, curr.node.right));
		}

		// for each tupleList (all tuple in same list has same idx)
		// sort w.r.t level first, then value
		for (List<Tuple> tupleList  : listMap.values()) {
			tupleList.sort((t1, t2) -> {
				if (Integer.compare(t1.level, t2.level) == 0)
					return Integer.compare(t1.node.key, t2.node.key);
				return Integer.compare(t1.level, t2.level);
			});
			// extract treeNode value from tupleList
			List<Integer> list = new ArrayList<>();
			for (Tuple t : tupleList) {
				list.add(t.node.key);
			}
			res.add(list);
		}
		return res;
	}

	static class Tuple {
		int level;
		int idx;
		TreeNode node;
		public Tuple(int level, int idx, TreeNode node) {
			this.level = level;
			this.idx = idx;
			this.node = node;
		}
	}

	public static void main(String[] args) {
		TreeNode root = TreeNode.formTreeByLayer(new Integer[]{3, 5, 10, null, 4, 8, null, null, 9});
		TreeNode.printTree(root);
		Test987_VerticalOrderTraversalOfABinaryTree t = new Test987_VerticalOrderTraversalOfABinaryTree();
		System.out.println(t.verticalTraversalII(root));
	}
}
