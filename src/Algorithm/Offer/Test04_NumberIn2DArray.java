package Algorithm.Offer;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Test04_NumberIn2DArray {
	// 在右上角，整个matrix是一个类BST
	// j--变小
	// i++变大
	public boolean findNumberIn2DArray(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) return false;
		int i = 0, j = matrix[0].length - 1;
		while (i < matrix.length && j >= 0) {
			if (matrix[i][j] > target) {
				// can only go left for smaller number
				// numbers in current columns are all ruled out
				// so every time the numbers on the right hand side of the current numbers are all ruled out
				j--;
			} else if (matrix[i][j] < target) {
				// can only go down for larger number
				// numbers in current rows are all ruled out
				// so every time the numbers above the current numbers are all ruled out
				i++;
			} else {
				return true;
			}
		}
		return false;
	}
}
