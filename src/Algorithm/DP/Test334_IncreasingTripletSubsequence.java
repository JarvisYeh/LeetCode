package Algorithm.DP;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test334_IncreasingTripletSubsequence {
	// Method 1: DP
	// TC: O(n^2)
	// SC: O(n^2)
	public boolean increasingTripletI(int[] array) {
		int n = array.length;
		// DP[i] is the longest subsequence ended at i
		int[] DP = new int[n];
		DP[0] = 1;
		for (int i = 1; i < n; i++) {
			DP[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (array[j] < array[i]) DP[i] = Math.max(DP[i], DP[j] + 1);
			}
			if (DP[i] >= 3) return true;
		}
		return false;
	}

	// Method 2: use trick, greedy
	// TC: O(n)
	// SC: O(1)
	public boolean increasingTripletII(int[] array) {
		int i = Integer.MAX_VALUE, j = Integer.MAX_VALUE;
		for (int num : array) {
			if (num <= i) {
				i = num;
			}
			// i < num <= j
			// update j means a larger value with larger index than i is found
			// so a increasing double is found
			else if (num <= j) {
				j = num;
			}
			// i < j < num
			// update j means a larger value with larger index than i and j is found
			// so a increasing triplet is found
			else {
				return true;
			}
		}
		return false;
	}



	public static void main(String[] args) {
		Test334_IncreasingTripletSubsequence t = new Test334_IncreasingTripletSubsequence();
		System.out.println(t.increasingTripletII(new int[]{1,2,0,3}));
	}
}
