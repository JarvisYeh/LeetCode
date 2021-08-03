package Algorithm.Math;

public class Test7_ReverseInteger {
	public int reverse(int x) {
		int res = 0;
		while (x != 0) {
			int curr = res;
			res = res * 10 + x % 10;
			// if res/10 != previous result, overflow happens
			if (res / 10 != curr) {
				return 0;
			}
			x /= 10;
		}
		return res;
	}
}
