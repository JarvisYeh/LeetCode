package Algorithm.String;

public class Test345_ReverseVowelsofaString {
	public String reverseVowels(String s) {
		int i = 0, j = s.length() - 1;
		char[] arr = s.toCharArray();
		while (i < j) {
			while (i < j && !isVowels(arr[i])) i++;
			while (i < j && !isVowels(arr[j])) j--;
			char tmp = arr[i];
			arr[i++] = arr[j];
			arr[j--] = tmp;
		}
		return new String(arr);
	}

	private boolean isVowels(char c) {
		c = Character.toLowerCase(c);
		return c == 'a' || c =='e' || c == 'i' || c == 'o' || c == 'u';
	}
}
