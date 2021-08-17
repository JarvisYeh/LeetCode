package Algorithm.Graph;

public class Test130_SurroundedRegions {
	// TC: O(2*m*n)
	// SC: O(1)
	public void solve(char[][] board) {
		int m = board.length, n = board[0].length;

		// mark board connected cell as '1'
		for (int i = 0; i < m; i++) {
			// check left most, right most, mark board connected node '1'
			if (board[i][0] == 'O') markBoard(i, 0, board);
			if (board[i][n - 1] == 'O') markBoard(i, n - 1, board);
		}

		for (int j = 0; j < n; j++) {
			// check top, bottom, mark board connected node with '1'
			if (board[0][j] == 'O') markBoard(0, j, board);
			if (board[m - 1][j] == 'O') markBoard(m - 1, j, board);
		}


		// traverse all cell again, mark board cell back to 'O'
		// mark all other 'O' to 'X' (i.e. flip them)
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O') {
					// flip those O that is not connect to board
					board[i][j] = 'X';
				} else if (board[i][j] == '1') {
					board[i][j] = 'O';
				}
			}
		}
	}

	private void markBoard(int i, int j, char[][] board) {
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
		if (board[i][j] == 'X' || board[i][j] == '1') return;

		board[i][j] = '1';
		markBoard(i + 1, j, board);
		markBoard(i - 1, j, board);
		markBoard(i, j + 1, board);
		markBoard(i, j - 1, board);
	}
}
