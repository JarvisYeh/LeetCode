package Algorithm.BitManipulation;

public class Test338_CountingBits {
	// DP solution
	public int[] countBits(int n) {
		int[] count = new int[n + 1];
		// 0 has no 1s
		count[0] = 0;
		for (int i = 1; i <= n; i++) {
			// 10011 => 1001, 1
			// count[10011] = count[1001] + LSB
			count[i] = count[i >> 1] + (i&1);
		}
		return count;
	}

	public static void main(String[] args) {
		Test338_CountingBits t = new Test338_CountingBits();
		t.countBits(5);
	}
}
