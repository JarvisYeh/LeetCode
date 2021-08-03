package Algorithm.BitManipulation;

public class Test191_NumberOf1Bits {
	// you need to treat n as an unsigned value
	public int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			count += n & 1;
			n >>>= 1;
		}
		return count;
	}
}
