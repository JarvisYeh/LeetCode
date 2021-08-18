package Algorithm.Arrays;

public class Test849_MaximizeDistancetoClosestPerson {
	// Method 1: DP three pass
	// TC: O(n)
	// SC: O(n)
	public int maxDistToClosestI(int[] seats) {
		int n = seats.length;
		// how many seats to left of i
		// between i and the closest person
		int[] left = new int[seats.length];
		// how many seats to right of i
		// between i and the closest person
		int[] right = new int[seats.length];

		left[0] = right[n - 1] = Integer.MAX_VALUE;
		for (int i = 1; i < n; i++) {
			if (seats[i - 1] == 1) {
				left[i] = 0;
			} else {
				left[i] = left[i - 1] == Integer.MAX_VALUE ? Integer.MAX_VALUE : (left[i - 1]  + 1);
			}
		}

		for (int i = n - 2; i >= 0; i--) {
			if (seats[i + 1] == 1) {
				right[i] = 0;
			} else {
				right[i] = right[i + 1] == Integer.MAX_VALUE ? Integer.MAX_VALUE : (right[i + 1]  + 1);
			}
		}

		int max = 0;
		for (int i = 0; i < n; i++) {
			if (seats[i] == 1) continue;
			max = Math.max(max, Math.min(left[i], right[i]) + 1);
		}
		return max;
	}

	// Method 1: two pointers one pass
	// TC: O(n)
	// SC: O(1)
	public int maxDistToClosestII(int[] seats) {
		int n = seats.length;
		int left = -1, max = 0;
		for (int i = 0; i < n; i++) {
			if (seats[i] == 0) continue;
			if (left == -1) {
				max = Math.max(max, i);
			} else {
				// calculate the gaps between two persons
				// left is the previous person's position
				int gap = i - left;
				max = Math.max(max, gap/2);
			}
			left = i;
		}

		// if the last position is empty, still need to process that position
		if (seats[n - 1] == 0) {
			max = Math.max(max, n - 1 - left);
		}
		return max;
	}
}
