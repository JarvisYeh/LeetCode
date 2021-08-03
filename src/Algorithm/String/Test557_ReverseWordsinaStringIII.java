package Algorithm.String;

public class Test557_ReverseWordsinaStringIII {
	public String reverseWords(String s) {
		char[] arr = s.toCharArray();
		// i points to the start of word
		// j points to the ' '
		int i = 0, j;
		while (i < arr.length) {
			// move j to the ' '
			j = i;
			while (j < arr.length && arr[j] != ' ') {
				j++;
			}

			// check if it's the last word
			reverse(arr, i, j - 1);
			i = j + 1;
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

	public static void main(String[] args) {
		Test557_ReverseWordsinaStringIII t = new Test557_ReverseWordsinaStringIII();
		String input = "Let's take LeetCode contest";
		System.out.println(input);
		System.out.println(t.reverseWords(input));
	}
}
