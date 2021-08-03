package Algorithm.BitManipulation;

public class Test371_SumofTwoIntegers {
	// get sum without using + or -
	public int getSum(int a, int b) {
		int carry = 0, res = 0;
		for (int i =0; i < 32; i++) {
			int intA = (a >>> i) & 1, intB = (b >>> i) & 1;
			int sumOut = intA ^ intB ^ carry;
			res |= (sumOut << i);
			// calculate carry for next bit
			carry = (intA & intB) | ((intA ^ intB) & carry);
		}
		// carry of 31th bit doesn't matter, just let it overflow
		return res;
	}
}
