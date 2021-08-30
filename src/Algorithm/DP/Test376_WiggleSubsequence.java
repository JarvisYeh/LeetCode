package Algorithm.DP;

public class Test376_WiggleSubsequence {
	public int wiggleMaxLengthI(int[] nums) {
		if (nums.length == 1) return 1;
		int n = nums.length;
		// up[i] is the longest wiggle subsequence length ended at i
		// where prev to i is going up
		int[] up = new int[n];
		// down[i] is the longest wiggle subsequence length ended at i
		// where prev to i is going down
		int[] down = new int[n];
		up[0] = down[0] = 1;
		for (int i = 1; i < n; i++) {
			int maxUp = 1;
			int maxDown = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] > nums[i]) {
					// j to i goes down
					maxDown = Math.max(maxDown, up[j] + 1);
				} else if (nums[j] < nums[i]) {
					// j to i goes up
					maxUp = Math.max(maxUp, down[j] + 1);
				}
			}
			up[i] = maxUp;
			down[i] = maxDown;
		}
		return Math.max(up[n - 1], down[n - 1]);
	}

	// SC improved to O(1)
	public int wiggleMaxLengthII(int[] nums) {
		// up is the longest wiggle subsequence from [0, i] NO NEED to end at i
		// where prev to end is going up
		// vice versa for down
		int up = 1, down = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i - 1]) {
				up = down + 1;
			} else if (nums[i] < nums[i - 1]){
				down = up + 1;
			}
		}
		return Math.max(up, down);
	}
}
