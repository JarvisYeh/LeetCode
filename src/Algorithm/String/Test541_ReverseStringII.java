package Algorithm.String;

public class Test541_ReverseStringII {
	public String reverseStr(String s, int k) {
		char[] arr = s.toCharArray();
		int i = 0; // i alwasy points to the start of the 2k period
		while (i < s.length()) {
			if (i + k > arr.length) {
				reverse(arr, i, arr.length - 1);
			} else {
				reverse(arr, i, i + k - 1);
			}
			i += 2*k;
		}
		return new String(arr);
	}

	private void reverse(char[] arr, int i, int j) {
		while (i < j) {
			char tmp = arr[i];
			arr[i++] = arr[j];
			arr[j--] = tmp;
		}
	}
}
