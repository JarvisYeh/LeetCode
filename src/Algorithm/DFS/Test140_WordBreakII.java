package Algorithm.DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Input
// 	s = "catsanddog"
// 	wordDict = ["cat","cats","and","sand","dog"]
// Output
// 	["cats and dog","cat sand dog"]
// TC: O(2^n * n)
// 	recursion tree O(n) level, idx always + 1 in each level
// 	each node has at most 2 children
// 	last level copy string
// SC: O(n) for stack and StringBuilder
public class Test140_WordBreakII {
	public List<String> wordBreakI(String s, List<String> wordDict) {
		HashSet<String> dict = new HashSet<>(wordDict);
		List<String> res = new ArrayList<>();
		dfs(0, 0, s, dict, new StringBuilder(), res);
		return res;
	}

	private void dfs(int startIdx, int endIdx, String s,
					 HashSet<String> dict, StringBuilder sb, List<String> res) {
		// base case 1: all characters used up and all formed into dictionary word
		if (startIdx == s.length()) {
			sb.deleteCharAt(sb.length() - 1);
			res.add(new String(sb));
			sb.append(' ');
			return;
		}
		// base case 2: last words right side out of boundary
		if (endIdx > s.length()) return;

		// check if current [s, e) is in dictionary word
		String word = s.substring(startIdx, endIdx);
		if (dict.contains(word)) {
			int size = sb.length();
			sb.append(word + " ");
			dfs(endIdx, endIdx + 1, s, dict, sb, res);
			sb.delete(size, sb.length());
		}
		// not adding word [s, e) but increase right side e by 1
		dfs(startIdx, endIdx + 1, s, dict, sb, res);
	}

	// Method 2: DP
	// TC: O(len(dict)) + O(n*(n + n*n))
	public List<String> wordBreakII(String s, List<String> wordDict) {
		HashSet<String> dict = new HashSet<>(wordDict);			// O(len(dict))
		// DP[i] is the list of ways how s[0, i] can be break
		List<List<String>> DP = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {					// O(n) loop
			DP.add(new ArrayList<>());
			if (dict.contains(s.substring(0, i + 1))) {			// substring O(n)
				DP.get(i).add(s.substring(0, i + 1));
			}
			// break s[0, i] into [0, j] (j, i]
			for (int j = 0; j < i; j++) {						// O(n) loop
				// check DP[j] for s[0, j]'s possible break
				String rightStr = s.substring(j + 1, i + 1);		// substring O(n)
				// for all possible break
				// if leftStr is in dict, merge leftStr with all possible one by one
				// add them into DP[i]
				if (dict.contains(rightStr)) {
					for (String leftStr : DP.get(j)) {
						DP.get(i).add(leftStr + " " + rightStr);	// string concate O(n)
					}
				}
			}
		}
		return DP.get(s.length() - 1);
	}
}
