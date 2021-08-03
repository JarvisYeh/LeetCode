package Algorithm.DP;

public class Test279_PerfectSquares {
	// perfect square is an integer who is a square of another integer
	// e.g. 1 4 9 16 ...
	public int numSquaresI(int n) {
		// count[i] is the least number of perfect squares sum up to i
		int[] count = new int[n + 1];

		// base case: 1 = 1
		count[1] = 1;


		for (int i = 2; i <= n; i++) {
			// if i is a perfect number, only need itself
			if (isPerfectNumber(i)) {
				count[i] = 1;
			} else {
				int min = Integer.MAX_VALUE;
				// divide the number into j and i - j
				// check each possibility
				for (int j = 1; j < i; j++) {
					int left = count[j];
					int right = count[i - j];
					min = Math.min(left + right, min);
				}
				count[i] = min;
			}
		}
		return count[n];
	}

	private boolean isPerfectNumber(int num) {
		int sqrt = (int) Math.sqrt(num);
		return sqrt * sqrt == num;
	}



	public int numSquaresII(int n) {
		// count[i] is the least number of perfect squares sum up to i
		int[] count = new int[n + 1];

		// base case: 1 = 1
		count[1] = 1;

		for (int i = 2; i < count.length; i++) {
			// divide i into a perfect number p and (i - p)
			int min = Integer.MAX_VALUE;
			for (int j = 1; j*j <= i; j++) {
				int left = count[i - j*j];
				int right = 1;
				min = Math.min(left + right, min);
			}
			count[i] = min;
		}
		return count[n];
	}
}
