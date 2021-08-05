package Algorithm.StackQueueDeque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Test503_NextGreaterElementII {
	// loop twice to solve rotation problem
	// like put left elements of last one to the right of it
	public int[] nextGreaterElements(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		Arrays.fill(res, -1);
		// non-increasing order stack, store index of each number
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < nums.length*2; i++) {
			int idx = i % n;
			while (!stack.isEmpty() && nums[stack.peekFirst()] < nums[idx]) {
				res[stack.pollFirst()] = nums[idx];
			}
			stack.offerFirst(idx);
		}

		return res;
	}

	public static void main(String[] args) {
		Test503_NextGreaterElementII t = new Test503_NextGreaterElementII();
		t.nextGreaterElements(new int[]{1, 2, 1});
	}
}
