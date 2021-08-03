package Algorithm.String;

public class Test443_StringCompression {
	// compress aaabccc to a3bc3
	// length always decrease, since 1 character remains the same
	public int compress(char[] chars) {
		int i = 0, k = 0;
		while (i < chars.length) {
			int j = i;
			while (j < chars.length && chars[j] == chars[i]) j++;
			// for single character
			if (j - i == 1) {
				chars[k++] = chars[i];
			}
			// for continuous characters
			else {
				chars[k++] = chars[i];
				int count = j - i;
				int start = k;
				while (count != 0) {
					chars[k++] = Character.forDigit(count % 10, 10);
					count /= 10;
				}
				reverse(chars, start, k - 1);
			}
			i = j;
		}
		return k;
	}

	private void reverse(char[] arr, int i, int j) {
		while (i < j) {
			char tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
			i++;
			j--;
		}
	}

	public static void main(String[] args) {
		Test443_StringCompression t = new Test443_StringCompression();
		t.compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'});
	}
}
