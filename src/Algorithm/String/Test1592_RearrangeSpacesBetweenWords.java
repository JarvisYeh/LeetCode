package Algorithm.String;

public class Test1592_RearrangeSpacesBetweenWords {
	public String reorderSpaces(String text) {
		String[] words = text.trim().split(" +");
		int count = 0;
		for (char c : text.toCharArray()) {
			if (c == ' ') {
				count++;
			}
		}

		int wordSpaceCount = 0;
		int tailSpaceCount = count;

		if (words.length != 1) {
			wordSpaceCount = count / (words.length - 1);
			tailSpaceCount = count % (words.length - 1);
		}

		StringBuilder deli = new StringBuilder();
		StringBuilder tail = new StringBuilder();
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < wordSpaceCount; i++) {
			deli.append(' ');
		}
		for (int i = 0; i < tailSpaceCount; i++) {
			tail.append(' ');
		}

		for (int i = 0; i < words.length - 1; i++) {
			res.append(words[i]).append(deli);
		}
		return res.append(words[words.length - 1]).append(tail).toString();
	}

	public static void main(String[] args) {
		Test1592_RearrangeSpacesBetweenWords t = new Test1592_RearrangeSpacesBetweenWords();
		System.out.println(t.reorderSpaces("asd  das  asd   asd"));
	}
}
