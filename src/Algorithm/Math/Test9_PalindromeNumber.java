package Algorithm.Math;

public class Test9_PalindromeNumber {
	public boolean isPalindrome(int x) {
		if (x < 0) return false;
		int tmp = x, count = 0;
		while (tmp != 0) {
			tmp/=10;
			count++;
		}
		// count is how many digits x has
		for (int i = 0; i < count/2; i++) {
			int left = (int)(x/Math.pow(10, count - 1 - i)) % 10;
			int right = (int)(x/Math.pow(10, i)) % 10;
			if (left != right) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Test9_PalindromeNumber t = new Test9_PalindromeNumber();
		t.isPalindrome(121);
	}
}
