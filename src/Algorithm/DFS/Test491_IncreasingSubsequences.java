package Algorithm.DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Test491_IncreasingSubsequences {
	// DFS
	// worst case all increasing
	// recursion tree n levels, n child at most
	// upper bound: O(n^n)
	// space complexity: O(n^2), each level has a hashset
	public List<List<Integer>> findSubsequences(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		dfs(0, nums, new ArrayList<>(), res);
		return res;
	}

	private void dfs(int idx, int[] nums, List<Integer> curr, List<List<Integer>> res) {
		if (curr.size() > 1) {
			res.add(new ArrayList<>(curr));
		}

		HashSet<Integer> used = new HashSet<>();
		for (int i = idx; i < nums.length; i++) {
			if (curr.size() == 0 || nums[i] >= curr.get(curr.size() - 1)) {
				if (used.contains(nums[i])) continue;
				curr.add(nums[i]);
				used.add(nums[i]);
				dfs(i + 1, nums, curr, res);
				curr.remove(curr.size() - 1);
			}
		}
	}


	public static void main(String[] args) {
		Test491_IncreasingSubsequences t = new Test491_IncreasingSubsequences();
		System.out.println(t.findSubsequences(new int[]{1, 2, 3, 4, 5, 5, 4}));
	}
}
