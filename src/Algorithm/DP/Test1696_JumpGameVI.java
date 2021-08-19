package Algorithm.DP;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test1696_JumpGameVI {
	// DP + sliding window
	// TC: O(n)
	// SC: O(n)
	public int maxResult(int[] nums, int k) {
		int n = nums.length;
		int[] scores = new int[n];
		scores[n - 1] = nums[n - 1];

		// use deque to simulate max value in sliding window problem
		// [large -> small]
		Deque<Integer> window = new ArrayDeque<>();
		window.offerFirst(n - 1);
		for (int i = n - 2; i >= 0; i--) {
			// pop until the window max is in range [i, i + k]
			while (window.peekFirst() > i + k) window.pollFirst();
			scores[i] = scores[window.peekFirst()] + nums[i];
			// add score[i] into window
			while (!window.isEmpty() && scores[window.peekLast()] <= scores[i]) window.pollLast();
			window.offerLast(i);
		}
		return scores[0];
	}

	public static void main(String[] args) {
		Test1696_JumpGameVI t = new Test1696_JumpGameVI();
		System.out.println(
				t.maxResult(new int[]{1, -1, -2, 4, -7, 3}, 2)
		);
	}
}
