package Algorithm.DP;

public class Test10_RegularExpressionMatching {
	public boolean isMatch(String input, String pattern) {
		int m = input.length(), n = pattern.length();

		// DP[i][j] whether first i in input matches first j in pattern
		boolean[][] DP = new boolean[m + 1][n + 1];
		// base cases: s="" & p=""; s="" & p="xxx"; s="xxx" & p=""
		DP[0][0] = true;
		for (int i = 1; i <= m; i++) DP[i][0] = false;
		for (int j = 1; j <= n; j++) {
			if (pattern.charAt(j - 1) == '*') {
				// if it's a *, skip its preceding element
				// xxxx a* => xxxx
				DP[0][j] = DP[0][j - 2];
			} else {
				DP[0][j] = false;
			}
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// xxx  a
				// xxx ./a
				if (input.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.') {
					DP[i][j] = DP[i - 1][j - 1];
				}
				// xxx aaaaa
				// xxx a*/b*
				else if (pattern.charAt(j - 1) == '*') {
					// xxx aaaaa
					// xxx a*
					// if preceding letter matches, can choose match 0/more letters
					if (pattern.charAt(j - 2) == '.' || input.charAt(i - 1) == pattern.charAt(j - 2)) {
						// match 0 || match more
						DP[i][j] = DP[i][j - 2] || DP[i - 1][j];
					} else {
						// preceding letter not match
						DP[i][j] = DP[i][j - 2];
					}
				}
			}
		}
		return DP[m][n];
	}

	public static void main(String[] args) {
		Test10_RegularExpressionMatching t = new Test10_RegularExpressionMatching();
		t.isMatch("abcdef", ".......*");
	}
}
