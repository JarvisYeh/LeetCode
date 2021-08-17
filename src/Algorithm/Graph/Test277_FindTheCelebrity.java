package Algorithm.Graph;

public class Test277_FindTheCelebrity {
	// celebrity definition
	// everyone knows him
	// he doesn't know anyone
	// assumption: there is guarantee to be at most one celebrity
	public int celebrity(int[][] matrix) {
		int n = matrix.length;
		// one pass to check for candidate
		int candidate = 0;
		for (int i = 1; i < n; i++) {
			// candidate knows i, meaning i should be the potential celebrity
			if (matrix[candidate][i] == 1) {
				candidate = i;
			}
			// candidate doesn't know i, meaning i can be not potential celebrity
		}

		for (int i = 0; i < n; i++) {
			if (i == candidate) continue;
			// if someone doesn't know candidate || candidate knows someone
			// there is no celebrity at all
			if (matrix[i][candidate] != 1 || matrix[candidate][i] == 1) return -1;
		}
		return candidate;
	}
}
