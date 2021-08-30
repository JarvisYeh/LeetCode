package Algorithm.DP;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test32_LongestValidParentheses {
	// Method 1: use stack
	// TC: O(n)
	// SC: O(n)
	public int longestValidParenthesesI(String s) {
		// stack stores the index of each brackets
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.offerFirst(i);
			} else {    // s[i] = ')'
				if (!stack.isEmpty() && s.charAt(stack.peekFirst()) == '(') {
					stack.pollFirst();
				} else {
					stack.offerFirst(i);
				}
			}
		}

		// now what left in stack are the indexes of brackets that are not valid
		// the range between two invlid indexes are valid range
		// the indexes from stack[top -> bottom] is [large index -> small index]
		int rangeEnd = s.length();
		int max = 0;
		while (!stack.isEmpty()) {
			int rangeStart = stack.pollFirst() + 1;
			// valid range: [start, end)
			max = Math.max(max, rangeEnd - rangeStart);
			rangeEnd = rangeStart - 1;
		}
		// last valid range is [0, end)
		max = Math.max(max, rangeEnd);
		return max;
	}

	// Method 2: DP
	// TC: O(n)
	// SC: O(n)
	public int longestValidParenthesesII(String s) {
		int n = s.length();
		// DP[i] is the longest valid parentheses end at i
		int[] DP = new int[n];

		int max = 0;
		// base case: DP[0] = 0
		for (int i = 1; i < n; i++) {
			if (s.charAt(i) == ')') {
				//     p-1 p    c
				//    ()   (()())
				int prevLeftIdx = i - DP[i - 1] - 1;
				// only when the previous matched '(' is found, DP[i] >= 0
				if (prevLeftIdx >= 0 && s.charAt(prevLeftIdx) == '(') {
					DP[i] = DP[i - 1] + 2 + (prevLeftIdx - 1 >= 0 ? DP[prevLeftIdx - 1] : 0);
					max = Math.max(max, DP[i]);
				}
			}
			// if s[i] == '(', substring end at i is certainly invalid, DP[i] = 0
		}
		return max;
	}
}
