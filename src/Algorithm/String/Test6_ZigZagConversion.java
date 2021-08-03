package Algorithm.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Test6_ZigZagConversion {
	public String convert(String s, int numRows) {
		// sbs[i] represents row i after conversion
		StringBuilder[] sbs = new StringBuilder[numRows];
		for (int i = 0; i < numRows; i++) {
			sbs[i] = new StringBuilder();
		}

		int j = 0, incre = 1;
		for (int i = 0; i < s.length(); i++) {
			if (j == 0) { // going down for next number
				incre = 1;
			} else if (j == numRows - 1) { // going up for next number
				incre = -1;
			}
			sbs[j].append(s.charAt(i));
			// if going down or going up for next level is not out of boundary
			// change to next level
			// this is actually set for the only corner case where numRows = 1
			if (j + incre >= 0 && j + incre < numRows) j += incre;
		}
		for (int i = 1; i < numRows; i++) {
			sbs[0].append(sbs[i]);
		}
		return new String(sbs[0]);
	}
}
