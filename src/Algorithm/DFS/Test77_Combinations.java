package Algorithm.DFS;

import java.util.ArrayList;
import java.util.List;

public class Test77_Combinations {
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		helper(1, n, k, new ArrayList<>(), res);
		return res;
	}

	private void helper(int num, int n, int k, List<Integer> curr, List<List<Integer>> res) {
		if (curr.size() == k) {
			res.add(new ArrayList<>(curr));
			return;
		}

		if (num == n + 1) {
			return;
		}

		curr.add(num);
		helper(num + 1, n, k, curr, res);
		curr.remove(curr.size() - 1);
		helper(num + 1, n, k, curr, res);
	}
}
