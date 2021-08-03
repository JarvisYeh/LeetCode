package Algorithm.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test49_GroupAnagrams {
	// len(strs) = n
	// len(s in strs) = m
	// TC: O(n*(m + mlogm)) = O(nmlogm)
	// SC: O(m*n + 2m) = O(m*n)
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> res = new ArrayList<>();
		HashMap<String, List<String>> dict = new HashMap<>();    // SC: at most O(n*m), every string differnet pattern
		// O(n)
		for (String s : strs) {
			// O(m)
			char[] arr = s.toCharArray();      // SC: O(m)
			// O(m log m)
			Arrays.sort(arr);
			// O(m)
			String tmp = String.valueOf(arr);  // SC: O(m)
			// O(1)
			if (dict.containsKey(tmp)) {
				dict.get(tmp).add(s);
			} else {
				List<String> stringList = new ArrayList<>();
				stringList.add(s);
				res.add(stringList);
				dict.put(tmp, stringList);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Test49_GroupAnagrams t = new Test49_GroupAnagrams();
		t.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
	}
}
