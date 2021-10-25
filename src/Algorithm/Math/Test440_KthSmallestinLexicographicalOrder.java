package Algorithm.Math;

public class Test440_KthSmallestinLexicographicalOrder {
	public int findKthNumber(int n, int k) {
		int currNum = 1;
		while (k > 1) {
			int subTreeAmount = getNumsInSubTree(n, currNum, currNum + 1);
			// k-th number is in this sub tree with root = currNum
			if (subTreeAmount >= k) {
				currNum = currNum*10;
				k--;    // remove one value from search range
			}
			// k is not in this subtree with root = currNum
			else {
				// check next root, which is currNum + 1
				currNum++;
				k -= subTreeAmount;
			}
		}
		return currNum;
	}

	private int getNumsInSubTree(int max, int root, int nextRoot) {
		//       1               2
		// 10 11 12 .. 19       20 ...

		// use long since endNumofRow and startNumOfRow might overflow int
		int startNumOfRow = root;  // 1 in first round, 10 in second round
		int endNumOfRow = nextRoot; // 2 in first round, 20 in second round
		int count = 0;

		// while the new level in this subtree still has number in range (i.e. <= max)
		// need to count them
		while (startNumOfRow <= max) {
			// endNumOfRow - 1 is the largest number possible in this level
			// if this max number is out of range, could only add (max - startOfRow + 1) amount of nums
			if (endNumOfRow - 1 > max) {
				count += max - startNumOfRow + 1;
			} else {
				// if this max number is in range, add the number in the whole levels into count
				count += endNumOfRow - startNumOfRow;
			}
			startNumOfRow *= 10;
			endNumOfRow *= 10;
		}
		return count;
	}

	public static void main(String[] args) {
		Test440_KthSmallestinLexicographicalOrder t = new Test440_KthSmallestinLexicographicalOrder();
		System.out.println(t.findKthNumber(100, 90));
	}
}
