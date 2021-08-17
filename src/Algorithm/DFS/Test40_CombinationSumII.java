package Algorithm.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test40_CombinationSumII {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> res = new ArrayList<>();
		dfs(0, new ArrayList<>(), res, candidates, target);
		return res;
	}

	private void dfs(int idx, List<Integer> currList, List<List<Integer>> res, int[] nums, int remain) {
		if (remain == 0) {
			res.add(new ArrayList<>(currList));
			return;
		}
		if (idx == nums.length) return;

		// add nums[idx]
		if (nums[idx] <= remain) {
			currList.add(nums[idx]);
			dfs(idx + 1, currList, res, nums, remain - nums[idx]);
			currList.remove(currList.size() - 1);
		}
		// not add nums[idx]
		while (idx + 1 < nums.length && nums[idx + 1] == nums[idx]) idx++;
		dfs(idx + 1, currList, res, nums, remain);
	}
}
