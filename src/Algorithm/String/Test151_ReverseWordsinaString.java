package Algorithm.String;

// reverse words in a string
// and trim leading and ending spaces
// remove extra spaces
public class Test151_ReverseWordsinaString {
	public String reverseWords(String s) {
		char[] arr = s.toCharArray();
		// step 1: reverse the whole sentence
		reverse(arr, 0, arr.length - 1);
		// step 2: reverse each words
		int i = 0;
		while (i < arr.length) {
			// make i the first of the word
			while (i < arr.length && arr[i] == ' ') i++;
			int j = i;
			// make j the first space after the word
			while (j < arr.length && arr[j] != ' ') j++;

			// reverse each word
			if (i < arr.length) {
				reverse(arr, i, j - 1);
			}
			i = j;
		}

		// trim and remove spaces
		int end = cleanSpaces(arr);
		return new String(arr, 0, end);
	}

	private void reverse(char[] arr, int i, int j) {
		while (i < j) {
			char tmp = arr[i];
			arr[i++] = arr[j];
			arr[j--] = tmp;
		}
	}

	private int cleanSpaces(char[] arr) {
		int i = 0, j = 0;
		while (j < arr.length) {
			if (arr[j] != ' ' || i != 0 && arr[i - 1] != ' ') {
				arr[i++] = arr[j++];
			} else if (i != 0 && arr[i - 1] != ' ') {
				arr[i++] = arr[j++];
			} else {
				j++;
			}
		}
		return arr[i - 1] == ' ' ? i - 1: i;
	}

	public static void main(String[] args) {
		Test151_ReverseWordsinaString t = new Test151_ReverseWordsinaString();
		String input = "the sky is blue";
		System.out.println(t.reverseWords(input));
	}
}
