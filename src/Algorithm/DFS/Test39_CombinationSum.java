package Algorithm.DFS;

import java.util.ArrayList;
import java.util.List;

public class Test39_CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		dfs(0, new ArrayList<>(), res, candidates, target);
		return res;
	}

	private void dfs(int idx, List<Integer> currList, List<List<Integer>> res, int[] nums, int remain) {
		if (idx == nums.length) {
			if (remain == 0) res.add(new ArrayList<>(currList));
			return;
		}

		// not add nums[idx]
		dfs(idx + 1, currList, res, nums, remain);

		// add 1, 2, 3,... nums[idx]
		int len = currList.size();
		for (int i = 1; i * nums[idx] <= remain; i++) {
			currList.add(nums[idx]);
			dfs(idx + 1, currList, res, nums, remain - i * nums[idx]);
		}
		currList.subList(len, currList.size()).clear();
	}
}
