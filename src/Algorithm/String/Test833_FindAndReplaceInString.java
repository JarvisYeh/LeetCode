package Algorithm.String;

import java.util.HashMap;

public class Test833_FindAndReplaceInString {
	public String findReplaceString(String s, int[] indexes, String[] sources, String[] targets) {
		// map stores (key = index in string, value = index in mapping array)
		HashMap<Integer, Integer> indexMap = new HashMap<>();
		int newLength = checkValidRep(s, indexes, sources, targets, indexMap);
		char[] sNew = new char[newLength];
		int i = sNew.length - 1, j = s.length() - 1;
		while (j >= 0) {
			int k = indexMap.getOrDefault(j, -1);
			if (k == -1) {
				// no need to replace, preserve s[j]
				sNew[i--] = s.charAt(j--);
			} else {
				// replace with the target string
				String target = targets[k];
				for (int t = target.length() - 1; t >= 0; t--) {
					sNew[i--] = target.charAt(t);
				}
				j -= sources[k].length();
			}
		}
		return new String(sNew);
	}

	private int checkValidRep(String s, int[] indexes, String[] sources, String[] targets, HashMap<Integer, Integer> indexMap) {
		int size = s.length();
		// map stores (key = index in string, value = index in mapping array)
		for (int i = 0; i < indexes.length; i++) {
			indexMap.put(indexes[i], i);
		}

		// check each index in string, if it's not the same as the source string
		// remove it from the map, e.g. not valid replacement
		// else
		// update map (since in main method we start with the end index of each source string)
		// and update new size
		for (int key : indexMap.keySet().toArray(new Integer[0])) {
			int val = indexMap.get(key);
			indexMap.remove(key);
			if (checkEquals(s, key, sources[val])) {
				// update start index point to end of the string
				indexMap.put(key + sources[val].length() - 1, val);
				size = size - sources[val].length() + targets[val].length();
			}
		}
		return size;
	}

	// check if the substring of string s, start with index
	// is equal to string source
	private boolean checkEquals(String s, int index, String source) {
		for (int i = 0; i < source.length(); i++) {
			if (index + i > s.length() || s.charAt(index + i) != source.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Test833_FindAndReplaceInString t = new Test833_FindAndReplaceInString();
		System.out.println(
				t.findReplaceString("abcd",
						new int[]{0, 2},
						new String[]{"a", "cd"},
						new String[]{"eee", "ffff"})
		);
	}
}
