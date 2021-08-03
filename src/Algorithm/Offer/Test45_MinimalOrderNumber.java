package Algorithm.Offer;

import java.util.ArrayList;
import java.util.List;

public class Test45_MinimalOrderNumber {
	public String minNumber(int[] nums) {
		List<String> strList = new ArrayList<>();
		for (int i : nums) {
			strList.add(String.valueOf(i));
		}

		strList.sort((s1, s2) -> (s1 + s2).compareTo(s2 + s1));
		return String.join("", strList);
	}
}
