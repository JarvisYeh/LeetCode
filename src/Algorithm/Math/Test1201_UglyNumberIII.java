package Algorithm.Math;

import java.util.PriorityQueue;
import java.util.Queue;

public class Test1201_UglyNumberIII {
	// 这里丑数定义为可以被a或b或c整除
	// 对于6来说，<= 6且可以被2整除的数有
	// 1, 2, 3, 4, 5, 6中的2， 4， 6
	// 一共有6/2个
	// 即对于num来说，<= num且能被d整除的数一共有num/d个
	// 1. <= num, can be divided by a, countA= num/a
	// 2. <= num, can be divided by b, countB = num/b
	// 3. <= num, can be divided by c, countC = num/c
	// 1.2. <= num, can be divided by a and b, countAB = num/lcm(a, b)
	// 1.3. <= num, can be divided by a and c, countAC = num/lcm(a, c)
	// 2.3. <= num, can be divided by b and c, countBC = num/lcm(b, c)
	// 1.2.3. <= num, can be divided by a, b, c, countABC = nums/lcm(a, b, c)
	// lcm: lowest common multiplier
	public int nthUglyNumber(int n, int a, int b, int c) {
		// the target is the num
		// 1. count(num) == n
		// 2. smallest among all count(num) == n
		int max = Integer.MAX_VALUE;
		int l = 0, r = max;
		Queue<Integer> q = new PriorityQueue<>();
		while (l <= r - 1) {
			int mid = l + (r - l)/2;
			int count = count(mid, a, b, c);
			if (count < n) {
				l = mid + 1;
			} else if (count > n) {
				r = mid - 1;
			} else {
				r = mid;
			}
		}
		return count(l, a, b, c) == n ? l : r;
	}

	private int count(int n, int a, int b, int c) {
		return (int)(n/a + n/b + n/c
				- n/lcm(a, b) - n/lcm(a, c) - n/lcm(b, c)
				+ n/lcm(c, lcm(a, b)));
	}

	private long lcm(long a, long b) {
		return a*b/gcd(a, b);
	}

	private long gcd(long a, long b) {
		// gcd(a, b) = gcd(b, a%b)
		while (b != 0) {
			long r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	private long gcd2(long a, long b) {
		if (b == 0) return b;
		return gcd2(b, a % b);
	}

	public static void main(String[] args) {
		Test1201_UglyNumberIII t = new Test1201_UglyNumberIII();
		System.out.println(t.nthUglyNumber(3, 2, 3, 5));
	}
}
