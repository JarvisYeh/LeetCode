package Algorithm.BinarySearch;

public class Test69_SqrtX {

	public static void main(String[] args) {
		System.out.println(mySqrt(2147395599));
		System.out.println(Math.sqrt(2147395599));
	}

	public static int mySqrt(int x) {
		long l = 0, r = x;
		while (l < r - 1) {
			long mid = l + (r - l) / 2;
			if (mid * mid > x) {
				r = mid;
			} else if (mid * mid < x) {
				l = mid;
			} else {
				return (int)mid;
			}
		}
		return Math.abs(l*l - x) - Math.abs(r*r - x) < 0 ? (int)l : (int)r;
	}

}
