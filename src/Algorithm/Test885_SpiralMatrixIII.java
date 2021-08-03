package Algorithm;

import Design.D2_ParkingLotExp.Test;

public class Test885_SpiralMatrixIII {
	public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
		int[][] res = new int[rows*cols][];
		int[] curr = {rStart, cStart};
		int size = 1;
		int n = 0;
		while (n < rows*cols) {
			// traverse right size step
			for (int i = 0; i < size; i++) {
				if (n == rows*cols) {
					break;
				}
				if (checkValid(curr[0], curr[1], rows, cols)) {
					res[n++] = new int[]{curr[0], curr[1]};
				}
				// move right
				curr[1]++;
			}

			// traverse down size step
			for (int i = 0; i < size; i++) {
				if (n == rows*cols) {
					break;
				}
				if (checkValid(curr[0], curr[1], rows, cols)) {
					res[n++] = new int[]{curr[0], curr[1]};
				}
				// move down
				curr[0]++;
			}

			// traverse left size + 1 step
			for (int i = 0; i < size + 1; i++) {
				if (n == rows*cols) {
					break;
				}
				if (checkValid(curr[0], curr[1], rows, cols)) {
					res[n++] = new int[]{curr[0], curr[1]};
				}
				// move left
				curr[1]--;
			}

			// traverse up size + 1 step
			for (int i = 0; i < size + 1; i++) {
				if (n == rows*cols) {
					break;
				}
				if (checkValid(curr[0], curr[1], rows, cols)) {
					res[n++] = new int[]{curr[0], curr[1]};
				}
				// move up
				curr[0]--;
			}
			size += 2;
		}
		return res;

	}

	private boolean checkValid(int i, int j, int rows, int cols) {
		return i >= 0 && i < rows && j >= 0 && j < cols;
	}

	public static void main(String[] args) {
		Test885_SpiralMatrixIII t = new Test885_SpiralMatrixIII();
		t.spiralMatrixIII(1, 4, 0, 0);
	}

}
