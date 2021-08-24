package Algorithm.DP;

public class Test97_InterleavingString {
	public boolean canMerge(String a, String b, String c) {
		int n1 = a.length(), n2 = b.length(), n3 = c.length();
		if (n1 + n2 != n3) return false;
		// DP[i][j]: whether first i letters in a and j letters in b can be merged into c[0:i + j]
		boolean[][] DP = new boolean[n1 + 1][n2 + 1];

		// base cases
		// a:"", b"" => c: ""
		DP[0][0] = true;
		// a:"xxx", b"" => c: "yyy"
		for (int i = 1; i <= n1; i++) {
			if (a.charAt(i - 1) == c.charAt(i - 1)) DP[i][0] = DP[i - 1][0];
		}
		// a:"", b"xxx" => c: "yyy"
		for (int j = 1; j <= n2; j++) {
			if (b.charAt(j - 1) == c.charAt(j - 1)) DP[0][j] = DP[0][j - 1];
		}

		for (int i = 1; i <= n1; i++) {
			for (int j = 1; j <= n2; j++) {
				// a[i - 1] = b[j - 1] = c[i + j - 1]
				// can match a[i - 1] || b[j - 1]
				if (a.charAt(i - 1) == c.charAt(i + j - 1) && b.charAt(j - 1) == c.charAt(i + j - 1)) {
					DP[i][j] = DP[i - 1][j] || DP[i][j - 1];
				}
				// b[j - 1] = c[i + j - 1], can only match b[j - 1]
				else if (b.charAt(j - 1) == c.charAt(i + j - 1)) {
					DP[i][j] = DP[i][j - 1];
				}
				// a[i - 1] = c[i + j - 1], can only match a[i - 1]
				else if (a.charAt(i - 1) == c.charAt(i + j - 1)) {
					DP[i][j] = DP[i - 1][j];
				}
			}
		}
		return DP[n1][n2];
	}
}
